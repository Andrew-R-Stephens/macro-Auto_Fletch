package recording.eventaction;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

import recording.EventAction;
import recording.areaselection.AreaSelection;

public class MouseAction extends EventAction {
	
	protected AreaSelection area = null;
	private MouseEvent mouseEvent = null;
	private int keyModifier = 0;
	
	/**
	 * MouseAction parameterized constructor - Creates a recording of a MouseAction which is the first action of a sequence.
	 * @param keyModifier 
	 * @param MouseEvent e - The InputEvent mouse button mask of the recorded action.
	 * @param AreaSelection area - The target area of the recorded action.
	 * @param long timeActual - The original system time of the recorded action.
	 */
	public MouseAction(MouseEvent e, int keyModifier, AreaSelection area, long timeActual) {
		
		super(timeActual);
		
		this.mouseEvent = e;
		this.keyModifier = keyModifier;
		this.area = area;
		
	}
	
	/**
	 * MouseAction parameterized constructor - Creates a recording of a MouseAction which is preceded by another action of a sequence.
	 * @param int mouseButton - The InputEvent mouse button mask of the recorded action.
	 * @param AreaSelection area - The target area of the recorded action.
	 * @param long timeActual - The original system time of the recorded action.
	 * @param long previousTime - The system time of the sequence action that's preceding this action. 
	 */
	public MouseAction(MouseEvent e, int keyModifier, AreaSelection area, long timeActual, long previousTime) {
		
		super(timeActual, previousTime);
		
		this.mouseEvent = e;
		this.keyModifier = keyModifier;
		this.area = area;
		
	}
	
	/**
	 * Fires a Mouse Event following the protocol pause duration.
	 * @param Robot
	 * @param boolean isTest - If the action execution is a test execution. Test executions will not fire mouse actions or sleep threads.
	 * @return The length of time required for execution completion.
	 */
	public long execute(Robot r, boolean isTest) throws InterruptedException {
		
		long testDuration = pause(duration, isTest);
		
		if(!isTest) {
			r.mouseMove(
					new Random().nextInt(area.getWidth()+1) + (area.getX()) + 1, 
					new Random().nextInt(area.getHeight()+1) + (area.getY()) + 1);
			
			r.mouseMove(
					new Random().nextInt(area.getWidth()+1) + (area.getX()), 
					new Random().nextInt(area.getHeight()+1) + (area.getY()));
		}
		
		//Pause before click
		testDuration += pause(100, false);
		
		//click
		testDuration += click(r, isTest);
		
		if(isTest)
			duration = testDuration;
		
		return duration;
		
	}
	
	/**
	 * Fires a Mouse Event immediately without forcing the action to follow a protocol pause duration.
	 * @param Robot
	 * @return The length of time required for completion.
	 */
	public long executeImmediately(Robot r) throws InterruptedException {
		
		long testDuration = 0;//pause(100, false);
		
		/*
		 * There are two mouse movements because either there's an issue with Java's mouse focus listeners or Jagex did something to break macros-
		 * 		The first movement is one pixel over from the actual click designation before the click is made. 
		 * 			This allows OSRS's mouse movement listener to focus the game window and register the mouse. 
		 * 			Without this step, the game will not register actions made on click-able objects (such as banks, containers, anvils) and will instead fire a walk-to action.
		 * 		The second movement is to the actual designated click location.
		 */
		//Abstract location
		r.mouseMove(
				new Random().nextInt(area.getWidth()+1) + (area.getX()) + 1, 
				new Random().nextInt(area.getHeight()+1) + (area.getY()) + 1);
		//Click location
		r.mouseMove(
				new Random().nextInt(area.getWidth()+1) + (area.getX()), 
				new Random().nextInt(area.getHeight()+1) + (area.getY()));
		
		//Pause before click
		testDuration += pause(100, false);
		
		//click
		testDuration += click(r, false);
		
		return testDuration; 
		
	}
	
	//TODO finish gradual mouse movement method
	public long moveTo (Robot r, int xB, int yB, boolean doGradual) {
		
		long testDuration = 0;
		
		Point p = MouseInfo.getPointerInfo().getLocation();
		double xA = p.getX(), yA = p.getY();
		
		double dY = (yB-yA), dX = (xB-xA);
		double d = Math.sqrt(Math.pow(dY, 2) + Math.pow(dX, 2));
		double m = dY/dX;
		
		return testDuration;
		
	}
	
	/**
	 * Fires a custom Mouse Click Event.
	 * @param Robot
	 * @param boolean isTest - If the action execution is a test execution. Test executions will not fire mouse actions or sleep threads.
	 * @return The length of time required for execution completion.
	 */
	public long click(Robot r, boolean isTest) throws InterruptedException {
		
		if(!isTest) {
		
			if(keyModifier != 0) {
				
				System.out.println(KeyEvent.getKeyText(keyModifier));
				r.keyPress(keyModifier);
				
			}
				
			r.mousePress(mouseEvent.getModifiers() + shiftModifier());
		
		}
		long testDuration = pause(RELEASE_SPEED, isTest);
		
		if(!isTest) {
		
			r.mouseRelease(mouseEvent.getModifiers() + shiftModifier());
			
			if(keyModifier != 0) 
				r.keyRelease(keyModifier);
			
		}
			
		return testDuration;
		
	}
	
	/**
	 * Tests the duration of the action's execution.
	 * @return The length of time required for completion of the action.
	 */
	public long analyzeTime() throws InterruptedException {
		
		return execute(null, true);
				
	}
	
	/**
	 * @return The InputEvent mouse button mask.
	 */
	public int getButton() {
		
		return mouseEvent.getModifiers();
		
	}
	
	public int shiftModifier() {
		
		if(mouseEvent.isShiftDown())
			return -1;
		else
			return 0;
		
	}
	
	public String toString() {
		
		return "Area: " + area + super.toString();
		
	}

}
