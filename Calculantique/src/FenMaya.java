import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import maya.*;
import fonts.*;

public class FenMaya extends Container {
	
	private JPanel container = new JPanel();
	private JLabel chiffreTraduit = new JLabel();
	  //Tableau stockant les éléments à afficher dans la calculatrice
	String[] tab_string = {"\uF7FB", ".", "-", "\n", "R", "=", "C", "+", "-", "*", "/"};
	  //Un bouton par élément à afficher
	JButton[] tab_button = new JButton[tab_string.length];
	private JTextArea ecran = new JTextArea();				//création du JTextArea (notre écran)
	private Dimension dim1 = new Dimension(65, 50);			//On déclare la dimension des touches chiffres
	private Dimension dim2 = new Dimension(65, 41);			//On déclare la dimension des touches d'opérations
	private JButton accueil = new JButton("Accueil");
	//On définit la police d'écriture à utiliser
    private Font police = LoadFonts.create();
    private Font policeText = new Font("Arial", Font.BOLD, 20);
	//déclaration de variables
	private String chiffre1;							
	private int chiffre2;
	Maya maya = new Maya();
	private boolean clicOperateur = false, update = false;
	private boolean enable = false;
	private String operateur = "";
	public static int reste;
	private Dimension size;
		
	//méthode de notre class
	public FenMaya(Dimension dim) throws FontFormatException, IOException{
		super(dim);
		size = dim;
		initPanel();
	}

	public void initPanel() throws FontFormatException, IOException{		
	    //On initialise le conteneur avec tous les composants
		  initComposant();
		panel.add(container);
		panel.setBackground((java.awt.Color) Color(234,225,191));		
	}
	
	//méthode qui fait appel à la class de chargement de police
	static class LoadFonts extends LoadFontMaya{
		static Font create(){
			Font tmp = new Font("Arial", Font.BOLD, 20);
			return change(tmp);
		}
	}
	
	boolean verification (MayaDeci maya){
    	return maya.validMaya();
    }
	
	private Object Color(int i, int j, int k) {
		Color couleur = new Color(i,j,k);
		return couleur;
	}
	
