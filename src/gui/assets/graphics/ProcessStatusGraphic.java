package gui.assets.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import gui.assets.StatusBar;

public class ProcessStatusGraphic  {

	private StatusBar status = null;
	
	private Color outlineColor = Color.BLACK, barBackgroundColor = Color.DARK_GRAY, barForegroundColor = Color.GREEN;
	private Font barLabelFont = new Font("System", Font.PLAIN, 8);
	
	private int w = 100, h = 12;
	
	private String name = "DEFAULT_OVERLAY_STATUS_NAME";
	
	public ProcessStatusGraphic(String name) {
		
		this.name = name;
		
	}
	
	public long setTime(long duration) {
		
		status = new StatusBar(duration);
		
		return duration;
		
	}
	
	public long addTime(long duration) {
		
		status.addTime(duration);
		
		return duration;
	}
	
	public long getDuration() {
		
		return status.getDuration();
		
	}
	
	public void paintComponent(Graphics2D g, int x, int y, int xOffset, int yOffset) {
		
		int totalOffsetY = yOffset*h;
		
		g.setColor(barBackgroundColor);
		g.fillRect(x+xOffset-1, y+totalOffsetY, w+1, h);
		
		if(status != null) {
			
			g.setColor(barForegroundColor);
			g.fillRect(x+xOffset, y+totalOffsetY, (int)((w+1)*status.getPercentCompleted())+1, h);
			
		}
		
		g.setColor(outlineColor);
		g.drawRect(x+xOffset-1, y+totalOffsetY, w+1, h);
		
		FontMetrics metrics = g.getFontMetrics(barLabelFont);
		g.setFont(barLabelFont);
		g.setColor(Color.BLACK);
		g.drawString(name, (x+xOffset) + ( (w - metrics.stringWidth(name)) /2 ), (y+totalOffsetY) + ( h - metrics.getHeight() / 2) + (metrics.getAscent() /2));
		
	}

	

}
