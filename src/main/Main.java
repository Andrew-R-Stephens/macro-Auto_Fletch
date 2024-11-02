package main;
import java.awt.AWTException;
import java.awt.GraphicsEnvironment;
import java.awt.Robot;

import bot.imageProcessing.ImageProcessor;
import controllers.listeners.GenericListener;
import controllers.listeners.RecordingListener;
import gui.assets.graphics.ProcessStatusGraphic;
import gui.window.OverlayWindow;
import recording.SequenceController;
import threading.ThreadWeaver;
import threading.threads.SequenceThread;

public class Main {
	
	public static void main(String[] args) {
		
		ThreadWeaver threadWeaver = new ThreadWeaver();
		
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		SequenceThread sequenceThread = new SequenceThread();
		ImageProcessor imageProcessor = new ImageProcessor(robot);
		SequenceController sequenceController = new SequenceController();
		RecordingListener recordingListener = new RecordingListener();
		GenericListener genericListener = new GenericListener();
		OverlayWindow overlayWindow = new OverlayWindow();
		ProcessStatusGraphic[] statusGraphics = new ProcessStatusGraphic[3];
		
		int refreshRate = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDisplayMode().getRefreshRate();
	
		if(args.length == 1)
			
			try {
				refreshRate = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				e.getMessage();
			}
		
		sequenceThread.init(robot, imageProcessor, sequenceController, statusGraphics);
		overlayWindow.init(genericListener, recordingListener, statusGraphics, refreshRate);
		genericListener.init(overlayWindow, sequenceThread, threadWeaver);
		recordingListener.init(sequenceController, overlayWindow, sequenceThread);
	
		overlayWindow.enableRecordingMode();
		
		try {
			threadWeaver.createThread(overlayWindow);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
