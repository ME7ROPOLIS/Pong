package game;

import java.awt.Color;
import java.awt.event.KeyEvent;

import engine.ApplicationRunner;
import engine.KeyboardController;

public class LocalPongTest extends ApplicationRunner {
	
	private Player player1 = new Player(100, 25, 50, KeyEvent.VK_UP, KeyEvent.VK_DOWN);
	private Player player2 = new Player(100, 25, 50, KeyEvent.VK_W, KeyEvent.VK_S);
	private Ball ball = new Ball(player1, player2);
	private AnyScreen pauseScreen;
	private long pauseScreenLimiter = System.currentTimeMillis();
	
	private boolean pause = false;
	

	public static void main(String[] args) {
		LocalPongTest app = new LocalPongTest("Pong Test!", 800, 600, Color.BLACK);
		
		app.start();	
	}
	
	public LocalPongTest(String title, int width, int height, Color bgcolor) {
		super(title, width, height, bgcolor);
		this.pauseScreen = new AnyScreen("Pause", 400, 300, 24, false);
		addRenderables();
	}

	@Override
	public void addRenderables() {
		
		getRenderManager().add(player1);
		getRenderManager().add(player2);
		getRenderManager().add(ball);
		getRenderManager().add(new Divider());
		getRenderManager().add(player1.getScoreObj());
		getRenderManager().add(player2.getScoreObj());
		
	}

	@Override
	public void update() {
		checkForPause(getInputManager().getKeyboard());
		if(!pause) {
			player1.move(getInputManager().getKeyboard());
			player2.move(getInputManager().getKeyboard());
			ball.move();
		}
		else {
			// Make pause screen
			getRenderManager().add(pauseScreen);
		}
		
	}
	
	public void pause() {
		if(!pause && (System.currentTimeMillis() - pauseScreenLimiter) > 500) {
			pause = true;
			pauseScreen.toggleVisible();
			pauseScreenLimiter = System.currentTimeMillis();
		}
		else if((System.currentTimeMillis() - pauseScreenLimiter) > 500){
			pause = false;
			pauseScreen.toggleVisible();
			pauseScreenLimiter = System.currentTimeMillis();
		}
	}
	
	public void checkForPause(KeyboardController kb) {
		if(kb.isKeyPressed(KeyEvent.VK_P)) {
			pause();
		}
	}

}

/* TODO/BUGS:
 * 	[X]-Well, paddles aren't filled in, but I kind of like that
 *  [X]-Paddles are shifted too far to the right
 *  [X]-Ball and paddles go through bottom
 *  [ ]-ApplicationRunner addRenderables() cause NullPointer error because it is called too early
 *  	for our Score object to be made in time, or something to that affect. (engine error?)
 * 	[X]-Paddles move in reverse
 *  [X]-Ball bounces on ceiling, but not floor
 *  [X]-Ball bounces off of Paddles inner edge, not outer
 *  [X]-Dividing line doesn't show up
 *  [X]-Score doesn't increment
 *  [X]-Make pause screen
 *  	[X]-Make it pause
 *  	[X]-Display Pause
 *  	[X]-Remove pause when unpausing
 *  	[X]-Pause toggles back and forth quickly
 *  [X]-Ball seems to serve the same way everytime.
 *  [ ]-Not sure ball is waiting 3 seconds to serve. 
 *  [X]-Paddle physics off: doesn't know ball passes paddle
 *  [ ]-Right paddle is farther from window edge than left paddle
 *  
 * OPTIONAL BUGS:
 *  [ ]-Ball does not bounce off of top or bottom of paddles, just outer edge.
 *  	[ ]-Ball should bounce upward off top of paddle on y axis, not x axis
 *  [ ]-Ball physics do not consider the entire outer edge of the ball, only the x point (upper
 *  	left) as though it were a cube.
 * 
 */
