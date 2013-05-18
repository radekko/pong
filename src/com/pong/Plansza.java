package com.pong;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Plansza extends JPanel implements KeyListener, Runnable {

	private int planszaWidth;
	private int planszaHeight;
	private Paddle leftPaddle;
	private Paddle rightPaddle;
	private boolean isPlay = false;
	private boolean isRightWin = true;
	private ScoreBoard scoreBoard;
	
	/* Pilka*/
	private int x;
	private int y;
	private int dx;
	private int dy;
	private int r = 20;

	public Plansza(int planszaWidth, int planszaHeight) {
		this.planszaWidth = planszaWidth;
		this.planszaHeight = planszaHeight;
		this.scoreBoard = new ScoreBoard();
		this.setBackground(Color.GREEN);
		
		initializePaddles();
	
		
		
		this.setFocusable(true);
		this.setVisible(true);
		this.addKeyListener(this);
		setBall();
		
	}

	private void initializePaddles() {
		this.leftPaddle = new Paddle(0, 0);
		this.rightPaddle = new Paddle(planszaWidth - Paddle.PADDLE_WIDTH , 0);
	}
	
	private void setBall(){
		this.x = planszaWidth / 2;
		this.y = 0;
		this.dx = (isRightWin == true ? 10 : -10);
		this.dy = 20;
		
		repaint();
		Runnable r = this;
		Thread ballThread = new Thread(r);
		ballThread.start();
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		drawObjects(g2);
		drawScoreBoard(g2);
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

	private void drawScoreBoard(Graphics2D g2) {
		g2.setFont(new Font("Arial", Font.PLAIN, 40));
		g2.setColor(Color.GRAY);
		g2.drawString(
				scoreBoard.getLeftScore().toString()+" - "+scoreBoard.getRightScore().toString(), 400, 50);
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
		case KeyEvent.VK_D:
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
		
		info();
		
			try {
				while(!Thread.currentThread().isInterrupted()) {
					move();
					Thread.sleep(Long.valueOf(100));
				}
			} catch (InterruptedException e) {
				setBall();
			}
	}
	
	private void info(){
		if(!isPlay) {
			JOptionPane.showMessageDialog(
				null, "Sterowanie:\n Lewy:\n A - góra, D - dó³\n Prawy:\n Strza³ki", "Info", JOptionPane.INFORMATION_MESSAGE);
			this.isPlay = true;
		}
	}
	
	private void move(){
		//if(!isPlay)
			//return;
		
		x += dx;
		y += dy;
		
		// lewa œciana
		if(x == Paddle.PADDLE_WIDTH) {
			// odbicie
			if (y >= leftPaddle.getP().getY() && y < leftPaddle.getP().getY() + Paddle.PADDLE_HEIGHT) {
				dx = -dx;
			}
			// punkt
			else {
				isRightWin = true;
				scoreBoard.add(Player.RIGHT);
				Thread.currentThread().interrupt();
			}
		}
		// prawa sciana
		else if (x == planszaWidth - Paddle.PADDLE_WIDTH - r) {
			// odbicie
			if (y >= rightPaddle.getP().getY() && y <= rightPaddle.getP().getY() + Paddle.PADDLE_HEIGHT){
				dx = -dx;
			}
			// punkt
			else {
				isRightWin = false;
				scoreBoard.add(Player.LEFT);
				Thread.currentThread().interrupt();
			}
		}
		// gora
		else if (y <= 0) {
			dy = -dy;
		}
		// dol
		else if (y >= planszaHeight - r){
			dy = -dy;
		}
		
		repaint();
	}

}
