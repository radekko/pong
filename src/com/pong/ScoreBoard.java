package com.pong;

public class ScoreBoard {
	
	private Integer leftScore = 0;
	private Integer rightScore = 0;
	
	public Integer getLeftScore() {
		return leftScore;
	}
	public void setLeftScore(Integer leftScore) {
		this.leftScore = leftScore;
	}
	public Integer getRightScore() {
		return rightScore;
	}
	public void setRightScore(Integer rightScore) {
		this.rightScore = rightScore;
	}
	
	public void add(Player player){
		switch(player) {
		case LEFT:
			leftScore++;
			break;
		case RIGHT:
			rightScore++;
			break;
		default:
			break;
		}
	}
	
}
