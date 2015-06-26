package ivan.mine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Panel extends JPanel implements MouseListener{
	private final Button[] button;
	private final Main main;
	private int clearcount;
	private final int row,col,minecount;
	public void clear(){
		clearcount--;
	}
	private void left(){
		if(row*col-minecount==clearcount&&pointer.left()!=0) {
			this.changeMine(row, col, minecount);
		}
		if(pointer.left()==-1)main.lose();
		else if(clearcount==0)main.win();
	}
	private void middle(){
		if(!pointer.middle())main.lose();
		else if(clearcount==0)main.win();
	}
	public void restart(){
		this.clearcount = row*col-minecount;
		for(Button bt:button)bt.setNormal();
	}
	public Panel(int row, int col, int minecount, Main main){
		super.setLayout(new GridLayout(row, col));
		this.row = row;
		this.col = col;
		this.minecount = minecount;
		this.main = main;
		button = new Button[row*col];
		for(int i=0;i<button.length;i++)button[i] = new Button(this.main);
		for(int i=0;i<row*col;i++){
			int r = i/col,c = i%col;
			if(r!=0&&c!=0)button[i].addAround(button[i-col-1]);
			if(r!=0)button[i].addAround(button[i-col]);
			if(r!=0&&c!=col-1)button[i].addAround(button[i-col+1]);
			if(c!=0)button[i].addAround(button[i-1]);
			if(c!=col-1)button[i].addAround(button[i+1]);
			if(r!=row-1&&c!=0)button[i].addAround(button[i+col-1]);
			if(r!=row-1)button[i].addAround(button[i+col]);
			if(r!=row-1&&c!=col-1)button[i].addAround(button[i+col+1]);
			button[i].addMouseListener(this);
			super.add(button[i]);
		}
		this.clearcount = row*col-minecount;
		Random random = new Random();
		for(int i=0;i<button.length;i++){
			if(random.nextInt(row*col-i)<minecount){
				button[i].setMine(true);
				minecount--;
			}
		}
	}
	private void changeMine(int row,int col,int minecount){
		this.clearcount = row*col-minecount;
		int num = pointer.removeMineAround();
		Random random = new Random();
		int i = 0;
		for(Button bt:button){
			if(pointer.friendly(bt))continue;
			if(random.nextInt(row*col-num-i)<minecount){
				bt.setMine(true);
				minecount--;
			}else bt.setMine(false);
			i++;
		}
	}
	private Button pointer;
	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {
		if(pointer==null)return;
		if(e.getButton()==1&&e.getClickCount()==2){
			this.middle();
		}else if(e.getButton()==2||e.getButton()==1&&
				(e.getModifiersEx()&InputEvent.BUTTON3_DOWN_MASK)!=0
				||e.getButton()==3&&(e.getModifiersEx()&
				InputEvent.BUTTON1_DOWN_MASK)!=0){
			pointer.middleDown(true);
		}else if(e.getButton()==3){
			pointer.right();
		}
	}
	public void mouseReleased(MouseEvent e) {
		if(pointer==null)return;
		if(e.getButton()==2||e.getButton()==1&&
				(e.getModifiersEx()&InputEvent.BUTTON3_DOWN_MASK)
				!=0||e.getButton()==3&&(e.getModifiersEx()&
				InputEvent.BUTTON1_DOWN_MASK)!=0){
			this.middle();
		}else if(e.getButton()==1){
			this.left();
		}
	}
	public void mouseEntered(MouseEvent e) {
		this.pointer = (Button) e.getSource();
		if((e.getModifiersEx()&InputEvent.BUTTON2_DOWN_MASK)
				!=0||(e.getModifiersEx()&
				(InputEvent.BUTTON1_DOWN_MASK|
				InputEvent.BUTTON3_DOWN_MASK))
				==(InputEvent.BUTTON1_DOWN_MASK|
				InputEvent.BUTTON3_DOWN_MASK)){
			pointer.middleDown(true);
		}else{
			pointer.leftDown((e.getModifiersEx()
					&InputEvent.BUTTON1_DOWN_MASK)!=0);
		}
	}
	public void mouseExited(MouseEvent e) {
		pointer.middleDown(false);
		this.pointer = null;
	}
}
