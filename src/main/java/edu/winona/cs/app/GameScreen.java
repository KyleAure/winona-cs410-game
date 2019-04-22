package edu.winona.cs.app;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

import edu.winona.cs.component.GameSession;
import edu.winona.cs.db.DatabaseManager;
import edu.winona.cs.gamelogic.Cell;
import edu.winona.cs.gamelogic.DifficultyLevel;
import edu.winona.cs.gamelogic.Randomize;
import edu.winona.cs.log.Log;
import edu.winona.cs.log.Log.LogLevel;

public class GameScreen extends JFrame {
	//##########################
	//VARIABLES
	//##########################
	
    //Serialized variable
    private static final long serialVersionUID = -2097350155286375640L;

    //Logger
    private static final Log LOG = new Log(GameScreen.class.getName());

    //Buttons and Cells
    private static final List<Cell> CELLS = new ArrayList<>();
    private Cell emptyCell;
    private List<BufferedImage> iconList;

    //image/game processing
    private Randomize randomize = new Randomize();
    private GameSession session;
    private Boolean playing = false;

    //Database
    private DatabaseManager dbm = DatabaseManager.getDatabaseManager();

    //UI variables
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem mainMenu;
    private javax.swing.JMenu optionsMenu;
    private javax.swing.JMenuItem play;
    private javax.swing.JMenuItem restart;
    private javax.swing.JMenuItem save;
    private javax.swing.JMenuItem settings;

