package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import engine.Renderable;

public class AnyScreen implements Renderable {
	/*====================================================
	 Makes a screen with text, such as a game over screen
	 or pause screen.
	====================================================*/
	String title;
	int x, y;
	int size;
	boolean visibleText;
	
	public AnyScreen(String text, int x, int y, int size, boolean visible) {
		this.title = text;
		this.x = x; 
		this.y = y;
		this.size = size;
		this.visibleText = visible;
	}

	@Override
	public void draw(Graphics g) {

		Font text = new Font("SanSeriff", Font.PLAIN, size);
		Font outline = new Font("SanSeriff", Font.BOLD, size+1);
		
		// Outline
		g.setColor(Color.BLACK);
		g.setFont(outline);
		
		FontMetrics fm = g.getFontMetrics();
		int w = fm.stringWidth(title);
		int h = fm.getAscent();
		
		// Body
		g.drawString(title, x - (w/2), y - (h/4));
		
		g.setColor(Color.WHITE);
		g.setFont(text);
		
		fm = g.getFontMetrics();
		w = fm.stringWidth(title);
		h = fm.getAscent();
		
		g.drawString(title, x - (w/2), y - (h/4));
		
	}
	
	public boolean visible() {
		return visibleText;
	}
	
	public void toggleVisible() {
		if(visibleText) {
			visibleText = false;
		}
		else {
			visibleText = true;
		}
	}

}
