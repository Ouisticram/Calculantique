import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import bibinaire.*;

import t2s.son.LecteurTexte;

public class FenBibi extends Container {
	
	private JPanel container = new JPanel();
	  //Tableau stockant les �l�ments � afficher dans la calculatrice
	String[] tab_string = {"HO", "HA", "HE", "HI", "BO", "BA", "BE", "BI", "KO", "KA", "KE", "KI", "DO", "DA", "DE", "DI", "R", "=", "C", "+", "-", "*", "/"};
	  //Un bouton par �l�ment � afficher
	JButton[] tab_button = new JButton[tab_string.length];
	JButton lecteur;
	private JTextArea ecran = new JTextArea();				//cr�ation du JTextArea (notre �cran)
	private Dimension dim1 = new Dimension(60, 50);			//On d�clare la dimension des touches chiffres
	private Dimension dim2 = new Dimension(60, 41);			//On d�clare la dimension des touches d'op�rations
	private Dimension dim3 = new Dimension(46, 44);			//On d�clare la dimension de la touche play
	//d�claration de variables
	private String chiffre1;							
	private int chiffre2;
	private boolean clicOperateur = false, update = false;
	private boolean enable = false;
	private String operateur = "";
	public static int reste;
	private String tmp = "";
	private LecteurTexte lt;
		
	//m�thode de notre class
	public FenBibi(Dimension dim){
		super(dim);
		initPanel();
	}

	public void initPanel(){		
	    //On initialise le conteneur avec tous les composants
		  initComposant();
		panel.add(container);
		panel.setBackground((java.awt.Color) Color(234,225,191));
	}
	
	private Object Color(int i, int j, int k) {
		Color couleur = new Color(i,j,k);
		return couleur;
	}
	
	 private void initComposant(){
		    //On d�finit la police d'�criture � utiliser
		    Font police = new Font("Arial", Font.BOLD, 20);
		    ecran = new JTextArea("");
		    ecran.setRows(2);
		    ecran.setEditable(false);
		    ecran.setFont(police);
		    JPanel operateur = new JPanel();      
		    operateur.setPreferredSize(new Dimension(100, 350));
		    JPanel reste = new JPanel();      
		    reste.setPreferredSize(new Dimension(100, 350));
		    JPanel chiffre = new JPanel();
		    chiffre.setPreferredSize(new Dimension(260, 350));
		    JPanel panEcran = new JPanel();
		    

		    //On parcourt le tableau initialis�
		    //afin de cr�er nos boutons
		    for(int i = 0; i < tab_string.length; i++){
		      tab_button[i] = new JButton(tab_string[i]);
		      tab_button[i].setPreferredSize(dim1);
		      switch(i){
		        //Pour chaque �l�ment situ� � la fin du tableau
		        //et qui n'est pas un chiffre
		        //on d�finit le comportement � avoir gr�ce � un listener
		        case 16 :
		    	  tab_button[i].addActionListener(new ResteListener());
		    	  tab_button[i].setEnabled(false);
		    	  chiffre.add(tab_button[i]);
		    	  break;
		        case 17 :
		          tab_button[i].addActionListener(new EgalListener());
		          chiffre.add(tab_button[i]);
		          break;
		        case 18 :
		          tab_button[i].setForeground(Color.red);
		          tab_button[i].addActionListener(new ResetListener());
		          operateur.add(tab_button[i]);
		          break;
		        case 19 :
		          tab_button[i].addActionListener(new PlusListener());
		          tab_button[i].setPreferredSize(dim2);
		          operateur.add(tab_button[i]);
		          break;
		        case 20 :
		          tab_button[i].addActionListener(new MoinsListener());
		          tab_button[i].setPreferredSize(dim2);
		          operateur.add(tab_button[i]);
		          break;	
		        case 21 :	
		          tab_button[i].addActionListener(new MultiListener());
		          tab_button[i].setPreferredSize(dim2);
		          operateur.add(tab_button[i]);
		          break;
		        case 22 :
		          tab_button[i].addActionListener(new DivListener());
		          tab_button[i].setPreferredSize(dim2);
		          operateur.add(tab_button[i]);
		          break;
		        default :
		          //Par d�faut, ce sont les premiers �l�ments du tableau
		          //donc des chiffres, on affecte alors le bon listener
		          chiffre.add(tab_button[i]);
		          tab_button[i].addActionListener(new ChiffreListener());
		          break;
		      }
		    }
		    
		    ClassLoader cl = this.getClass().getClassLoader();
		    lecteur = new JButton(new ImageIcon(cl.getResource("play.png")));		    
		    lecteur.addActionListener(new LectureListener());
		    lecteur.setPreferredSize(dim3);
		    lecteur.setEnabled(false);	    
	        operateur.add(lecteur);
	        JScrollPane scrollArea = new JScrollPane(ecran);
	        panEcran.setLayout(new BorderLayout());
		    panEcran.add(scrollArea);
		    //panEcran.setBorder(BorderFactory.createLineBorder(Color.black));
		    container.setLayout(new BorderLayout());		//d�claration de l'utilisation du BorderLayout (ATTENTION ! sans �a les BorderLayout ne fonctionnent pas !)
		    container.add(panEcran, BorderLayout.NORTH);	//On place l'�cran en haut 
		    panEcran.setBackground(Color.white);
		    container.add(chiffre, BorderLayout.CENTER);	//On place les chiffres au centre
		    chiffre.setBackground((java.awt.Color) Color(234,225,191));
		    container.add(operateur, BorderLayout.EAST);	// et les op�rateurs � droite
		    operateur.setBackground((java.awt.Color) Color(234,225,191));
		  }

