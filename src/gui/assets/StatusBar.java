package gui.assets;

public class StatusBar {

	public long startTime = 0, finishTime = 0, duration = 0;
	
	public StatusBar(long duration) {
		
		this.duration = duration;
		this.startTime = System.currentTimeMillis();
		setFinishTime();
		
	}
	
	public void addTime(long extraDuration) {

		this.duration += extraDuration;
		setFinishTime();
		
	}
	
	private void setFinishTime() {
		this.finishTime = this.startTime+ this.duration;
	}
	
	public double getPercentCompleted() {
		
		return (double)(finishTime - System.currentTimeMillis()) / (double)duration;
		
	}
	
	public long getDuration() {
		
		return duration;
		
	}

}
