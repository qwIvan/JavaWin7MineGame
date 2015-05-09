package ivan.mine;
import javax.swing.ImageIcon;
public class Source {
	private static final String path = "img/";
	private static final ImageIcon[] imgs = {
		new ImageIcon(path+"0.png"),
		new ImageIcon(path+"1.png"),
		new ImageIcon(path+"2.png"),
		new ImageIcon(path+"3.png"),
		new ImageIcon(path+"4.png"),
		new ImageIcon(path+"5.png"),
		new ImageIcon(path+"6.png"),
		new ImageIcon(path+"7.png"),
		new ImageIcon(path+"8.png"),
		new ImageIcon(path+"flag.png"),
		new ImageIcon(path+"flag_l.png"),
		new ImageIcon(path+"normal.png"),
		new ImageIcon(path+"normal_l.png"),
		new ImageIcon(path+"question.png"),
		new ImageIcon(path+"question_l.png"),
		new ImageIcon(path+"question_0.png"),
		new ImageIcon(path+"icon.png")
	};
	public static ImageIcon getImage(int num){
		return imgs[num];
	}
}
