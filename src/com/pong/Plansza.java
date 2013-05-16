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

public class Plansza extends JPanel implements KeyListener {

	public int x;
	private int planszaWidth;
	private int planszaHeight;
	private int PADDLEWIDTH = 50;
	private int PADDLEHEIGHT = 100;
	private Paddle leftPaddle;
	private Paddle rightPaddle;

	public Plansza(int planszaWidth, int planszaHeight) {
		this.planszaWidth = planszaWidth;
		this.planszaHeight = planszaHeight;

		initializePaddles();
		this.setFocusable(true);
		this.addKeyListener(this);
		this.setVisible(true);
	}

	public void initializePaddles() {
		this.leftPaddle = createLeftPaddle();
		this.rightPaddle = createRightPaddle();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);

		Rectangle2D leftRect = new Rectangle2D.Double(leftPaddle.getP().getX(),
				leftPaddle.getP().getY(), leftPaddle.PADDLE_WIDTH,
				leftPaddle.PADDLE_HEIGHT);

		Rectangle2D rightRect = new Rectangle2D.Double(rightPaddle.getP()
				.getX(), rightPaddle.getP().getY(), rightPaddle.PADDLE_WIDTH,
				rightPaddle.PADDLE_HEIGHT);

		g2.fill(leftRect);
		g2.draw(leftRect);

		g2.fill(rightRect);
		g2.draw(rightRect);

	}

	private Paddle createLeftPaddle() {
		Paddle p = new Paddle(0, 0, PADDLEWIDTH, PADDLEHEIGHT);
		return p;
	}

	private Paddle createRightPaddle() {
		Paddle p = new Paddle(planszaWidth - PADDLEWIDTH, 0, PADDLEWIDTH,
				PADDLEHEIGHT);
		return p;
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
			if (leftPaddle.getP().getY() < planszaHeight - PADDLEHEIGHT) {
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
			if (rightPaddle.getP().getY() < planszaHeight - PADDLEHEIGHT) {
				rightPaddle.movePaddle(PaddleMoves.DOWN);
				repaint();
			}
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
