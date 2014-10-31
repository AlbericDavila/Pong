/*
 * 	by Alberic A. Davila Cadilla
 * 	Player01.java
 * 
 * Creates the player, sets it's collision box and speed. 
 */

import java.awt.*;

public class Player01 {
	
	int x, y;
	int width = 15;
	int height = 40;
	int speed = 1;
	boolean moveUp = false;
	boolean moveDown = false;
	
	Rectangle collisionBox;
	
	// Class constructor
	public Player01(int x, int y) {
		this.x = x;
		this.y = y;
		
		collisionBox = new Rectangle(x, y, width, height);
		collisionBox.setBounds(x, y, width, height);
	}
	
	public void tick(GameMaster game) {
		// update the collision box to move with the player
		collisionBox.setBounds(x, y, width, height);

		// Moving the player up
		if(moveUp == true && y > 0) {
			y -= speed;
		}
		
		// Moving the player down
		if(moveDown == true && y + height <= game.getHeight()) {
			y += speed;
		}
		
	}
	
	// Choose color for player and set it
	public void render(Graphics grph) {
		grph.setColor(Color.WHITE);
		grph.fillRect(x, y, width, height);
	}
}