	 private void initComposant() throws FontFormatException, IOException{
		    Font policeEspace = new Font("Courier", Font.BOLD, 20);
		    ecran = new JTextArea("");
		    ecran.setRows(3);
		    ecran.setColumns(10);
		    ecran.setEditable(false);
		    ecran.setFont(police);
		    JPanel calculatrice = new JPanel();
		    JPanel operateur = new JPanel();
		    operateur.setPreferredSize(new Dimension(100, 350));
		    JPanel reste = new JPanel();      
		    reste.setPreferredSize(new Dimension(100, 350));
		    JPanel chiffre = new JPanel();
		    chiffre.setFont(police);
		    chiffre.setPreferredSize(new Dimension(260, 350));
		    JPanel panEcran = new JPanel();
		    JPanel header = new JPanel();
		    JPanel footer = new JPanel();

		    //On parcourt le tableau initialisé
		    //afin de créer nos boutons
		    for(int i = 0; i < tab_string.length; i++){
		      tab_button[i] = new JButton(tab_string[i]);
		      tab_button[i].setPreferredSize(dim1);
		      switch(i){
		        //Pour chaque élément situé à la fin du tableau
		        //et qui n'est pas un chiffre
		        //on définit le comportement à avoir grâce à un listener
		        case 3:
		        	tab_button[i] = new JButton("\u21B5");
		        	tab_button[i].setPreferredSize(dim1);
		        	tab_button[i].setFont(policeEspace);
			        chiffre.add(tab_button[i]);
			        tab_button[i].addActionListener(new RetourLigneListener());
				    break;
		        case 4 :
		    	  tab_button[i].addActionListener(new ResteListener());
		    	  tab_button[i].setEnabled(false);
		    	  chiffre.add(tab_button[i]);
		    	  break;
		        case 5 :
		          tab_button[i].addActionListener(new EgalListener());
		          chiffre.add(tab_button[i]);
		          break;
		        case 6 :
		          tab_button[i].setForeground(Color.red);
		          tab_button[i].addActionListener(new ResetListener());
		          operateur.add(tab_button[i]);
		          break;
		        case 7 :
		          tab_button[i].addActionListener(new PlusListener());
		          tab_button[i].setPreferredSize(dim2);
		          operateur.add(tab_button[i]);
		          break;
		        case 8 :
		          tab_button[i].addActionListener(new MoinsListener());
		          tab_button[i].setPreferredSize(dim2);
		          operateur.add(tab_button[i]);
		          break;	
		        case 9 :	
		          tab_button[i].addActionListener(new MultiListener());
		          tab_button[i].setPreferredSize(dim2);
		          operateur.add(tab_button[i]);
		          break;
		        case 10 :
		          tab_button[i].addActionListener(new DivListener());
		          tab_button[i].setPreferredSize(dim2);
		          operateur.add(tab_button[i]);
		          break;
		        default :
		          //Par défaut, ce sont les premiers éléments du tableau
		          //donc des chiffres, on affecte alors le bon listener
		          tab_button[i].setFont(police);
		          chiffre.add(tab_button[i]);
		          tab_button[i].addActionListener(new ChiffreListener());
		          break;
		      }
		    }
		    
		    JScrollPane scrollArea = new JScrollPane(ecran);
		    panEcran.setLayout(new BorderLayout());
		    panEcran.add(scrollArea);
		    JLabel egalite = new JLabel(" = ");
		    egalite.setFont(arial);
		    chiffreTraduit = new JLabel("");
		    chiffreTraduit.setFont(arial);
		    chiffreTraduit.setBackground((java.awt.Color) Color(234,225,191));
		    header.add(egalite);
		    header.add(chiffreTraduit);
		    accueil.addActionListener(new PageAccueil());
		    JLabel fileArianne = new JLabel(" > Maya");
		    fileArianne.setFont(arial);
		    footer.add(accueil);
		    footer.add(fileArianne);
		    calculatrice.setLayout(new BorderLayout());
		    calculatrice.add(panEcran, BorderLayout.WEST);
		    panEcran.setBackground(Color.white);
		    calculatrice.add(chiffre, BorderLayout.CENTER);
		    chiffre.setBackground((java.awt.Color) Color(234,225,191));
		    calculatrice.add(operateur, BorderLayout.EAST);
		    operateur.setBackground((java.awt.Color) Color(234,225,191));
		    container.setLayout(new BorderLayout());		//déclaration de l'utilisation du BorderLayout (ATTENTION ! sans ça les BorderLayout ne fonctionnent pas !)
		    container.add(header, BorderLayout.NORTH);
		    header.setBackground((java.awt.Color) Color(234,225,191));
		    container.add(calculatrice, BorderLayout.CENTER);
		    container.add(footer, BorderLayout.SOUTH);
		    footer.setBackground((java.awt.Color) Color(234,225,191));
		  }

