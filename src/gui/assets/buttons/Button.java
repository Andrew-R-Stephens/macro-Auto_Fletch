package gui.assets.buttons;

import gui.assets.graphics.GUIGraphic;

public abstract class Button extends GUIGraphic {
	
	protected boolean isEnabled = false;
	
	public Button(int x, int y, int w, int h) {
		
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
	}
	
	public abstract void doAction();
	
}
