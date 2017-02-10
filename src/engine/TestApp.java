package engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/*
 * CONTROLS FOR TEST:
 * ================================================
 * USE ARROW KEYS TO MOVE RED OVAL
 * 
 * USE SPACE BAR TO INCREMENT COUNT
 * ================================================
 */

public class TestApp {

	private static class AppTest extends ApplicationRunner {
		
		private Oval testObj = new Oval();
		private Counter testText = new Counter();

		public AppTest(String title, int width, int height, Color bgcolor) {
			super(title, width, height, bgcolor);
			addRenderables();
		}
		
		@Override
		public void addRenderables() {
			// Because of super call, make sure we don't add renderable 
			// until AFTER renderable is instantiated
			getRenderManager().add(testObj);
			getRenderManager().add(testText);
			
		}

		@Override
		public void update() {
			testObj.move(getInputManager().getKeyboard());
			testText.update(getInputManager().getKeyboard());
		}
		
	}
	
	private static class Oval implements Renderable {

		private String name = "test";
		private int x = 100, y = 100;
		
		@Override
		public void draw(Graphics g) {
			g.setColor(Color.RED);
			g.fillOval(x, y, 100, 100);
			
		}
		
		public void move(KeyboardController kb) {
			// Check if a key is pressed, adjust x & y accordingly
			if(kb.isKeyPressed(KeyEvent.VK_UP)) {
				y -= 10;
			}
			
			if(kb.isKeyPressed(KeyEvent.VK_DOWN)) {
				y += 10;
			}
			
			if(kb.isKeyPressed(KeyEvent.VK_LEFT)) {
				x-= 10;
			}
			
			if(kb.isKeyPressed(KeyEvent.VK_RIGHT)) {
				x += 10;
			}
			
		}
		
		public boolean visible() {
			return true;
		}
		
	}
	
	private static class Counter implements Renderable {

		private static Font count;
		int num;
		
		public Counter() {
			count = new Font("SanSeriff", Font.PLAIN, 24);
			num = 0;
		}
		
		@Override
		public void draw(Graphics g) {
			g.setColor(Color.WHITE);
			g.setFont(count);
			
			// For adjusting Font placement by length of text
			FontMetrics fm = g.getFontMetrics();
			int w = fm.stringWidth(Integer.toString(num)); // width
			int h = fm.getAscent(); // height
			
			// Draw
			g.drawString(Integer.toString(num), 400 - (w/2), 100 - (h/4));
			
		}
		
		public void update(KeyboardController kb) {
			
			if(kb.isKeyPressed(KeyEvent.VK_SPACE)) {
				num++;
			}
			
		}
		
		public boolean visible() {
			return true;
		}
		
	}
	
	
	public static void main(String[] args) {
		AppTest app = new AppTest("test", 800, 600, Color.BLACK);
		
		app.start();
	}

}

/*
 * NOTE:
 * 		-write some sort of function that updates the x and y values of  the oval to show that 
 * 		 the keyboard listener works.
 * 
 *  	-We have written some code here, but everytime our RenderManager tries to render our
 *  	 renderables, we get a nullpointerexception. I think it has to do with a problem with
 *  	 our Oval class, but I am not sure what. It doesn't seem to be given to the renderables,
 *  	 or it doesn't seem to be anything at all after instantiation.
 *  
 *  	-NOW we get our red oval, now that I've called addrenderables in the constructor, however
 *  	 the oval will not move. Something wrong with the update call, perhaps?
 *  	 
 * 
 * 
 */
