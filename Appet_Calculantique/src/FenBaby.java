import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import babylonien.*;
import fonts.*;

public class FenBaby extends Container {
	
	private JPanel container = new JPanel();
	  //Tableau stockant les �l�ments � afficher dans la calculatrice
	String[] tab_string = {"\uD808\uDF0B", "\uD808\uDC79", " ", "R", "=", "C", "+", "-", "*", "/"};
	  //Un bouton par �l�ment � afficher
	JButton[] tab_button = new JButton[tab_string.length];
	private JTextArea ecran = new JTextArea();				//cr�ation du JTextArea (notre �cran)
	private Dimension dim1 = new Dimension(60, 50);			//On d�clare la dimension des touches chiffres
	private Dimension dim2 = new Dimension(60, 41);			//On d�clare la dimension des touches d'op�rations
	private Dimension dim3 = new Dimension(240, 41);
	//On d�finit la police d'�criture � utiliser
    private Font police = LoadFonts.create();
	private Font policeText = new Font("Arial", Font.BOLD, 20);
	//d�claration de variables
	private String chiffre1;
	private int chiffre2;
	private boolean clicOperateur = false, update = false;
	private boolean enable = false;
	private String operateur = "";
	public static int reste;
		
	//m�thode de notre class
	public FenBaby(Dimension dim) throws FontFormatException, IOException{
		super(dim);
		initPanel();
	}

	public void initPanel() throws FontFormatException, IOException{		
	    //On initialise le conteneur avec tous les composants
		  initComposant();
		panel.add(container);
		panel.setBackground((java.awt.Color) Color(234,225,191));
	}
	
	private Object Color(int i, int j, int k) {
		Color couleur = new Color(i,j,k);
		return couleur;
	}
	
	//m�thode qui fait appel � la class de chargement de police
	static class LoadFonts extends LoadFontBaby{
		static Font create(){
			Font tmp = new Font("Arial", Font.BOLD, 20);
			return change(tmp);
		}
	}
	
	boolean verification (BabyDeci baby){
    	return baby.validBaby();
    }
	