		  //Méthode permettant d'effectuer un calcul selon l'opérateur sélectionné
		  private void calcul(){
		    if(operateur.equals("+")){
		    	MayaDeci maya1 = new MayaDeci(chiffre1);
		    	MayaDeci maya2 = new MayaDeci(ecran.getText());
                chiffre2 = maya1.convertDeci() + maya2.convertDeci();
  		        if(!verification(maya2)){
  		        	ecran.setFont(policeText);
		    	    ecran.setText("Ce n'est pas un nombre maya !");
			    }
			    else if( chiffre2 > 3200000){
			    	ecran.setFont(policeText);
                    ecran.setText("Vous avez depasse la limite des nombres maya !");
                }
                else{
                	ecran.setFont(police);
                	ecran.setText(maya.convertNombre(chiffre2));
                	chiffreTraduit.setText(String.valueOf(chiffre2));}
		    }
		    if(operateur.equals("-")){                
                MayaDeci maya1 = new MayaDeci(chiffre1);
		    	MayaDeci maya2 = new MayaDeci(ecran.getText());
                chiffre2 = maya1.convertDeci() - maya2.convertDeci();
                if(!verification(maya2)){
                	ecran.setFont(policeText);
		    	    ecran.setText("Ce n'est pas un nombre maya !");
			    }
			    else if( chiffre2 > 3200000){
			    	ecran.setFont(policeText);
                    ecran.setText("Vous avez depasse la limite des nombres maya !");
                }
                else if(chiffre2 < 0){
                	ecran.setFont(policeText);
                    ecran.setText("Les nombres negatifs n'existent pas en maya !");
                }
                else{
                	ecran.setFont(police);
                	ecran.setText(maya.convertNombre(chiffre2));
                	chiffreTraduit.setText(String.valueOf(chiffre2));}
		    }          
		    if(operateur.equals("*")){
                MayaDeci maya1 = new MayaDeci(chiffre1);
		    	MayaDeci maya2 = new MayaDeci(ecran.getText());
                chiffre2 = maya1.convertDeci() * maya2.convertDeci();
                if(!verification(maya2)){
                	ecran.setFont(policeText);
		    	    ecran.setText("Ce n'est pas un nombre maya !");
			    }
			    else if( chiffre2 > 3200000){
			    	ecran.setFont(policeText);
                    ecran.setText("Vous avez depasse la limite des nombres maya !");
                }
                else{
                	ecran.setFont(police);
                	ecran.setText(maya.convertNombre(chiffre2));
                	chiffreTraduit.setText(String.valueOf(chiffre2));}
		    }     
		    if(operateur.equals("/")){
		      try{
		        MayaDeci maya1 = new MayaDeci(chiffre1);
		    	MayaDeci maya2 = new MayaDeci(ecran.getText());
                chiffre2 = maya1.convertDeci() / maya2.convertDeci();
		        reste = maya1.convertDeci() % maya2.convertDeci();
		        if(!verification(maya2)){
		        	ecran.setFont(policeText);
		    	    ecran.setText("Ce n'est pas un nombre maya !");
			    }
			    else if( chiffre2 > 3200000){
			    	ecran.setFont(policeText);
                    ecran.setText("Vous avez depasse la limite des nombres maya !");
                }
                else{
                	ecran.setFont(police);
                	ecran.setText(maya.convertNombre(chiffre2));
                	chiffreTraduit.setText(String.valueOf(chiffre2));}
		      } catch(ArithmeticException e) {
		    	  ecran.setFont(policeText);
		    	  ecran.setText("Operation impossible");
		      }
		    }
		  }
		  
		  class PageAccueil implements ActionListener {
			  public void actionPerformed(ActionEvent e){
				  panel.removeAll();
				  panel.setBackground((java.awt.Color) Color(238,232,170));
				  panel.setLayout(new BorderLayout());
				  panel.add(new FenAccueil(size).getPanel());
				  panel.revalidate();
			  }
		  }

		//Listener utilisé pour les chiffres
		  //Permet de stocker les chiffres et de les afficher
		  class ChiffreListener implements ActionListener {
		    public void actionPerformed(ActionEvent e){
		      if(ecran.getFont() == policeText){
			    	ecran.setFont(police);
			  }
		      //On affiche le chiffre additionnel dans le label
		      String str = ((JButton)e.getSource()).getText();
		      if(update){
		        update = false;
		      }
		      else{
		        if(!ecran.getText().equals(""))
		          str = ecran.getText() + str;
		      }
		      ecran.setText(str);
		      MayaDeci maya = new MayaDeci(str);
		      int chiffre = maya.convertDeci();
		      if(!verification(maya)){
		    	  chiffreTraduit.setText("Ce n'est pas un nombre maya !");
		      }
		      else if( chiffre > 3200000){
		    	  chiffreTraduit.setText("Vous avez depasse la limite des nombres maya !");
		      }
              else{		      
			      chiffreTraduit.setText(String.valueOf(chiffre));
              }
		    }
		  }
		  
		  //Listener affecté au bouton retour à la ligne
		  class RetourLigneListener implements ActionListener {
				public void actionPerformed(ActionEvent e){
					String str = "\n";
					if(!ecran.getText().equals("")){
						str = ecran.getText() + str;
					}
					ecran.setText(str);
				}
			}
		  
