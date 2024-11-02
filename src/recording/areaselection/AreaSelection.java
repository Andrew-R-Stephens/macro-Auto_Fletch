package recording.areaselection;

import java.awt.Rectangle;

public class AreaSelection {

	private int x, y, w, h;
	
	public AreaSelection() {
	}
	
	public AreaSelection(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public int[] getSel(){
		return new int[] {x, y, w, h};
	}
	
	public Rectangle getRectangle() {
		return new Rectangle(x, y, w, h);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return w;
	}
	
	public int getHeight() {
		return h;
	}
	
	public int getMidPointX() {
		return x+((int)w/2);
	}

	public int getMidPointY() {
		return y+((int)h/2);
	}
	
	public String printSimple() {
		return x + "," + y + "|" + w + "," + h;
	}
	
	public String toString() {
		return "XY: " + x + "," + y + " WH: " + w + "," + h;
	}
	
}
