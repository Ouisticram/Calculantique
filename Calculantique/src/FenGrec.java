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
import grec.*;

//Je pense pas avoir à expliquer cette class, pour plus d'info aller voir FenAccueil.java
public class FenGrec extends Container{
	
	private JPanel container = new JPanel();
	private JLabel chiffreTraduit = new JLabel();
	//Tableau stockant les éléments à afficher dans la calculatrice
	String[] tab_string = {"\u03B1", "\u03B2", "\u03B3", "\u03B4", "\u03B5", "\u03DD", "\u03B6", "\u03B7", "\u03B8", "\u03B9", "\u03BA", "\u03BB", "\u03BC", "\u03BD", "\u03BE", "\u03BF", "\u03C0", "\u03DF", "\u03C1", "\u03C3", "\u03C4", "\u03C5", "\u03C6", "\u03C7", "\u03C8", "\u03C9", "\u03E1", "\u02B9", "R", "=", "C", "+", "-", "*", "/"};
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
	public FenGrec(Dimension dim){
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
	
	static boolean verificationGrec(String nb)
	{
		boolean verif = false;
		
		if (nb.length() == 1) {verif = nb.matches("\u03B1|\u03B2|\u03B3|\u03B4|\u03B5|\u03DD|\u03B6|\u03B7|\u03B8|\u03B9|\u03BA|\u03BB|\u03BC|\u03BD|\u03BE|\u03BF|\u03C0|\u03DF|\u03C1|\u03C3|\u03C4|\u03C5|\u03C6|\u03C7|\u03C8|\u03C9|\u03E1");}
		
		if (nb.length() > 1 && nb.length() <= 5)
		{ 
			if (nb.length()==5) // si le nombre contient cinq chiffres il est soumis a une synthaxe '+unite+centaine+dizaine+unite
			{verif = nb.matches("\u02B9(\u03B1|\u03B2|\u03B3|\u03B4|\u03B5|\u03DD|\u03B6|\u03B7|\u03B8)(\u03C1|\u03C3|\u03C4|\u03C5|\u03C6|\u03C7|\u03C8|\u03C9|\u03E1)(\u03B9|\u03BA|\u03BB|\u03BC|\u03BD|\u03BE|\u03BF|\u03C0|\u03DF)(\u03B1|\u03B2|\u03B3|\u03B4|\u03B5|\u03DD|\u03B6|\u03B7|\u03B8)");
			}
			else
			{
				if (nb.charAt(0) == '\u02B9') // si le nombre commence par '
				{verif = Character.toString(nb.charAt(1)).matches("(\u03B1|\u03B2|\u03B3|\u03B4|\u03B5|\u03DD|\u03B6|\u03B7|\u03B8)");
				}
				else
				{
					if (Character.toString(nb.charAt(0)).matches("(\u03B1|\u03B2|\u03B3|\u03B4|\u03B5|\u03DD|\u03B6|\u03B7|\u03B8)")) // si le nombre commence par un chiffre des unites
					{if (nb.length() == 1){verif = true;}
					else {return false;}
					}
					else
					{
						if (Character.toString(nb.charAt(0)).matches("(\u03C1|\u03C3|\u03C4|\u03C5|\u03C6|\u03C7|\u03C8|\u03C9|\u03E1)")) // si le nombre commence par un chiffre des centaines
						{verif = (nb.length() <= 3 && Character.toString(nb.charAt(1)).matches("(\u03B1|\u03B2|\u03B3|\u03B4|\u03B5|\u03DD|\u03B6|\u03B7|\u03B8|\u03B9|\u03BA|\u03BB|\u03BC|\u03BD|\u03BE|\u03BF|\u03C0|\u03DF)"));
						}
						else
						{
							if (Character.toString(nb.charAt(0)).matches("\u03B9|\u03BA|\u03BB|\u03BC|\u03BD|\u03BE|\u03BF|\u03C0|\u03DF")) // si le chiffre commence par un chiffre des dizaines
							{verif = (nb.length() <= 2 && Character.toString(nb.charAt(1)).matches("(\u03B1|\u03B2|\u03B3|\u03B4|\u03B5|\u03DD|\u03B6|\u03B7|\u03B8)"));
							}
						}
					}
				}
			}
		}
			
		if (nb.length() > 2 && nb.length() <= 5)
		{
			if (Character.toString(nb.charAt(1)).matches("(\u03B1|\u03B2|\u03B3|\u03B4|\u03B5|\u03DD|\u03B6|\u03B7|\u03B8)")) // si le deuxieme chiffre est une unite (cad le premier est une apostrophe) il peut etre suivi par tout sauf une apostrophe.
			{verif = Character.toString(nb.charAt(2)).matches("\u03B1|\u03B2|\u03B3|\u03B4|\u03B5|\u03DD|\u03B6|\u03B7|\u03B8|\u03B9|\u03BA|\u03BB|\u03BC|\u03BD|\u03BE|\u03BF|\u03C0|\u03DF|\u03C1|\u03C3|\u03C4|\u03C5|\u03C6|\u03C7|\u03C8|\u03C9|\u03E1");
			}
			else
			{
				if (Character.toString(nb.charAt(1)).matches("\u03B9|\u03BA|\u03BB|\u03BC|\u03BD|\u03BE|\u03BF|\u03C0|\u03DF")) // si le deuxieme chiffre est une dizaine, il ne peut etre suivi que par un chiffre des unites.
				{verif = Character.toString(nb.charAt(2)).matches("\u03B1|\u03B2|\u03B3|\u03B4|\u03B5|\u03DD|\u03B6|\u03B7|\u03B8");
				}
				else
				{
					if (Character.toString(nb.charAt(1)).matches("\u03C1|\u03C3|\u03C4|\u03C5|\u03C6|\u03C7|\u03C8|\u03C9|\u03E1")) // si le deuxieme chiffre est une centaine, il ne peut etre suivi que par un chiffre des unites ou un chiffre des dizaines.
					{verif = Character.toString(nb.charAt(2)).matches("\u03B1|\u03B2|\u03B3|\u03B4|\u03B5|\u03DD|\u03B6|\u03B7|\u03B8|\u03B9|\u03BA|\u03BB|\u03BC|\u03BD|\u03BE|\u03BF|\u03C0|\u03DF");
					}
				}
			}
		}
		
		if (nb.length() > 3 && nb.length() <= 5)
		{
			if (Character.toString(nb.charAt(2)).matches("\u03B1|\u03B2|\u03B3|\u03B4|\u03B5|\u03DD|\u03B6|\u03B7|\u03B8")) // si le troisieme chiffre est une unite il ne peut rien y avoir derriere.
			{verif = nb.length()==3;
			}
			else
			{
				if (Character.toString(nb.charAt(2)).matches("\u03B9|\u03BA|\u03BB|\u03BC|\u03BD|\u03BE|\u03BF|\u03C0|\u03DF")) // si le troisieme chiffre est une dizaine, il ne peut etre suivi que par un chiffre des unites.
				{verif = Character.toString(nb.charAt(3)).matches("\u03B1|\u03B2|\u03B3|\u03B4|\u03B5|\u03DD|\u03B6|\u03B7|\u03B8") && nb.length() <= 4;
				}
				else
				{
					if (Character.toString(nb.charAt(2)).matches("\u03C1|\u03C3|\u03C4|\u03C5|\u03C6|\u03C7|\u03C8|\u03C9|\u03E1")) // si le troisieme chiffre est une centaine, il ne peut etre suivi que par un chiffre des unites ou un chiffre des dizaines.
					{verif = Character.toString(nb.charAt(3)).matches("\u03B1|\u03B2|\u03B3|\u03B4|\u03B5|\u03DD|\u03B6|\u03B7|\u03B8|\u03B9|\u03BA|\u03BB|\u03BC|\u03BD|\u03BE|\u03BF|\u03C0|\u03DF");
					}
				}
			}
		}		
	
		
		return verif;
	}
	
	private Object Color(int i, int j, int k) {
		Color couleur = new Color(i,j,k);
		return couleur;
	}
  
  	private void initComposant(){
	    //On définit la police d'écriture à utiliser
	    Font police = new Font("Arial", Font.BOLD, 20);
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
	    chiffre.setPreferredSize(new Dimension(450, 400));
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
	        case 28 :
	    	  tab_button[i].addActionListener(new ResteListener());
	    	  tab_button[i].setEnabled(false);
	    	  chiffre.add(tab_button[i]);
	    	  break;
	        case 29 :
	          tab_button[i].addActionListener(new EgalListener());
	          chiffre.add(tab_button[i]);
	          break;
	        case 30 :
	          tab_button[i].setForeground(Color.red);
	          tab_button[i].addActionListener(new ResetListener());
	          operateur.add(tab_button[i]);
	          break;
	        case 31 :
	          tab_button[i].addActionListener(new PlusListener());
	          tab_button[i].setPreferredSize(dim2);
	          operateur.add(tab_button[i]);
	          break;
	        case 32 :
	          tab_button[i].addActionListener(new MoinsListener());
	          tab_button[i].setPreferredSize(dim2);
	          operateur.add(tab_button[i]);
	          break;	
	        case 33 :	
	          tab_button[i].addActionListener(new MultiListener());
	          tab_button[i].setPreferredSize(dim2);
	          operateur.add(tab_button[i]);
	          break;
	        case 34 :
	          tab_button[i].addActionListener(new DivListener());
	          tab_button[i].setPreferredSize(dim2);
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
	    JLabel fileArianne = new JLabel(" > Grec");
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
	      chiffre2 = Grtode.convert(chiffre1) + 
	    		  Grtode.convert(ecran.getText());
	      if(!verificationGrec(ecran.getText())){
	    	  ecran.setText("Ce n'est pas un nombre grec !");
	      }
    	  else if(chiffre2 > 9999){
	          ecran.setText("Vous avez dépassé la limite des nombres grec !");
	      }
	      else{
	    	  ecran.setText(Detogr.convert(chiffre2));
	    	  chiffreTraduit.setText(String.valueOf(chiffre2));
	      }     
	      
	    }
	    if(operateur.equals("-")){
	      chiffre2 = Grtode.convert(chiffre1) - 
	    		  Grtode.convert(ecran.getText());
	        if(!verificationGrec(ecran.getText())){
	    	    ecran.setText("Ce n'est pas un nombre grec !");
	        }
    	    else if(chiffre2 < 0){
	        	ecran.setText("Les nombres négatifs n'existent pas en grec !");
	        }
	        else if(chiffre2 > 9999){
	        	ecran.setText("Vous avez dépassé la limite des nombres grec !");
	        }
	        else if(chiffre2 == 0){
	        	ecran.setText("Le chiffre \"0\" n'existe pas en grec !");
	        }
	        else{
	        	ecran.setText(Detogr.convert(chiffre2));
	        	chiffreTraduit.setText(String.valueOf(chiffre2));
	        }
	    }          
	    if(operateur.equals("*")){
	      chiffre2 = Grtode.convert(chiffre1) * 
	    		  Grtode.convert(ecran.getText());
	      if(!verificationGrec(ecran.getText())){
	    	  ecran.setText("Ce n'est pas un nombre grec !");
	      }
    	  else if(chiffre2 > 9999){
	    	  ecran.setText("Vous avez dépassé la limite des nombres grec !");
	      }
	      else{
	    	  ecran.setText(Detogr.convert(chiffre2));
	    	  chiffreTraduit.setText(String.valueOf(chiffre2));
	      }
	    }     
	    if(operateur.equals("/")){
	      try{
	        chiffre2 = Grtode.convert(chiffre1) / 
	        		Grtode.convert(ecran.getText());
	        reste = Grtode.convert(chiffre1) % 
	        		Grtode.convert(ecran.getText());
	        if(!verificationGrec(ecran.getText())){
		    	ecran.setText("Ce n'est pas un nombre grec !");
		    }
	    	else if(chiffre2 > 9999){
	        	ecran.setText("Vous avez dépassé la limite des nombres grec !");
	        }
	        else if(chiffre2 == 0){
	        	ecran.setText("Le chiffre \"0\" n'existe pas en grec !");
	        }
	        else{
	        ecran.setText(Detogr.convert(chiffre2));
	        chiffreTraduit.setText(String.valueOf(chiffre2));}
	      } catch(ArithmeticException e) {
	    	ecran.setText("Opération impossible");
	      }
	    }
	  }

