/*
 * 	by Alberic A. Davila Cadilla
 * 	GameMaster.java
 * 
 * This class builds the game and keeps it running by calling
 * upon other classes. 
 */


import java.awt.*;
import java.awt.image.*;

import javax.sound.sampled.Clip;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameMaster extends Canvas implements Runnable {

	//private final long serialVersionUID = 1L; 
	 
	public static Player01 player;
	public static AI ai;
	public static Ball ball; 
	public static JFrame frame;		// Frame for the window
	public static Clip click;
	Input input;
	
	public final String TITLE = "Pong Beta";
	private final int WIDTH = 600;				// width of the window
	private final int HEIGTH = WIDTH / 17 * 9;	// height of the window
	public final Dimension frameSize = new Dimension(WIDTH, HEIGTH);	// compact WIDTH and HEIGTH
	
	BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	static boolean gameRunning = false;
	
	int player1Score, player2Score;	// the scores
	
	// initialize thread variable of type Thread
	Thread thread;
	
	// start the game
	public void run() {
		
		while (gameRunning == true) {
			tick();			// tick = update
			render();	
			
			// Set the speed of the player, by stopping the tick process for 7 milliseconds
			try{
				Thread.sleep(7);
			} catch(Exception e) {
				e.printStackTrace();
			}	
		}
	}
	
	
		// Start method
	public synchronized void start() {
		gameRunning = true;
		thread = new Thread(this);
		thread.start();
	}
		
		// stop method
	public static synchronized void stop() {
		gameRunning = false;
		System.exit(0);
	}
	

		// Constructor
	public GameMaster() {
		frame = new JFrame();
		
		setMinimumSize(frameSize);
		setMaximumSize(frameSize);
		setPreferredSize(frameSize);
		
		frame.add(this, BorderLayout.CENTER); // "this" refers to the Canvas
		frame.pack();	// pack everything into the frame
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle(TITLE);
		frame.setLocationRelativeTo(null);	// make the frame appear in the middle of the screen
		
		input = new Input(this);
		
		player = new Player01(10, 60);
		ai = new AI(getWidth() - 25, 60);
		ball = new Ball(getWidth() / 2, getHeight() / 2);
		

		click = null;
		
	}
	
	// tick = update
	public void tick() {
		player.tick(this);
		ai.tick(this);
		ball.tick(this);
	}
	
	public void render() {
		BufferStrategy buffStrat = getBufferStrategy();
		if (buffStrat == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics graphs = buffStrat.getDrawGraphics();
		
		
		graphs.setColor(Color.BLACK);
		graphs.drawImage(image, 0, 0, getWidth(), getHeight(), null);	// Background
		
		
		// Setting the Scores
		graphs.setColor(Color.WHITE);								// color for the scores
		graphs.drawString("Player 1: " + player1Score, 5, 10);
		graphs.drawString("Player 2: " + player2Score, getWidth() - 70, 10);
		
		player.render(graphs);    // render(create) the player
		ai.render(graphs);		   // render(create) the AI
		ball.render(graphs);		// render(create) the ball
		
		graphs.dispose();
		buffStrat.show();
			
	}
	
}
