package edu.winona.cs.component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import edu.winona.cs.gamelogic.DifficultyLevel;
import edu.winona.cs.log.Log;
import edu.winona.cs.log.Log.LogLevel;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Variables here match those stored in the SaveStateTable. fileURL (String) -
 * Location of serialized file
 *
 * Within serialized file difficulty (DifficultyLevel) clickCount (number of
 * clicks)
 *
 * @author Kyle Aure
 * @version 1.0
 *
 */
public class GameSession implements Serializable {
    //Used to serialize the GameSession to be loaded again later

    private static final long serialVersionUID = -5135624122335249008L;

    //Logger
    private static final Log LOG = new Log(GameSession.class.getName());

    //Variables 
    private String fileURL;
    private DifficultyLevel difficulty;
    private int clickCount;
    public List<BufferedImage> imageList = new ArrayList<>();
    public List<BufferedImage> keyList = imageList;

    public GameSession(String username, DifficultyLevel level, int clickCount, List<BufferedImage> imageList, List<BufferedImage> keyList) {
        this.fileURL = "./saveState/" + username + ".ser";
        this.difficulty = level;
        this.clickCount = 0;
        this.imageList = imageList;
        this.keyList = keyList;
    }

    public GameSession(DifficultyLevel difficulty) {
        this.difficulty = difficulty;
    }

    public DifficultyLevel getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DifficultyLevel difficulty) {
        this.difficulty = difficulty;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }
    
    public void incrementClick() {
    	clickCount++;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }

    public void setImageList(List<BufferedImage> imageList) {
        this.imageList = imageList;
    }
    
    public List<BufferedImage> getImageList() {
    	return imageList;
    }

    public void setKeyList(List<BufferedImage> keyList) {
        this.keyList = keyList;
    }
    
    public List<BufferedImage> getKeyList() {
    	return keyList;
    }

    /**
     * Save game session
     */
    public void saveHighScores() {
        try {
            FileOutputStream fos = new FileOutputStream(fileURL);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            LOG.log(e, LogLevel.SEVERE, "File Not Found Exception. Unable to save high scores.");
        } catch (IOException e) {
            LOG.log(e, LogLevel.SEVERE, "Exception while saving high scores.");
        }
    }

    /**
     * Load high scores list into memory. This will overwrite any existing game
     * session data.
     */
    public void loadHighScores() {
        GameSession temp = null;
        try {
            FileInputStream fis = new FileInputStream(fileURL);
            ObjectInputStream ois = new ObjectInputStream(fis);
            temp = (GameSession) ois.readObject();
            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            LOG.log(e, LogLevel.SEVERE, "File Not Found Exception. Unable to load high scores.");
        } catch (IOException e) {
            LOG.log(e, LogLevel.SEVERE, "File was found, but was unable to read data.");
        } catch (ClassNotFoundException e) {
            LOG.log(e, LogLevel.SEVERE, "File was found and data was read, but was serialized as the incorrect class.");
        }

        if (temp != null) {
            //TODO add any other variables that we create later here!
            this.difficulty = temp.difficulty;
            this.clickCount = temp.clickCount;
        }
    }

    @Override
    public String toString() {
        return "Difficulty: " + difficulty;
    }

}
