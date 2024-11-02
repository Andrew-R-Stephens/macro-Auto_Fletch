package controllers.listeners;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import gui.window.OverlayWindow;
import threading.ThreadWeaver;
import threading.threads.SequenceThread;

public class GenericListener implements KeyListener, MouseListener, MouseMotionListener{

	private OverlayWindow overlayWindow = null;
	private SequenceThread botThread = null;
	private ThreadWeaver threadWeaver = null;
	
	private int x = 0, y = 0;
	
	public void init(OverlayWindow overlayWindow, SequenceThread botThread, ThreadWeaver threadWeaver) {
		
		this.overlayWindow = overlayWindow;
		this.botThread = botThread;
		this.threadWeaver = threadWeaver;
		
	}
	
	public void keyPressed(KeyEvent e) {
		
		switch(e.getKeyCode()) {
		
		case KeyEvent.VK_UP:{
			
			System.out.println("Toggling Recording Mode");
			overlayWindow.toggleRecordingMode();
			
			break;
			
		}
		
		case KeyEvent.VK_ESCAPE:{
			
			overlayWindow.setIsRunning(false);
			botThread.setIsRunning(false);
			threadWeaver.destroyAllThreads(botThread.getThreadID());
			
			System.out.println("Quitting");
			System.exit(0);
			
			break;
			
		}
		
		case KeyEvent.VK_SPACE:{
			
			if(!overlayWindow.isRecording() && !botThread.isRunning()) {
				
				System.out.println("Running Bot");
				
				try {
					botThread.setThreadID(threadWeaver.createThread(botThread));
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				
			}
			
			break;
			
		}
		
		}
		
	}

	public void keyReleased(KeyEvent e) {
		
	}

	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	public int getMouseX() {
		return x;
	}
	
	public int getMouseY() {
		return y;
	}
	
}
