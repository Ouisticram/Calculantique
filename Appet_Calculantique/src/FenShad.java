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
import shadok.*;
import t2s.son.LecteurTexte;

public class FenShad extends Container {
	
	private JPanel container = new JPanel();
	  //Tableau stockant les �l�ments � afficher dans la calculatrice
	String[] tab_string = {"GA", "BU", "ZO", "MEU", "R", "=", "C", "+", "-", "*", "/"};
	  //Un bouton par �l�ment � afficher
	JButton[] tab_button = new JButton[tab_string.length];
	JButton lecteur;
	private JTextArea ecran = new JTextArea();				//cr�ation du JTextArea (notre �cran)
	private Dimension dim1 = new Dimension(60, 50);			//On d�clare la dimension des touches chiffres
	private Dimension dim2 = new Dimension(60, 41);			//On d�clare la dimension des touches d'op�rations
	private Dimension dim3 = new Dimension(46, 44);			//On d�clare la dimension de la touche play
	//d�claration de variables
	private String chiffre1;							
	private long chiffre2;
	private boolean clicOperateur = false, update = false;
	private boolean enable = false;
	private String operateur = "";
	public static long reste;
	private String tmp = "";
	private LecteurTexte lt;
		
	//m�thode de notre class
	public FenShad(Dimension dim){
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
		    Font policeT = new Font("Arial", Font.BOLD, 12);
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
		          //Par d�faut, ce sont les premiers �l�ments du tableau
		          //donc des chiffres, on affecte alors le bon listener
		          chiffre.add(tab_button[i]);
		          tab_button[i].setFont(policeT);
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
		      Shadok mot1 = new Shadok("");
		      Shadok mot2 = new Shadok("");
		      chiffre2 = mot1.convDec(chiffre1) + mot2.convDec(ecran.getText());
		      Decimal nombre = new Decimal(0);
		      ecran.setText(nombre.ConvSha(chiffre2));
		      tmp = nombre.ConvSha(chiffre2);
		    }
		    if(operateur.equals("-")){
		      Shadok mot1 = new Shadok("");
		      Shadok mot2 = new Shadok("");
		      chiffre2 = mot1.convDec(chiffre1) - mot2.convDec(ecran.getText());
		      Decimal nombre = new Decimal(0);
		      if (chiffre2 < 0){
		    	  ecran.setText("Les nombres n�gatifs n'existent pas en shadock !");
		    	  tmp = "Les nombres n�gatifs n'existent pas en shadock !";
		      }
		      else{
			      ecran.setText(nombre.ConvSha(chiffre2));
			      tmp = nombre.ConvSha(chiffre2);}
		    }          
		    if(operateur.equals("*")){
		    	Shadok mot1 = new Shadok("");
			    Shadok mot2 = new Shadok("");
			    chiffre2 = mot1.convDec(chiffre1) * mot2.convDec(ecran.getText());
			    Decimal nombre = new Decimal(0);
			    ecran.setText(nombre.ConvSha(chiffre2));
			    tmp = nombre.ConvSha(chiffre2);
		    }     
		    if(operateur.equals("/")){
		      try{
		    	Shadok mot1 = new Shadok("");
			    Shadok mot2 = new Shadok("");
			    chiffre2 = mot1.convDec(chiffre1) / mot2.convDec(ecran.getText());
			    reste = mot1.convDec(chiffre1) % mot2.convDec(ecran.getText());
			    Decimal nombre = new Decimal(0);
			    ecran.setText(nombre.ConvSha(chiffre2));
			    tmp = nombre.ConvSha(chiffre2);
		      } catch(ArithmeticException e) {
		        ecran.setText("Op�ration impossible");
		        tmp = "op�ration impossible";
		      }
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
				  Decimal nombre = new Decimal(0);
				  ecran.setText(nombre.ConvSha(reste));
				  tmp = nombre.ConvSha(reste);
			  }
		  }
		  
		//Listener affect� au bouton lecture
		  class LectureListener implements ActionListener {
			  public void actionPerformed(ActionEvent arg0){
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
		    		tab_button[4].setEnabled(true);
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
		      tab_button[4].setEnabled(false);
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