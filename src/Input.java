/*
 * 	by Alberic A. Davila Cadilla
 * 	Input.java
 * 
 * Set the events that occur upon pressing a key such as 
 * the player's movement and existing the game.
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

 
public class Input implements KeyListener {
	
	public Input (GameMaster game) {
		game.addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		// Controls for player 1
		if(keyCode == KeyEvent.VK_W) {
			GameMaster.player.moveUp = true;
 		}
		
		if(keyCode == KeyEvent.VK_S) {
			GameMaster.player.moveDown = true;
		}

		// Controls for player 2
		if(keyCode == KeyEvent.VK_UP) {
			GameMaster.ai.moveUp = true;
		}

		if(keyCode == KeyEvent.VK_DOWN) {
			GameMaster.ai.moveDown = true;
		}
		
		// Close the game
		if(keyCode == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	int keyCode = e.getKeyCode();
		
		// Controls for player 1
		if(keyCode == KeyEvent.VK_W) {
			GameMaster.player.moveUp = false;
		}
		
		if(keyCode == KeyEvent.VK_S) {
			GameMaster.player.moveDown = false;
		}
		
		// Controls for player 2
		if(keyCode == KeyEvent.VK_UP) {
			GameMaster.ai.moveUp = false;
		}
		
		if(keyCode == KeyEvent.VK_DOWN) {
			GameMaster.ai.moveDown = false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