	 private void initComposant() throws FontFormatException, IOException{
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
		        case 2 :
		        	tab_button[i] = new JButton("espace");
		        	tab_button[i].setPreferredSize(dim3);
			        chiffre.add(tab_button[i]);
					tab_button[i].addActionListener(new EspaceListener());
				    break;
		        case 3 :
		    	  tab_button[i].addActionListener(new ResteListener());
		    	  tab_button[i].setEnabled(false);
		    	  chiffre.add(tab_button[i]);
		    	  break;
		        case 4 :
		          tab_button[i].addActionListener(new EgalListener());
		          chiffre.add(tab_button[i]);
		          break;
		        case 5 :
		          tab_button[i].setForeground(Color.red);
		          tab_button[i].addActionListener(new ResetListener());
		          operateur.add(tab_button[i]);
		          break;
		        case 6 :
		          tab_button[i].addActionListener(new PlusListener());
		          tab_button[i].setPreferredSize(dim2);
		          operateur.add(tab_button[i]);
		          break;
		        case 7 :
		          tab_button[i].addActionListener(new MoinsListener());
		          tab_button[i].setPreferredSize(dim2);
		          operateur.add(tab_button[i]);
		          break;	
		        case 8 :	
		          tab_button[i].addActionListener(new MultiListener());
		          tab_button[i].setPreferredSize(dim2);
		          operateur.add(tab_button[i]);
		          break;
		        case 9 :
		          tab_button[i].addActionListener(new DivListener());
		          tab_button[i].setPreferredSize(dim2);
		          operateur.add(tab_button[i]);
		          break;
		        default :
		          //Par d�faut, ce sont les premiers �l�ments du tableau
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
		      BabyDeci babylonien1 = new BabyDeci(chiffre1);
		      BabyDeci babylonien2 = new BabyDeci(ecran.getText());
		      chiffre2 = babylonien1.convBaby() + babylonien2.convBaby();
		      DeciBaby babylonien = new DeciBaby(chiffre2);
		      if(!verification(babylonien2)){
		    	  ecran.setFont(policeText);
		    	  ecran.setText("Ce n'est pas un nombre babylonien !");
		      }
	    	  else if( chiffre2 > 777_600_000){
	    		  ecran.setFont(policeText);
                  ecran.setText("Vous avez d�pass� la limite des nombres babylonien !");
	          }
	          else{
	        	ecran.setFont(police);
	            ecran.setText(babylonien.convDeciBaby());}
		    }
		    if(operateur.equals("-")){
		    	BabyDeci babylonien1 = new BabyDeci(chiffre1);
			    BabyDeci babylonien2 = new BabyDeci(ecran.getText());
			    chiffre2 = babylonien1.convBaby() - babylonien2.convBaby();
			    DeciBaby babylonien = new DeciBaby(chiffre2);
			    if(!verification(babylonien2)){
			    	ecran.setFont(policeText);
			    	ecran.setText("Ce n'est pas un nombre babylonien !");
			    }
		    	else if( chiffre2 > 777_600_000){
		    		ecran.setFont(policeText);
                    ecran.setText("Vous avez d�pass� la limite des nombres babylonien !");
                }
			    else if(chiffre2 == 0){
			    	ecran.setFont(policeText);
		        	ecran.setText("Le chiffre \"0\" n'existe pas en babylonien !");
		        }
                else if(chiffre2 < 0){
                	ecran.setFont(policeText);
                    ecran.setText("Les nombres n�gatifs n'existent pas en babylonien !");
                }
                else{
                	ecran.setFont(police);
                	ecran.setText(babylonien.convDeciBaby());}
		    }          
		    if(operateur.equals("*")){
		    	BabyDeci babylonien1 = new BabyDeci(chiffre1);
			    BabyDeci babylonien2 = new BabyDeci(ecran.getText());
			    chiffre2 = babylonien1.convBaby() * babylonien2.convBaby();
			    DeciBaby babylonien = new DeciBaby(chiffre2);
			    if(!verification(babylonien2)){
			    	ecran.setFont(policeText);
			    	ecran.setText("Ce n'est pas un nombre babylonien !");
			    }
		    	else if( chiffre2 > 777_600_000){
		    		ecran.setFont(policeText);
                    ecran.setText("Vous avez d�pass� la limite des nombres babylonien !");
                }
                else{
                	ecran.setFont(police);
			      ecran.setText(babylonien.convDeciBaby());}
		    }     
		    if(operateur.equals("/")){
		      try{
		    	  BabyDeci babylonien1 = new BabyDeci(chiffre1);
			      BabyDeci babylonien2 = new BabyDeci(ecran.getText());
			      chiffre2 = babylonien1.convBaby() / babylonien2.convBaby();			      
		          reste = babylonien1.convBaby() % babylonien2.convBaby();
		          DeciBaby babylonien = new DeciBaby(chiffre2);
		          if(!verification(babylonien2)){
		        	  ecran.setFont(policeText);
			    	  ecran.setText("Ce n'est pas un nombre babylonien !");
			      }
		    	  else if( chiffre2 > 777_600_000){
		    		  ecran.setFont(policeText);
	                  ecran.setText("Vous avez d�pass� la limite des nombres babylonien !");
	              }
	              else{
	            	  ecran.setFont(police);
	            	  ecran.setText(babylonien.convDeciBaby());}
		      } catch(ArithmeticException e) {
		    	  ecran.setFont(policeText);
		          ecran.setText("Op�ration impossible");
		      }
		    }
		  }

		//Listener utilis� pour les chiffres
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
		    }
		  }
		  
		  //Listener affect� � la touche espace
		  class EspaceListener implements ActionListener {
				public void actionPerformed(ActionEvent e){
					String str = " ";
					if(update){
				        update = false;
				    }
				    else{
					if(!ecran.getText().equals("")){
						str = ecran.getText() + str;
					}
					ecran.setText(str);
				    }
				}
			}
		  
		  //Listener affect� au bouton R
		  class ResteListener implements ActionListener {
			  public void actionPerformed(ActionEvent arg0){
				  DeciBaby babylonien = new DeciBaby(reste);
				  ecran.setFont(police);
				  if( reste > 777_600_000){
					  ecran.setFont(policeText);
	                  ecran.setText("Vous avez d�pass� la limite des nombres babylonien !");
	              }
	              else{ecran.setText(babylonien.convDeciBaby());}
			  }
		  }
		  
		  //Listener affect� au bouton =
		  class EgalListener implements ActionListener {
		    public void actionPerformed(ActionEvent arg0){
		    	  BabyDeci babylonien = new BabyDeci(ecran.getText());
		    	  ecran.setFont(police);
			    if(!verification(babylonien)){
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
		    		tab_button[3].setEnabled(true);
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
		    	BabyDeci babylonien = new BabyDeci(ecran.getText());
		    	ecran.setFont(police);
		      if(!verification(babylonien)){
		    	ecran.setFont(policeText);
			    ecran.setText("Ce n'est pas un nombre babylonien !");
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
		    	BabyDeci babylonien = new BabyDeci(ecran.getText());
		    	ecran.setFont(police);
			  if(!verification(babylonien)){
				  ecran.setFont(policeText);
				  ecran.setText("Ce n'est pas un nombre babylonien !");
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
		    	BabyDeci babylonien = new BabyDeci(ecran.getText());
		    	ecran.setFont(police);
			  if(!verification(babylonien)){
				ecran.setFont(policeText);
				ecran.setText("Ce n'est pas un nombre babylonien !");
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
		    	BabyDeci babylonien = new BabyDeci(ecran.getText());
		    	ecran.setFont(police);
			  if(!verification(babylonien)){
				ecran.setFont(policeText);
				ecran.setText("Ce n'est pas un nombre babylonien !");
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
		      ecran.setFont(police);
		      clicOperateur = false;
		      reste =  0;
		      tab_button[3].setEnabled(false);
		      update = true;
		      enable = false;
		      chiffre2 = 0;
		      chiffre1 = "";
		      operateur = "";
		      ecran.setText("");
		    }
		  }
}