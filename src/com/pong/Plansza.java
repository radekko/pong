package com.pong;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;


public class Plansza extends JPanel implements KeyListener{
	
	public int x;
	private int planszaWidth;
	private int planszaHeight;
	private int PADDLEWIDTH=50;
	private int PADDLEHEIGHT=100;
	private Paddle leftPaddle;
	
	public Plansza(int planszaWidth, int planszaHeight){
		this.planszaWidth = planszaWidth;
		this.planszaHeight = planszaHeight;
		
		initializePaddles();
		this.setFocusable(true);
		this.addKeyListener(this);
		this.setVisible(true);
	}
	
	public void initializePaddles(){
		createLeftPaddle();
		createRightPaddle();
	}
	
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.RED);
		
		Rectangle2D leftRect = new Rectangle2D.Double(0,0,100,100);
		g2.fill(leftRect);
		g2.draw(leftRect);
		
	}
	
	private Paddle createLeftPaddle(){
		Paddle p = new Paddle(0,0,PADDLEWIDTH,PADDLEHEIGHT);
		return p;
	}
	
	private Paddle createRightPaddle() {
		Paddle p = new Paddle(planszaWidth - PADDLEWIDTH,0,PADDLEWIDTH,PADDLEHEIGHT);
		return p;
	}


	@Override
	public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()){
			case KeyEvent.VK_W:
				System.out.println("w");
				break;
			case KeyEvent.VK_S:
				break;
			case KeyEvent.VK_UP:
				//x = x+10;
				repaint();
				System.out.println("up");
				break;
			case KeyEvent.VK_DOWN:
				break;
			default:
				break;
			}
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
