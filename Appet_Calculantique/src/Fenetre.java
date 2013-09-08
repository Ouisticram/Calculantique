import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Desktop;

// Class qui créée la fenêtre principale.
public class Fenetre extends JApplet{

	  /**
	 * 
	 */
	private static final long serialVersionUID = -8222532534393464715L;
	private JMenuBar menu = null;				//création d'un objet JMenuBar, bon je pense pas besoin d'expliquer (tout est dans le nom)
	  private JMenu fichier = null;				//création d'un objet JMenu, bon ba premier menu de la barre précédemment créée
	  //Créations des sous menu (objet JMenuItem)
	  private JMenuItem Baby = null;			
	  private JMenuItem Bibi = null;
	  private JMenuItem Deci = null;
	  private JMenuItem Egyp = null;
	  private JMenuItem Grec = null;
	  private JMenuItem Japo = null;
	  private JMenuItem Maya = null;
	  private JMenuItem Roma = null;
	  private JMenuItem Shad = null;
	  private JMenu apropos = null;				// création du deuxième menu de la barre de menu
	  //Créations des sous menu
	  private JMenuItem apropos1 = null;
	  private JMenuItem apropos2 = null;
	  //crétion de l'objet JPanel (en gros c'est une zone de contenu dans une fenêtre)
	  private JPanel conteneur = new JPanel();
	  // création d'une variable size de type Dimension (bon je crois pas avoir besoin d'approfondir)
	  private Dimension size;
	  
