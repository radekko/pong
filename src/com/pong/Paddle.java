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
					int y = p.getY() - 10;
					p.setY(y);
					//paddle.getP().setY(paddle.getP().getX()+10);
					break;
			case DOWN:
					int yy = p.getY() + 10;
					p.setY(yy);
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