		  //M�thode permettant d'effectuer un calcul selon l'op�rateur s�lectionn�
		  private void calcul(){
		    if(operateur.equals("+")){
		      chiffre2 = Bitode.convert(chiffre1) + 
		    		  Bitode.convert(ecran.getText());
		      ecran.setText(Detobi.convert(chiffre2));
		      tmp = Detobi.convert(chiffre2);
		    }
		    if(operateur.equals("-")){
		      chiffre2 = Bitode.convert(chiffre1) - 
		    		  Bitode.convert(ecran.getText());
		      ecran.setText(Detobi.convert(chiffre2));
		      tmp = Detobi.convert(chiffre2);
		    }          
		    if(operateur.equals("*")){
		      chiffre2 = Bitode.convert(chiffre1) * 
		    		  Bitode.convert(ecran.getText());
		      ecran.setText(Detobi.convert(chiffre2));
		      tmp = Detobi.convert(chiffre2);
		    }     
		    if(operateur.equals("/")){
		      try{
		        chiffre2 = Bitode.convert(chiffre1) / 
		        		Bitode.convert(ecran.getText());
		        reste = Bitode.convert(chiffre1) % 
		        		Bitode.convert(ecran.getText());
		        tmp = Detobi.convert(chiffre2);
		        ecran.setText(Detobi.convert(chiffre2));
		      } catch(ArithmeticException e) {
		        ecran.setText("Op�ration impossible");
		        tmp = "Op�ration impossible";
		      }
		    }
		  }

		//m�thode qui fait appel � notre class de conversion
		static class Detobi extends Decitobibi{
			static String convert(long chiffre){
			return Decitobibi.Main(chiffre);
			}
		}

		//m�thode qui fait appel � notre class de conversion
		static class Bitode extends Bibitodeci{
			static int convert(String bb){
			return Bibitodeci.Main(bb);
			}
		}

		//Listener utilis� pour les chiffres
		  //Permet de stocker les chiffres et de les afficher
		  class ChiffreListener implements ActionListener {
		    public void actionPerformed(ActionEvent e){
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
		      tmp = str;
		    }
		  }
		  
		  //Listener affect� au bouton R
		  class ResteListener implements ActionListener {
			  public void actionPerformed(ActionEvent arg0){
				  ecran.setText(Detobi.convert(reste));
				  tmp = Detobi.convert(reste);
			  }
		  }
		  
		//Listener affect� au bouton lecture
		  class LectureListener implements ActionListener {
			  public void actionPerformed(ActionEvent arg0){
				 //LecteurTexte.class.getClassLoader().getResource("adresse/de/la/ressource/dans/le/jar.lextensio"); 
				  lt = new LecteurTexte(tmp);
			      lt.playAll();
			  }
		  }
		  
		  //Listener affect� au bouton =
		  class EgalListener implements ActionListener {
		    public void actionPerformed(ActionEvent arg0){
		    	if (enable == true){
		    		calcul();
				    update = true;
				    clicOperateur = false;
		    		tab_button[16].setEnabled(true);
		    		lecteur.setEnabled(true);
		    		chiffre1 = "";
		    	}
		    	else
		    	{
		    		calcul();
		    		update = true;
		    		clicOperateur = false;
		    		lecteur.setEnabled(true);
		    		chiffre1 = "";
		    	}
		    }
		  }

		  //Listener affect� au bouton +
		  class PlusListener implements ActionListener {
		    public void actionPerformed(ActionEvent arg0){
		      if(clicOperateur){
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

		  //Listener affect� au bouton -
		  class MoinsListener implements ActionListener {
		    public void actionPerformed(ActionEvent arg0){
		      if(clicOperateur){
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

		  //Listener affect� au bouton *
		  class MultiListener implements ActionListener {
		    public void actionPerformed(ActionEvent arg0){
		      if(clicOperateur){
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

		  //Listener affect� au bouton /
		  class DivListener implements ActionListener {
		    public void actionPerformed(ActionEvent arg0){
		      if(clicOperateur){
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

		  //Listener affect� au bouton de remise � z�ro
		  class ResetListener implements ActionListener {
		    public void actionPerformed(ActionEvent arg0){
		      clicOperateur = false;
		      reste =  0;
		      tab_button[16].setEnabled(false);
		      lecteur.setEnabled(false);
		      update = true;
		      enable = false;
		      chiffre2 = 0;
		      tmp = "";
		      chiffre1 = "";
		      operateur = "";
		      ecran.setText("");
		    }
		  }
}