	//méthode de notre class
	public Fenetre(){
	    this.setSize(800, 600);											//setSize (qui signifie taille au cas ou)
	    this.size = new Dimension(this.getWidth(), this.getHeight());	//Bon dure à expliquer, en gros ça sert à régler l'emplacement du contenu en fonction de la taille de la fenêtre (testez de l'enlever, vous verrez !)
	    
	    menu = new JMenuBar();											//On utilise notre barre
	    
	    fichier = new JMenu("Numération");								//On créé le Menu que l'on appel Numération
	    fichier.setMnemonic('N');										//le sens du détail (j'avoue ça sert à rien mais bon) ça souligne la lettre 'N' enfin regardez en lançant !
	    
	    Baby = new JMenuItem("Babylonien");								//On créé le sous-menu nommé Babylonien
	    Baby.addActionListener(new ActionListener(){					//Là c'est l'ajout de ActionListener, c'est à dire qui gère les events lorsque l'on appuie sur une touche, ici si on click sur le sous menu Babylonien
			public void actionPerformed(ActionEvent arg0){				
				conteneur.removeAll();									//On supprime tout le contenu de notre JPanel conteneur créer plus haut
				FenBaby baby = null;
				try {
					baby = new FenBaby(size);
				} catch (FontFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}						//on ajoute le contenu de FenBaby (voir FenBaby.java)
				conteneur.add(baby.getPanel());							//on le met dans le JPanel conteneur
				conteneur.revalidate();									//On actualise la fenêtre
			}
		});
	    
	    // De même pour tout les sous menu je vais pas réécrire la même chose ...
	    Bibi = new JMenuItem("Bibinaire");
	    Bibi.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				conteneur.removeAll();
				FenBibi bibi = new FenBibi(size);
				conteneur.add(bibi.getPanel());
				conteneur.revalidate();
			}
		});
	    
	 // De même pour tout les sous menu je vais pas réécrire la même chose ...
	    Deci = new JMenuItem("Décimal");
	    Deci.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				conteneur.removeAll();
				FenDeci deci = new FenDeci(size);
				conteneur.add(deci.getPanel());
				conteneur.revalidate();
			}
		});
	    
	    Egyp = new JMenuItem("Egyptien");
	    Egyp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				conteneur.removeAll();
				FenEgyp egyp = new FenEgyp(size);
				conteneur.add(egyp.getPanel());
				conteneur.revalidate();
			}
		});
	    
	    Grec = new JMenuItem("Grec");
	    Grec.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				conteneur.removeAll();
				FenGrec grec = new FenGrec(size);
				conteneur.add(grec.getPanel());
				conteneur.revalidate();
			}
		});
	    
	    Japo = new JMenuItem("Japonais");
	    Japo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				conteneur.removeAll();
				FenJapo japo = new FenJapo(size);
				conteneur.add(japo.getPanel());
				conteneur.revalidate();
			}
		});
	    
	    Maya = new JMenuItem("Maya");
	    Maya.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				conteneur.removeAll();
				FenMaya maya = null;
				try {
					maya = new FenMaya(size);
				} catch (FontFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				conteneur.add(maya.getPanel());
				conteneur.revalidate();
			}
		});
	    
	    Roma = new JMenuItem("Romain");
	    Roma.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				conteneur.removeAll();
				FenRoma roma = new FenRoma(size);
				conteneur.add(roma.getPanel());
				conteneur.revalidate();
			}
		});
	    
	    Shad = new JMenuItem("Shadok");
	    Shad.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				conteneur.removeAll();
				FenShad shad = new FenShad(size);
				conteneur.add(shad.getPanel());
				conteneur.revalidate();
			}
		});
	    
	    //On ajoute chaque sous menu au menu fichier.
	    fichier.add(Baby);
	    fichier.add(Bibi);
	    fichier.add(Deci);
	    fichier.add(Egyp);
	    fichier.add(Grec);
	    fichier.add(Japo);
	    fichier.add(Maya);
	    fichier.add(Roma);
	    fichier.add(Shad);

	    //On passe au deuxième menu de la barre, pas grand chose qui change ... je vais commenter seulement les changements
	    apropos = new JMenu("Aide");
	    apropos.setMnemonic('A');
	    
	    apropos1 = new JMenuItem("Documentation");
	    apropos1.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    			// On vérifie que la classe Desktop soit bien supportée :
	    			if ( Desktop.isDesktopSupported() ) {
	    				// On récupère l'instance du desktop :
	    				Desktop desktop = Desktop.getDesktop();
	    			 
	    				// On vérifie que la fonction browse est bien supportée :
	    				if (desktop.isSupported(Desktop.Action.BROWSE)) {
	    			 
	    					// Et on lance l'application associé au protocole :
	    					try {
								desktop.browse(new URI("http://calculantique.legtux.org/download/Doc_calculette.pdf"));
							} catch (IOException e1) {
								e1.printStackTrace();
							} catch (URISyntaxException e1) {
								e1.printStackTrace();
							}
	    				}
	    			}
	    	}
	    });

	    //Bon là, ce n'est pas définitif (clickez sur Help puis sur about Eclipse dans la barre de menu d'Eclipse pour voir à quoi cela ressemblera à peu prêt)
	    apropos2 = new JMenuItem("À propos de Calculantique");
	    apropos2.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		JOptionPane.showMessageDialog(null,
	    		          "Créateur : Etudiants IUT Nantes\nLicence : Creative Commons\nCopyright : http://calculantique.legtux.org/",
	    		          "Informations", JOptionPane.NO_OPTION);
	    		conteneur.removeAll();
	    		conteneur.add(new FenAccueil(size).getPanel());			// on est aussi redirigé vers la page d'acceuil.
	    		conteneur.revalidate();
	    	}
	    });
	    
	    //On ajoute tout ça au menu apropos
	    apropos.add(apropos1);
	    apropos.add(apropos2);

	    //et on ajoute finalement les menu à la barre menu
	    menu.add(fichier);
	    menu.add(apropos);
	    
	    //Bon là on défini la taille, la couleur et le contenu du JPanel conteneur
	    this.conteneur.setBackground((java.awt.Color) Color(238,232,170));
	    this.conteneur.setLayout(new BorderLayout());
	    this.conteneur.add(new FenAccueil(this.size).getPanel());
	    this.setContentPane(this.conteneur);				//Bon ça c'est pour ajouter le conteneur à la fenêtre
	    	    
	    //On ajoute finalement notre barre qu'on a eu temps de mal à créer.
	    this.setJMenuBar(menu);
	}
	
	private Object Color(int i, int j, int k) {
		Color couleur = new Color(i,j,k);
		return couleur;
	}
	
	public void init(){
		//Bon ba on lance notre fenêtre et on l'affiche.
		Fenetre fen = new Fenetre();
		fen.setVisible(true);
	}
}
