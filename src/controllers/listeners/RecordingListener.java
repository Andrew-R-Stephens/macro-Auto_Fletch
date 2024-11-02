package controllers.listeners;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import exceptions.EmptySequenceException;
import exceptions.SequenceDoesNotExistException;
import gui.window.OverlayWindow;
import recording.EventAction;
import recording.SequenceController;
import recording.areaselection.AreaSelection;
import recording.eventaction.MouseAction;
import threading.threads.SequenceThread;

public class RecordingListener implements MouseListener, MouseMotionListener, KeyListener {
	
	private EventAction lastMouseAction = null;
	private int keyModifier = 0;
	
	private SequenceController sequenceController = null;
	private OverlayWindow overlayWindow = null;
	private SequenceThread botThread = null;
	
	private boolean isEnabled = false;
	
	public RecordingListener() {
	}
	
	public void init(SequenceController sequenceController, OverlayWindow overlayWindow, SequenceThread botThread) {
		
		this.sequenceController = sequenceController;
		this.overlayWindow = overlayWindow;
		this.botThread = botThread;
		
	}
	
	public void enable() {
		
		isEnabled = true;
		
	}
	
	public void disable() {
		
		isEnabled = false;
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		switch(e.getKeyCode()) {
		
		case KeyEvent.VK_DOWN:{
			
			try {
				
				sequenceController.addTempToList();
				System.out.println("Saving Recent Sequence, Starting New Sequence");
				
			} catch (EmptySequenceException ex) {
				System.out.println(ex.getMessage());
			}
			
			break;
		}
		
		case KeyEvent.VK_LEFT:{
			
			try {
				
				sequenceController.removePrevSeq();
				System.out.println("Deleting Previous Cycle");
				
			} catch (EmptySequenceException | SequenceDoesNotExistException ex) {
				System.out.println(ex.getMessage());
			}
			
			break;
		}
		
		case KeyEvent.VK_RIGHT:{
			
			try {
				
				sequenceController.removePrevEvnt();
				System.out.println("Deleting Previous Action");
				
			} catch (EmptySequenceException | SequenceDoesNotExistException ex) {
				System.out.println(ex.getMessage());
			}
			
			break;
		}
		
		case KeyEvent.VK_SHIFT: {
			
			if(keyModifier == 0) {
				
				keyModifier = e.getKeyCode();
				System.out.println("Pressed");
				
			}
			
			break;
			
		}
		
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		switch(e.getKeyCode()) {
		
		case KeyEvent.VK_SHIFT: {
			
			if (keyModifier != 0) {
				keyModifier = 0;
				System.out.println("Released");
			}
			
		}
		
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
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
		
		if(isEnabled) {
			
			if((e.isShiftDown() || !e.isShiftDown()) || (e.getModifiers() == MouseEvent.BUTTON1_MASK || e.getModifiers() == MouseEvent.BUTTON3_MASK)) {
				
				if(e.getY() > overlayWindow.getBorderHeight()) {

					lastMouseAction = new MouseAction(
							e, keyModifier,
							new AreaSelection(e.getX(), e.getY(), 0, 0),
							System.currentTimeMillis());
					
					if(keyModifier != 0) {
						botThread.getRobot().keyPress(keyModifier);
						keyModifier = 0;
					}
					
					overlayWindow.disableRecordingMode();
						
					try {
						lastMouseAction.executeImmediately(botThread.getRobot());
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
						
					overlayWindow.enableRecordingMode();					
					sequenceController.addToTemp(lastMouseAction);
					
					if(keyModifier != 0) {
						botThread.getRobot().keyRelease(keyModifier);
						keyModifier = 0;
					}
					
					System.out.println("Saved click event to sequence.");
					
				}
				
			}
			
		}
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}	
}
