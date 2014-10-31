/*
 * 	by Alberic A. Davila Cadilla
 * 	Ball.java
 * 
 * Creates the ball and it's behavior upon collision 
 * with walls and players. 
 */

import java.awt.*;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Ball {
	
	Random rand = new Random();
	
	int x, y;
	int velx, vely;
	int size = 16;
	int speed = 2;

	Rectangle collisionBox;
	
	// Class Constructor
	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
		velx = speed * (rand.nextInt(2) - 2);
		vely = speed * (rand.nextInt(2) - 2);
		
		collisionBox = new Rectangle(x, y, size, size);
		collisionBox.setBounds(x, y, size, size);
	}
	
	public void tick(GameMaster game) {
		// update the collision box to move with the ball
		collisionBox.setBounds(x, y, size, size);
		
		// if it hits the LEFT wall
		if (x <= 0) {
			scoreSound();
			game.player2Score++;
			velx = speed;
		}
		// if it hits the RIGHT wall
		else if (x + size >= game.getWidth()) {
			scoreSound();
			game.player1Score++;
			velx = -speed;
		}
		
		// if it hits the TOP
		if(y <= 0) {
			wallSound();		
			vely = speed;
			}
		// if it hits the BOTTOM
		else if (y + size >= game.getHeight()) {
			wallSound();	
			vely = -speed;
		}
		
		x += velx;
		y += vely;
		
		// Check for collision
		collision(game);	
	}
	
	@SuppressWarnings("static-access")
	public void collision(GameMaster game) {
		// Collision with the player
		if(collisionBox.intersects(game.player.collisionBox)) {
			paddleSound();
			// here
			velx = speed;
		}
		
		// Collision with the AI
		else if(collisionBox.intersects(game.ai.collisionBox)) {
			paddleSound();
			//here
			velx = -speed;
		}
	}	
	
	// Sound when colliding with players
	public void paddleSound(){
		try {
			Clip click = AudioSystem.getClip();
			click.open(AudioSystem.getAudioInputStream
			(Ball.class.getResource("Click.wav")));
			click.start();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}
	
	// Sound when colliding with walls
	public void wallSound() {
		try {
			Clip hit = AudioSystem.getClip();
			hit.open(AudioSystem.getAudioInputStream
			(Ball.class.getResource("Hit.wav")));
			hit.start();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}
	
	// Sound when someone scores
	public void scoreSound() {
		try {
			Clip score = AudioSystem.getClip();
			score.open(AudioSystem.getAudioInputStream
			(Ball.class.getResource("Bonus.wav")));
			score.start();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}
	
	// Choose the ball color and set it
	public void render(Graphics grph) {
		grph.setColor(Color.CYAN);
		grph.fillOval(x, y, size, size);
	}

}
