package com.pong;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class Main extends JFrame {

	public JPanel panel;
	int WIDTH = 816;
	int HEIGHT = 639;

	public Main(){
		
		initialize();
		
		this.setVisible(true);
	}
	
	private void initialize(){
		setSize(WIDTH,HEIGHT);
		this.setTitle("PONG");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		panel = new Plansza(
				(int)this.getContentPane().getSize().getWidth(),
				(int)this.getContentPane().getSize().getHeight());
		this.add(panel);
		
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){public void run(){
			new Main();
			  }
			});	
	}

}
