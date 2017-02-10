package engine;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class RenderManager {
	
	private Canvas screen;
	
	private ArrayList<Renderable> renderables = new ArrayList<Renderable>();
	
	public RenderManager(Canvas screen) {
		this.screen = screen;
	}
	
	public void update() {
		BufferStrategy b = screen.getBufferStrategy();
		if(b == null) {
			screen.createBufferStrategy(2);
			return;
		}
		
		Graphics g = b.getDrawGraphics();
		g.clearRect(0, 0, screen.getWidth(), screen.getHeight());
		
		for(Renderable r: renderables) {
			
			if(r != null) {
				//System.out.println(renderables); <----for testing Renderables
				if(r.visible()) {
					r.draw(g);
				}
			}
		}
		
		b.show(); // buffers to screen
		g.dispose(); // empties Graphics obj
		
	}
	
	public void add(Renderable r) {
		renderables.add(r);
	}
	
	public void remove(Renderable r) {
		renderables.remove(r);
	}
	
	
}
