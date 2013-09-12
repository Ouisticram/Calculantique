// importation des packages des langues
import japonais.*;
import bibinaire.*;
import babylonien.*;
import maya.*;
import romain.*;
import egyptien.*;
import grec.*;
import shadok.*;

//importation des element java utile pour l'applet
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import fonts.*;
//import t2s.son.LecteurTexte; a rajouter dans la version finale pour le son !


public class Conversion extends JApplet
{
	private static final long serialVersionUID = 7478007682114575787L;
	private Container c;
	private DefaultComboBoxModel<String> comboModel;
	private JComboBox<String> combo;
	private DefaultComboBoxModel<String> comboModel2;
	private JComboBox<String> combo2;
	String[] tab_string_Ba = {"\uD808\uDF0B","\uD808\uDC79", " "};
	JButton[] tab_button_Ba = new JButton[tab_string_Ba.length];
	String[] tab_string_Eg = {"\uD80C\uDFFA", "\uD80C\uDF86", "\uD80C\uDF62", "\uD80C\uDDBC", "\uD80C\uDCAD", "\uD80C\uDD8F", "\uD80C\uDC4F"};
	JButton[] tab_button_Eg = new JButton[tab_string_Eg.length];
	String[] tab_string_Gr = {"\u03B1", "\u03B2", "\u03B3", "\u03B4", "\u03B5", "\u03DD", "\u03B6", "\u03B7", "\u03B8", "\u03B9", "\u03BA", "\u03BB", "\u03BC", "\u03BD", "\u03BE", "\u03BF", "\u03C0", "\u03DF", "\u03C1", "\u03C3", "\u03C4", "\u03C5", "\u03C6", "\u03C7", "\u03C8", "\u03C9", "\u03E1", "\u02B9"};
	JButton[] tab_button_Gr = new JButton[tab_string_Gr.length];
	String[] tab_string_Ja = {"\u3007", "\u4E00", "\u4E8C", "\u4E09", "\u56DB", "\u4E94", "\u516D", "\u4E03", "\u516B", "\u4E5D", "\u5341", "\u767E", "\u5343", "\u4E07"};
	JButton[] tab_button_Ja = new JButton[tab_string_Ja.length];
	String[] tab_string_Ma = {"\uF7FB", ".", "-", " "};
	JButton[] tab_button_Ma = new JButton[tab_string_Ma.length];
	//On definit les polices d'ecriture a utiliser
    final Font policeBa = LoadFontB.create();
    final Font policeMa = LoadFontM.create();
    final Font policeEg = LoadFontE.create();
    public Font policeJa = new Font("Arial", Font.BOLD, 20);
    final Font police = new Font("Arial", Font.BOLD,15);
    Font policeEspace = new Font("Courier", Font.BOLD, 20);
	private JTextArea input1;
	private JTextArea input2;
	private JButton bouton;
	// private JButton lecteur;
	// private LecteurTexte lt;    a rajouter a la version finale pour le son !! 
	private Dimension dim1 = new Dimension(100, 50);
	//private Dimension dim2 = new Dimension(46, 44);
	private Dimension dim3 = new Dimension(65, 50);
	private Dimension dimG = new Dimension(50, 40);
	private Dimension dim4 = new Dimension(80, 70);
	private Dimension dim5 = new Dimension(240, 41);
	private String choix = "Decimal";
	private String choix2 = "Decimal";
	boolean clicked = false;
	String tmp = "";

    public Conversion() {
    	super();
    	initialize();
    }
    
    //méthode qui fait appel à la class de chargement de police
  	static class LoadFontE extends LoadFontEgyp{
  		static Font create(){
  			Font tmp = new Font("Arial", Font.BOLD, 20);
  			return change(tmp);
  		}
  	}
  	
  	//méthode qui fait appel à la class de chargement de police
  	static class LoadFontM extends LoadFontMaya{
  		static Font create(){
  			Font tmp = new Font("Arial", Font.BOLD, 20);
  			return change(tmp);
  		}
  	}
  	
  	//méthode qui fait appel à la class de chargement de police
  	static class LoadFontB extends LoadFontBaby{
  		static Font create(){
  			Font tmp = new Font("Arial", Font.BOLD, 20);
  			return change(tmp);
  		}
  	}
    
    boolean Verifentier(String test){
    	boolean correct = true;
    	@SuppressWarnings("unused")
		long monNombre = 0;
    	try {
    		monNombre = Long.parseLong(test.trim());
    	} 
    	catch (NumberFormatException e) {
    		correct = false;
    	}
    	return correct;
    }
    
    boolean Verifchaine(String test){
    	boolean correct = false;
		@SuppressWarnings("unused")
		long monNombre = 0;
    	try {
    		monNombre = Long.parseLong(test.trim());
    	} 
    	catch (NumberFormatException e) {
    		correct = true;
    	}
    	return correct;
    }
           
   	boolean Verifromain(RomainDeci romain){
   		return romain.validSaisie();
   	}
    
    // Changement des polices pour la premiere zone de texte
    void changePolice1(){
    	input1.setFont(police);
    	input1.setColumns(20);
		input1.setRows(2);
    }
    
    void changePoliceBa1(){    	
    	input1.setFont(policeBa);
    	input1.setColumns(8);
		input1.setRows(2);
    }
    void changePoliceEg1(){
	    input1.setFont(policeEg);
	    input1.setColumns(12);
		input1.setRows(2);
    }
    
    void changePoliceJa1(){
    	input1.setFont(policeJa);
    	input1.setColumns(20);
		input1.setRows(3);
    }    
    
    void changePoliceMa1(){
    	input1.setFont(policeMa);
    	input1.setColumns(10);
		input1.setRows(2);
    }
    
    
 // Changement des polices pour la deuxieme zone de texte
    void changePolice2(){
    	input2.setFont(police);
    	input2.setColumns(20);
		input2.setRows(2);
    }
    
    void changePoliceBa2(){
    	input2.setFont(policeBa);
    	input2.setColumns(8);
		input2.setRows(2);
    }
    void changePoliceEg2(){
	    input2.setFont(policeEg);
	    input2.setColumns(12);
		input2.setRows(2);
    }
    
    void changePoliceJa2(){    	
    	input2.setFont(policeJa);
    	input2.setColumns(20);
		input2.setRows(3);
    }    
    
    void changePoliceMa2(){
    	input2.setFont(policeMa);
    	input2.setColumns(10);
		input2.setRows(2);
    }
    
