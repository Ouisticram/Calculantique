import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import egyptien.*;
import fonts.*;

public class FenEgyp extends Container {
	
	private JPanel container = new JPanel();
	private JLabel chiffreTraduit = new JLabel();
	  //Tableau stockant les éléments à afficher dans la calculatrice
	String[] tab_string = { "\uD80C\uDFFA", "\uD80C\uDF86", "\uD80C\uDF62", "\uD80C\uDDBC", "\uD80C\uDCAD", "\uD80C\uDD8F", "\uD80C\uDC4F", "R", "=", "C", "+", "-", "*", "/"};
	  //Un bouton par élément à afficher
	JButton[] tab_button = new JButton[tab_string.length];
	private JTextArea ecran = new JTextArea();				//création du JTextArea (notre écran)
	private Dimension dim1 = new Dimension(80, 70);			//On déclare la dimension des touches chiffres
	private Dimension dim2 = new Dimension(65, 41);			//On déclare la dimension des touches d'opérations
	private Dimension dim3 = new Dimension(65, 50);			//On déclare la dimension de la touche effacer
	private JButton accueil = new JButton("Accueil");
	//On définit la police d'écriture à utiliser
    private Font police = LoadFonts.create();
    private Font policeText = new Font("Arial", Font.BOLD, 20);
	//déclaration de variables
	private String chiffre1;							
	private int chiffre2;
	private boolean clicOperateur = false, update = false;
	private Decitoegypt egyptien = new Decitoegypt();
	private boolean enable = false;
	private String operateur = "";
	public static int reste;
	private Dimension size;
		
	//méthode de notre class
	public FenEgyp(Dimension dim){
		super(dim);
		size = dim;
		initPanel();
	}

	public void initPanel(){		
	    //On initialise le conteneur avec tous les composants
		  initComposant();
		panel.add(container);
		panel.setBackground((java.awt.Color) Color(234,225,191));
	}
	
	//méthode qui fait appel à la class de chargement de police
		static class LoadFonts extends LoadFontEgyp{
			static Font create(){
				Font tmp = new Font("Arial", Font.BOLD, 20);
				return change(tmp);
			}
		}
	
	boolean verification (Egyptodeci egypt){
    	return egypt.validationEgypt();
    }
	
	private Object Color(int i, int j, int k) {
		Color couleur = new Color(i,j,k);
		return couleur;
	}
	
