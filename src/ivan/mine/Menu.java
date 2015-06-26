package ivan.mine;

import javax.swing.*;

public class Menu extends JMenuBar{
	private JMenuItem level;
	private Main main;
	public Menu(Main main){
		this();
		this.main = main;
	}
	public Menu(){
		level = new JMenuItem("Option");
		level.setMnemonic('O');
		level.setAccelerator(KeyStroke.getKeyStroke("F5"));
		level.addActionListener(e -> main.openOption());
		JMenu game = new JMenu("Game");
		game.setMnemonic('G');
		game.add(level);
		super.add(game);
	}
}