    private void initialize() {
	    final JPanel bout = new JPanel();      
	    final JPanel menu = new JPanel();   
	    final JPanel menu1 = new JPanel();
	    final JPanel champ1 = new JPanel();
	    final JPanel champ2 = new JPanel();
	    final JPanel clavier = new JPanel();
	    clavier.setPreferredSize(new Dimension(300, 350));
	    //JPanel boutplay = new JPanel();	

    //Listener utilise pour les chiffres
	//Permet de stocker les chiffres et de les afficher
	class ChiffreListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			//On affiche le chiffre additionnel dans le label
			String str = ((JButton)e.getSource()).getText();
			if(!input1.getText().equals("")){
				str = input1.getText() + str;
			}
			input1.setText(str);
		}
	}
	
	class EspaceListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			String str = " ";
			if(!input1.getText().equals("")){
				str = input1.getText() + str;
			}
			input1.setText(str);
		}
	}
	
	//Listener affecte au bouton retour a la ligne
	  class RetourLigneListener implements ActionListener {
			public void actionPerformed(ActionEvent e){
				String str = "\n";
				if(!input1.getText().equals("")){
					str = input1.getText() + str;
				}
				input1.setText(str);
			}
		}
	
	class ZoneTextListener implements MouseListener {
		public void mouseEntered(MouseEvent e) {
	    	if (input1.getText().equals("Tapez votre nombre ici")){
	    		input1.setText("");
	    	}
		}
		public void mouseExited(MouseEvent ev) {
			if (input1.getText().equals("") && !clicked){
		    	input1.setText("Tapez votre nombre ici");
		    }
		}
		public void mousePressed(MouseEvent eve) {
			clicked = true;
		}
		@Override
		public void mouseClicked(MouseEvent arg0) {
			clicked = true;
		}
		@Override
		public void mouseReleased(MouseEvent arg0) {}
	}
		c = getContentPane();
		//comboModel = new DefaultComboBoxModel();
		comboModel = new DefaultComboBoxModel<String>();
		comboModel.addElement("Babylonien");
		comboModel.addElement("Bibinaire");
		comboModel.addElement("Decimal");
		comboModel.addElement("Egyptien");
		comboModel.addElement("Grec");
		comboModel.addElement("Japonais");
		comboModel.addElement("Maya");
		comboModel.addElement("Romain");
		comboModel.addElement("Shadok");	
		comboModel.setSelectedItem("Decimal");
		combo = new JComboBox<String>(comboModel);
		combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input1.setText("");
				clicked = true;
				choix = (String)combo.getSelectedItem();
				if ((String)combo.getSelectedItem() == "Babylonien") {
					clavier.setVisible(false);
					clavier.removeAll();
					changePoliceBa1();
					for(int i = 0; i < tab_string_Ba.length; i++){
						tab_button_Ba[i] = new JButton(tab_string_Ba[i]);
						tab_button_Ba[i].setPreferredSize(dim3);
						switch(i){
						case 2 :
							  tab_button_Ba[i] = new JButton("espace");
					          tab_button_Ba[i].setPreferredSize(dim5);
					          clavier.add(tab_button_Ba[i]);
							  tab_button_Ba[i].addActionListener(new EspaceListener());
						      break;
						default :
							tab_button_Ba[i].setFont(policeBa);
							clavier.add(tab_button_Ba[i]);
							tab_button_Ba[i].addActionListener(new ChiffreListener());
							break;
						}
					}					
					revalidate();
					clavier.setVisible(true);
				}
				else if ((String)combo.getSelectedItem() == "Egyptien") {
					clavier.setVisible(false);
					clavier.removeAll();
					changePoliceEg1();
					for(int i = 0; i < tab_string_Eg.length; i++){
						tab_button_Eg[i] = new JButton(tab_string_Eg[i]);
						tab_button_Eg[i].setPreferredSize(dim4);
						switch(i){
						default :
							tab_button_Eg[i].setFont(policeEg);
							clavier.add(tab_button_Eg[i]);
							tab_button_Eg[i].addActionListener(new ChiffreListener());
							break;
						}
					}
					clavier.setVisible(true);
					revalidate();
				}
				else if ((String)combo.getSelectedItem() == "Grec") {
					clavier.setVisible(false);
					clavier.removeAll();
					changePolice1();
					for(int i = 0; i < tab_string_Gr.length; i++){
						tab_button_Gr[i] = new JButton(tab_string_Gr[i]);
						tab_button_Gr[i].setPreferredSize(dimG);
						switch(i){
						default :
							clavier.add(tab_button_Gr[i]);
							tab_button_Gr[i].addActionListener(new ChiffreListener());
							break;
						}
					}					
					revalidate();
					clavier.setVisible(true);
				}
				else if ((String)combo.getSelectedItem() == "Japonais") {
					clavier.setVisible(false);
					clavier.removeAll();
					changePoliceJa1();
					for(int i = 0; i < tab_string_Ja.length; i++){
						tab_button_Ja[i] = new JButton(tab_string_Ja[i]);
						tab_button_Ja[i].setPreferredSize(dim3);
						switch(i){
						default :
							clavier.add(tab_button_Ja[i]);
							tab_button_Ja[i].addActionListener(new ChiffreListener());
							break;
						}
					}
					clavier.setVisible(true);
					revalidate();
				}
				else if ((String)combo.getSelectedItem() == "Maya") {
					clavier.setVisible(false);
					clavier.removeAll();
					changePoliceMa1();
					for(int i = 0; i < tab_string_Ma.length; i++){
						tab_button_Ma[i] = new JButton(tab_string_Ma[i]);
						tab_button_Ma[i].setPreferredSize(dim3);
						switch(i){
						case 3:
				        	tab_button_Ma[i] = new JButton("\u21B5");
				        	tab_button_Ma[i].setPreferredSize(dim1);
				        	tab_button_Ma[i].setFont(policeEspace);
					        clavier.add(tab_button_Ma[i]);
					        tab_button_Ma[i].addActionListener(new RetourLigneListener());
						    break;
						default :
							tab_button_Ma[i].setFont(policeMa);
							clavier.add(tab_button_Ma[i]);
							tab_button_Ma[i].addActionListener(new ChiffreListener());
							break;
						}
					}					
					revalidate();
					clavier.setVisible(true);
				}
				else{
					clavier.setVisible(false);
					clavier.removeAll();
					changePolice1();
					revalidate();
					clavier.setVisible(true);
					}
			}
		});
		
		comboModel2 = new DefaultComboBoxModel<String>();
		comboModel2.addElement("Babylonien");
		comboModel2.addElement("Bibinaire");
		comboModel2.addElement("Decimal");
		comboModel2.addElement("Egyptien");
		comboModel2.addElement("Grec");
		comboModel2.addElement("Japonais");
		comboModel2.addElement("Maya");
		comboModel2.addElement("Romain");
		comboModel2.addElement("Shadok");
		comboModel2.setSelectedItem("Decimal");
		combo2 = new JComboBox<String>(comboModel2);
		combo2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choix2 = (String)combo2.getSelectedItem();
			}
		});
		
		
		
		bouton = new JButton ("Convertir");
		bouton.setPreferredSize(dim1);
		policeJa = bouton.getFont();
		bouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (choix == "Babylonien"){ //Ok
					if (choix2 == "Babylonien"){
						changePoliceBa2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) // verifie
						{				
						BabyDeci tmp = new BabyDeci (input1.getText().trim()); // on convertit d'abord en decimal
							if(tmp.validBaby()){							
							input2.setText(input1.getText().trim());
							}
							else{
								changePolice2();
								input2.setText("mauvaise saisie");
							}
						}
						else {
							changePolice2();
							input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Bibinaire"){ //Ok
						changePolice2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) // verifie
						{						
						BabyDeci tmp = new BabyDeci (input1.getText().trim()); // on convertit d'abord en decimal
							if (tmp.validBaby()) {						
							long tmp1 = Long.valueOf(tmp.convBaby());
							input2.setText(Decitobibi(Long.valueOf(tmp1))); // on convertit le decimal en bibinaire
							}
							else input2.setText("mauvaise saisie");					
						}
						else {input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Decimal"){ //Ok
						changePolice2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) // verifie
						{
						BabyDeci tmp = new BabyDeci (input1.getText().trim());
							if (tmp.validBaby()) {
							input2.setText(String.valueOf(tmp.convBaby()));
							}
							else input2.setText("mauvaise saisie");		
						}
						else {input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Egyptien"){ // Ok
						changePoliceEg2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) 
						{
						BabyDeci tmp = new BabyDeci (input1.getText().trim()); // on convertit d'abord en decimal
							if(tmp.validBaby()){							
							long tmp1 = Long.valueOf(tmp.convBaby()); // on recupere le valeur en decimal
							Decitoegypt egypt = new Decitoegypt(); // nouvelle variable de type decitoegypt
							input2.setText(egypt.convertionNombre(tmp1)); // on convertit le decimal en egyptien
							}						
							else{
								changePolice2();
								input2.setText("mauvaise saisie");
							}
						}
						else {
							changePolice2();
							input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Grec"){ // Ok
						changePolice2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) 
						{
						BabyDeci tmp = new BabyDeci (input1.getText().trim()); // on convertit d'abord en decimal
							if(tmp.validBaby()){							
							long tmp1 = Long.valueOf(tmp.convBaby());
							input2.setText(DecimalToGrec.main(tmp1)); // on convertit le decimal en grec
							}						
							else input2.setText("mauvaise saisie");
						}
						else {input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Japonais"){ //Ok
						changePoliceJa2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) 
						{
						BabyDeci tmp = new BabyDeci (input1.getText().trim()); // on convertit d'abord en decimal
							if(tmp.validBaby()){						
							long tmp1 = Long.valueOf(tmp.convBaby());
							input2.setText(Decitojapo(Long.valueOf(tmp1))); // on convertit le decimal en japonnais
							}						
							else input2.setText("mauvaise saisie");
						}
						else {input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Maya"){ //Ok
						changePoliceMa2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) 
						{
						BabyDeci tmp = new BabyDeci (input1.getText().trim()); // on convertit d'abord en decimal
							if(tmp.validBaby()){
							long tmp1 = Long.valueOf(tmp.convBaby()); // on recupere le valeur en decimal
							Maya maya = new Maya(); // nouvelle variable de type Maya
							input2.setText(maya.convertNombre(tmp1)); // on convertit le decimal en Maya
							}						
							else{
								changePolice2();
								input2.setText("mauvaise saisie");
							}
						}
						else {
							changePolice2();
							input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Romain"){ //Ok
						changePolice2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) 
						{
						BabyDeci tmp = new BabyDeci (input1.getText().trim()); // on convertit d'abord en decimal
							if(tmp.validBaby()) {
							long tmp1 = Long.valueOf(tmp.convBaby()); // on recupere le valeur en decimal
							DeciRomain maya = new DeciRomain(tmp1); // nouvelle variable de type DeciRomain
							input2.setText(maya.convertDeci()); // on convertit le decimal en Romain
							}						
							else input2.setText("mauvaise saisie");
						}
						else {input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Shadok"){ // Ok
						changePolice2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) 
						{
						BabyDeci tmp = new BabyDeci (input1.getText().trim()); // on convertit d'abord en decimal
							if(tmp.validBaby()) {
							long tmp1 = Long.valueOf(tmp.convBaby()); // on recupere le valeur en decimal
							Decimal shadok = new Decimal(0); // nouvelle variable de type Decimal(shadok)
							shadok.setVal(tmp1);
							input2.setText(shadok.ConvSha(tmp1)); // on convertit le decimal en Romain
							}						
							else input2.setText("mauvaise saisie");
						}
						else {input2.setText("Saisie non valide");}
					}	
				}
				else if (choix == "Bibinaire"){ //ok
					input1.setText(input1.getText().toUpperCase().trim());
					if (choix2 == "Babylonien"){
						changePoliceBa2();
						revalidate();
						if(Verifchaine(input1.getText().trim())){
						long tmp = Bibitodeci(input1.getText());
							if (Bibitodeci.VerifBibi(input1.getText().trim()))
							{
							DeciBaby baby = new DeciBaby((int) tmp);
							input2.setText(baby.convDeciBaby());
							}
							else{
								changePolice2();
								input2.setText("mauvaise saisie");
							}
						}
						else{
							changePolice2();
							input2.setText("Saisie non valide");}		
					}
					else if (choix2 == "Bibinaire"){ //Ok
						changePolice2();
						revalidate();
							if (Bibitodeci.VerifBibi(input1.getText().trim()))
							{
							tmp = input1.getText().trim();
							input2.setText(tmp);
							}
							else input2.setText("mauvaise saisie");
					}
					else if (choix2 == "Decimal"){ //Ok
						changePolice2();
						revalidate();
						if(Verifchaine(input1.getText().trim()))
						{
							if(Bibitodeci.VerifBibi(input1.getText().trim()))
							{
							long tmp1 = Bibitodeci(input1.getText().trim());
							input2.setText(String.valueOf(tmp1));
							}
							else input2.setText("mauvaise saisie");
						}
						else{input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Egyptien"){ // Ok
						changePoliceEg2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) 
						{
						int tmp1 = (int) Bibitodeci(input1.getText().trim());
							if (Bibitodeci.VerifBibi(input1.getText().trim()))
							{
							Decitoegypt egypt = new Decitoegypt(); 
							input2.setText(egypt.convertionNombre(tmp1));
							}
							else{
								changePolice2();
								input2.setText("mauvaise saisie");
							}
						}
						else {
							changePolice2();
							input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Grec"){ // Ok
						changePolice2();
						revalidate();
						if(Verifchaine(input1.getText().trim())){
						int tmp1 =(int) Bibitodeci(input1.getText().trim()); 
							if(Bibitodeci.VerifBibi(input1.getText().trim()))
							{
							input2.setText(DecimalToGrec.main(tmp1)); // on renvoie la valeur en grec
							}
							else input2.setText("mauvaise saisie");
						}
						else{input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Japonais"){ //OK
						changePoliceJa2();
						revalidate();
						if(Verifchaine(input1.getText().trim())){
						long tmp1 = Bibitodeci(input1.getText().trim());
							if(Bibitodeci.VerifBibi(input1.getText().trim()))
							{
							tmp = Decitojapo(tmp1); // on stock la conversion en japonnais dans tmp
							input2.setText(String.valueOf(tmp)); // on renvoie la valeur
							}
							else input2.setText("mauvaise saisie");
						}
						else{input2.setText("Saisie non valide");}	
					}
					else if (choix2 == "Maya"){ // Ok
						changePoliceMa2();
						revalidate();
						if(Verifchaine(input1.getText().trim())){
						int tmp = (int) Bibitodeci(input1.getText().trim()); // on convertit de bibinaire a decimal
							if(Bibitodeci.VerifBibi(input1.getText().trim()))
							{
							Maya maya = new Maya(); // nouvelle variable de type maya
							input2.setText(maya.convertNombre(tmp)); //renvoie la conversion du nombre
							}
							else{
								changePolice2();
								input2.setText("mauvaise saisie");
							}
						}
						else{
							changePolice2();
							input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Romain"){ // Ok
						changePolice2();
						revalidate();
						if(Verifchaine(input1.getText().trim())){
						int tmp = (int) Bibitodeci(input1.getText().trim()); // on convertit de bibinaire a decimal
							if(Bibitodeci.VerifBibi(input1.getText().trim()))
							{							
							DeciRomain romain = new DeciRomain(tmp); //variable de type deciRomain
							input2.setText(romain.convertDeci()); // on renvoie la conversion du nombre
							}
							else input2.setText("mauvaise saisie");
						}
						else{input2.setText("Caractere(s) non valide");}
					}
					else if (choix2 == "Shadok"){ // Ok
						changePolice2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) 
						{
						long tmp1 = Bibitodeci(input1.getText().trim()); // on convertit de bibinaire a decimal
							if(Bibitodeci.VerifBibi(input1.getText().trim()))
							{
							Decimal shadok = new Decimal(0); // nouvelle variable de type Decimal(shadok)
							shadok.setVal(tmp1);
							input2.setText(shadok.ConvSha(tmp1)); // on convertit le decimal en Romain
							}
							else input2.setText("mauvaise saisie");
						}
						else {input2.setText("Saisie non valide");}
					}
				}
				else if (choix == "Decimal"){
					if (choix2 == "Babylonien"){
						changePoliceBa2();
						revalidate();
						if(Verifentier(input1.getText().trim())){ // Ok
							DeciBaby tmp = new DeciBaby(Integer.valueOf(input1.getText().trim()));
							input2.setText(tmp.convDeciBaby());
						}
						else{
							changePolice2();
							input2.setText("Caractere(s) non valide");}
					}
					else if (choix2 == "Bibinaire"){	
						changePolice2();
						revalidate();
						if(Verifentier(input1.getText())){ //Ok
							tmp = Decitobibi(Long.valueOf(input1.getText().trim()));
							input2.setText(tmp);
						}
						else{input2.setText("Caractere(s) non valide");}
					}
					else if (choix2 == "Decimal"){ // Ok
						changePolice2();
						revalidate();
						if(Verifentier(input1.getText().trim())){
						tmp = input1.getText().trim();
						input2.setText(tmp);}
						else{input2.setText("Caractere(s) non valide");}
					}
					else if (choix2 == "Egyptien"){ // Ok
						changePoliceEg2();
						revalidate();
						if(Verifentier(input1.getText().trim())){ 
							Decitoegypt egypt = new Decitoegypt();
							input2.setText(egypt.convertionNombre(Long.valueOf(input1.getText().trim())));
						}
						else{
							changePolice2();
							input2.setText("Caractere(s) non valide");}
					}
					else if (choix2 == "Grec"){ // Ok
						changePolice2();
						revalidate();
						if(Verifentier(input1.getText().trim())){
							tmp = DecimalToGrec.main(Long.valueOf(input1.getText().trim()));
							input2.setText(tmp);
						}
						else{input2.setText("Caractere(s) non valide");}
					}
					else if (choix2 == "Japonais"){ // Ok
						changePoliceJa2();
						revalidate();
						if(Verifentier(input1.getText().trim())){
						tmp = Decitojapo(Long.valueOf(input1.getText().trim()));
						input2.setText(tmp);
						}
						else{input2.setText("Caractere(s) non valide");}
					}
					else if (choix2 == "Maya"){ // Ok
						changePoliceMa2();
						revalidate();
						if(Verifentier(input1.getText().trim())){
						Maya tmp = new Maya();
						input2.setText(tmp.convertNombre(Long.valueOf(input1.getText().trim())));
						}
						else{
							changePolice2();
							input2.setText("Caractere(s) non valide");}
					}
					else if (choix2 == "Romain"){ //Ok
						changePolice2();
						revalidate();
						if(Verifentier(input1.getText().trim())){
						DeciRomain tmp = new DeciRomain(Long.valueOf(input1.getText().trim()));
						input2.setText(tmp.convertDeci());
						}
						else{input2.setText("Caractere(s) non valide");}
					}
					else if (choix2 == "Shadok"){//en cours
						changePolice2();
						revalidate();
						if(Verifentier(input1.getText().trim())){
						Decimal tmp = new Decimal(0);
						input2.setText(tmp.ConvSha(Long.valueOf(input1.getText().trim())));
						}
						else{input2.setText("Caractere(s) non valide");}
					}
				}
				else if (choix == "Egyptien"){
					if (choix2 == "Babylonien"){ // Ok 
						changePoliceBa2();
						revalidate();
						if(Verifchaine(input1.getText().trim())){
						Egyptodeci egypt = new Egyptodeci(input1.getText().trim());
							if(egypt.validationEgypt()){
							long tmp = egypt.convertion();
							DeciBaby baby = new DeciBaby(tmp);
							input2.setText(baby.convDeciBaby());
							}				
							else{
								changePolice2();
								input2.setText("mauvaise saisie");
							}
						}
						else{
							changePolice2();
							input2.setText("Saisie non valide");}	
					}
					else if (choix2 == "Bibinaire"){ // Ok
						changePolice2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) // verifie
						{
						Egyptodeci egypt = new Egyptodeci(input1.getText().trim());
							if(egypt.validationEgypt()){
							long tmp = egypt.convertion();
							input2.setText(Decitobibi(Long.valueOf(tmp))); // on convertit le decimal en bibinaire
							}						
							else input2.setText("mauvaise saisie");
						}
						else {input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Decimal"){ // Ok
						changePolice2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) // verifie
						{
						Egyptodeci egypt = new Egyptodeci(input1.getText().trim());
							if(egypt.validationEgypt()){
							long tmp = egypt.convertion();
							input2.setText(String.valueOf(tmp));
							}						
							else input2.setText("mauvaise saisie");
						}
						else {input2.setText("Saisie non valide");}	
					}
					else if (choix2 == "Egyptien"){ // Ok
						changePoliceEg2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) // verifie
						{
						Egyptodeci egypt = new Egyptodeci(input1.getText().trim());
							if(egypt.validationEgypt()){
							input2.setText(input1.getText().trim());
							}
							else{
								changePolice2();
								input2.setText("mauvaise saisie");
							}
						}
						else {
							changePolice2();
							input2.setText("Saisie non valide");}	
					}
					else if (choix2 == "Grec"){ // ok
						changePolice2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) // verifie
						{
						Egyptodeci egypt = new Egyptodeci(input1.getText().trim());
							if(egypt.validationEgypt()){
							long tmp = egypt.convertion();
							input2.setText(DecimalToGrec.main(tmp));
							}
							else input2.setText("mauvaise saisie");
						}
						else {input2.setText("Saisie non valide");}	
					}
					else if (choix2 == "Japonais"){ // ok
						changePoliceJa2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) // verifie
						{
						Egyptodeci egypt = new Egyptodeci(input1.getText().trim());
							if(egypt.validationEgypt()){
							long tmp = egypt.convertion();
							input2.setText(Decitojapo(tmp));
							}
							else input2.setText("mauvaise saisie");
						}
						else {input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Maya"){ //ok
						changePoliceMa2();
						revalidate();
						if(Verifchaine(input1.getText().trim())){
						Egyptodeci egypt = new Egyptodeci(input1.getText().trim());
							if(egypt.validationEgypt()){
							long tmp = egypt.convertion();
							Maya maya = new Maya ();
							input2.setText(maya.convertNombre(tmp));
							}
							else{
								changePolice2();
								input2.setText("mauvaise saisie");
							}
						}
						else{
							changePolice2();
							input2.setText("Saisie non valide");}	
					}
					else if (choix2 == "Romain"){ //ok
						changePolice2();
						revalidate();
						if(Verifchaine(input1.getText().trim())){
						Egyptodeci egypt = new Egyptodeci(input1.getText().trim());
							if(egypt.validationEgypt()){
							long tmp = egypt.convertion();
							DeciRomain romain = new DeciRomain(tmp);
							input2.setText(romain.convertDeci());
							}
							else input2.setText("mauvaise saisie");
						}
						else{input2.setText("Saisie non valide");}	
					}
					else if (choix2 == "Shadok"){ //ok
						changePolice2();
						revalidate();
						if(Verifchaine(input1.getText().trim())){
						Egyptodeci egypt = new Egyptodeci(input1.getText().trim());
							if(egypt.validationEgypt()){
							long tmp = egypt.convertion();
							Decimal shadok = new Decimal(tmp);
							input2.setText(shadok.ConvSha(tmp));
							}
							else input2.setText("mauvaise saisie");
						}
						else{input2.setText("Saisie non valide");}	
					}						
				}
				else if (choix == "Grec"){ 
					if (choix2 == "Babylonien"){ //Ok
						changePoliceBa2();
						revalidate();
						if(Verifchaine(input1.getText().trim())){
							if(GrecToDecimal.verification(input1.getText().trim())){
							long tmp = GrecToDecimal.main(input1.getText().trim());
							DeciBaby baby = new DeciBaby(tmp);
							input2.setText(baby.convDeciBaby());
							}
							else{
								changePolice2();
								input2.setText("mauvaise saisie");
							}
						}
						else{
							changePolice2();
							input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Bibinaire"){ //ok
						changePolice2();
						revalidate();
						if(Verifchaine(input1.getText().trim())){
							if(GrecToDecimal.verification(input1.getText().trim())){
							long tmp = GrecToDecimal.main(input1.getText().trim());
							input2.setText(Decitobibi(tmp));
							}
							else input2.setText("mauvaise saisie");
						}
						else{input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Decimal"){ // Ok
						changePolice2();
						revalidate();
						if(Verifchaine(input1.getText().trim())){
							if(GrecToDecimal.verification(input1.getText().trim())){
							long tmp = GrecToDecimal.main(input1.getText().trim());
							input2.setText(String.valueOf(tmp));
							}
							else input2.setText("mauvaise saisie");
						}
						else{input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Egyptien"){ // Ok
						changePoliceEg2();
						revalidate();
						if(Verifchaine(input1.getText().trim())){
							if(GrecToDecimal.verification(input1.getText().trim())){
							long tmp = GrecToDecimal.main(input1.getText().trim());
							Decitoegypt egypt = new Decitoegypt();
							input2.setText(egypt.convertionNombre(tmp));
							}
							else{
								changePolice2();
								input2.setText("mauvaise saisie");
							}
						}
						else{
							changePolice2();
							input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Grec"){ //Ok
						changePolice2();
						revalidate();
						if(Verifchaine(input1.getText().trim())){
							if(GrecToDecimal.verification(input1.getText().trim())){
							long tmp = GrecToDecimal.main(input1.getText().trim());
							input2.setText(DecimalToGrec.main(tmp));
							}
							else input2.setText("mauvaise saisie");
						}
						else{input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Japonais"){ // ok
						changePoliceJa2();
						revalidate();
						if(Verifchaine(input1.getText().trim())){
							if(GrecToDecimal.verification(input1.getText().trim())){
							long tmp = GrecToDecimal.main(input1.getText().trim());
							input2.setText(Decitojapo(tmp));
							}
							else input2.setText("mauvaise saisie");
						}
						else{input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Maya"){ //Ok
						changePoliceMa2();
						revalidate();
						if(Verifchaine(input1.getText().trim())){
							if(GrecToDecimal.verification(input1.getText().trim())){
							long tmp = GrecToDecimal.main(input1.getText().trim());
							Maya maya = new Maya();
							input2.setText(maya.convertNombre(tmp));
							}
							else{
								changePolice2();
								input2.setText("mauvaise saisie");
							}
						}
						else{
							changePolice2();
							input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Romain"){ // Ok
						changePolice2();
						revalidate();
						if(Verifchaine(input1.getText().trim())){
							if(GrecToDecimal.verification(input1.getText().trim())){
							long tmp = GrecToDecimal.main(input1.getText().trim());
							DeciRomain romain = new DeciRomain(tmp);
							input2.setText(romain.convertDeci());
							}
							else input2.setText("mauvaise saisie");
						}
						else{input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Shadok"){ // ok
						changePolice2();
						revalidate();
						if(Verifchaine(input1.getText().trim())){
							if(GrecToDecimal.verification(input1.getText().trim())){
							long tmp = GrecToDecimal.main(input1.getText().trim());
							Decimal shadok = new Decimal(tmp);
							input2.setText(shadok.ConvSha(tmp));
							}
							else input2.setText("mauvaise saisie");
						}
						else{input2.setText("Saisie non valide");}
					}						
				}
				else if (choix == "Japonais"){ 
					if (choix2 == "Babylonien"){ // Ok
						changePoliceBa2();
						revalidate();
						if(Verifchaine(input1.getText().trim())){
						long tmp = Japotodeci(input1.getText().trim());
							if(Japotodeci.verif(input1.getText().trim()))
							{
							DeciBaby baby = new DeciBaby((int) tmp);
							input2.setText(baby.convDeciBaby());
							}
							else{
								changePolice2();
								input2.setText("mauvaise saisie");
							}
						}
						else{
							changePolice2();
							input2.setText("Saisie non valide");}		
					}
					else if (choix2 == "Bibinaire"){ // OK
						changePolice2();
						revalidate();
						if(Verifchaine(input1.getText().trim())){
						long tmp1 = Japotodeci(input1.getText().trim());
							if(Japotodeci.verif(input1.getText().trim()))
							{
							tmp = Decitobibi(tmp1);
							input2.setText(tmp);
							}
							else input2.setText("mauvaise saisie");
						}
						else{input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Decimal"){ // Ok
						changePolice2();
						revalidate();
						if(Verifchaine(input1.getText().trim())){
						long tmp1 = Japotodeci(input1.getText().trim());
							if(Japotodeci.verif(input1.getText().trim()))
							{
							input2.setText(String.valueOf(tmp1));
							}
							else input2.setText("mauvaise saisie");
						}
						else{input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Egyptien"){ // Ok
						changePoliceEg2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) 
						{
						int tmp1 = (int) Japotodeci(input1.getText().trim());
							if(Japotodeci.verif(input1.getText().trim()))
							{
							Decitoegypt egypt = new Decitoegypt(); 
							input2.setText(egypt.convertionNombre(tmp1)); 
							}
							else{
								changePolice2();
								input2.setText("mauvaise saisie");
							}
						}
						else {
							changePolice2();
							input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Grec"){ //Ok
						changePolice2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) 
						{
						int tmp1 = (int) Japotodeci(input1.getText().trim());
							if(Japotodeci.verif(input1.getText().trim()))
							{
							input2.setText(DecimalToGrec.main(tmp1)); // on convertit le decimal en grec
							}
							else input2.setText("mauvaise saisie");
						}
						else {input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Japonais"){ // Ok
						changePoliceJa2();
						revalidate();
						if(Verifchaine(input1.getText().trim())){
							if(Japotodeci.verif(input1.getText().trim()))
							{
							tmp = input1.getText().trim();
							input2.setText(tmp);
							}
							else input2.setText("mauvaise saisie");
						}
						else{input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Maya"){ // Ok
						changePoliceMa2();
						revalidate();
						if(Verifchaine(input1.getText().trim())){						
						int tmp = (int) Japotodeci(input1.getText().trim()); // on convertit en decimal 
							if(Japotodeci.verif(input1.getText().trim()))
							{
							Maya maya = new Maya(); // on creer une nouvelle variable de type maya
							input2.setText(maya.convertNombre(tmp)); // on retourne la valeur en maya
							}
							else{
								changePolice2();
								input2.setText("mauvaise saisie");	
							}
						}
						else{
							changePolice2();
							input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Romain"){ // Ok
						changePolice2();
						revalidate();
						if(Verifchaine(input1.getText().trim())){						
						int tmp = (int) Japotodeci(input1.getText().trim()); // on convertit en decimal 
							if(Japotodeci.verif(input1.getText().trim()))
							{
							DeciRomain romain = new DeciRomain(tmp); 
							input2.setText(romain.convertDeci()); // on retourne la valeur en maya
							}
							else input2.setText("mauvaise saisie");
						}
						else{input2.setText("Saisie non valide");}
						
					}
					else if (choix2 == "Shadok"){ // Ok
						changePolice2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) 
						{
						long tmp1 = Japotodeci(input1.getText().trim());
							if(Japotodeci.verif(input1.getText().trim()))
							{
							Decimal shadok = new Decimal(tmp1);
							input2.setText(shadok.ConvSha(tmp1)); // on convertit le decimal en shadok
							}
							else input2.setText("mauvaise saisie");
						}
						else {input2.setText("Saisie non valide");}
					}
				}
				else if (choix == "Maya"){ 
					if (choix2 == "Babylonien"){ // Ok
						changePoliceBa2();
						revalidate();
						MayaDeci maya = new MayaDeci (input1.getText().trim()); // creation d'une variable MayaDeci
						if(maya.validMaya()) {						
						long tmp = Long.valueOf(maya.convertDeci()); // on recupere le valeur en decimal
						DeciBaby baby = new DeciBaby(tmp); // nouvelle variable de type Babylonien
						input2.setText(baby.convDeciBaby()); // on convertit le decimal en Babylonien 
						}
						else{
							changePolice2();
							input2.setText("mauvaise saisie");
						}
					}
					else if (choix2 == "Bibinaire"){ //Ok
						changePolice2();	
						revalidate();
						MayaDeci maya = new MayaDeci (input1.getText().trim()); // creation d'une variable MayaDeci
						if (maya.validMaya()) {						
						long tmp = Long.valueOf(maya.convertDeci()); // on recupere le valeur en decimal
						input2.setText(String.valueOf(Decitobibi(tmp))); // on convertit le decimal en Bibinaire 
						}
						else input2.setText("mauvaise saisie");				
					}
					else if (choix2 == "Decimal"){ // Ok
						changePolice2();
						revalidate();
						MayaDeci maya = new MayaDeci (input1.getText().trim()); // creation d'une variable MayaDeci
						if (maya.validMaya()) {						
						long tmp = Long.valueOf(maya.convertDeci()); // on recupere le valeur en decimal
						input2.setText(String.valueOf(tmp)); // on renvoie la valeur decimal
						} 
						else input2.setText("mauvaise saisie");
					}
					else if (choix2 == "Egyptien"){ // Ok
						changePoliceEg2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) 
						{
						MayaDeci maya = new MayaDeci (input1.getText().trim()); // creation d'une variable MayaDeci
						if (maya.validMaya()){						
						long tmp = Long.valueOf(maya.convertDeci()); // on recupere le valeur en decimal
						Decitoegypt egypt = new Decitoegypt(); // nouvelle variable de type decitoegypt
						input2.setText(egypt.convertionNombre(tmp)); // on convertit le decimal en egyptien
						}		
						else{
							changePolice2();
							input2.setText("mauvaise saisie");		
						}
						}
						else {
							changePolice2();
							input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Grec"){ //OK
						changePolice2();
						revalidate();
						MayaDeci maya = new MayaDeci (input1.getText().trim()); // creation d'une variable MayaDeci
						if (maya.validMaya()){						
						long tmp = Long.valueOf(maya.convertDeci()); // on recupere le valeur en decimal
						input2.setText(String.valueOf(DecimalToGrec.main(tmp))); // on convertit le decimal en grec
						}
						else input2.setText("mauvaise saisie");					
					}
					else if (choix2 == "Japonais"){//Ok		
						changePoliceJa2();
						revalidate();
						MayaDeci maya = new MayaDeci (input1.getText().trim()); // creation d'une variable MayaDeci
						if (maya.validMaya()){						
						long tmp = Long.valueOf(maya.convertDeci()); // on recupere le valeur en decimal
						input2.setText(String.valueOf(Decitojapo(tmp))); // on convertit le decimal en Japonais 
						}
						else input2.setText("mauvaise saisie");					
					}
					else if (choix2 == "Maya"){ // Ok	
						changePoliceMa2();
						revalidate();
						MayaDeci maya = new MayaDeci (input1.getText().trim());
						if (maya.validMaya()) {
						tmp = input1.getText().trim();
						input2.setText(tmp);
						}
						else{
							changePolice2();
							input2.setText("mauvaise saisie");
						}
					}
					else if (choix2 == "Romain"){ // Ok	
						changePolice2();
						revalidate();
						MayaDeci maya = new MayaDeci (input1.getText().trim()); // creation d'une variable MayaDeci
						if (maya.validMaya()) {					
						DeciRomain romain = new DeciRomain(maya.convertDeci());// on recupere le valeur en decimal dans une variable de type DeciRomain
						input2.setText(String.valueOf(romain.convertDeci())); // on convertit le decimal en Romain
						}
						else input2.setText("mauvaise saisie");				
					}
					else if (choix2 == "Shadok"){ // Ok
						changePolice2();
						revalidate();
						MayaDeci maya = new MayaDeci (input1.getText().trim()); // creation d'une variable MayaDeci
						if (maya.validMaya()) {						
						long tmp = Long.valueOf(maya.convertDeci()); // on recupere le valeur en decimal
						Decimal shadok = new Decimal (tmp);
						input2.setText(shadok.ConvSha(tmp)); // on convertit le decimal en shadok
						}
						else input2.setText("mauvaise saisie");
					}
				}
				else if (choix == "Romain"){ // ok
					input1.setText(input1.getText().toUpperCase().trim());
					if (choix2 == "Babylonien"){ // ok
						changePoliceBa2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) {						
						RomainDeci romain = new RomainDeci(input1.getText().trim());//
							if(romain.validSaisie())
							{
							DeciBaby baby = new DeciBaby (romain.convertRomain());// creation d'une variable baby
							input2.setText(String.valueOf(baby.convDeciBaby()));// on retourne la valeur
							}
							else{
								changePolice2();
								input2.setText("mauvaise saisie");
							}
						}
						else {
							changePolice2();
							input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Bibinaire"){ // ok
						changePolice2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) {						
						RomainDeci romain = new RomainDeci(input1.getText().trim());
							if(romain.validSaisie())
							{
							tmp = (String.valueOf(Decitobibi((long) romain.convertRomain())));
							input2.setText(tmp);
							}
							else input2.setText("mauvaise saisie");
						}
						else {input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Decimal"){ // ok
						changePolice2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) {
							RomainDeci romain = new RomainDeci(input1.getText().trim());
							if(romain.validSaisie())
							{
							input2.setText(String.valueOf(romain.convertRomain()));	
							}
							else input2.setText("mauvaise saisie");
						}
						else {input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Egyptien"){ //ok
						changePoliceEg2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) 
						{
							RomainDeci romain = new RomainDeci (input1.getText().trim());
							if(romain.validSaisie())
							{
							long tmp1 = Long.valueOf(romain.convertRomain());
							Decitoegypt egypt = new Decitoegypt();
							input2.setText(egypt.convertionNombre(tmp1));
							}
							else{
								changePolice2();
								input2.setText("mauvaise saisie");
							}
						}
						else {
							changePolice2();
							input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Grec"){ // Ok
						changePolice2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) {						
						RomainDeci romain = new RomainDeci(input1.getText().trim());
							if(romain.validSaisie())
							{
							long tmp = romain.convertRomain();
							input2.setText(String.valueOf(DecimalToGrec.main(tmp)));//retourne
							}
							else input2.setText("mauvaise saisie");
						}
						else {input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Japonais"){ // ok
						changePoliceJa2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) {						
						RomainDeci romain = new RomainDeci(input1.getText().trim());
							if(romain.validSaisie())
							{
							tmp = (String.valueOf(Decitojapo((long) romain.convertRomain())));
							input2.setText(tmp);
							}
							else input2.setText("mauvaise saisie");
						}
						else {input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Maya"){ // ok
						changePoliceMa2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) {						
						RomainDeci romain = new RomainDeci(input1.getText().trim());
							if(romain.validSaisie())
							{
							Maya maya = new Maya ();// creation d'une variable Maya
							tmp = (String.valueOf(maya.convertNombre(romain.convertRomain())));
							input2.setText(tmp);	
							}
							else{
								changePolice2();
								input2.setText("mauvaise saisie");					
							}
						}
						else {
							changePolice2();
							input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Romain"){ // Ok
						changePolice2();
						revalidate();
						RomainDeci romain = new RomainDeci(input1.getText().trim());
						if(Verifchaine(input1.getText())) {	
							
							if(romain.validSaisie())
							{
							tmp = input1.getText().trim();
							input2.setText(tmp);
							}
							else input2.setText("mauvaise saisie");
						}
						else {input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Shadok"){ // Ok
						changePolice2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) {						
						RomainDeci romain = new RomainDeci(input1.getText().trim());
							if(romain.validSaisie())
							{
							long tmp1 = romain.convertRomain();
							Decimal shadok = new Decimal(tmp1);// creation d'une variable shadok
							input2.setText(String.valueOf(shadok.ConvSha(tmp1)));//retourne	
							}
							else input2.setText("Mauvaise saisie");	
						}
						else {input2.setText("Saisie non valide");}
					}	
				}
				else if (choix == "Shadok"){
					input1.setText(input1.getText().toUpperCase().trim());
					if (choix2 == "Babylonien"){ // Ok	
						changePoliceBa2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) {						
						Shadok shadok = new Shadok("");// creation d'une variable shadok
							if(shadok.VerifShadok(input1.getText().trim()))
							{
							long tmp =  shadok.convDec(input1.getText().trim());
							DeciBaby baby = new DeciBaby(tmp);
							input2.setText(baby.convDeciBaby());
							}
							else{
								changePolice2();
								input2.setText("mauvaise saisie");
							}
						}
						else {
							changePolice2();
							input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Bibinaire"){ //ok	
						changePolice2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) {						
						Shadok shadok = new Shadok("");// creation d'une variable shadok
							if(shadok.VerifShadok(input1.getText().trim()))
							{
							long tmp = shadok.convDec(input1.getText().trim());
							input2.setText(Decitobibi(tmp));
							}
							else input2.setText("mauvaise saisie");
						}
						else {input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Decimal"){ // ok
						changePolice2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) {						
						Shadok shadok = new Shadok("");// creation d'une variable shadok
							if(shadok.VerifShadok(input1.getText().trim()))
							{
							long tmp = shadok.convDec(input1.getText().trim());
							input2.setText(String.valueOf(tmp));
							}
							else input2.setText("mauvaise saisie");
						}
						else {input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Egyptien"){ // ok
						changePoliceEg2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) {						
						Shadok shadok = new Shadok("");// creation d'une variable shadok
							if(shadok.VerifShadok(input1.getText().trim()))
							{							
							long tmp = shadok.convDec(input1.getText().trim());
							Decitoegypt egypt = new Decitoegypt();
							input2.setText(egypt.convertionNombre(tmp));
							}
							else{
								changePolice2();
								input2.setText("mauvaise saisie");
							}
						}
						else {
							changePolice2();
							input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Grec"){ // Ok
						changePolice2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) {						
						Shadok shadok = new Shadok("");// creation d'une variable shadok
							if(shadok.VerifShadok(input1.getText().trim()))
							{							
							long tmp = shadok.convDec(input1.getText().trim());
							input2.setText(DecimalToGrec.main(tmp));
							}
							else input2.setText("mauvaise saisie");
						}
						else {input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Japonais"){ // ok
						changePoliceJa2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) {						
						Shadok shadok = new Shadok("");// creation d'une variable shadok
							if(shadok.VerifShadok(input1.getText().trim()))
							{							
							long tmp = shadok.convDec(input1.getText().trim());
							input2.setText(Decitojapo(tmp));
							}
							else input2.setText("mauvaise saisie");
						}
						else {input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Maya"){ // ok
						changePoliceMa2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) {						
						Shadok shadok = new Shadok("");// creation d'une variable shadok
							if(shadok.VerifShadok(input1.getText().trim()))
							{							
							long tmp = shadok.convDec(input1.getText().trim());
							Maya maya = new Maya();
							input2.setText(maya.convertNombre(tmp));
							}
							else{
								changePolice2();
								input2.setText("mauvaise saisie");
							}
						}
						else {
							changePolice2();
							input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Romain"){ // ok
						changePolice2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) {						
						Shadok shadok = new Shadok("");// creation d'une variable shadok
							if(shadok.VerifShadok(input1.getText().trim()))
							{							
							long tmp = shadok.convDec(input1.getText().trim());
							DeciRomain romain = new DeciRomain(tmp);
							input2.setText(romain.convertDeci());
							}
							else input2.setText("mauvaise saisie");
						}
						else {input2.setText("Saisie non valide");}
					}
					else if (choix2 == "Shadok"){
						changePolice2();
						revalidate();
						if(Verifchaine(input1.getText().trim())) {	
						Shadok shadok = new Shadok("");
							if(shadok.VerifShadok(input1.getText().trim()))
							{
							tmp = input1.getText().trim();
							input2.setText(tmp);
							}
							else input2.setText("mauvaise saisie");
						}
						else {input2.setText("Saisie non valide");}
					}	
				}
				
			}
			
			private String Decitobibi(long tmp1) {
				return Decitobibi.Main(tmp1);
			}

			private long Japotodeci(String text) {
				return Japotodeci.Main(text);
			}

			private String Decitojapo(long tmp1) {
				return Decitojapo.Main(tmp1);
			}

			private long Bibitodeci(String text) {
				return Bibitodeci.Main(text);
			}			
		});
		
		/*ClassLoader cl = this.getClass().getClassLoader();
		lecteur = new JButton(new ImageIcon(cl.getResource("play.png")));
		lecteur.setPreferredSize(dim2);
		lecteur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    	lt = new LecteurTexte(tmp);
			    	lt.playAll();
			}
		});*/ //A rajouter a la version final pour le son !!
		
		//On definit le layout manager
		c.setLayout(new GridBagLayout());
		
		//L'objet servant a positionner les composants
		GridBagConstraints gbc = new GridBagConstraints();
		
		//boutplay.add(lecteur);
		bout.add(bouton);
		menu.add(combo);
		menu1.add(combo2);
		input1  = new JTextArea("Tapez votre nombre ici",2,20);
		input1.setFont(police);
		input1.addMouseListener(new ZoneTextListener());		
		JScrollPane scrollArea = new JScrollPane(input1);
		champ1.setLayout(new BorderLayout());
		champ1.add(scrollArea);
		input2  = new JTextArea("",2,20);
		input2.setFont(new Font("Arial", Font.BOLD,15));
		input2.setEditable(false);
		JScrollPane scrollArea2 = new JScrollPane(input2);		
		champ2.setLayout(new BorderLayout());
		champ2.add(scrollArea2);
		
		//On positionne la case de depart du composant
				gbc.gridx = 0;
				gbc.gridy = 0;
				//La taille en hauteur et en largeur
				gbc.gridheight = 1;
				gbc.gridwidth = 1;
				c.add(menu, gbc);
				//---------------------------------------------
				gbc.gridx = 1;
				c.add(bout, gbc);
				//---------------------------------------------
				//Cette instruction informe le layout que c'est une fin de ligne
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				gbc.gridx = 3;
				c.add(menu1, gbc);
				//---------------------------------------------
				gbc.gridx = 0;
				gbc.gridy = 1;
				gbc.gridwidth = 1;
				gbc.gridheight = 2;				
				c.add(champ1, gbc);
				//---------------------------------------------
				gbc.gridx = 3;
				c.add(champ2, gbc);
				//---------------------------------------------
				gbc.gridx = 0;
				gbc.gridy = 3;
				gbc.gridwidth = 1;
				gbc.gridheight = 10;
				c.add(clavier, gbc);
				//On ajoute le conteneur
				this.setContentPane(c);
				this.setVisible(true);
    }
    
    public void init() {
		Conversion select = new Conversion();
		select.setVisible(true);
	}
}
