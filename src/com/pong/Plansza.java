package com.pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class Plansza extends JPanel implements KeyListener, Runnable {

	private int planszaWidth;
	private int planszaHeight;
	private Paddle leftPaddle;
	private Paddle rightPaddle;
	private boolean isPlay = false;
	private boolean stopFlag = false;
	
	/* Pilka*/
	private int x;
	private int y = 0;
	private int dx = 10;
	private int dy = 20;
	private int r = 20;

	public Plansza(int planszaWidth, int planszaHeight) {
		this.planszaWidth = planszaWidth;
		this.planszaHeight = planszaHeight;
		this.x = Paddle.PADDLE_WIDTH;
		
		initializePaddles();
		
		startMovingBall();
		
		this.setFocusable(true);
		this.addKeyListener(this);
		this.setVisible(true);
		this.isPlay = true;
	}

	private void initializePaddles() {
		this.leftPaddle = new Paddle(0, 0);
		this.rightPaddle = new Paddle(planszaWidth - Paddle.PADDLE_WIDTH , 0);
	}
	
	private void startMovingBall(){
		Runnable r = this;
		Thread ballThread = new Thread(r);
		ballThread.start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		drawObjects(g2);
	}
	
	private void drawObjects(Graphics2D g2){
		g2.setColor(Color.BLACK);

		Rectangle2D leftRect = new Rectangle2D.Double(leftPaddle.getP().getX(),
				leftPaddle.getP().getY(), Paddle.PADDLE_WIDTH,
				Paddle.PADDLE_HEIGHT);

		Rectangle2D rightRect = new Rectangle2D.Double(rightPaddle.getP()
				.getX(), rightPaddle.getP().getY(), Paddle.PADDLE_WIDTH,
				Paddle.PADDLE_HEIGHT);
		
		Rectangle2D ballRect = new Rectangle2D.Double(x, y, r, r);

		g2.fill(leftRect);
		g2.draw(leftRect);

		g2.fill(rightRect);
		g2.draw(rightRect);
		
		g2.fill(ballRect);
		g2.draw(ballRect);
	}



	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			if (leftPaddle.getP().getY() > 0) {
				leftPaddle.movePaddle(PaddleMoves.UP);
				repaint();
			}
			break;
		case KeyEvent.VK_S:
			if (leftPaddle.getP().getY() < planszaHeight - Paddle.PADDLE_HEIGHT) {
				leftPaddle.movePaddle(PaddleMoves.DOWN);
				repaint();
			}
			break;
		case KeyEvent.VK_UP:
			if (rightPaddle.getP().getY() > 0) {
				rightPaddle.movePaddle(PaddleMoves.UP);
				repaint();
			}
			break;
		case KeyEvent.VK_DOWN:
			if (rightPaddle.getP().getY() < planszaHeight - Paddle.PADDLE_HEIGHT) {
				rightPaddle.movePaddle(PaddleMoves.DOWN);
				repaint();
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public void run() {
		
	//	for(int i=0;i<50;i++) {
			try {
				//Thread.sleep(Long.valueOf(5000));
				while(!Thread.currentThread().isInterrupted()) {
					move();
					Thread.sleep(Long.valueOf(100));
				}
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}
		//}
	}
	
	private void move(){
		if(!isPlay)
			return;
		
		x += dx;
		y += dy;
		
		// lewa sciana
		if(x == Paddle.PADDLE_WIDTH) {
			if (y > leftPaddle.getP().getY() && y < leftPaddle.getP().getY() + Paddle.PADDLE_HEIGHT) 
				System.out.println("punkt");
			else
				dx = -dx;
			//Thread.currentThread().interrupt();
		}
		// prawa sciana
		else if (x == planszaWidth - Paddle.PADDLE_WIDTH - r) {
			if (y > rightPaddle.getP().getY() && y <= rightPaddle.getP().getY() + Paddle.PADDLE_HEIGHT) 
				dx = -dx;
			else
				System.out.println("punkt");
			//Thread.currentThread().interrupt();
		}
		else if (y <= 0) {
			dy = -dy;
		}
		else if (y >= planszaHeight - r){
			dy = -dy;
		}
		
		repaint();
	}

}
