/*
 * 	by Alberic A. Davila Cadilla
 * 	AI.java
 * 
 *  Creates the ball and it's collision box.
 *  Determines it's behavior.
 */

import java.awt.*;

public class AI {

	int x, y;
	int width = 15;
	int height = 40;
	int speed = 1;
	boolean moveUp = false;
	boolean moveDown = false;
	boolean twoPlayer = false;
	
	Rectangle collisionBox;
	
	// Class constructor
	public AI(int x, int y) {
		this.x = x;
		this.y = y;
		
		collisionBox = new Rectangle(x, y, width, height);
		collisionBox.setBounds(x, y, width, height);
	}
	
	public void tick(GameMaster game) {
		// update the collision box to move wit the AI
		collisionBox.setBounds(x, y, width, height);
		
		// if the Game is not with two players
		// The following if move the AI to look for the ball
		if(!twoPlayer) {
			if(GameMaster.ball.y < y && y >= 0) {
				y -= speed;
			}

			if(GameMaster.ball.y > y && y + height <= game.getHeight()) {
				y += speed;
			}
		}
		// If the game is with two players
		else {
			if(moveUp == true  && y > 0) {
				y -= speed;
			}
			else if (moveDown == true && y + height <= game.getHeight()) {
				y += speed;
			}
		}
	}
	
	// Choose the color for the ball and set it
	public void render(Graphics grph) {
		grph.setColor(Color.WHITE);
		grph.fillRect(x, y, width, height);
	}
	
}
