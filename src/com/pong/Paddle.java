package com.pong;

public class Paddle {

	private Point p;
	private int width;
	private int height;
	public int PADDLE_WIDTH = 40;
	public int PADDLE_HEIGHT = 100;
	
	
	public Paddle(int x, int y,int width, int height){
		this.width = width;
		this.height = height;
		p = new Point(x,y);
	}
	
	public void movePaddle(PaddleMoves pm){
			switch(pm){
			case UP:
					p.setY(p.getY() + 10);
					break;
			case DOWN:
					p.setY(p.getY() - 10);
					break;
			default:
				break;
			}
	}
	
	public Point getP() {
		return p;
	}

	public void setP(Point p) {
		this.p = p;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}


}
