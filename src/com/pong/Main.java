package com.pong;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		panel = new Plansza(
				(int)this.getContentPane().getSize().getWidth(),
				(int)this.getContentPane().getSize().getHeight()/*,
				WIDTH - (int)this.getContentPane().getSize().getWidth(),
				HEIGHT - (int)this.getContentPane().getSize().getHeight()*/);
		//panel = new Plansza(WIDTH,HEIGHT);
		this.add(panel);
		
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){public void run(){
			new Main();
			  }
			});	
	}

}
