package recording;

import java.awt.Robot;

import bot.interfaces.BotInterface;

public class EventAction extends BotInterface{
	
	protected long timeActual = 0L, duration = 0L;
	protected final int RELEASE_SPEED = 50;
	
	public EventAction(long timeActual) {
		
		this.timeActual = timeActual;
		
	}
	
	public EventAction(long timeActual, long previousTime) {
		
		this.timeActual = timeActual;
		
		if(previousTime == 0L)
			this.duration = 0L;
		else
			this.duration = timeActual-previousTime;
		
	}
	
	public void addPreviousTime(long previousTime) {
		
		if(previousTime == 0L)
			this.duration = 0L;
		else
			this.duration = timeActual-previousTime;
		
	}
	
	public void setDuration(long duration) {
		
		this.duration = duration;
		
	}
	
	public long getTime() {
		
		return timeActual;
		
	}
	
	public long getDuration() {
		
		return duration;
		
	}
	
	public String toString() {
		
		return " Time Actual: " + timeActual + " Time Paused: " + duration;
		
	}

	public long execute(Robot robot, boolean isTest) throws InterruptedException {
		
		System.out.println("Parent execute fired :(");
		
		return 0;
		
	}
	
	public long executeImmediately(Robot robot) throws InterruptedException {
		
		System.out.println("Parent executeImmediately fired :(");
		
		return 0;
		
	}
	
	public long analyzeTime() throws InterruptedException {
		
		System.out.println("Parent analyzeTime fired :(");
		
		return duration;
		
	}

	
	
}
