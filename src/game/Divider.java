package game;

import java.awt.Color;
import java.awt.Graphics;

import engine.Renderable;

public class Divider implements Renderable {

	private int width = 8, height = 600;
	
	@Override
	public void draw(Graphics g) {
		
		g.setColor(Color.WHITE);
		g.fillRect(400 - (width/2), 0, width, height);
		
	}
	
	public boolean visible() {
		return true;
	}

}
