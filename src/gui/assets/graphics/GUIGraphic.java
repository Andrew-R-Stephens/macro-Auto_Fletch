package gui.assets.graphics;

import java.awt.Graphics2D;

public abstract class GUIGraphic {
	
	protected int x = 0, y = 0, w = 0, h = 0;
	
	public abstract void paintComponent(Graphics2D g2d);
	
}
