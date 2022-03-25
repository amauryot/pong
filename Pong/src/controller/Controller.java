package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import view.OptionPane;
import view.Window;
import view.frame.MenuItem;

public class Controller implements ActionListener, KeyListener {

	private OptionPane optionPane;
	private Window window;
	private Timer timer;
	
	public Controller() {
		optionPane = new OptionPane();
		window = new Window();
		timer = new Timer(1/30, this);
	}
	
	public void run() {
		window.show();
		window.addController(this);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		window.game().moveBall();
		window.game().movePaddle2();
		window.game().checkCollisions();
		window.game().checkScore();
		
		window.game().update();
		
		/* MENU ITEM NEW GAME */
		
		if (event.getSource() == window.getMenuItem(MenuItem.NEW_GAME)) {
			// TODO
			return;
		}
		
		/* MENU ITEM EXIT */
		
		if (event.getSource() == window.getMenuItem(MenuItem.EXIT)) {
			window.close();
		}
		
		/* MENU ITEM ABOUT */
		
		if (event.getSource() == window.getMenuItem(MenuItem.ABOUT)) {
			optionPane.showMessageAbout();
			return;
		}
	}

	@Override
	public void keyTyped(KeyEvent event) {
		switch (event.getKeyChar()) {
		case 'w':
			window.game().moveUpPaddle1();
			break;
		case 's':
			window.game().moveDownPaddle1();
			break;
		}
	}

	@Override
	public void keyPressed(KeyEvent event) {
		switch (event.getKeyCode()) {
		case 38:
			window.game().moveUpPaddle1();
			break;
		case 40:
			window.game().moveDownPaddle1();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {}
}
