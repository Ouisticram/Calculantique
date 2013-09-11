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

public class FenDeci extends Container {
	
	private JPanel container = new JPanel();
	  //Tableau stockant les éléments à afficher dans la calculatrice
	  String[] tab_string = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "R", "=", "C", "+", "-", "*", "/"};
	  //Un bouton par élément à afficher
	  JButton[] tab_button = new JButton[tab_string.length];
	  private JTextArea ecran = new JTextArea();				//création du JTextArea (notre écran)
	  private Dimension dim = new Dimension(60, 50);
	  private Dimension dim2 = new Dimension(60, 41);
	  private JButton accueil = new JButton("Accueil");
	  private int chiffre1;
	  private boolean clicOperateur = false, update = false;
	  private String operateur = "";
	  private boolean enable = false;
	  public static int reste;
	  private Dimension size;
		
	//méthode de notre class
	public FenDeci(Dimension dim){
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
	
	private Object Color(int i, int j, int k) {
		Color couleur = new Color(i,j,k);
		return couleur;
	}
	
	 private void initComposant(){
		    //On définit la police d'écriture à utiliser
		    Font police = new Font("Arial", Font.BOLD, 16);
		    Font policeNb = new Font("Arial", Font.PLAIN, 16);
		    Font policeAct = new Font("Arial", Font.PLAIN, 18);
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
		    JPanel footer = new JPanel();

		  //On parcourt le tableau initialisé
		    	//afin de créer nos boutons
		    	for(int i = 0; i < tab_string.length; i++){
		    	  tab_button[i] = new JButton(tab_string[i]);
		    	  tab_button[i].setPreferredSize(dim);
		    	  tab_button[i].setFont(policeNb);
		    	  switch(i){
		    	  	//Pour chaque élément situé à la fin du tableau
		    		//et qui n'est pas un chiffre
		    	    //on définit le comportement à avoir grâce à un listener
		    	  	case 10 :
		    	  		tab_button[i].addActionListener(new ResteListener());
				    	tab_button[i].setEnabled(false);
				    	tab_button[i].setFont(policeAct);
				    	chiffre.add(tab_button[i]);
				    	break;
		    	  	case 11 :
		    	  	  tab_button[i].addActionListener(new EgalListener());
		    	  	  chiffre.add(tab_button[i]);
		    	  	  tab_button[i].setFont(policeAct);
		    	  	  break;
		    	  	case 12 :
		    	  	  tab_button[i].setForeground(Color.red);
		    	  	  tab_button[i].addActionListener(new ResetListener());
		    	  	  operateur.add(tab_button[i]);
		    	  	  break;
		    	  	case 13 :
		    	  	  tab_button[i].addActionListener(new PlusListener());
		    	  	  tab_button[i].setPreferredSize(dim2);
		    	  	  operateur.add(tab_button[i]);
		    	  	  tab_button[i].setFont(policeAct);
		    	  	  break;
		    	  	case 14 :
		    	  	  tab_button[i].addActionListener(new MoinsListener());
		    	  	  tab_button[i].setPreferredSize(dim2);
		    	  	  operateur.add(tab_button[i]);
		    	  	  tab_button[i].setFont(policeAct);
		    	  	  break;
		    	  	case 15 :
		    	 	  tab_button[i].addActionListener(new MultiListener());
		    	 	  tab_button[i].setPreferredSize(dim2);
		    	 	  operateur.add(tab_button[i]);
		    	 	  tab_button[i].setFont(policeAct);
		    	 	  break;
		    	  	case 16 :
		    	  	  tab_button[i].addActionListener(new DivListener());
		    	  	  tab_button[i].setPreferredSize(dim2);
		    	  	  operateur.add(tab_button[i]);
		    	  	  tab_button[i].setFont(policeAct);
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
		    accueil.addActionListener(new PageAccueil());
		    JLabel fileArianne = new JLabel(" > Décimal");
		    fileArianne.setFont(arial);
		    footer.add(accueil);
		    footer.add(fileArianne);
		    container.setLayout(new BorderLayout());		//déclaration de l'utilisation du BorderLayout (ATTENTION ! sans ça les BorderLayout ne fonctionnent pas !)
		    container.add(panEcran, BorderLayout.NORTH);	//On place l'écran en haut 
		    panEcran.setBackground(Color.white);
		    container.add(chiffre, BorderLayout.CENTER);	//On place les chiffres au centre
		    chiffre.setBackground((java.awt.Color) Color(234,225,191));
		    container.add(operateur, BorderLayout.EAST);	// et les opérateurs à droite
		    operateur.setBackground((java.awt.Color) Color(234,225,191));
		    container.add(footer, BorderLayout.SOUTH);
		    footer.setBackground((java.awt.Color) Color(234,225,191));
		  }

	  //Méthode permettant d'effectuer un calcul selon l'opérateur sélectionné
	   private void calcul(){
		 if(operateur.equals("+")){
		   chiffre1 = chiffre1 + 
				 Integer.valueOf(ecran.getText());
		   ecran.setText(String.valueOf(chiffre1));
		 }
		 if(operateur.equals("-")){
		   chiffre1 = chiffre1 - 
				   Integer.valueOf(ecran.getText());
		   ecran.setText(String.valueOf(chiffre1));
		 }
		 if(operateur.equals("*")){
		   chiffre1 = chiffre1 * 
				   Integer.valueOf(ecran.getText());
		   ecran.setText(String.valueOf(chiffre1));
		 }
		 if(operateur.equals("/")){
		   try{
			 reste = chiffre1 %
					 Integer.valueOf(ecran.getText());
			 chiffre1 = chiffre1 / 
					 Integer.valueOf(ecran.getText());
			 ecran.setText(String.valueOf(chiffre1));
		   } catch(ArithmeticException e) {
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
		  
		  //Listener affecté au bouton R
		  class ResteListener implements ActionListener {
			  public void actionPerformed(ActionEvent arg0){
				  ecran.setText(String.valueOf(reste));
			  }
		  }
		  
		  //Listener affecté au bouton =
		  class EgalListener implements ActionListener {
		    public void actionPerformed(ActionEvent arg0){
		    	if (enable == true){
		    		calcul();
				    update = true;
				    clicOperateur = false;
		    		tab_button[10].setEnabled(true);
		    		chiffre1 = 0;
		    	}
		    	else
		    	{
		    		calcul();
		    		update = true;
		    		clicOperateur = false;
		    		chiffre1 = 0;
		    	}
		    }
		  }

		  //Listener affecté au bouton +
		  class PlusListener implements ActionListener {
		    public void actionPerformed(ActionEvent arg0){
		      if(clicOperateur){
		        calcul();
		        ecran.setText(String.valueOf(chiffre1));
		      }
		      else{
		        chiffre1 = Integer.valueOf(ecran.getText());
		        clicOperateur = true;
		      }
		      operateur = "+";
		      update = true;
		    }
		  }

		  //Listener affecté au bouton -
		  class MoinsListener implements ActionListener {
		    public void actionPerformed(ActionEvent arg0){
		      if(clicOperateur){
		        calcul();
		        ecran.setText(String.valueOf(chiffre1));
		      }
		      else{
		    	Integer.valueOf(ecran.getText());
		        clicOperateur = true;
		      }
		      operateur = "-";
		      update = true;
		    }
		  }

		  //Listener affecté au bouton *
		  class MultiListener implements ActionListener {
		    public void actionPerformed(ActionEvent arg0){
		      if(clicOperateur){
		        calcul();
		        ecran.setText(String.valueOf(chiffre1));
		      }
		      else{
		        chiffre1 = Integer.valueOf(ecran.getText());
		        clicOperateur = true;
		      }
		      operateur = "*";
		      update = true;
		    }
		  }

		  //Listener affecté au bouton /
		  class DivListener implements ActionListener {
		    public void actionPerformed(ActionEvent arg0){
		      if(clicOperateur){
		        calcul();
		        ecran.setText(String.valueOf(chiffre1));
		      }
		      else{
		        chiffre1 = Integer.valueOf(ecran.getText());
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
		      tab_button[10].setEnabled(false);
		      clicOperateur = false;
		      update = true;
		      chiffre1 = 0;
		      operateur = "";
		      ecran.setText("0");
		    }
		  }
}
