package view.subpanel.table;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Ball;
import model.Paddle;

public class SubPanelTable extends JPanel {

	private static final long serialVersionUID = -1918775861653731814L;

	private final int TABLE_X      = 10;
	private final int TABLE_Y      = 10;
	private final int TABLE_WIDTH  = 512;
	private final int TABLE_HEIGHT = 256;
	
	private final int SCORE_WIDTH  = 20;
	private final int SCORE_HEIGHT = 20;
	private final int SCORE_1_X    = TABLE_WIDTH / 4;
	private final int SCORE_1_Y    = 10;
	private final int SCORE_2_X    = (3 * TABLE_WIDTH) / 4;
	private final int SCORE_2_Y    = SCORE_1_Y;
	
	private final int BALL_WIDTH  = 12;
	private final int BALL_HEIGHT = 12;
	private final int BALL_INIT_X = (TABLE_WIDTH - BALL_WIDTH) / 2;
	private final int BALL_INIT_Y = (TABLE_HEIGHT - BALL_HEIGHT) / 2;
	
	private final int PADDLE_WIDTH    = 10;
	private final int PADDLE_HEIGHT   = 50;
	private final int PADDLE_1_X      = 10;
	private final int PADDLE_1_INIT_Y = (TABLE_HEIGHT - PADDLE_HEIGHT) / 2;
	private final int PADDLE_2_X      = TABLE_WIDTH - PADDLE_WIDTH - PADDLE_1_X;
	private final int PADDLE_2_INIT_Y = PADDLE_1_INIT_Y;
	
	private int score1 = 0;
	private int score2 = 0;
	
	private int xVelocity = 2;
	private int yVelocity = 2;
	
	private JLabel labelScore1;
	private JLabel labelScore2;
	
	private Ball ball;
	private Paddle paddle1;
	private Paddle paddle2;
	
	public SubPanelTable() {
		super();
		initialize();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		drawBall(g2d);
		drawPaddles(g2d);
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
		paddle2.setPositionY(ball.y() - ((PADDLE_HEIGHT - BALL_HEIGHT) / 2));
	}
	
	public void checkCollisions() {
		if (collidingTableTopEdge() || collidingTableBottomEdge()) {
			yVelocity *= -1;
		}
		
		if (collidingPaddle1Edge() || collidingPaddle2Edge()) {
			xVelocity *= -1;
		}
	}
	
	public void checkScore() {
		if (ball.leftEdge() < 0) {
			score2++;
			labelScore2.setText(String.valueOf(score2));
			restart();
		}
		
		if (ball.rightEdge() > TABLE_WIDTH) {
			score1++;
			labelScore1.setText(String.valueOf(score1));
			restart();
		}
	}
	
	private void restart() {
		ball.setPositionX(BALL_INIT_X);
		ball.setPositionY(BALL_INIT_Y);
		paddle1.setPositionY(PADDLE_1_INIT_Y);
		paddle2.setPositionY(PADDLE_2_INIT_Y);
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
	
	private boolean collidingTableTopEdge() {
		return (ball.topEdge() < 0);
	}
	
	private boolean collidingTableBottomEdge() {
		return (ball.bottomEdge() > TABLE_HEIGHT);
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
	
	private void initialize() {
		
		/* SUB PANEL */
		
		this.setLayout(null);
		this.setBounds(TABLE_X, TABLE_Y, TABLE_WIDTH, TABLE_HEIGHT);
		this.setBackground(Color.BLACK);
		
		/* FONT */
		
		Font labelFont = new Font("Tahoma", Font.PLAIN, 20);
		
		labelScore1 = new JLabel(String.valueOf(score1));
		labelScore1.setBounds(SCORE_1_X, SCORE_1_Y, SCORE_WIDTH, SCORE_HEIGHT);
		labelScore1.setFont(labelFont);
		labelScore1.setForeground(Color.WHITE);
		this.add(labelScore1);
		
		labelScore2 = new JLabel(String.valueOf(score2));
		labelScore2.setBounds(SCORE_2_X, SCORE_2_Y, SCORE_WIDTH, SCORE_HEIGHT);
		labelScore2.setFont(labelFont);
		labelScore2.setForeground(Color.WHITE);
		this.add(labelScore2);
		
		ball = new Ball(BALL_INIT_X, BALL_INIT_Y, BALL_WIDTH, BALL_HEIGHT);
		paddle1 = new Paddle(PADDLE_1_X, PADDLE_1_INIT_Y, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle2 = new Paddle(PADDLE_2_X, PADDLE_2_INIT_Y, PADDLE_WIDTH, PADDLE_HEIGHT);
	}
}
