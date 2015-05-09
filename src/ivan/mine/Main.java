package ivan.mine;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
	public static void main(String[] args) {
		new Main();
	}
	private Main(){
		this.readINI();
		this.window = new Window(row, col, mines,this);
	}
	private Window window;
	private int row = 10,col = 10,mines = 10,last = 0;
	public void lose() {
		if(last>0)last = 0;
		last--;
		String[] jbt = {"Exit","Play again","Start a new game"};
		int num = JOptionPane.showOptionDialog(null, "You lose!", "Info", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, jbt, jbt[2]);
		switch (num) {
			case 0:this.window.dispose();break;
			case 1:this.window.restart();break;
			case 2:this.window.reload();break;
			case -1:this.window.reload();break;
		}
	}
	public void win() {
		if(last<0)last = 0;
		last++;
		String[] jbt = {"Start a new game","Exit"};
		int num = JOptionPane.showOptionDialog(null, "You win!", "Congratulation!", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, jbt, jbt[0]);
		switch (num) {
			case 0:this.window.reload();break;
			case 1:this.window.dispose();break;
			case -1:this.window.reload();break;
		}
	}
	public void openOption(){
		FileOutputStream fos = null;
		try {
			JTextField height = new JTextField();
			JTextField wight = new JTextField();
			JTextField minesnum = new JTextField();
			height.setText(row+"");
			wight.setText(col+"");
			minesnum.setText(mines+"");
			JPanel optionpane = new JPanel(new GridLayout(3,2));
			optionpane.add(new JLabel("Height(10-99)"));
		    optionpane.add(height);
		    optionpane.add(new JLabel("Wight(10-99)"));
		    optionpane.add(wight);
		    optionpane.add(new JLabel("Mines(10-99)"));
		    optionpane.add(minesnum);
		    int selete = JOptionPane.showConfirmDialog(null, optionpane, "Option", JOptionPane.OK_CANCEL_OPTION);
		    if(selete==0){
		    	row = Integer.parseInt(height.getText());
			    col = Integer.parseInt(wight.getText());
			    mines = Integer.parseInt(minesnum.getText());
			    mines = mines<=(row-1)*(col-1)?mines:(row-1)*(col-1);
		    	Properties prop = new Properties();
		    	prop.setProperty("row", row+"");
		    	prop.setProperty("col", col+"");
		    	prop.setProperty("minecount", mines+"");
		    	prop.setProperty("last", last+"");
			    fos = new FileOutputStream("mine.ini");
		    	prop.store(fos, null);
		    	this.window.reload();
		    }
	    } catch (IOException | NumberFormatException e) {
			e.printStackTrace();
		} finally {
	    	try {
				fos.close();
			} catch (Exception e1) {
			}
	    }
	}
	private  boolean readINI(){
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("mine.ini");
			Properties prop = new Properties();
			prop.load(fis);
			row = Integer.parseInt((String)prop.get("row"));
			col = Integer.parseInt((String)prop.get("col"));
			mines = Integer.parseInt((String)prop.get("minecount"));
			last = Integer.parseInt((String)prop.get("last"));
			return true;
		} catch (Exception e) {
			return false;
		}finally{
			try {
				fis.close();
			} catch (Exception e) {
			}
		}
	}
	public Window getWindow() {
		return window;
	}
}
