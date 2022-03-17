package view.subpanel.table;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class SubPanelTable extends JPanel {

	private static final long serialVersionUID = -1918775861653731814L;

	private final int TABLE_X      = 10;
	private final int TABLE_Y      = 10;
	private final int TABLE_WIDTH  = 512;
	private final int TABLE_HEIGHT = 256;
	private final int TABLE_TOP    = 0;
	private final int TABLE_BOTTOM = TABLE_HEIGHT;
	private final int TABLE_LEFT   = 0;
	private final int TABLE_RIGHT  = TABLE_WIDTH;
	
	private final int BALL_WIDTH  = 12;
	private final int BALL_HEIGHT = 12;
	
	private final int PADDLE_WIDTH   = 10;
	private final int PADDLE_HEIGHT  = 50;
	private final int PADDLE_1_X     = 10;
	private final int PADDLE_2_X     = TABLE_WIDTH - PADDLE_WIDTH - PADDLE_1_X;
	private final int PADDLE_1_RIGHT = PADDLE_1_X + PADDLE_WIDTH;
	private final int PADDLE_2_LEFT  = PADDLE_2_X;
	
	private int xBall, yBall;
	private int xVelocity, yVelocity;
	private int yPaddle1, yPaddle2;
	
	public SubPanelTable() {
		super();
		initialize();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		drawTable(g2d);
		drawBall(g2d);
		drawPaddles(g2d);
		drawScore(g2d);
	}
	
	public void update() {
		repaint();
	}
	
	public void moveBall() {
		xBall += xVelocity;
		yBall += yVelocity;
	}
	
	public void moveUpPaddle1() {
		yPaddle1 -= 10;
	}
	
	public void moveDownPaddle1() {
		yPaddle1 += 10;
	}
	
	public void movePaddle2() {
		yPaddle2 = yBall - ((PADDLE_HEIGHT - BALL_HEIGHT) / 2);
	}
	
	public void checkCollisions() {
		if (collidingTopTable() || collidingBottomTable()) {
			yVelocity *= -1;
		}
		
		if (collidingLeftTable() || collidingRightTable() || collidingPaddle1Right() || collidingPaddle2Left()) {
			xVelocity *= -1;
		}
	}
	
	private void drawTable(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, TABLE_WIDTH, TABLE_HEIGHT);
	}
	
	private void drawBall(Graphics2D g2d) {
		g2d.setColor(Color.WHITE);
		g2d.fillOval(xBall, yBall, BALL_WIDTH, BALL_HEIGHT);
	}
	
	private void drawPaddles(Graphics2D g2d) {
		g2d.setColor(Color.WHITE);
		g2d.fillRect(PADDLE_1_X, yPaddle1, PADDLE_WIDTH, PADDLE_HEIGHT);
		g2d.fillRect(PADDLE_2_X, yPaddle2, PADDLE_WIDTH, PADDLE_HEIGHT);
	}
	
	private void drawScore(Graphics2D g2d) {
		// TODO
	}
	
	private boolean collidingTopTable() {
		return (topBall() < TABLE_TOP);
	}
	
	private boolean collidingBottomTable() {
		return (bottomBall() > TABLE_BOTTOM);
	}
	
	private boolean collidingLeftTable() {
		return (leftBall() < TABLE_LEFT);
	}
	
	private boolean collidingRightTable() {
		return (rightBall() > TABLE_RIGHT);
	}
	
	private boolean collidingPaddle1Right() {
		return (leftBall() < PADDLE_1_RIGHT) && (topBall() < bottomPaddle1()) && (bottomBall() > topPaddle1());
	}
	
	private boolean collidingPaddle2Left() {
		return (rightBall() > PADDLE_2_LEFT) && (topBall() < bottomPaddle2()) && (bottomBall() > topPaddle2());
	}
	
	private int topBall() {
		return yBall;
	}
	
	private int bottomBall() {
		return yBall + BALL_HEIGHT;
	}
	
	private int leftBall() {
		return xBall;
	}
	
	private int rightBall() {
		return xBall + BALL_WIDTH;
	}
	
	private int topPaddle1() {
		return yPaddle1;
	}
	
	private int bottomPaddle1() {
		return yPaddle1 + PADDLE_HEIGHT;
	}
	
	private int topPaddle2() {
		return yPaddle2;
	}
	
	private int bottomPaddle2() {
		return yPaddle2 + PADDLE_HEIGHT;
	}
	
	private void initialize() {
		this.setBounds(TABLE_X, TABLE_Y, TABLE_WIDTH, TABLE_HEIGHT);
		
		xBall = (TABLE_WIDTH - BALL_WIDTH) / 2;
		yBall = (TABLE_HEIGHT - BALL_HEIGHT) / 2;
		
		xVelocity = 2;
		yVelocity = 2;
		
		yPaddle1 = (TABLE_HEIGHT - PADDLE_HEIGHT) / 2;
		yPaddle2 = (TABLE_HEIGHT - PADDLE_HEIGHT) / 2;
	}
}
