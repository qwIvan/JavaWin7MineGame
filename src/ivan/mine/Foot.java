package ivan.mine;

import javax.swing.*;
import java.awt.*;

public class Foot extends JPanel{
	private int sec;
	private int mines;
	private JLabel left = new JLabel();
	private JLabel right = new JLabel();
	private Thread thr = new Thread() {
		public void run() {
			while(true){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				left.setText(++sec+"");
			}
		}
	};
	public Foot(int mines){
		this.mines = mines;
		super.setLayout(new BorderLayout());
		super.add(left,BorderLayout.WEST);
		super.add(right,BorderLayout.EAST);
		left.setText(this.sec+"");
		right.setText(this.mines+"");
	}
	public void start(){
		thr.start();
	}
	public void downMines(boolean isdown){
		if(isdown)right.setText(--this.mines+"");
		else right.setText(++this.mines+"");
	}
}
