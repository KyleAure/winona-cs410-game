package edu.winona.cs.app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * TODO finish creating this menu
 * 
 * Game Settings Menu - Had to update name due to conflict with GameSettings(Component)
 * Let's user set App-Setting: background color
 * Let's user set App-Setting: high score tracking
 * Let's user set User-Setting: preferred difficulty level
 * 
 * @author Kyle Aure
 * @version 0.0
 */
public class GameSettingsMenu extends JFrame {
	private static final long serialVersionUID = -7709639604667440085L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameSettingsMenu frame = new GameSettingsMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GameSettingsMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
