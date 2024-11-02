package gui.dialogs;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import threading.threads.SequenceThread;

public class Dialog {
	
	private JFrame f = new JFrame();
	private SequenceThread botThread = null;
	
	public Dialog(SequenceThread botThread) {
		
		this.botThread = botThread;
		
	}
	
	public void init() {
		
		JPanel p_a = new JPanel();
		JLabel err_a = new JLabel("Error here");
		err_a.setForeground(new Color(0, 0, 0, 0));
		JTextField t_itemCountTotal = new JTextField(10);
		t_itemCountTotal.setPreferredSize(new Dimension(100, 20));
		t_itemCountTotal.addFocusListener(new FocusListener() {
			
			public void focusGained(FocusEvent e) {
			}
			
			public void focusLost(FocusEvent e) {
				
				if(t_itemCountTotal.getText().equals(""))
					err_a.setForeground(Color.red);
				else
					err_a.setForeground(new Color(0, 0, 0, 0));
				
			}
			
		});
		
		p_a.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p_a.add(new JLabel("Total number of items"));
		p_a.add(t_itemCountTotal);
		p_a.add(err_a);
		
		JPanel p_b = new JPanel();
		JLabel err_b = new JLabel("Error here");
		err_b.setForeground(new Color(0, 0, 0, 0));
		JTextField t_itemsPerCycle = new JTextField(10);
		
		t_itemsPerCycle.addFocusListener(new FocusListener() {
			
			public void focusGained(FocusEvent e) {
			}
			
			public void focusLost(FocusEvent e) {
				if(t_itemsPerCycle.getText().equals(""))
					err_b.setForeground(Color.red);
				else
					err_b.setForeground(new Color(0, 0, 0, 0));
			}
		});
		
		p_b.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p_b.add(new JLabel("Items per cycle"));
		p_b.add(t_itemsPerCycle);
		p_b.add(err_b);
		
		JPanel p_d = new JPanel();
		JButton b = new JButton("Run");
		
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int a = 0, b = 0;
				
				String ta = t_itemCountTotal.getText(), tb = t_itemsPerCycle.getText();
				if(ta.equals(""))
					a = 0;
				if(tb.equals(""))
					b = 0;
				
				boolean isAccepted = true;
				
				try {
					
					a = Integer.parseInt(t_itemCountTotal.getText().trim());
					
				} catch (NumberFormatException nfe) {
					
					err_a.setForeground(Color.red);
					isAccepted = false;
					
				}
				try {
					
					b = Integer.parseInt(t_itemsPerCycle.getText().trim());
					
				} catch (NumberFormatException nfe) {
					
					err_b.setForeground(Color.red);
					isAccepted = false;
					
				}
				
				double total = 0.0;
				
				if(isAccepted) {
					
					if(a < 0)
						a = 0;
					if(b < 0)
						b = 0;	
				
					try{
						
						total = (double)a / (double)b;	
						
					} catch (ArithmeticException ae) {
						
						err_a.setForeground(Color.red);
						err_b.setForeground(Color.red);
						isAccepted = false;
						
					}
				}
				
				if(isAccepted) {
					
					System.out.println((int)total);
					botThread.setCycleCount((int)total);
					f.setVisible(false);
					f = null;
					
				}
			}
		});
		
		p_d.add(b);
		
		JPanel p_main = new JPanel();
		p_main.setLayout(new BoxLayout(p_main, BoxLayout.Y_AXIS));
		
		p_main.add(p_a);
		p_main.add(p_b);
		p_main.add(p_d);
		
		f.add(p_main);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
	
}