package ivan.mine;
import javax.swing.*;
import java.awt.*;
@SuppressWarnings("serial")
public class Window extends JFrame{
	private Foot foot;
	private Pane pane;
	private Main main;
	private int row = 10,col = 10,mines = 10;
	public Window(int row,int col,int mines,Main main){
		super("MineGame");
		this.row = row;
		this.col = col;
		this.mines = mines;
		this.main = main;
		super.setIconImage(Resource.getImage(16).getImage());
		super.setResizable(false);
		super.setLayout(new BorderLayout());
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setJMenuBar(new Menu(this.main));
		this.reload();
	}
	public Foot getFoot() {
		return foot;
	}
	public Pane getPane() {
		return pane;
	}
	private void updateUI(){
		super.setVisible(false);
		super.pack();
		super.setLocationRelativeTo(null);
		super.setVisible(true);
	}
	public void reload(){
		if(pane!=null)super.remove(pane);
		if(foot!=null)super.remove(foot);
		super.add(pane=new Pane(row, col, mines,this.main),BorderLayout.CENTER);
		super.add(foot=new Foot(mines),BorderLayout.SOUTH);
		this.updateUI();
	}
	public void restart(){
		this.pane.restart();
		if(foot!=null)super.remove(foot);
		super.add(foot=new Foot(mines),BorderLayout.SOUTH);
		this.updateUI();
	}
	public Main getMain() {
		return main;
	}
}
	