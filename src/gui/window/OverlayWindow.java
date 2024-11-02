package gui.window;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.synth.SynthSpinnerUI;

import controllers.listeners.GenericListener;
import controllers.listeners.RecordingListener;
import gui.assets.graphics.ProcessStatusGraphic;
import gui.assets.graphics.RecordingStatusGraphic;
import gui.assets.graphics.SidePanelGraphic;

public class OverlayWindow implements Runnable {
	
	private GenericListener genericListener = new GenericListener();
	private RecordingListener recordingListener = new RecordingListener();
	
	private SidePanelGraphic gsp = null;
	private ProcessStatusGraphic[] statusGraphics = null;
	private RecordingStatusGraphic grs = null;
	private Graphics2D g2d = null;
	
	private JFrame frame = null;
	private InnerPane panel = null; 
	
	private final Color BG_TRANSPARENT = new Color(0, 0, 0, 0);
	private final Color BG_OPAQUE = new Color(255, 255, 255, 50);
	private final int BORDER_HEIGHT = 20;
	
	private boolean isRunning = true;
	private boolean isRecording = true;
	
	@SuppressWarnings("unused")
	private double framesActual = 0, maxFPS = 60, sleepTime = 1000.0/maxFPS, lastRefresh = 0, startTime = System.currentTimeMillis(), lastFPSCheck = 0, checks = 0;
    
	public OverlayWindow() {
		
    	gsp = new SidePanelGraphic();
    	grs = new RecordingStatusGraphic();
    	
    }
    
    public void init(GenericListener genericListener, RecordingListener recordingListener, ProcessStatusGraphic[] statusGraphics, int maxFPS) {
    	
    	this.statusGraphics = statusGraphics;
    	this.genericListener = genericListener;
    	this.recordingListener = recordingListener;
    	this.maxFPS  = maxFPS;
//    	this.recIconTick = 0;
    	
    	panel = new InnerPane();
        panel.requestFocus();
        
        frame = new JFrame("Testing");
        //frame.requestFocus();
        frame.setUndecorated(true);
        frame.setBackground(BG_OPAQUE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setAlwaysOnTop(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }
    
    public void toggleRecordingMode() {
		
		if(isRecording)
			disableRecordingMode();
		else 
			enableRecordingMode();
		
	}

    public void enableRecordingMode() {
		
		enableBackground();
		recordingListener.enable();
		isRecording = true;
		
		
	}
    
    public void disableRecordingMode() {
		
		disableBackground();
		recordingListener.disable();
		isRecording = false;
		
	}
    
	public void enableBackground() {
		
		frame.setBackground(BG_OPAQUE);
		frame.repaint();
		
	}
	
	public void disableBackground() {
		
		frame.setBackground(BG_TRANSPARENT);
		frame.repaint();
		
	}
	
	public void setIsRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	
	public boolean isRecording() {
		
		return isRecording;
		
	}
	
	public int getBorderHeight() {
		
		return BORDER_HEIGHT;
		
	}
	
	public void repaint() {
		
		frame.repaint();
		
	}
	
	@SuppressWarnings("serial")
	public class InnerPane extends JPanel {

		public InnerPane() {
			
        	setFocusable(true);
            setOpaque(false);
            setLayout(new GridBagLayout());
            addKeyListener(genericListener);
            addMouseListener(genericListener);
            addMouseMotionListener(genericListener);
            addKeyListener(recordingListener);
            addMouseListener(recordingListener);
            addMouseMotionListener(recordingListener);
            addFocusListener(new FocusListener() {

				@Override
				public void focusGained(FocusEvent arg0) {
					System.out.println("Focus Won");
				}

				@Override
				public void focusLost(FocusEvent arg0) {
					System.out.println("Focus Lossed");
				}
            	
            });
            
        }

        @Override
        public Dimension getPreferredSize() {
        	
            return new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
            
        }

        @Override
        protected void paintComponent(Graphics g) {
        	
            g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            
            g2d.setColor(new Color(255, 0, 0, 100));
            g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
            g2d.fillRect(0,0,getWidth(), BORDER_HEIGHT);
            
            if(isRecording) {
            	
            	grs.paintComponent(g2d, 10, 10);
	        	gsp.paintComponent(g2d);
	        	
	        	g2d.setStroke(new BasicStroke(1));
	        	
            }

            if(!isRecording && statusGraphics != null)
	            for(int i = 0; i < statusGraphics.length; i++)
					if (statusGraphics[i] != null) {
						statusGraphics[i].paintComponent(g2d, 50, 50, 0, i);
					}
            
            super.paintComponent(g2d);
            g2d.dispose();
            
        }
        
    }

	public void run() {
		
		while(isRunning)
		{
			
			long timeSLU = (long) (System.currentTimeMillis() - lastRefresh); 

			checks ++;			
			if(checks >= 15)
			{
				framesActual = checks/((System.currentTimeMillis() - lastFPSCheck)/1000.0);
				lastFPSCheck = System.currentTimeMillis();
				checks = 0;
			}
			
			if(timeSLU < 1000.0/maxFPS)
			{
				try {
					Thread.sleep((long) (1000.0/maxFPS - timeSLU));
				} catch (InterruptedException e) {
					System.out.println("[OverlayWindow] " +  e.getMessage());
				}	
			}	
			
			this.repaint();		
			
			lastRefresh = System.currentTimeMillis();
		}
	}
	
}