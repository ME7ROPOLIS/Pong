package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import engine.KeyboardController;
import engine.Renderable;

public class Player implements Renderable {
	
	private int x, y;
	private int paddleSpeed = 10;
	private int length, width;
	private static int playerCount = 0;
	private int playerID;
	
	int upControl;
	int downControl;
	
	private Score scoreKeeper;
	
	public Player(int length, int width, int frameSpace, int upControl, int downControl) {
		
		this.length = length;
		this.width = width;
		
		// Set controls
		this.upControl = upControl;
		this.downControl = downControl;
		
		// Increment playerCount, set playerID
		playerCount++;
		playerID = playerCount;
		
		// y value is centered on y axis
		this.y = 300 - (length/2);
		
		// x value is frameSpace away from the left or right border
		if(playerID==1) {
			this.x = frameSpace;
		}
		else {
			this.x = 775 - frameSpace;
		}
		
		scoreKeeper = new Score(this);
		
	}
	
	public boolean inFrame() {
		// Checks the paddle doesn't exceed the window
		if(y > (570 - length) | y < 1) {
			return false;
		}
		
		return true;
		
	}
	
	public void move(KeyboardController kb) {
		
		if(kb.isKeyPressed(upControl)) {
			y -= paddleSpeed;
			
			// Check if out of bounds
			if(!inFrame()) {
				y += paddleSpeed;
			}
		}
		
		if(kb.isKeyPressed(downControl)) {
			y += paddleSpeed;
			
			// Check if out of bounds
			if(!inFrame()) {
				y -= paddleSpeed;
			}
		}	
	}
	
	public int getScore() {
		return scoreKeeper.getScore();
	}
	
	public void addPoint() {
		scoreKeeper.addPoint();
	}
	
	public int length() {
		return length;
	}
	
	public int width() {
		return width;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getID() {
		return playerID;
	}
	
	public Score getScoreObj() {
		return scoreKeeper;
	}

	@Override
	public void draw(Graphics g) {
		
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, length);
		
	}
	
	public boolean visible() {
		return true;
	}

}
/*
 * -The move() method won't work right when we are being sent things over the server.
 * 	We need to look into alternatives. It will work fine, however.
 * 
 */


