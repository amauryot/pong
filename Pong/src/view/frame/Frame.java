package view.frame;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Frame extends JFrame {

	private static final long serialVersionUID = 8296914021670968846L;

	private final int FRAME_WIDTH  = 567;
	private final int FRAME_HEIGHT = 357;
	
	private final String FRAME_TITLE  = "Pong";
	private final String MENU_1_TITLE = "Menu";
	private final String MENU_2_TITLE = "Ajuda";
	
	private JMenu menu;
	private JMenu menuHelp;
	
	private ArrayList<JMenuItem> menuItemList;
	
	public Frame() {
		super();
		initialize();
	}
	
	public ArrayList<JMenuItem> menuItemList() {
		return menuItemList;
	}
	
	private JMenu menu(int index) {
		return (index < 2) ? menu : menuHelp;
	}
	
	private void initialize() {
		
		/* FRAME */
		
		this.setTitle(FRAME_TITLE);
		this.getContentPane().setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setResizable(false);
		this.setLocationRelativeTo(null);		
		
		/* MENU */
		
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		menu = new JMenu(MENU_1_TITLE);
		menuBar.add(menu);
		
		menuHelp = new JMenu(MENU_2_TITLE);
		menuBar.add(menuHelp);
		
		menuItemList = new ArrayList<JMenuItem>();
		
		for (MenuItem menuItemEnum : MenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(menuItemEnum.title());
			menu(menuItemEnum.index()).add(menuItem);
			menuItemList.add(menuItem);
		}
	}
}
