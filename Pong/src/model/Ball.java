package model;

public class Ball {

	private int x;
	private int y;
	private int width;
	private int height;
	
	public Ball(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public int x() {
		return x;
	}
	
	public int y() {
		return y;
	}
	
	public void setPositionX(int x) {
		this.x = x;
	}
	
	public void setPositionY(int y) {
		this.y = y;
	}
	
	public int topEdge() {
		return y;
	}
	
	public int bottomEdge() {
		return y + height;
	}
	
	public int leftEdge() {
		return x;
	}
	
	public int rightEdge() {
		return x + width;
	}
}