	//méthode qui fait appel à notre class de conversion
	static class Detogr extends DecimalToGrec{
		static String convert(long chiffre){
			return DecimalToGrec.main(chiffre);
		}
	}

	//méthode qui fait appel à notre class de conversion
	static class Grtode extends GrecToDecimal{
		static long convert(String gr){
			return GrecToDecimal.main(gr);
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
	      long chiffre = Grtode.convert(str);
	      if(!verificationGrec(str)){
	    	  chiffreTraduit.setText("Ce n'est pas un nombre grec !");
	      }
    	  else if(chiffre > 9999){
    		  chiffreTraduit.setText("Vous avez dépassé la limite des nombres grec !");
	      }
    	  else{		      
    		  chiffreTraduit.setText(String.valueOf(chiffre));
          }
	    }
	  }
	  
	  //Listener affecté au bouton R
	  class ResteListener implements ActionListener {
		  public void actionPerformed(ActionEvent arg0){
			  if(reste > 9999){
		        	ecran.setText("Vous avez dépassé la limite des nombres grec !");
		        }
		        else if(reste == 0){
		        	ecran.setText("Le chiffre \"0\" n'existe pas en grec !");
		        }
		        else{ecran.setText(Detogr.convert(reste));}
		  }
	  }
	  
	  //Listener affecté au bouton =
	  class EgalListener implements ActionListener {
	    public void actionPerformed(ActionEvent arg0){
	    	if (enable == true){
	    		calcul();
			    update = true;
			    clicOperateur = false;
	    		tab_button[28].setEnabled(true);
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
	      if(!verificationGrec(ecran.getText())){
		    ecran.setText("Ce n'est pas un nombre grec !");
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
	      if(!verificationGrec(ecran.getText())){
		    ecran.setText("Ce n'est pas un nombre grec !");
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
	      if(!verificationGrec(ecran.getText())){
		    ecran.setText("Ce n'est pas un nombre grec !");
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
	      if(!verificationGrec(ecran.getText())){
		    ecran.setText("Ce n'est pas un nombre grec !");
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
	      tab_button[28].setEnabled(false);
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
