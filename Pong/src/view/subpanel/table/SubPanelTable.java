package view.subpanel.table;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import model.Ball;
import model.Paddle;

public class SubPanelTable extends JPanel {

	private static final long serialVersionUID = -1918775861653731814L;

	private final int TABLE_X      = 10;
	private final int TABLE_Y      = 10;
	private final int TABLE_WIDTH  = 512;
	private final int TABLE_HEIGHT = 256;
	
	private final int BALL_WIDTH  = 12;
	private final int BALL_HEIGHT = 12;
	private final int BALL_X      = (TABLE_WIDTH - BALL_WIDTH) / 2;
	private final int BALL_Y      = (TABLE_HEIGHT - BALL_HEIGHT) / 2;
	
	private final int PADDLE_WIDTH  = 10;
	private final int PADDLE_HEIGHT = 50;
	
	private final int PADDLE_1_X = 10;
	private final int PADDLE_1_Y = (TABLE_HEIGHT - PADDLE_HEIGHT) / 2;
	
	private final int PADDLE_2_X = TABLE_WIDTH - PADDLE_WIDTH - PADDLE_1_X;
	private final int PADDLE_2_Y = PADDLE_1_Y;
	
	private int xVelocity = 2;
	private int yVelocity = 2;
	
	private Ball ball;
	private Paddle paddle1;
	private Paddle paddle2;
	
	public SubPanelTable() {
		super();
		initialize();
	}
	
	private void initialize() {
		this.setBounds(TABLE_X, TABLE_Y, TABLE_WIDTH, TABLE_HEIGHT);
		
		ball = new Ball(BALL_X, BALL_Y, BALL_WIDTH, BALL_HEIGHT);
		
		paddle1 = new Paddle(PADDLE_1_X, PADDLE_1_Y, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle2 = new Paddle(PADDLE_2_X, PADDLE_2_Y, PADDLE_WIDTH, PADDLE_HEIGHT);
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
		ball.setPositionX(ball.x() + xVelocity);
		ball.setPositionY(ball.y() + yVelocity);
	}
	
	public void moveUpPaddle1() {
		paddle1.setPositionY(paddle1.y() - 10);
	}
	
	public void moveDownPaddle1() {
		paddle1.setPositionY(paddle1.y() + 10);
	}
	
	public void movePaddle2() {
		paddle2.setPositionY(ball.y());
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
		g2d.fillOval(ball.x(), ball.y(), BALL_WIDTH, BALL_HEIGHT);
	}
	
	private void drawPaddles(Graphics2D g2d) {
		g2d.setColor(Color.WHITE);
		g2d.fillRect(PADDLE_1_X, paddle1.y(), PADDLE_WIDTH, PADDLE_HEIGHT);
		g2d.fillRect(PADDLE_2_X, paddle2.y(), PADDLE_WIDTH, PADDLE_HEIGHT);
	}
	
	private void drawScore(Graphics2D g2d) {
		// TODO
	}
	
	private boolean collidingTableTopEdge() {
		return (ball.topEdge() < 0);
	}
	
	private boolean collidingTableBottomEdge() {
		return (ball.bottomEdge() > TABLE_HEIGHT);
	}
	
	private boolean collidingTableLeftEdge() {
		return (ball.leftEdge() < 0);
	}
	
	private boolean collidingTableRightEdge() {
		return (ball.rightEdge() > TABLE_WIDTH);
	}
	
	private boolean collidingPaddle1Edge() {
		return (ball.leftEdge()   < paddle1.rightEdge())  &&
			   (ball.topEdge()    < paddle1.bottomEdge()) &&
			   (ball.bottomEdge() > paddle1.topEdge());
	}
	
	private boolean collidingPaddle2Edge() {
		return (ball.rightEdge()  > paddle2.leftEdge())   &&
			   (ball.topEdge()    < paddle2.bottomEdge()) &&
			   (ball.bottomEdge() > paddle2.topEdge());
	}
}
