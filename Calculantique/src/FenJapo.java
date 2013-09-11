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
import japonais.*;

//Je pense pas avoir à expliquer cette class, pour plus d'info aller voir FenAccueil.java
public class FenJapo extends Container{
	
	private JPanel container = new JPanel();
	private JLabel chiffreTraduit = new JLabel();
	//Tableau stockant les éléments à afficher dans la calculatrice
	String[] tab_string = {"\u3007", "\u4E00", "\u4E8C", "\u4E09", "\u56DB", "\u4E94", "\u516D", "\u4E03", "\u516B", "\u4E5D", "\u5341", "\u767E", "\u5343", "\u4E07", "R", "=", "C", "+", "-", "*", "/"};
	//Un bouton par élément à afficher
	JButton[] tab_button = new JButton[tab_string.length];
	private JTextArea ecran = new JTextArea();				//création du JTextArea (notre écran)
	private Dimension dim1 = new Dimension(65, 50);			//On déclare la dimension des touches chiffres
	private Dimension dim2 = new Dimension(65, 41);			//On déclare la dimension des touches d'opérations
	private JButton accueil = new JButton("Accueil");
	//déclaration de variables
	private String chiffre1;
	private long chiffre2;
	private boolean clicOperateur = false, update = false;
	private boolean enable = false;
	private String operateur = "";
	public static long reste;
	private Dimension size;
	
	//méthode de notre class
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
	    ecran = new JTextArea("",1,1);
	    ecran.setRows(3);
	    ecran.setEditable(false);
	    Font policeNb = new Font("Arial", Font.PLAIN, 16);
	    Font policeAct = new Font("Arial", Font.PLAIN, 18);
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
	        case 14 :
	    	  tab_button[i].addActionListener(new ResteListener());
	    	  tab_button[i].setEnabled(false);
	    	  tab_button[i].setFont(policeAct);
	    	  chiffre.add(tab_button[i]);
	    	  break;
	        case 15 :
	          tab_button[i].addActionListener(new EgalListener());
	          tab_button[i].setFont(policeAct);
	          chiffre.add(tab_button[i]);
	          break;
	        case 16 :
	          tab_button[i].setForeground(Color.red);
	          tab_button[i].addActionListener(new ResetListener());
	          tab_button[i].setFont(policeNb);
	          operateur.add(tab_button[i]);
	          break;
	        case 17 :
	          tab_button[i].addActionListener(new PlusListener());
	          tab_button[i].setPreferredSize(dim2);
	          tab_button[i].setFont(policeAct);
	          operateur.add(tab_button[i]);
	          break;
	        case 18 :
	          tab_button[i].addActionListener(new MoinsListener());
	          tab_button[i].setPreferredSize(dim2);
	          tab_button[i].setFont(policeAct);
	          operateur.add(tab_button[i]);
	          break;	
	        case 19 :	
	          tab_button[i].addActionListener(new MultiListener());
	          tab_button[i].setPreferredSize(dim2);
	          tab_button[i].setFont(policeAct);
	          operateur.add(tab_button[i]);
	          break;
	        case 20 :
	          tab_button[i].addActionListener(new DivListener());
	          tab_button[i].setPreferredSize(dim2);
	          tab_button[i].setFont(policeAct);
	          operateur.add(tab_button[i]);
	          break;
	        default :
	          //Par défaut, ce sont les premiers éléments du tableau
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
	        ecran.setText("Opération impossible");
	      }
	    }
	  }

	//méthode qui fait appel à notre class de conversion
	static class Detoja extends Decitojapo{
		static String convert(long chiffre){
			return Decitojapo.Main(chiffre);
		}
	}

	//méthode qui fait appel à notre class de conversion
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

	//Listener utilisé pour les chiffres
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
	  
	  //Listener affecté au bouton R
	  class ResteListener implements ActionListener {
		  public void actionPerformed(ActionEvent arg0){
			  ecran.setText(Detoja.convert(reste));
		  }
	  }
	  
	  //Listener affecté au bouton =
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

	  //Listener affecté au bouton +
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

	  //Listener affecté au bouton -
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

	  //Listener affecté au bouton *
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

	  //Listener affecté au bouton /
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

	  //Listener affecté au bouton de remise à zéro
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