	//##########################
	//CONSTRUCTOR
	//##########################
    public GameScreen() throws IOException {
    	LOG.log(LogLevel.INFO, "Initialzing GUI Components...");
        initComponents();
        LOG.log(LogLevel.INFO, "Done Initializing GUI.\n");
        
        LOG.log(LogLevel.INFO, "Game Startup...");
        LOG.log(LogLevel.INFO, "Settings: " + App.getSettings());
        LOG.log(LogLevel.INFO, "Session: " + App.getSession() + "\n");
        
        //Step 0: set session
        this.session = App.getSession();

        //Step 1: Set window color to users preference
        Container a = GameScreen.this.getContentPane();
        if (App.isSettingsSet()) {
        	a.setBackground(App.getSettings().getBackgroundColor());
        } else {
            LOG.log(LogLevel.WARNING, "App settings not set when entering GameScreen.");
            a.setBackground(App.DEFAULT_SETTINGS.getBackgroundColor());
        }

        //Step 2: set rows and columns
        DifficultyLevel level = App.getSession().getDifficulty();

        //STEP 3: Set up buttonPanel
        LOG.log(LogLevel.INFO, "level.getInt() = " + level.getInt());
        this.setLayout(new GridLayout(0, level.getInt()));
        
        //STEP 4: Get icons
        iconList = session.loadIcons();

        //STEP 5: Create cell list
        List<Integer> stateList = session.getState();
        LOG.log(LogLevel.INFO, "StateList.size() = " + stateList.size() + "\nDifficulty.getSquare() = " + session.getDifficulty().getSqure());
        for (int i = 0; i < stateList.size(); i++) {
        	//Create new Cell with value = value saved in state list at index i
        	
            //Location 
            int row = level.getSqure() / level.getInt()-1 - ( i / level.getInt());
            int col = level.getSqure()-1 - (row * level.getInt() + i);
        	
        	Cell c = new Cell(stateList.get(i), session.getDifficulty(), row, col);
            c.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) { 
            		  buttonPressed(e);
            	} 
            });
            CELLS.add(c);
            this.add(c);
            
            if(stateList.get(i) == level.getSqure() - 1) {
            	emptyCell = c;
            }
        }
        LOG.log(LogLevel.INFO, "CELLS: " + CELLS);
        LOG.log(LogLevel.INFO, "EMPTY CELL: " + emptyCell);
        
        //STEP 6: Assign icons
        assignIcons();

        //STEP 7: set this screen visible
        this.pack();
        this.setVisible(true);
        
        //Step 8: set close operation
        addWindowListener(new WindowAdapter() {
            @Override
			public void windowClosing(WindowEvent e) {
            	super.windowClosing(e);
                int result = JOptionPane.showConfirmDialog(null, "Save before exiting?",
                        "alert", JOptionPane.YES_NO_CANCEL_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    saveGame();
                } else if (result == JOptionPane.CANCEL_OPTION) {
                	return;
                }
                System.exit(0);
            }
        });
    }
    
	//##########################
	//ACTION HANDLERS
	//##########################
    
    //BUTTON PRESSED
    private void buttonPressed(ActionEvent e) {
        if (checkNeighbor((Cell) e.getSource())) {
            swap((Cell) e.getSource());
            session.incrementClick();
            checkVictory();
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }
    
    //PLAY
    private void playActionPerformed(ActionEvent evt) {//GEN-FIRST:event_playActionPerformed
        if(App.isNewGame()) {
        	LOG.log(LogLevel.INFO, "Play button pressed. Starting to randomize...");
        	randomize.randomize(session.getState());
        }
        
        for(int i = 0; i < CELLS.size(); i++) {
        	int val = session.getState().get(i);
        	Cell temp = CELLS.get(i);
        	temp.setValue(val);
        	CELLS.set(i, temp);
        }
        
        LOG.log(LogLevel.INFO, "Finished randomizing. Result: \n" + CELLS);
        
        playing = true;
        assignIcons();
    	
    }//GEN-LAST:event_playActionPerformed

    //SAVE
    private void SaveActionPerformed(ActionEvent evt) {
    	App.setGameSession(session);
        saveGame();
    }
    
    //SETTINGS
    private void SettingsActionPerformed(ActionEvent evt) {
        if (playing) {//game started
            JOptionPane.showMessageDialog(null, "Unable to change setting midway through a game...",
                    "Game Settings", 1);
        } else {//game not started
            Settings settings = new Settings();
            settings.setVisible(true);
            //User will need to reload game screen
            this.dispose();
        }
    }

    //MAIN MENU
    private void mainMenuActionPerformed(ActionEvent evt) {
        int result = JOptionPane.showConfirmDialog((Component) null, "Save before returning to main menu?",
                "alert", JOptionPane.YES_NO_CANCEL_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            saveGame();

            //close game screen and open menu
            this.dispose();

            MainMenuScreen menu = new MainMenuScreen();
            menu.setVisible(true);
        } else if (result == JOptionPane.NO_OPTION) {
            //don't save
            //close game screen and open menu
            this.dispose();

            MainMenuScreen menu = new MainMenuScreen();
            menu.setVisible(true);
        }
    }

    //RESTART
    private void restartActionPerformed(ActionEvent evt) {

        int result = JOptionPane.showConfirmDialog((Component) null, "Are you sure you'd like to restart the current game?",
                "alert", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            session.setState(session.getKey());
            assignIcons();
            session.setClickCount(0);
            App.setGameSession(session);
        }
    }

	//##########################
	//SUBROUTINES
	//##########################
    
    //ASSIGN ICONS
    private void assignIcons() {
        //set icons for each button
        for (int i = 0; i < CELLS.size(); i++) {
        	//If cell is marked not empty
        	if(!CELLS.get(i).isEmpty()) {
        		//Get icon for cells value
        		BufferedImage img = iconList.get(CELLS.get(i).getValue());
        		//Set icon 
        		CELLS.get(i).setIcon(new ImageIcon(img));
        	} 
        	CELLS.get(i).setEnabled(playing);
        }
    }
    
    //Check neighbor
    private boolean checkNeighbor(Cell c) {    	
    	//STEP 2: get distance in x and y directions
        int x = Math.abs(c.getCol() - emptyCell.getCol());
        int y = Math.abs(c.getRow() - emptyCell.getRow());

        //STEP 3: return if neighbor is empty cell
        return (x == 0 && y == 1) || (y == 0 && x == 1);
    }

    //SWAP
    private void swap(Cell c) {
    	LOG.log(LogLevel.INFO, "State before: " + session.getState());
    	//STEP 2: Swap
        Icon tmpIcon = emptyCell.getIcon();
        int tempVal = emptyCell.getValue();
        emptyCell.setIcon(c.getIcon());
        emptyCell.setValue(c.getValue());
        c.setIcon(tmpIcon);
        c.setValue(tempVal);
        // update reference to empty button
        emptyCell = c;
        
        List<Integer> temp = new ArrayList<Integer>();
        for(Cell cell : CELLS) {
        	temp.add(cell.getValue());
        }
        
        session.setState(temp);
        
        LOG.log(LogLevel.INFO, "State after: " + session.getState());
        
        checkVictory();

    }


    //VICTORY
    private void checkVictory() {
    	List<Integer> state = session.getState();
        for (int i = 0; i < state.size(); i++) {
        	if(state.get(i) != session.getKey().get(i)) {
        		return;
        	}
        }

        //Handle end game
        String congratsMessage = "You achieved victory in " + session.getClickCount() + " moves.";
        
        if(session.getClickCount() < dbm.getScoreTable().getHighScore(App.getUsername())) {
        	congratsMessage += "\nThis is a new high score!";
        	JOptionPane.showMessageDialog(null, 
        			congratsMessage,
                    "Congratulations!", 
                    JOptionPane.INFORMATION_MESSAGE);
        	if(App.getSettings().isHighScoreTracking()) {
        		dbm.getScoreTable().recordHighScore(App.getUsername(), session.getClickCount());
        	}
        } else {
        	JOptionPane.showMessageDialog(null, 
        			congratsMessage,
                    "Congratulations!", 
                    JOptionPane.INFORMATION_MESSAGE);
        }
        
        //close game screen and open menu
        this.dispose();

        MainMenuScreen menu = new MainMenuScreen();
        menu.setVisible(true);
    }
    
    //SAVE GAME
    private void saveGame() {
        //create a serialized file
        try {
        	//Create file
        	File file = new File("." + App.getUsername() + ".json");
        	
        	// Create Jsonb and serialize
        	Jsonb jsonb = JsonbBuilder.create();
        	String result = jsonb.toJson(session);
        	FileUtils.writeStringToFile(file, result, "UTF-8");

            //add the serialized file to the database
            dbm.getSaveStateTable().createSaveState(App.getUsername(), file);

            JOptionPane.showMessageDialog(null, "Game saved successfully",
                    "Saved Game Confirmation", 1);

        } catch (HeadlessException | IOException ex) {
        	LOG.log(ex, LogLevel.WARNING, "Saving game error encountered.  Session not saved.");
        }
    }

	//##########################
	//INITIALIZER FOR GUI
	//##########################
    private void initComponents() {
        menuBar = new javax.swing.JMenuBar();
        optionsMenu = new javax.swing.JMenu();
        play = new javax.swing.JMenuItem();
        save = new javax.swing.JMenuItem();
        settings = new javax.swing.JMenuItem();
        mainMenu = new javax.swing.JMenuItem();
        restart = new javax.swing.JMenuItem();
        
        optionsMenu.setText("File");

        play.setText("Play");
        play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playActionPerformed(evt);
            }
        });
        optionsMenu.add(play);

        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });
        optionsMenu.add(save);

        settings.setText("Settings");
        settings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsActionPerformed(evt);
            }
        });
        optionsMenu.add(settings);

        mainMenu.setText("Main Menu");
        mainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainMenuActionPerformed(evt);
            }
        });
        optionsMenu.add(mainMenu);

        restart.setText("Restart");
        restart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restartActionPerformed(evt);
            }
        });
        optionsMenu.add(restart);

        menuBar.add(optionsMenu);

        setJMenuBar(menuBar);

        pack();
    }
}
