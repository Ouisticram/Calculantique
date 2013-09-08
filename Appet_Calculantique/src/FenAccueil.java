import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

//Class qui créée la page d'acceuil.
public class FenAccueil extends Container {

	//méthode de la class
	public FenAccueil(Dimension dim){
		super(dim);					//Bon ba on a vu ça en cours pas besoin d'expliquer
		initPanel();				//On appel la méthode du dessous
	}
	
	//deuxième méthode de notre classe
	public void initPanel(){
		JLabel titre = new JLabel("Bienvenue sur Calculantique\n");			//Titre de la page
		titre.setHorizontalAlignment(JLabel.CENTER);						//On le centre (ça marche très bien sans)
		titre.setFont(comics30);											//Style d'écriture
		this.panel.add(titre);												//On ajoute le titre au panel
		this.panel.setBackground((java.awt.Color) Color(212,166,89));
		
		JLabel img = new JLabel();
		ClassLoader cl = this.getClass().getClassLoader();
		img.setIcon(new ImageIcon(cl.getResource("calculatrice.png")));
		img.setHorizontalAlignment(JLabel.CENTER);
		this.panel.add(img);
		//this.panel.add(new JLabel(new ImageIcon("the-matrix-glmatrix-500x400.png")));		//On ajoute un JLabel qui contient une image
		
		//Bon ba JTextArea on va pas épiloguer la dessus (/n pour entrer donc saut de ligne et /t pour tab)
		JTextArea texte = new JTextArea(	"\n" +
											"\t« S'il n'y a pas de solution,\n" +
											"\tc'est qu'il n'y a pas de problème »\n" +
											"\t\t     devise shadok");
		texte.setFont(arial);                                       //Style d'écriture
		texte.setBackground((java.awt.Color) Color(212,166,89));
		texte.setEditable(false);									//Non éditable
		this.panel.add(texte);										//On ajoute le texte dans le panel
	}

	private Object Color(int i, int j, int k) {
		Color couleur = new Color(i,j,k);
		return couleur;
	}
}
