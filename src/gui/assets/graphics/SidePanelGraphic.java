package gui.assets.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

public class SidePanelGraphic {
	
	private boolean isEnabled = false;
	
	private final int W = 100, H = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	public void setEnabled(boolean b) {
		
		isEnabled = b;
		
	}
	
	public void paintComponent(Graphics g) {
		
		if(isEnabled) {
			
			g.setColor(new Color(255, 0, 0));
			g.fillRect(0, 0, W, H);
			
		}
		
	}
	
}
