package recording.eventaction;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import recording.EventAction;

public class KeyAction extends EventAction {

	private int keyCode = -1;
	
	public KeyAction(int keyCode, long timeActual) {
	
		super(timeActual);
		
		this.keyCode = keyCode;
		
	}
	
	public KeyAction(int keyCode, long timeActual, long previousTime) {
		
		super(timeActual, previousTime);
		
	}
	
	private long pressKey(Robot r, boolean isTest) throws InterruptedException {
		
		if(!isTest)
			r.keyPress(keyCode);
		
		long testDuration = pause(duration, isTest);
		
		if(!isTest)
			r.keyRelease(keyCode);
		
		return testDuration;
	
	}
	
	public long execute(Robot r, boolean isTest) throws InterruptedException {
		
		long testDuration = pause(duration, isTest);
		
		if(!isTest) {
			
			pressKey(r, isTest);
			
		}
		
		testDuration += pressKey(r, isTest);
		
		if(isTest)
			duration = testDuration;
		
		return duration;
		
	}
	
	public long executeImmediately(Robot r) throws InterruptedException {
		
		return pressKey(r, false);
		
	}
	
	public String toString() {
		
		return "Key: '" + KeyEvent.getKeyText(keyCode)  + "', Action duration: " + duration;
		
	}

}
