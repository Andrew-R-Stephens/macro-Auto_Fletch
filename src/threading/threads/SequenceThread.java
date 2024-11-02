package threading.threads;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import bot.imageProcessing.ImageProcessor;
import bot.interfaces.BotInterface;
import exceptions.BotException;
import gui.assets.graphics.ProcessStatusGraphic;
import recording.EventAction;
import recording.Sequence;
import recording.SequenceController;

public class SequenceThread extends BotInterface implements Runnable{
	
	private ProcessStatusGraphic[] statusGraphics = null;
	private ImageProcessor imageProcessor = null;
	private SequenceController sequenceController = null;
	private Robot robot = null;
	
	private int threadID = -1;
	private boolean isRunning = false;
	private int cycleCount = -1;
	private long PAUSE_TIME = 3000;
	
	public void init(Robot robot, ImageProcessor imageProcessor, SequenceController sequenceController, ProcessStatusGraphic[] statusGraphics) {
		
		this.statusGraphics = statusGraphics;
		this.imageProcessor = imageProcessor;
		this.sequenceController = sequenceController;
		this.robot = robot;
	
	}

	public void setIsRunning(boolean b) {
		
		this.isRunning = b;
		
	}
	
	public void setCycleCount(int count) {
		
		this.cycleCount = count;
		
	}
	
	public Robot getRobot() {
		
		return robot;
		
	}
	
	public boolean isRunning() {
		
		return isRunning;
		
	}

	public void setThreadID(int threadID) {
		
		this.threadID = threadID;
		
	}
	

	public int getThreadID() {
		
		return threadID;
		
	}
	
	public boolean destroyThread() throws BotException {
			
		setIsRunning(false);
		
		return true;
		
	}
	
	public ImageProcessor getImageProcessor() {
		
		return imageProcessor;
		
	}
	
	@Override
	public void run() {
		
		while (cycleCount <= 0) {
        	
            System.out.print("INPUT CYCLE AMOUNT: ");
            cycleCount = new Scanner(System.in).nextInt();
            System.out.println();
            
        }
		
		sequenceController.setInterimDuration(PAUSE_TIME);
		
		System.out.print("Analyzing time... ");
		try {
			System.out.print(displayTime(sequenceController.analyzeTime()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(" ... done.");
		
		System.out.println(
			"-------------------------------------------------------------------" + 
			"\nTotal Cycles: " + cycleCount +
			"\nTime Per Cycle: " + displayTime(sequenceController.getDuration()) +
			"\nTime to finish: " + displayTime(sequenceController.getDuration() * cycleCount) +
			"\n-------------------------------------------------------------------");
		
		statusGraphics[0] = new ProcessStatusGraphic("TOTAL");
		statusGraphics[1] = new ProcessStatusGraphic("SEQUENCE");
		statusGraphics[2] = new ProcessStatusGraphic("EVENT");
		
		//ANALYZE 1 SEQUENCE
		System.out.println(statusGraphics[0].setTime(sequenceController.getDuration() * cycleCount));
		
		setIsRunning(sequenceController != null);	
		
		while(isRunning && cycleCount > 0) {
			
			System.out.println("Cycles remaining: " + cycleCount + "\t Time remaining: " + displayTime(sequenceController.getDuration() * cycleCount));
			
			ArrayList<Sequence> sequences = sequenceController.getSequenceRandomized();
			for(int i = 0; isRunning() && i < sequences.size(); i++) {
				
				System.out.println("\tSequence ( " + (i+1) + "/" + sequences.size() + " )");
				Sequence actions = sequences.get(i);
				
				statusGraphics[1].setTime(actions.getDuration());
				
				for(int j = 0; isRunning() && j < actions.getSize(); j++) {
					
					System.out.print("\t\tEvent ( " + (j+1) + "/" + actions.getSize() + " ) ... ");
					EventAction action = actions.get(j);
					
					statusGraphics[2].setTime(action.getDuration());
					
					try {
						
						action.execute(robot, false);
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					System.out.println("done.");
						
				}
			
				setIsRunning(isRunning && ((cycleCount--) > 0));
			
			}
			
		}
		
		System.out.println("Process complete!");
		
	}
	
	public String displayTime(long timeMili) {
		
		int d = 0;
		String s = String.format("%02d hrs, :%02d mins, %02d secs, %01d milis", 
				(d += TimeUnit.MILLISECONDS.toHours(timeMili)),
			    (d += TimeUnit.MILLISECONDS.toMinutes(timeMili) % TimeUnit.HOURS.toMinutes(1)),
			    (d += TimeUnit.MILLISECONDS.toSeconds(timeMili) % TimeUnit.MINUTES.toSeconds(1)),
			    (timeMili - (d*1000)));
		return s;
		
	}

}
