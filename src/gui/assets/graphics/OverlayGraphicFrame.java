package gui.assets.graphics;

import java.awt.Graphics2D;

import javax.swing.JFrame;

public class OverlayGraphicFrame {

	private JFrame ow = null;
	
	public OverlayGraphicFrame(JFrame ow) {
		this.ow = ow;
	}
	
	public void paintComponent(Graphics2D g) {
		
		g.drawRect(0, 0, (int)(ow.getPreferredSize().getWidth()) - 1, (int)(ow.getPreferredSize().getHeight()) - 1);
		g.fillRect(0,0, (int)(ow.getPreferredSize().getWidth()), 20);
		
	}
}
