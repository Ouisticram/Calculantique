import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Desktop;

// Class qui cr��e la fen�tre principale.
public class Fenetre extends JApplet{

	  /**
	 * 
	 */
	private static final long serialVersionUID = -8222532534393464715L;
	private JMenuBar menu = null;				//cr�ation d'un objet JMenuBar, bon je pense pas besoin d'expliquer (tout est dans le nom)
	  private JMenu fichier = null;				//cr�ation d'un objet JMenu, bon ba premier menu de la barre pr�c�demment cr��e
	  //Cr�ations des sous menu (objet JMenuItem)
	  private JMenuItem Baby = null;			
	  private JMenuItem Bibi = null;
	  private JMenuItem Deci = null;
	  private JMenuItem Egyp = null;
	  private JMenuItem Grec = null;
	  private JMenuItem Japo = null;
	  private JMenuItem Maya = null;
	  private JMenuItem Roma = null;
	  private JMenuItem Shad = null;
	  private JMenu apropos = null;				// cr�ation du deuxi�me menu de la barre de menu
	  //Cr�ations des sous menu
	  private JMenuItem apropos1 = null;
	  private JMenuItem apropos2 = null;
	  //cr�tion de l'objet JPanel (en gros c'est une zone de contenu dans une fen�tre)
	  private JPanel conteneur = new JPanel();
	  // cr�ation d'une variable size de type Dimension (bon je crois pas avoir besoin d'approfondir)
	  private Dimension size;
	  
	//m�thode de notre class
	public Fenetre(){
	    this.setSize(800, 600);											//setSize (qui signifie taille au cas ou)
	    this.size = new Dimension(this.getWidth(), this.getHeight());	//Bon dure � expliquer, en gros �a sert � r�gler l'emplacement du contenu en fonction de la taille de la fen�tre (testez de l'enlever, vous verrez !)
	    
	    menu = new JMenuBar();											//On utilise notre barre
	    
	    fichier = new JMenu("Num�ration");								//On cr�� le Menu que l'on appel Num�ration
	    fichier.setMnemonic('N');										//le sens du d�tail (j'avoue �a sert � rien mais bon) �a souligne la lettre 'N' enfin regardez en lan�ant !
	    
	    Baby = new JMenuItem("Babylonien");								//On cr�� le sous-menu nomm� Babylonien
	    Baby.addActionListener(new ActionListener(){					//L� c'est l'ajout de ActionListener, c'est � dire qui g�re les events lorsque l'on appuie sur une touche, ici si on click sur le sous menu Babylonien
			public void actionPerformed(ActionEvent arg0){				
				conteneur.removeAll();									//On supprime tout le contenu de notre JPanel conteneur cr�er plus haut
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
				conteneur.revalidate();									//On actualise la fen�tre
			}
		});
	    
	    // De m�me pour tout les sous menu je vais pas r��crire la m�me chose ...
	    Bibi = new JMenuItem("Bibinaire");
	    Bibi.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				conteneur.removeAll();
				FenBibi bibi = new FenBibi(size);
				conteneur.add(bibi.getPanel());
				conteneur.revalidate();
			}
		});
	    
	 // De m�me pour tout les sous menu je vais pas r��crire la m�me chose ...
	    Deci = new JMenuItem("D�cimal");
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

	    //On passe au deuxi�me menu de la barre, pas grand chose qui change ... je vais commenter seulement les changements
	    apropos = new JMenu("Aide");
	    apropos.setMnemonic('A');
	    
	    apropos1 = new JMenuItem("Documentation");
	    apropos1.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    			// On v�rifie que la classe Desktop soit bien support�e :
	    			if ( Desktop.isDesktopSupported() ) {
	    				// On r�cup�re l'instance du desktop :
	    				Desktop desktop = Desktop.getDesktop();
	    			 
	    				// On v�rifie que la fonction browse est bien support�e :
	    				if (desktop.isSupported(Desktop.Action.BROWSE)) {
	    			 
	    					// Et on lance l'application associ� au protocole :
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

	    //Bon l�, ce n'est pas d�finitif (clickez sur Help puis sur about Eclipse dans la barre de menu d'Eclipse pour voir � quoi cela ressemblera � peu pr�t)
	    apropos2 = new JMenuItem("� propos de Calculantique");
	    apropos2.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		JOptionPane.showMessageDialog(null,
	    		          "Cr�ateur : Etudiants IUT Nantes\nLicence : Creative Commons\nCopyright : http://calculantique.legtux.org/",
	    		          "Informations", JOptionPane.NO_OPTION);
	    		conteneur.removeAll();
	    		conteneur.add(new FenAccueil(size).getPanel());			// on est aussi redirig� vers la page d'acceuil.
	    		conteneur.revalidate();
	    	}
	    });
	    
	    //On ajoute tout �a au menu apropos
	    apropos.add(apropos1);
	    apropos.add(apropos2);

	    //et on ajoute finalement les menu � la barre menu
	    menu.add(fichier);
	    menu.add(apropos);
	    
	    //Bon l� on d�fini la taille, la couleur et le contenu du JPanel conteneur
	    this.conteneur.setBackground((java.awt.Color) Color(238,232,170));
	    this.conteneur.setLayout(new BorderLayout());
	    this.conteneur.add(new FenAccueil(this.size).getPanel());
	    this.setContentPane(this.conteneur);				//Bon �a c'est pour ajouter le conteneur � la fen�tre
	    	    
	    //On ajoute finalement notre barre qu'on a eu temps de mal � cr�er.
	    this.setJMenuBar(menu);
	}
	
	private Object Color(int i, int j, int k) {
		Color couleur = new Color(i,j,k);
		return couleur;
	}
	
	public void init(){
		//Bon ba on lance notre fen�tre et on l'affiche.
		Fenetre fen = new Fenetre();
		fen.setVisible(true);
	}
}