		  //Listener affecté au bouton R
		  class ResteListener implements ActionListener {
			  public void actionPerformed(ActionEvent arg0){
				  ecran.setText(maya.convertNombre(reste));
			  }
		  }
		  
		  //Listener affecté au bouton =
		  class EgalListener implements ActionListener {
		    public void actionPerformed(ActionEvent arg0){
		    	  MayaDeci maya = new MayaDeci(ecran.getText());
		    	  ecran.setFont(police);
			    if(!verification(maya)){
			    	ecran.setFont(policeText);
				    ecran.setText("Ce n'est pas un nombre babylonien !");
				    update = true;
		    		clicOperateur = false;
		    		chiffre1 = "";
				}
			    else if (enable == true){
		    		calcul();
				    update = true;
				    clicOperateur = false;
		    		tab_button[4].setEnabled(true);
		    		chiffre1 = "";
		    	}
		    	else
		    	{
		    		calcul();
		    		update = true;
		    		clicOperateur = false;
		    		chiffre1 = "";
		    	}
		    }
		  }

		  //Listener affecté au bouton +
		  class PlusListener implements ActionListener {
		    public void actionPerformed(ActionEvent arg0){
		    	MayaDeci maya = new MayaDeci(ecran.getText());
		    	ecran.setFont(police);
		      if(!verification(maya)){
		    	  ecran.setFont(policeText);
		    	  ecran.setText("Ce n'est pas un nombre maya !");
			  }
			  else if(clicOperateur){
		        calcul();
		        ecran.setText(chiffre1);
		      }
		      else{
		        chiffre1 = ecran.getText();
		        clicOperateur = true;
		      }
		      operateur = "+";
		      update = true;
		    }
		  }

		  //Listener affecté au bouton -
		  class MoinsListener implements ActionListener {
		    public void actionPerformed(ActionEvent arg0){
		    	MayaDeci maya = new MayaDeci(ecran.getText());
		    	ecran.setFont(police);
			  if(!verification(maya)){
				  ecran.setFont(policeText);
				  ecran.setText("Ce n'est pas un nombre maya !");
			  }
			  else if(clicOperateur){
		        calcul();
		        ecran.setText(chiffre1);
		      }
		      else{
		        chiffre1 = ecran.getText();
		        clicOperateur = true;
		      }
		      operateur = "-";
		      update = true;
		    }
		  }

		  //Listener affecté au bouton *
		  class MultiListener implements ActionListener {
		    public void actionPerformed(ActionEvent arg0){
		    	MayaDeci maya = new MayaDeci(ecran.getText());
		    	ecran.setFont(police);
			  if(!verification(maya)){
				  ecran.setFont(policeText);
				  ecran.setText("Ce n'est pas un nombre maya !");
			  }
			  else if(clicOperateur){
		        calcul();
		        ecran.setText(chiffre1);
		      }
		      else{
		        chiffre1 = ecran.getText();
		        clicOperateur = true;
		      }
		      operateur = "*";
		      update = true;
		    }
		  }

		  //Listener affecté au bouton /
		  class DivListener implements ActionListener {
		    public void actionPerformed(ActionEvent arg0){
		    	MayaDeci maya = new MayaDeci(ecran.getText());
		    	ecran.setFont(police);
			  if(!verification(maya)){
				  ecran.setFont(policeText);
				  ecran.setText("Ce n'est pas un nombre maya !");
			  }
			  else if(clicOperateur){
		        calcul();
		        ecran.setText(chiffre1);
		      }
		      else{
		        chiffre1 = ecran.getText();
		        clicOperateur = true;
		        enable = true;
		      }
		      operateur = "/";
		      update = true;
		    }
		  }

		  //Listener affecté au bouton de remise à zéro
		  class ResetListener implements ActionListener {
		    public void actionPerformed(ActionEvent arg0){
		      ecran.setFont(police);
		      clicOperateur = false;
		      reste =  0;
		      tab_button[4].setEnabled(false);
		      update = true;
		      enable = false;
		      chiffre2 = 0;
		      chiffre1 = "";
		      operateur = "";
		      ecran.setText("");
		      chiffreTraduit.setText("");
		    }
		  }
}