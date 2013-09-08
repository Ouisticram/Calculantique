import javax.swing.*;
import java.awt.*;

public abstract class Container{
	// je créé un JPanel et 4 styles d'écriture (utiliser dans Fenetre.java)
	protected JPanel panel;
	protected Font comics30 = new Font("Comics Sans MS", Font.BOLD, 30);
	protected Font comics40 = new Font("Comics Sans MS", Font.BOLD, 40);
	protected Font comics20 = new Font("Comics Sans MS", Font.BOLD, 20);
	protected Font arial = new Font("Arial", Font.BOLD, 15);
	
	//methode de notre class
	public Container(Dimension dim){
		this.panel = new JPanel();
		this.panel.setPreferredSize(dim);
		this.panel.setBackground(Color.white);		
	}
	
	//getPanel utiliser dans Fenetre.java
	protected JPanel getPanel(){
		return this.panel;
	}
}
