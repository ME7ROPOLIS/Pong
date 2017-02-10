package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import engine.Renderable;

public class Ball implements Renderable {
	
	Player player1;
	Player player2;
	
	private Random randNum = new Random();
	private int xVelocity;
	private int yVelocity;
	private int x, y;
	private int ballWidth;
	
	public Ball(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		
		// Set ball up, centered
		ballWidth = 25;
		x = 400 - (ballWidth/2);
		y = 300 - (ballWidth/2);
		
		// Set Start velocity
		startVelocity();
		
	}

	public void checkGoal() {
		// Checks if a goal has been made
		// 1 - player1, 2 - player2, 0 - no goal
		
		if(x < -50) {
			// Passes the Left wall; increment score
			player1.getScoreObj().addPoint();
			serveBall();
		}
		else if(x > 850) {
			// Passes the Right wall; increment score
			player2.getScoreObj().addPoint();
			serveBall();
		}
		
	}
	
	public void serveBall() {
		// Waits 3 seconds, then serves the ball

		long timeScored = System.currentTimeMillis();
		
		while(System.currentTimeMillis() - timeScored != 3) {
			// Do nothing; perhaps there is a better way?
		}
		
		x = 400 - (ballWidth/2);
		y = 300 - (ballWidth/2);
		
		startVelocity();
		
	}
	
	public boolean inFrame() {
		// Checks if ball is within the Y axis frame boundry
		if(y >= 0 | y <= 600) {
			return true;
		}
		
		return false;
	}
	
	public void bounce() {
		// Bounces ball off of objects and changes velocity values
		
		// PADDLES //////////////////////////////////////////////////		
		// Top x, Bottom x, top y, bottom y of paddles
		int x1, x2, y1, y2;
		boolean player1Hit;
		
		if(x < 400) {
			x1 = player1.getX();
			x2 = player1.getX() + player1.width();
			y1 = player1.getY();
			y2 = player1.getY() + player1.length();
			player1Hit = true;
		}
		else {
			x1 = player2.getX();
			x2 = player2.getX() - player2.width();
			y1 = player2.getY();
			y2 = player2.getY() + player2.length();
			player1Hit = false;
		}
		// Check if hit a paddle
		if(y >= y1 && y <= y2) {
			if(player1Hit && x <= x2 && x >= x1) {
				int[] newVelocities = adjustVelocity(xVelocity, yVelocity); 
				// xVelocity changes direction
				xVelocity = newVelocities[0];

				// yVelocity travels in the same direction until hitting a wall
				yVelocity = newVelocities[1];
				
			}
			else if(!player1Hit && x >= x2 && x <= x1) {
				int[] newVelocities = adjustVelocity(xVelocity, yVelocity);
				xVelocity = -xVelocity;
				
				// yVelocity travels in the same direction until hitting a wall
				yVelocity = newVelocities[1];
			}
		}
		
		// WALLS ////////////////////////////////////////////////
		
		if(y < 1 | y > (575 - ballWidth)) {
			yVelocity = -yVelocity;
		}		
		
	}
	
	public int[] adjustVelocity(int vx, int vy) {
		// Adjusts the velocity of the ball movement
		vx = randNum.nextInt(15);
		vy = randNum.nextInt(15);
		
		int[] coords = {vx, vy};
		
		for(int i=0; i<coords.length; i++) {
			if(coords[i] < 5) {
				coords[i] += 5;
			}
		}
		
		return coords;
	}
	
	public void startVelocity() {
		
		xVelocity = randNum.nextInt(10);
		if(xVelocity < 5) {
			xVelocity += 5;
		}
		yVelocity = randNum.nextInt(10);
		if(yVelocity < 5) {
			yVelocity += 5;
		}
		
		// Randomize x and y direction
		int direction1 = randNum.nextInt(2);
		int direction2 = randNum.nextInt(2);
				
		if(direction1 == 1) {
			xVelocity = -xVelocity;
		}
		if(direction2 == 1) {
			yVelocity = -yVelocity;
		}
		
	}
	
	public void move() {
		// Move the ball
		x += xVelocity;
		y += yVelocity;
		bounce();
		checkGoal();
		
	}
	
	@Override
	public void draw(Graphics g) {
		
		g.setColor(Color.WHITE);
		g.fillOval(x, y, ballWidth, ballWidth);
		
		
	}
	
	public boolean visible() {
		return true;
	}
	

}

/*TODO:
 * 
 *  [X]-Constructor
 *  [X]-Serve the ball
 *  [X]-inFrame
 *  [X]-checkGoal
 *  [X]-fix velocity in constructor so they have minimum of 5, max of 10
 *  [X]-Bounce
 *  	[X]-Paddles
 *  	[X]-Walls
 *  [X]-adjustVelocity
 * 
 * 
 *  IDEA: We could change it so the ball keeps a constant 20 total velocity across xVelocity
 *  	and yVelocity. So if one is 10, the other must be 10, and if one is 15, the other must
 *  	be 5.
 */
