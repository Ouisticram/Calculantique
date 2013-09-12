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
import romain.*;

public class FenRoma extends Container {
	
	private JPanel container = new JPanel();
	private JLabel chiffreTraduit = new JLabel();
	  //Tableau stockant les �l�ments � afficher dans la calculatrice
	String[] tab_string = {"\u2160", "\u2164", "\u2169", "\u216C", "\u216D", "\u216E", "\u216F", "R", "=", "C", "+", "-", "*", "/"};
	  //Un bouton par �l�ment � afficher
	JButton[] tab_button = new JButton[tab_string.length];
	private JTextArea ecran = new JTextArea();				//cr�ation du JTextArea (notre �cran)
	private Dimension dim1 = new Dimension(60, 50);			//On d�clare la dimension des touches chiffres
	private Dimension dim2 = new Dimension(60, 41);			//On d�clare la dimension des touches d'op�rations
	private JButton accueil = new JButton("Accueil");
	//d�claration de variables
	private String chiffre1;							
	private long chiffre2;
	private boolean clicOperateur = false, update = false;
	private boolean enable = false;
	private String operateur = "";
	public static long reste;
	private Dimension size;
		
	//m�thode de notre class
	public FenRoma(Dimension dim){
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
	
	boolean Verifromain(RomainDeci romain){
   		return romain.validSaisie();
   	}
	
	private Object Color(int i, int j, int k) {
		Color couleur = new Color(i,j,k);
		return couleur;
	}
	
	 private void initComposant(){
		    //On d�finit la police d'�criture � utiliser
		    Font police = new Font("Courier", Font.BOLD, 20);
		    Font policeNb = new Font("Arial", Font.PLAIN, 16);
		    Font policeAct = new Font("Arial", Font.PLAIN, 18);
		    ecran = new JTextArea("",1,1);
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

		    //On parcourt le tableau initialis�
		    //afin de cr�er nos boutons
		    for(int i = 0; i < tab_string.length; i++){
		      tab_button[i] = new JButton(tab_string[i]);
		      tab_button[i].setPreferredSize(dim1);
		      switch(i){
		        //Pour chaque �l�ment situ� � la fin du tableau
		        //et qui n'est pas un chiffre
		        //on d�finit le comportement � avoir gr�ce � un listener
		        case 7 :
		    	  tab_button[i].addActionListener(new ResteListener());
		    	  tab_button[i].setEnabled(false);
		    	  tab_button[i].setFont(policeAct);
		    	  chiffre.add(tab_button[i]);
		    	  break;
		        case 8 :
		          tab_button[i].addActionListener(new EgalListener());
		          tab_button[i].setFont(policeAct);
		          chiffre.add(tab_button[i]);
		          break;
		        case 9 :
		          tab_button[i].setForeground(Color.red);
		          tab_button[i].addActionListener(new ResetListener());
		          tab_button[i].setFont(policeNb);
		          operateur.add(tab_button[i]);
		          break;
		        case 10 :
		          tab_button[i].addActionListener(new PlusListener());
		          tab_button[i].setPreferredSize(dim2);
		          tab_button[i].setFont(policeAct);
		          operateur.add(tab_button[i]);
		          break;
		        case 11 :
		          tab_button[i].addActionListener(new MoinsListener());
		          tab_button[i].setPreferredSize(dim2);
		          tab_button[i].setFont(policeAct);
		          operateur.add(tab_button[i]);
		          break;	
		        case 12 :	
		          tab_button[i].addActionListener(new MultiListener());
		          tab_button[i].setPreferredSize(dim2);
		          tab_button[i].setFont(policeAct);
		          operateur.add(tab_button[i]);
		          break;
		        case 13 :
		          tab_button[i].addActionListener(new DivListener());
		          tab_button[i].setPreferredSize(dim2);
		          tab_button[i].setFont(policeAct);
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
		    
		    JScrollPane scrollArea = new JScrollPane(ecran,
                    JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
		    JLabel fileArianne = new JLabel(" > Romain");
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
		    container.setLayout(new BorderLayout());		//d�claration de l'utilisation du BorderLayout (ATTENTION ! sans �a les BorderLayout ne fonctionnent pas !)
		    container.add(header, BorderLayout.NORTH);
		    header.setBackground((java.awt.Color) Color(234,225,191));
		    container.add(calculatrice, BorderLayout.CENTER);
		    container.add(footer, BorderLayout.SOUTH);
		    footer.setBackground((java.awt.Color) Color(234,225,191));
		  }

		  //M�thode permettant d'effectuer un calcul selon l'op�rateur s�lectionn�
		  private void calcul(){
		    if(operateur.equals("+")){
		    	RomainDeci nombreR1 =  new RomainDeci(chiffre1);
		    	RomainDeci nombreR2 =  new RomainDeci(ecran.getText());
		        chiffre2 = nombreR1.convertRomain() + nombreR2.convertRomain();
		        if(!Verifromain(nombreR2)){
			    	  ecran.setText("Ce n'est pas un nombre romain !");
			      }
		    	else if (chiffre2 >= 5000){
		        	ecran.setText("Vous avez d�pass� la limite des nombres romain !");
		        }
		        else{
			        DeciRomain nombreR = new DeciRomain(chiffre2);
			        ecran.setText(nombreR.convertDeci());
			        chiffreTraduit.setText(String.valueOf(chiffre2));
		        }
		    }
		    if(operateur.equals("-")){
		    	RomainDeci nombreR1 =  new RomainDeci(chiffre1);
		    	RomainDeci nombreR2 =  new RomainDeci(ecran.getText());
		        chiffre2 = nombreR1.convertRomain() - nombreR2.convertRomain();
		        if(!Verifromain(nombreR2)){
			    	  ecran.setText("Ce n'est pas un nombre romain !");
			      }
		    	else if (chiffre2 >= 5000){
		        	ecran.setText("Vous avez d�pass� la limite des nombres romain !");
		        }
		        else if (chiffre2 == 0){
		        	ecran.setText("Le chiffre \"0\" n'existe pas en romain !");
		        }
		        else if (chiffre2 < 0){
		        	ecran.setText("Les nombres n�gatifs n'existent pas en romain !");
		        }
		        else{
			        DeciRomain nombreR = new DeciRomain(chiffre2);
			        ecran.setText(nombreR.convertDeci());
			        chiffreTraduit.setText(String.valueOf(chiffre2));
		        }
		    }          
		    if(operateur.equals("*")){
		    	RomainDeci nombreR1 =  new RomainDeci(chiffre1);
		    	RomainDeci nombreR2 =  new RomainDeci(ecran.getText());
		        chiffre2 = nombreR1.convertRomain() * nombreR2.convertRomain();
		        if(!Verifromain(nombreR2)){
			    	  ecran.setText("Ce n'est pas un nombre romain !");
			      }
		    	else if (chiffre2 >= 5000){
		        	ecran.setText("Vous avez d�pass� la limite des nombres romain !");
		        }
		        else{
		        	DeciRomain nombreR = new DeciRomain(chiffre2);
			        ecran.setText(nombreR.convertDeci());
			        chiffreTraduit.setText(String.valueOf(chiffre2));
		        }		        
		    }     
		    if(operateur.equals("/")){
		      try{
		        RomainDeci nombreR1 =  new RomainDeci(chiffre1);
			    RomainDeci nombreR2 =  new RomainDeci(ecran.getText());
			    chiffre2 = nombreR1.convertRomain() / nombreR2.convertRomain();        
		        reste = nombreR1.convertRomain() % nombreR2.convertRomain();
		        if(!Verifromain(nombreR2)){
			    	  ecran.setText("Ce n'est pas un nombre romain !");
			      }
		    	else if(chiffre2 >= 5000){
		        	ecran.setText("Vous avez d�pass� la limite des nombres romain !");
		        }
		        else if(chiffre2 == 0){
		        	ecran.setText("Le chiffre \"0\" n'existe pas en romain !");
		        }
		        else{
		        	DeciRomain nombreR = new DeciRomain(chiffre2);
			        ecran.setText(nombreR.convertDeci());
			        chiffreTraduit.setText(String.valueOf(chiffre2));
		        }		        
		      } catch(ArithmeticException e) {
		        ecran.setText("Op�ration impossible");
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
		      RomainDeci nombreR =  new RomainDeci(str);
		      long chiffre = nombreR.convertRomain();
		      if(!Verifromain(nombreR)){
		    	  chiffreTraduit.setText("Ce n'est pas un nombre romain !");
		      }
		      else if (chiffre >= 5000){
		    	  chiffreTraduit.setText("Vous avez d�pass� la limite des nombres romain !");
		      }
		      else{
			      chiffreTraduit.setText(String.valueOf(chiffre));
              }
		    }
		  }
		  
		  //Listener affect� au bouton R
		  class ResteListener implements ActionListener {
			  public void actionPerformed(ActionEvent arg0){
				  if(reste > 9999){
			        	ecran.setText("Vous avez d�pass� la limite des nombres romain !");
			        }
			        else if(reste == 0){
			        	ecran.setText("Le chiffre \"0\" n'existe pas en romain !");
			        }
			        else{
			        	DeciRomain nombreR = new DeciRomain(reste);
				        ecran.setText(nombreR.convertDeci());
				        }
			  }
		  }
		  
		  //Listener affect� au bouton =
		  class EgalListener implements ActionListener {
		    public void actionPerformed(ActionEvent arg0){
		    	if (enable == true){
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

		  //Listener affect� au bouton +
		  class PlusListener implements ActionListener {
		    public void actionPerformed(ActionEvent arg0){
		    	RomainDeci nombreR =  new RomainDeci(ecran.getText());
		      if(!Verifromain(nombreR)){
			    ecran.setText("Ce n'est pas un nombre romain !");
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

		  //Listener affect� au bouton -
		  class MoinsListener implements ActionListener {
		    public void actionPerformed(ActionEvent arg0){
		    	RomainDeci nombreR =  new RomainDeci(ecran.getText());
			  if(!Verifromain(nombreR)){
				ecran.setText("Ce n'est pas un nombre romain !");
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

		  //Listener affect� au bouton *
		  class MultiListener implements ActionListener {
		    public void actionPerformed(ActionEvent arg0){
		    	RomainDeci nombreR =  new RomainDeci(ecran.getText());
			  if(!Verifromain(nombreR)){
				ecran.setText("Ce n'est pas un nombre romain !");
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

		  //Listener affect� au bouton /
		  class DivListener implements ActionListener {
		    public void actionPerformed(ActionEvent arg0){
		    	RomainDeci nombreR =  new RomainDeci(ecran.getText());
			  if(!Verifromain(nombreR)){
				ecran.setText("Ce n'est pas un nombre romain !");
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

		  //Listener affect� au bouton de remise � z�ro
		  class ResetListener implements ActionListener {
		    public void actionPerformed(ActionEvent arg0){
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