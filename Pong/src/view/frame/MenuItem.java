package view.frame;

public enum MenuItem {

	NEW_GAME(0, "Novo Jogo"),
	EXIT    (1, "Sair"     ),
	ABOUT   (2, "Sobre"    );
	
	private int index;
	private String title;
	
	private MenuItem(int index, String title) {
		this.index = index;
		this.title = title;
	}
	
	public int index() {
		return index;
	}
	
	public String title() {
		return title;
	}
}
