package ivan.mine;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
@SuppressWarnings("serial")
public class Button extends JButton{
	private List<Button> around = new LinkedList<Button>();
	private boolean clear;
	private boolean flag;
	private boolean question;
	private boolean mine;
	private int num;
	private final Main main;
	public void addAround(Button friend){
		this.around.add(friend);
	}
	public int removeMineAround(){
		for(Button f:around)f.setMine(false);
		return around.size();
	}
	public Button(Main main){
		this.main = main;
		this.setNormal();
		super.setBorder(null);
		this.addAround(this);
	}
	public boolean friendly(Button friend){
		if(around.indexOf(friend)==-1)return false;
		else return true;
	}
	public boolean setMine(boolean flag){
		this.setNormal();
		if(!mine^flag)return false;
		if(flag)for(Button friend:this.around)friend.num++;
		else for(Button friend:this.around)friend.num--;
		this.mine = flag;
		return true;
	}
	private void dig(){
		super.setIcon(Source.getImage(num));
		super.setRolloverIcon(null);
		super.setPressedIcon(null);
		flag = false;
		question = false;
		clear = true;
	}
	public void middleDown(boolean pressed){
		for(Button mb:this.around)mb.leftDown(pressed);
	}
	public boolean middle(){
		middleDown(false);
		if(!clear)return true;
		int hasCount = 0;
		for(Button mb:this.around)if(mb.flag)hasCount++;
		boolean flag = true;
		if(hasCount==this.num)
			for(Button mb:this.around)
				if(mb.left()==-1)flag=false;
		return flag;
	}
	public void leftDown(boolean pressed){
		if(pressed){
			super.getModel().setArmed(true);
			super.getModel().setPressed(true);
		}else{
			super.getModel().setArmed(false);
			super.getModel().setPressed(false);
		}
	}
	public int left(){
		if(flag||clear)return -2;
		if(mine)return -1;
		this.dig();
		this.main.getWindow().getPane().clear();
		if(num==0)
			for(Button mls:around)
				mls.left();
		return this.num;
	}
	public void right(){
		if(clear)return;
		if(!flag&&!question){
			this.setFlag();
			this.main.getWindow().getFoot().downMines(true);
		}else if(flag){
			this.main.getWindow().getFoot().downMines(false);
			this.setQuestion();
		}else if(question){
			this.setNormal();
		}
	}
	private void setFlag(){
		super.setIcon(Source.getImage(9));
		super.setRolloverIcon(Source.getImage(10));
		super.setPressedIcon(Source.getImage(9));
		flag = true;
		question = false;
		clear = false;
	}
	public void setNormal(){
		super.setIcon(Source.getImage(11));
		super.setRolloverIcon(Source.getImage(12));
		super.setPressedIcon(Source.getImage(0));
		flag = false;
		question = false;
		clear = false;
	}
	private void setQuestion(){
		super.setIcon(Source.getImage(13));
		super.setRolloverIcon(Source.getImage(14));
		super.setPressedIcon(Source.getImage(15));
		flag = false;
		question = true;
		clear = false;
	}
}
