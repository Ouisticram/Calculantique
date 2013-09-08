import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import japonais.*;

//Je pense pas avoir � expliquer cette class, pour plus d'info aller voir FenAccueil.java
public class FenJapo extends Container{
	
	private JPanel container = new JPanel();
	private JLabel chiffreTraduit = new JLabel();
	//Tableau stockant les �l�ments � afficher dans la calculatrice
	String[] tab_string = {"\u3007", "\u4E00", "\u4E8C", "\u4E09", "\u56DB", "\u4E94", "\u516D", "\u4E03", "\u516B", "\u4E5D", "\u5341", "\u767E", "\u5343", "\u4E07", "R", "=", "C", "+", "-", "*", "/"};
	//Un bouton par �l�ment � afficher
	JButton[] tab_button = new JButton[tab_string.length];
	private JTextArea ecran = new JTextArea();				//cr�ation du JTextArea (notre �cran)
	private Dimension dim1 = new Dimension(65, 50);			//On d�clare la dimension des touches chiffres
	private Dimension dim2 = new Dimension(65, 41);			//On d�clare la dimension des touches d'op�rations
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
	public FenJapo(Dimension dim){
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
	    JLabel egalite = new JLabel(" = ");
	    egalite.setFont(arial);
	    chiffreTraduit = new JLabel("");
	    chiffreTraduit.setFont(arial);
	    chiffreTraduit.setBackground((java.awt.Color) Color(234,225,191));
	    header.add(egalite);
	    header.add(chiffreTraduit);
	    accueil.addActionListener(new PageAccueil());
	    JLabel fileArianne = new JLabel(" > Japonais");
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
	      chiffre2 = Jatode.convert(chiffre1) + 
	    		  Jatode.convert(ecran.getText());
	      if(!verification(ecran.getText())){
	    	  ecran.setText("Ce n'est pas un nombre Japonais !");
	      }
    	else{
	      ecran.setText(Detoja.convert(chiffre2));
	      chiffreTraduit.setText(String.valueOf(chiffre2));}
	      
	    }
	    if(operateur.equals("-")){
	      chiffre2 = Jatode.convert(chiffre1) - 
	    		  Jatode.convert(ecran.getText());
	      if(!verification(ecran.getText())){
	    	  ecran.setText("Ce n'est pas un nombre Japonais !");
	      }
    	else{
	      ecran.setText(Detoja.convert(chiffre2));
	      chiffreTraduit.setText(String.valueOf(chiffre2));}
	    }          
	    if(operateur.equals("*")){
	      chiffre2 = Jatode.convert(chiffre1) * 
	    		  Jatode.convert(ecran.getText());
	      if(!verification(ecran.getText())){
	    	  ecran.setText("Ce n'est pas un nombre Japonais !");
	      }
    	else{
	      ecran.setText(Detoja.convert(chiffre2));
	      chiffreTraduit.setText(String.valueOf(chiffre2));}
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
	        ecran.setText(Detoja.convert(chiffre2));
	        chiffreTraduit.setText(String.valueOf(chiffre2));}
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
	      long chiffre = Jatode.convert(str);
	      if(!verification(str)){
	    	  chiffreTraduit.setText("Ce n'est pas un nombre Japonais !");
	      }
          else{		      
		      chiffreTraduit.setText(String.valueOf(chiffre));
          }
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
	      chiffreTraduit.setText("");
	    }
	  }
}