	 private void initComposant(){
		    ecran = new JTextArea("");
		    ecran.setRows(2);
		    ecran.setEditable(false);
		    ecran.setFont(police);
		    JPanel calculatrice = new JPanel();
		    JPanel operateur = new JPanel();      
		    operateur.setPreferredSize(new Dimension(100, 350));
		    JPanel reste = new JPanel();      
		    reste.setPreferredSize(new Dimension(100, 350));
		    JPanel chiffre = new JPanel();
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
		        case 7 :
		    	  tab_button[i].addActionListener(new ResteListener());
		    	  tab_button[i].setEnabled(false);
		    	  chiffre.add(tab_button[i]);
		    	  break;
		        case 8 :
		          tab_button[i].addActionListener(new EgalListener());
		          chiffre.add(tab_button[i]);
		          break;
		        case 9 :
		          tab_button[i].setForeground(Color.red);
		          tab_button[i].addActionListener(new ResetListener());
		          tab_button[i].setPreferredSize(dim3);
		          operateur.add(tab_button[i]);
		          break;
		        case 10 :
		          tab_button[i].addActionListener(new PlusListener());
		          tab_button[i].setPreferredSize(dim2);
		          operateur.add(tab_button[i]);
		          break;
		        case 11 :
		          tab_button[i].addActionListener(new MoinsListener());
		          tab_button[i].setPreferredSize(dim2);
		          operateur.add(tab_button[i]);
		          break;	
		        case 12 :	
		          tab_button[i].addActionListener(new MultiListener());
		          tab_button[i].setPreferredSize(dim2);
		          operateur.add(tab_button[i]);
		          break;
		        case 13 :
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
		    JLabel fileArianne = new JLabel(" > Egyptien");
		    fileArianne.setFont(arial);
		    footer.add(accueil);
		    footer.add(fileArianne);
		    calculatrice.setLayout(new BorderLayout());
		    calculatrice.add(panEcran, BorderLayout.NORTH);
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
		      Egyptodeci egyptien2 = new Egyptodeci(chiffre1);
		      Egyptodeci egyptien3 = new Egyptodeci(ecran.getText());
		      chiffre2 = egyptien2.convertion() + egyptien3.convertion();
		      if(!verification(egyptien3)){
		    	  ecran.setFont(policeText);
		    	  ecran.setText("Ce n'est pas un nombre egyptien !");
			  }
			  else{
				  ecran.setFont(police);
		          ecran.setText(egyptien.convertionNombre(chiffre2));
		          chiffreTraduit.setText(String.valueOf(chiffre2));}
		    }
		    if(operateur.equals("-")){
		    	Egyptodeci egyptien2 = new Egyptodeci(chiffre1);
			    Egyptodeci egyptien3 = new Egyptodeci(ecran.getText());
		      chiffre2 = egyptien2.convertion() - egyptien3.convertion();
		      if(!verification(egyptien3)){
		    	  ecran.setFont(policeText);
		    	  ecran.setText("Ce n'est pas un nombre egyptien !");
			  }
			  else if( chiffre2 == 0){
				  ecran.setFont(policeText);
                  ecran.setText("Le chiffre \"0\" n'existe pas en Egyptien !");
              }
              else if(chiffre2 < 0){
            	  ecran.setFont(policeText);
                  ecran.setText("Les nombres négatifs n'existent pas en Egyptien !");
              }
              else{
            	  ecran.setFont(police);
            	  ecran.setText(egyptien.convertionNombre(chiffre2));
            	  chiffreTraduit.setText(String.valueOf(chiffre2));
              }
		    }          
		    if(operateur.equals("*")){
		    	Egyptodeci egyptien2 = new Egyptodeci(chiffre1);
			    Egyptodeci egyptien3 = new Egyptodeci(ecran.getText());
		      chiffre2 = egyptien2.convertion() * egyptien3.convertion();
		      if(!verification(egyptien3)){
		    	  ecran.setFont(policeText);
		    	  ecran.setText("Ce n'est pas un nombre egyptien !");
			  }
			  else{
				  ecran.setFont(police);
				  ecran.setText(egyptien.convertionNombre(chiffre2));
				  chiffreTraduit.setText(String.valueOf(chiffre2));}
		    }     
		    if(operateur.equals("/")){
		      try{
		    	  Egyptodeci egyptien2 = new Egyptodeci(chiffre1);
				  Egyptodeci egyptien3 = new Egyptodeci(ecran.getText());
			      chiffre2 = egyptien2.convertion() / egyptien3.convertion();
			      reste = egyptien2.convertion() % egyptien3.convertion();
			      if(!verification(egyptien3)){
			    	  ecran.setFont(policeText);
			    	  ecran.setText("Ce n'est pas un nombre egyptien !");
				  }
				  else{
					  ecran.setFont(police);
					  ecran.setText(egyptien.convertionNombre(chiffre2));
					  chiffreTraduit.setText(String.valueOf(chiffre2));}
		      } catch(ArithmeticException e) {
		    	ecran.setFont(policeText);
		        ecran.setText("Opération impossible");
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
		      Egyptodeci egyptien = new Egyptodeci(str);
		      int chiffre = egyptien.convertion();
		      if(!verification(egyptien)){
		    	  chiffreTraduit.setText("Ce n'est pas un nombre egyptien !");
			  }
              else{		      
			      chiffreTraduit.setText(String.valueOf(chiffre));
              }
		    }
		  }
		  
		  //Listener affecté au bouton R
		  class ResteListener implements ActionListener {
			  public void actionPerformed(ActionEvent arg0){
				  ecran.setFont(police);
				  if( chiffre2 == 0){
					  ecran.setFont(policeText);
	                  ecran.setText("Le chiffre \"0\" n'existe pas en Egyptien !");
	              }
				  else{
					  ecran.setText(egyptien.convertionNombre(reste));
				  }
			  }
		  }
		  
		  //Listener affecté au bouton =
		  class EgalListener implements ActionListener {
		    public void actionPerformed(ActionEvent arg0){
		    	Egyptodeci egyptien = new Egyptodeci(ecran.getText());
		    	  ecran.setFont(police);
			    if(!verification(egyptien)){
			    	ecran.setFont(policeText);
				    ecran.setText("Ce n'est pas un nombre egyptien !");
				    update = true;
		    		clicOperateur = false;
		    		chiffre1 = "";
				}
			    else if (enable == true){
		    		calcul();
				    update = true;
				    clicOperateur = false;
		    		tab_button[7].setEnabled(true);
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
		    	Egyptodeci egyptien = new Egyptodeci(ecran.getText());
		    	ecran.setFont(police);
		      if(!verification(egyptien)){
		    	ecran.setFont(policeText);
			    ecran.setText("Ce n'est pas un nombre egyptien !");
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
		    	Egyptodeci egyptien = new Egyptodeci(ecran.getText());
		    	ecran.setFont(police);
			  if(!verification(egyptien)){
				ecran.setFont(policeText);
				ecran.setText("Ce n'est pas un nombre egyptien !");
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
		    	Egyptodeci egyptien = new Egyptodeci(ecran.getText());
		    	ecran.setFont(police);
			  if(!verification(egyptien)){
				ecran.setFont(policeText);
				ecran.setText("Ce n'est pas un nombre egyptien !");
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
		    	Egyptodeci egyptien = new Egyptodeci(ecran.getText());
		    	ecran.setFont(police);
			  if(!verification(egyptien)){
				ecran.setFont(policeText);
				ecran.setText("Ce n'est pas un nombre egyptien !");
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
		      tab_button[7].setEnabled(false);
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
