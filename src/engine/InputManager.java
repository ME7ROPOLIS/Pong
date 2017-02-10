package engine;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class InputManager implements KeyListener, MouseListener, MouseMotionListener {

	private KeyboardController keyboard = new KeyboardController();
	private MouseController mouse = new MouseController();
	
	// Constructor makes this our Master Listener
	public InputManager(Component c) {
		c.addKeyListener(this);
		c.addMouseListener(this);
		c.addMouseMotionListener(this);
	}
	
	public KeyboardController getKeyboard() {
		return keyboard;
	}
	
	public MouseController getMouse() {
		return mouse;
	}
	
	
	// Overridden Methods for implemented Interfaces/////////////////
	
	@Override
	public void mouseDragged(MouseEvent e) {
		mouse.updateCoords(e.getX(), e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouse.updateCoords(e.getX(), e.getY());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		mouse.toggleButtonClicked(e.getButton(), true);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		mouse.toggleInScreen(true);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		mouse.toggleInScreen(false);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouse.toggleButtonPressed(e.getButton(), true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouse.toggleButtonReleased(e.getButton(), true);
		mouse.toggleButtonPressed(e.getButton(), false);
		mouse.toggleButtonClicked(e.getButton(), false);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keyboard.toggleKeyPressed(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyboard.toggleKeyReleased(e.getKeyCode(), true);
		keyboard.toggleKeyPressed(e.getKeyCode(), false);
		keyboard.toggleKeyTyped(e.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		keyboard.toggleKeyTyped(e.getKeyCode(), true);
	}

	
	
}
