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
	
	private final int BALL_WIDTH  = 12;
	private final int BALL_HEIGHT = 12;
	
	private final int PADDLE_WIDTH  = 10;
	private final int PADDLE_HEIGHT = 50;
	private final int PADDLE_1_X    = 10;
	private final int PADDLE_2_X    = TABLE_WIDTH - PADDLE_WIDTH - PADDLE_1_X;
	private final int PADDLE_1_EDGE = PADDLE_1_X + PADDLE_WIDTH;
	private final int PADDLE_2_EDGE = PADDLE_2_X;
	
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
		if (collidingTableTopEdge() || collidingTableBottomEdge()) {
			yVelocity *= -1;
		}
		
		if (collidingTableLeftEdge() || collidingTableRightEdge() || collidingPaddle1Edge() || collidingPaddle2Edge()) {
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
	
	private boolean collidingTableTopEdge() {
		return (ballTopEdge() < 0);
	}
	
	private boolean collidingTableBottomEdge() {
		return (ballBottomEdge() > TABLE_HEIGHT);
	}
	
	private boolean collidingTableLeftEdge() {
		return (ballLeftEdge() < 0);
	}
	
	private boolean collidingTableRightEdge() {
		return (ballRightEdge() > TABLE_WIDTH);
	}
	
	private boolean collidingPaddle1Edge() {
		return (ballLeftEdge() < PADDLE_1_EDGE) && (ballTopEdge() < paddle1BottomEdge()) && (ballBottomEdge() > paddle1TopEdge());
	}
	
	private boolean collidingPaddle2Edge() {
		return (ballRightEdge() > PADDLE_2_EDGE) && (ballTopEdge() < paddle2BottomEdge()) && (ballBottomEdge() > paddle2TopEdge());
	}
	
	private int ballTopEdge() {
		return yBall;
	}
	
	private int ballBottomEdge() {
		return yBall + BALL_HEIGHT;
	}
	
	private int ballLeftEdge() {
		return xBall;
	}
	
	private int ballRightEdge() {
		return xBall + BALL_WIDTH;
	}
	
	private int paddle1TopEdge() {
		return yPaddle1;
	}
	
	private int paddle1BottomEdge() {
		return yPaddle1 + PADDLE_HEIGHT;
	}
	
	private int paddle2TopEdge() {
		return yPaddle2;
	}
	
	private int paddle2BottomEdge() {
		return yPaddle2 + PADDLE_HEIGHT;
	}
	
	private void initialize() {
		this.setBounds(TABLE_X, TABLE_Y, TABLE_WIDTH, TABLE_HEIGHT);
		
		xBall = (TABLE_WIDTH - BALL_WIDTH) / 2;
		yBall = (TABLE_HEIGHT - BALL_HEIGHT) / 2;
		
		xVelocity = 2;
		yVelocity = 2;
		
		yPaddle1 = (TABLE_HEIGHT - PADDLE_HEIGHT) / 2;
		yPaddle2 = yPaddle1;
	}
}
