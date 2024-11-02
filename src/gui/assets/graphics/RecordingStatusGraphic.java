package gui.assets.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class RecordingStatusGraphic {

	private int circleSize = 50;
	
	private int recIconTick = 0;
	private boolean recIconEnabled = false;
	
	public RecordingStatusGraphic() {
		
		this.recIconTick = 0;
		
	}
	
	public void tick(double framesActual) {
    	
    	recIconTick ++;
    	if(recIconTick >= framesActual) {
    		recIconTick = 0;
    		recIconEnabled = !recIconEnabled;
    	}
    	
    }
	
	public void paintComponent(Graphics g, int x, int y) {
		
		Graphics2D g2d = (Graphics2D)g;
		y += circleSize;

    	if(recIconEnabled) {    
    		
        	g2d.setColor(Color.green);
        	g2d.fillOval(x + circleSize, y - circleSize, circleSize, circleSize);	
        	
    	}
    	
        g2d.setStroke(new BasicStroke(5));
        g2d.setColor(Color.lightGray);
    	g2d.drawOval(x + circleSize, y - circleSize, circleSize, circleSize);
    	
    	g2d.setStroke(new BasicStroke(1));
    	
	}
	
}
