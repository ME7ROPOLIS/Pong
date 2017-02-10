package engine;

public class KeyboardController {

	private int numKeys = 500;
	private boolean[] keyTyped = new boolean[numKeys];
	private boolean[] keyPressed = new boolean[numKeys];
	private boolean[] keyReleased = new boolean[numKeys];
	
	protected void toggleKeyTyped(int keyCode, boolean toggle) {
		keyTyped[keyCode] = toggle;
	}
	
	protected void toggleKeyPressed(int keyCode, boolean toggle) {
		keyPressed[keyCode] = toggle;
	}
	
	protected void toggleKeyReleased(int keyCode, boolean toggle) {
		keyReleased[keyCode] = toggle;
	}
	
	public boolean isKeyTyped(int keyCode) {
		return keyTyped[keyCode];
	}
	
	public boolean isKeyPressed(int keyCode) {
		return keyPressed[keyCode];
	}
	
	public boolean isKeyReleased(int keyCode) {
		boolean r = keyReleased[keyCode]; // saves released status
		
		// set it back to false, since keyReleased only gets called once (to release)
		keyReleased[keyCode] = false; 
		
		return r;
	}
	
}
