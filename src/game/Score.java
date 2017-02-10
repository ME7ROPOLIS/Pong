package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import engine.Renderable;

public class Score implements Renderable {
	
	private Player player;
	private int x, y;
	private Font scoreText;
	private int score = 0;
	
	public Score(Player player) {
		this.player = player;
		
		scoreText = new Font("SansSeriff", Font.PLAIN, 24);
		
		// Set x and y based on player association
		if(player.getID()==1) {
			x = 200;
		}
		else {
			x = 600;
		}
		
		y = 50;
		
	}
	
	public void addPoint() {
		score++;
	}
	
	public int getScore() {
		return score;
	}

	@Override
	public void draw(Graphics g) {
		
		g.setColor(Color.WHITE);
		g.setFont(scoreText);
		
		FontMetrics fm = g.getFontMetrics();
		int w = fm.stringWidth(Integer.toString(score)); // width
		int h = fm.getAscent(); // height
		
		g.drawString(Integer.toString(score), x - (w/2), y - (h/4));
		
	}
	
	public boolean visible() {
		return true;
	}

}
