package controller;

import view.Window;

public class Controller {

	private Window window;
	
	public Controller() {
		window = new Window();
	}
	
	public void run() {
		window.show();
	}
}
