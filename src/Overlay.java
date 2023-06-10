import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Overlay {
	
	CFrame f = null;
	
	public Overlay() {
		//f = new CFrame(Toolkit.getDefaultToolkit().getScreenSize());
		f = new CFrame(new Dimension(100, 100));
	}

	@SuppressWarnings("serial")
	private class CFrame extends JFrame {
		
		public CFrame(Dimension d) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setUndecorated(true);
			setPreferredSize(d);
			
			addKeyListener(new KeyListener() {

				public void keyPressed(KeyEvent e) {
					
					if(e.getComponent() instanceof JFrame)
						if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
							((JFrame)e.getComponent()).dispose();
				}

				public void keyReleased(KeyEvent e) {
					
				}

				public void keyTyped(KeyEvent e) {	
					
				}
				
			});
			addMouseListener(new MouseListener() {

				public void mouseClicked(MouseEvent e) {
					
				}

				public void mouseEntered(MouseEvent e) {
					
				}

				public void mouseExited(MouseEvent e) {
					
				}

				public void mousePressed(MouseEvent e) {
					
				}

				public void mouseReleased(MouseEvent e) {
					
				}
				
			});
			setVisible(true);
			pack();
		}
		
	}
	
}
