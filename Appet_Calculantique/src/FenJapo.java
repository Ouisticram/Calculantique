import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import japonais.*;

//Je pense pas avoir � expliquer cette class, pour plus d'info aller voir FenAccueil.java
public class FenJapo extends Container{
	
	private JPanel container = new JPanel();
	//Tableau stockant les �l�ments � afficher dans la calculatrice
	String[] tab_string = {"\u3007", "\u4E00", "\u4E8C", "\u4E09", "\u56DB", "\u4E94", "\u516D", "\u4E03", "\u516B", "\u4E5D", "\u5341", "\u767E", "\u5343", "\u4E07", "R", "=", "C", "+", "-", "*", "/"};
	//Un bouton par �l�ment � afficher
	JButton[] tab_button = new JButton[tab_string.length];
	private JTextArea ecran = new JTextArea();				//cr�ation du JTextArea (notre �cran)
	private Dimension dim1 = new Dimension(65, 50);			//On d�clare la dimension des touches chiffres
	private Dimension dim2 = new Dimension(65, 41);			//On d�clare la dimension des touches d'op�rations
	//d�claration de variables
	private String chiffre1;
	private long chiffre2;
	private boolean clicOperateur = false, update = false;
	private boolean enable = false;
	private String operateur = "";
	public static long reste;
	
	//m�thode de notre class
	public FenJapo(Dimension dim){
		super(dim);
		initPanel();
	}
		
	public void initPanel(){		
		//On initialise le conteneur avec tous les composants
			initComposant();
		panel.add(container);
		panel.setBackground((java.awt.Color) Color(234,225,191));
	}
	
	boolean verification (String test){
		return Japotodeci.verif(test);
	}
	
	private Object Color(int i, int j, int k) {
		Color couleur = new Color(i,j,k);
		return couleur;
	}
  
  	private void initComposant(){
	    ecran = new JTextArea("");
	    ecran.setRows(3);
	    ecran.setEditable(false);
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
	        case 14 :
	    	  tab_button[i].addActionListener(new ResteListener());
	    	  tab_button[i].setEnabled(false);
	    	  chiffre.add(tab_button[i]);
	    	  break;
	        case 15 :
	          tab_button[i].addActionListener(new EgalListener());
	          chiffre.add(tab_button[i]);
	          break;
	        case 16 :
	          tab_button[i].setForeground(Color.red);
	          tab_button[i].addActionListener(new ResetListener());
	          operateur.add(tab_button[i]);
	          break;
	        case 17 :
	          tab_button[i].addActionListener(new PlusListener());
	          tab_button[i].setPreferredSize(dim2);
	          operateur.add(tab_button[i]);
	          break;
	        case 18 :
	          tab_button[i].addActionListener(new MoinsListener());
	          tab_button[i].setPreferredSize(dim2);
	          operateur.add(tab_button[i]);
	          break;	
	        case 19 :	
	          tab_button[i].addActionListener(new MultiListener());
	          tab_button[i].setPreferredSize(dim2);
	          operateur.add(tab_button[i]);
	          break;
	        case 20 :
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
	    
	    JScrollPane scrollArea = new JScrollPane(ecran);
	    panEcran.setLayout(new BorderLayout());
	    panEcran.add(scrollArea);
	    // panEcran.setBorder(BorderFactory.createLineBorder(Color.black));
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
	      chiffre2 = Jatode.convert(chiffre1) + 
	    		  Jatode.convert(ecran.getText());
	      if(!verification(ecran.getText())){
	    	  ecran.setText("Ce n'est pas un nombre Japonais !");
	      }
    	else{
	      ecran.setText(Detoja.convert(chiffre2));}
	      
	    }
	    if(operateur.equals("-")){
	      chiffre2 = Jatode.convert(chiffre1) - 
	    		  Jatode.convert(ecran.getText());
	      if(!verification(ecran.getText())){
	    	  ecran.setText("Ce n'est pas un nombre Japonais !");
	      }
    	else{
	      ecran.setText(Detoja.convert(chiffre2));}
	    }          
	    if(operateur.equals("*")){
	      chiffre2 = Jatode.convert(chiffre1) * 
	    		  Jatode.convert(ecran.getText());
	      if(!verification(ecran.getText())){
	    	  ecran.setText("Ce n'est pas un nombre Japonais !");
	      }
    	else{
	      ecran.setText(Detoja.convert(chiffre2));}
	    }     
	    if(operateur.equals("/")){
	      try{
	        chiffre2 = Jatode.convert(chiffre1) / 
	        		Jatode.convert(ecran.getText());
	        reste = Jatode.convert(chiffre1) % 
	        		Jatode.convert(ecran.getText());
	        if(!verification(ecran.getText())){
		    	  ecran.setText("Ce n'est pas un nombre Japonais !");
		      }
	    	else{
	        ecran.setText(Detoja.convert(chiffre2));}
	      } catch(ArithmeticException e) {
	        ecran.setText("Op�ration impossible");
	      }
	    }
	  }

	//m�thode qui fait appel � notre class de conversion
	static class Detoja extends Decitojapo{
		static String convert(long chiffre){
			return Decitojapo.Main(chiffre);
		}
	}

	//m�thode qui fait appel � notre class de conversion
	static class Jatode extends Japotodeci{
		static long convert(String ja){
			return Japotodeci.Main(ja);
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
	    }
	  }
	  
	  //Listener affect� au bouton R
	  class ResteListener implements ActionListener {
		  public void actionPerformed(ActionEvent arg0){
			  ecran.setText(Detoja.convert(reste));
		  }
	  }
	  
	  //Listener affect� au bouton =
	  class EgalListener implements ActionListener {
	    public void actionPerformed(ActionEvent arg0){
	    	if (enable == true){
	    		calcul();
			    update = true;
			    clicOperateur = false;
	    		tab_button[14].setEnabled(true);
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
	      if(!verification(ecran.getText())){
	    	  ecran.setText("Ce n'est pas un nombre Japonais !");
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
	    	if(!verification(ecran.getText())){
		    	  ecran.setText("Ce n'est pas un nombre Japonais !");
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
	    	if(!verification(ecran.getText())){
		    	  ecran.setText("Ce n'est pas un nombre Japonais !");
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
	    	if(!verification(ecran.getText())){
		    	  ecran.setText("Ce n'est pas un nombre Japonais !");
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
	      tab_button[14].setEnabled(false);
	      update = true;
	      enable = false;
	      chiffre2 = 0;
	      chiffre1 = "";
	      operateur = "";
	      ecran.setText("");
	    }
	  }
}
