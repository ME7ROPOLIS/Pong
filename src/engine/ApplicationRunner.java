package engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public abstract class ApplicationRunner implements Runnable {

	private RenderManager renderManager;
	private InputManager inputManager;
	
	private boolean running = false;
	
	// Abstract Methods to be defined/////
	public abstract void addRenderables();
	public abstract void update();
	//////////////////////////////////////
	
	public ApplicationRunner(String title, int width, int height, Color bgcolor) {
		
		Canvas screen = new Canvas();
		screen.setSize(width, height);
		screen.setVisible(true);
		
		JFrame frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(screen);
		
		renderManager = new RenderManager(screen);
		inputManager = new InputManager(screen);
		
		// Set background color
		Background bg = new Background(bgcolor, width, height);
		renderManager.add(bg);
		
		// CHECK ON THIS LATER; CAUSES NULL POINTER ERROR BECAUSE CALLED TOO EARLY
		//addRenderables();
		
	}
	
	@Override
	public void run() {
		
		double ups = 60.0;
		double frameTime = 1000.0/ups;
		
		long timeAtLastUpdate = System.currentTimeMillis();
		
		while(running) {
			if(System.currentTimeMillis() - timeAtLastUpdate >= frameTime) {
				// Update
				update(); // The abstract update
				renderManager.update();
				timeAtLastUpdate = System.currentTimeMillis();
			}
		}
		
	}
	
	public void start() {
		if(!running) {
			running = true;
			new Thread(this).start();
		}
	}
	
	public RenderManager getRenderManager() {
		return renderManager;
	}
	
	public InputManager getInputManager() {
		return inputManager;
	}
	
	public boolean isKeyPressed(int keyCode) {
		return inputManager.getKeyboard().isKeyPressed(keyCode);
	}
	
	public static class Background implements Renderable {
		
		Color bgcolor;
		int width;
		int height;
		
		public Background(Color bgcolor, int width, int height) {
			this.bgcolor = bgcolor;
			this.width = width;
			this.height = height;
		}

		@Override
		public void draw(Graphics g) {
			g.setColor(bgcolor);
			g.fillRect(0, 0, width, height);
		}
		
		@Override
		public boolean visible() {
			return true;
		}
		
	}
	
	
	
}

/*NOTES:
 * 
 * addRenderables: this should call getRenderManager.add() and add in
 * 	renderable objs. 
 * 
 * update: update should run through all of the renderables and make
 * 	sure their logic is up to date, i.e. move objs needing to move 
 * 	this frame tick, and stop those that need to stop. Renderables 
 * 	will need anew method that will help with this. 
 * 
 * 	Need a new way to do this. I want update to update all objs, but
 * 	it can't be within the renderables because the different renderables
 * 	need to be able to see each other for the logic to work out. Update 
 * 	needs to call on the BOARD (or the game itself) and check to see if 
 * 	everything is in order. 
 * 
 * 
 */
