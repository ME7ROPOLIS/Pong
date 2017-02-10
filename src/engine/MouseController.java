package engine;

public class MouseController {

	// Only 3 buttons on mouse, usually; open for more options
	private int numButtons = 10;
	
	private boolean[] buttonClicked = new boolean[numButtons];
	private boolean[] buttonPressed = new boolean[numButtons];
	private boolean[] buttonReleased = new boolean[numButtons];
	
	// Coords of mouse pointer
	private int x, y; 
	
	// Are mouse coords in screen window
	private boolean inScreen;
	
	protected void toggleButtonClicked(int buttonCode, boolean toggle) {
		buttonClicked[buttonCode] = toggle;
	}
	
	protected void toggleButtonPressed(int buttonCode, boolean toggle) {
		buttonPressed[buttonCode] = toggle;
	}
	
	protected void toggleButtonReleased(int buttonCode, boolean toggle) {
		buttonReleased[buttonCode] = toggle;
	}
	
	protected void updateCoords(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	protected void toggleInScreen(boolean toggle) {
		inScreen = toggle;
	}
	
	public boolean isMouseClicked(int button) {
		return buttonClicked[button];
	}
	
	public boolean isMousePressed(int button) {
		return buttonPressed[button];
	}
	
	public boolean isMouseReleased(int button) {
		boolean r = buttonReleased[button]; //  saves released status
		
		// set it back to false, since buttonReleased only gets called once (to release)
		buttonReleased[button] = false;
		
		return r;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isInScreen() {
		return inScreen;
	}
}
