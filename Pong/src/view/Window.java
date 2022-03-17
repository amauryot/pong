package view;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class Window {

	private JFrame frame;

	public Window() {
		initialize();
	}

	public void show() {
		frame.setVisible(true);
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Pong");
		frame.setBounds(100, 100, 574, 364);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("Menu");
		menuBar.add(menu);
		
		JMenuItem menuItemNewGame = new JMenuItem("Novo Jogo");
		menu.add(menuItemNewGame);
		
		JMenuItem menuItemExit = new JMenuItem("Sair");
		menu.add(menuItemExit);
		
		JMenu menuHelp = new JMenu("Ajuda");
		menuBar.add(menuHelp);
		
		JMenuItem menuItemAbout = new JMenuItem("Sobre");
		menuHelp.add(menuItemAbout);
		frame.getContentPane().setLayout(null);
		
		JPanel panelMain = new JPanel();
		panelMain.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelMain.setBounds(10, 10, 532, 276);
		frame.getContentPane().add(panelMain);
		panelMain.setLayout(null);
		
		JPanel panelTable = new JPanel();
		panelTable.setBounds(10, 10, 512, 256);
		panelMain.add(panelTable);
	}
}
