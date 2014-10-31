/*
 * 	by Alberic A. Davila Cadilla
 * 	Menu.java
 * 
 * Creates the GUI menu. Displays "Play", "Quit" and 
 * the option to play with two players.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Menu extends JFrame {
	private static final long serialVersionUID = 1L;

	int screenWidth = 250;
	int screenHeight = 150;

	int buttonWidth = 100;
	int buttonHeight = 40;

	JButton Play, Quit;
	JCheckBox twoPlayer;

	public Menu() {

		// Call methods for adding
		addButtons();
		addActions();

		// Center the window
		getContentPane().setLayout(null);

		// Positioning the buttons
		Play.setBounds((screenWidth - buttonWidth) / 2, 5, buttonWidth, buttonHeight);	
		Quit.setBounds((screenWidth - buttonWidth) / 2, 50, buttonWidth, buttonHeight);
		twoPlayer.setBounds((screenWidth - buttonWidth) / 2, 100, buttonWidth * 2, buttonHeight);

		
		// Add the buttons to the JFrame
		getContentPane().add(Play);
		getContentPane().add(Quit);
		getContentPane().add(twoPlayer);

		// JFrame details
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setSize(screenWidth, screenHeight);
		setTitle("Pong");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);	
	}

	// Add the buttons
	public void addButtons() {
		Play = new JButton("Play");
		Quit = new JButton("Quit");
		twoPlayer = new JCheckBox("Two Players?");
	}

	// Handles the actions of the buttons
	public void addActions() {

		Play.addActionListener(new ActionListener() {		// Add an actionListener to the Play button
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {	// Turn the action into a variable to be used
				
				// dispose of the Menu
				dispose();
				
				// Create a new game
				GameMaster game = new GameMaster();
				
				// If there are two players
				if(twoPlayer.isSelected()) {
					game.ai.twoPlayer = true;
				}	
				else {
					game.ai.twoPlayer = false;
				}
				
				// Start the game
				game.start();
			}		
		}); // Play button
		
		Quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}		
		}); // Quit button

	}

}


