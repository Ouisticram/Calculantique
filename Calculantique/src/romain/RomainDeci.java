package romain;

public class RomainDeci {
	private String valeur;
	static int cptM;
	static int cptD;
	static int cptC;
	static int cptL;
	static int cptX;
	static int cptV;
	static int cptI;
	public int[] tab;
	

	public RomainDeci(String saValeur){
		this.valeur=saValeur;
	}

	public boolean validCompteur(char lettre){
		boolean valide=true;

		//if (lettre=='M')
		if (lettre=='\u216F')
					{		
					this.cptM++;	
					if (this.cptM > 10) { valide=false;} // on "dit" qu'on ne peux pas avoir plus de 10 M
					}
		//else if (lettre=='D')
		else if (lettre=='\u216E')
					{		
					this.cptD++;	
					if (this.cptD > 1) { valide=false;} // on peut avoir au maximum un D
					}	
	
		else if (lettre=='\u216D')
					{		
					this.cptC++;	
					if (this.cptC > 4) { valide=false;} // on ne peux avoir plus de quatre C
					}	
		else if (lettre=='\u216C')
					{		
					this.cptL++;	
					if (this.cptL > 1) { valide=false;}// on ne peux avoir plus de un L
					}	
		else if (lettre=='\u2169')
					{		
					this.cptX++;	
					if (this.cptX > 4) { valide=false;}// on ne peux avoir plus de quatre X
					}	
		else if (lettre=='\u2164')
					{		
					this.cptV++;	
					if (this.cptV > 1) { valide=false;}// on ne peux avoir plus de un V
					}
		else if (lettre=='\u2160')
					{		
					this.cptI++;	
					if (this.cptI > 4) { valide=false;} // on ne peux avoir plus de quatre I
					}		
		cptM = 0; cptD = 0; cptC = 0; cptL = 0; cptX = 0; cptV = 0; cptI = 0;
		return valide;
	}

	public boolean validLettre(char lettre1) { //Permet de controler une lettre
		boolean valide=true;
		// '\u2160'='I', '\u2164'='V', '\u2169'='X', '\u216C'='L', '\u216D'='C', '\u216E'='D, '\u216F'='M'
		if (lettre1!='\u216F' && lettre1 !='\u216E' && lettre1!='\u216D' && lettre1!='\u216C' && lettre1!='\u2169' && lettre1!='\u2164' && lettre1!='\u2160' && lettre1!=' ')
		{valide=false;}
		return valide;
	}

	public boolean validOrdre(char lettre1, char lettre2){ //Permet de controler deux lettres
		boolean valide=true;
		if (lettre1=='\u216E')
		{
			if (lettre2!='\u216D' && lettre2!='\u216C' && lettre2!='\u2169' && lettre2!='\u2164' && lettre2!='\u2160')
			{
				valide=false;
			}
		}
		else if (lettre1=='\u216D')
		{
			if (lettre2!='\u216E' && lettre2!='\u216D' && lettre2!='\u216C' && lettre2!='\u2169' && lettre2!='\u2164' && lettre2!='\u2160')
			{
				valide=false;
			}
		}
		else if (lettre1=='\u216C')
		{
			if (lettre2!='\u2169' && lettre2!='\u2164' && lettre2!='\u2160')
			{
				valide=false;
			}
		}
		else if (lettre1=='\u2169')
		{
			if (lettre2!='\u216D' && lettre2!='\u216C' && lettre2!='\u2169' && lettre2!='\u2164' && lettre2!='\u2160')
			{
				valide=false;
			}
		}
		else if (lettre1=='\u2164')
		{
			if (lettre2!='\u2160')
			{
				valide=false;
			}
		}
		else if (lettre1=='\u2160')
		{
			if (lettre2!='\u2169' && lettre2!='\u2164' && lettre2!='\u2160')
			{
				valide=false;
			}
		}
	return valide;
	}

	public boolean validOrdre3lettre (char lettre1,char lettre2,char lettre3) { // Permet de controler 3 lettres en même temps
	boolean valide=true;

		if(lettre1=='\u216F') // si on a un M
		{
			if(lettre2!='\u216D' && lettre2!='\u216F' && lettre3=='\u216F') // on ne peut avoir que MCM ou MMM
			{
				valide=false;
			}

		}
		else if (lettre1=='\u216E') // si on a un D
		{
			if (lettre2!='\u216D' && lettre2!='\u216C' && lettre2!='\u2169' && lettre2!='\u2164' && lettre2!='\u2160') // on peut tout avoir en 2ème lettre
			{
				valide=false;
			}
		}
		
		else if (lettre1=='\u216D') // si on a un C
		{
			if (lettre2!='\u216E' && lettre2!='\u216D' && lettre2!='\u216C' && lettre2!='\u2169' && lettre2!='\u2164' && lettre2!='\u2160') //controle des lettres
			{
				valide=false;
			}
			
			if(lettre2!='\u2169' && lettre2!='\u216D' && lettre3=='\u216D') // on ne peut avoir CLC,CIC,CVC,CMC,CDC
			{
				valide = false;
			}
		}
		else if (lettre1=='\u216C') // si on a un L
		{
			if (lettre2!='\u2169' && lettre2!='\u2164' && lettre2!='\u2160') // derrière un L on en peut avoir que des X,V,ou I
			{
				valide=false;
			}
		}
		else if (lettre1=='\u2169') // si on a un X
		{
			if (lettre2!='\u216D' && lettre2!='\u216C' && lettre2!='\u2169' && lettre2!='\u2164' && lettre2!='\u2160') // controle des lettres
			{
				valide=false;
			}
			if (lettre2=='\u216D' || lettre2=='\u216F' || lettre2=='\u216C' && lettre3=='\u2169') // on ne peux pas avoir XMX,XLX,XCX
			{
				valide=false;
			}
			if(lettre2=='\u216D' && lettre3=='\u2164' || lettre3=='\u2160') // on peut avoir que XCI ou XCV
			{
				valide=true;
			}
			if (lettre2=='\u2169' && lettre3!='\u2169' && lettre3!='\u2160' && lettre3!='\u2164')//si on a deux X on peut avoir que des X, un V ou des I
			{
				valide=false;
			}
		}
		else if (lettre1=='\u2164') // si on a un V
		{
			if (lettre2!='\u2160') // derriere un V on ne peut avoir que un I
			{
				valide=false;
			}
		}
		else if (lettre1=='\u2160') // si on a un I
		{
			if (lettre2!='\u2169' && lettre2!='\u2164' && lettre2!='\u2160') // derriere un I on ne peut avoir que des X,V,I
			{
				valide=false;
			}
		}
	return valide;
	}
	




	public boolean validSaisie(){ // fonction qui permet de controler la validité du mot 
		char lettre1;// correspond a la premiere lettre
		char lettre2;// correspond a la deuxième lettre
		char lettre3;// correspond a la troisième lettre
		int x=0;
		boolean valide=true;

		for (int i=0; i < this.valeur.length();i++) // on boucle sur tout le mot
		{
			lettre1 = this.valeur.charAt(i); // on recupère la 1ère lettre
			if (this.validCompteur(lettre1)==false) { return false;}//si cette lettre est fausse on sort & renvoie faux
			if (this.valeur.length() == 1 ) // sinon si la taille du mot est de 1
			{
				if(this.validLettre(this.valeur.charAt(0))==false) // on re-test la validité de la premiere lettre
				{
					return false; // si elle est fausse on sort et renvoie faux
				}
			}
			else if (this.valeur.length()==2) // si elle est de 2
			{
				if (this.validLettre(lettre1)==false) // on test la validité de la 1 ere lettre
				{
					return false;// si elle est fausse on sort, et on renvoie faux
				}
				else // si la première est valide
				{
					for (int y=0; y < this.valeur.length()-1;y++) // on boucle sur tout le mot
					{
						
						lettre1 = this.valeur.charAt(y); // on remet la 1ere lettre dans lettre1
						x = y+1;
						while ( x <= y+1)
						{	
							lettre2 = this.valeur.charAt(x); // on met la deuxième lettre dans lettre2
							
							if (this.validOrdre(lettre1,lettre2)==false) // on regarde la validité de l'ordre 
							{
								return false; // si faux on sort et renvoie faux
							}
						x++; // on incrémente x pour sortir de la boucle
						}
					}
				}
			}
			
			else if (this.valeur.length()>=3) // sinon si la taille du mot est plus grande que 3
			{
				if (this.validLettre(lettre1)==false) // on test (une nouvelle fois) la validité de la 1ere lettre
				{
					valide=false; break; // on sort et renvoie faux
				}

				else
				{
					for (int a=0; a < this.valeur.length()-2;a++) // si ont boucle sur la taille-2
					{
						
						lettre1 = this.valeur.charAt(a); // on met la 1ere lettre dans lettre1
						x = a+1;
						while ( x <= a+1)
						{	
							lettre2 = this.valeur.charAt(x); // on met la deuxieme lettre dans lettre2
							lettre3 = this.valeur.charAt(x+1); // on met la troisième lettre dans lettre3
							if (this.validOrdre3lettre(lettre1,lettre2,lettre3)==false) // on test l'ordre 
							{
								valide=false;break; // on sort et renvoie faux
							}

						x++; // on incrémente x
						}
					}
				}
			}
		}
	return valide; // on retourne valide 
	}



	public void setTab() { // initialisation d'un tableau qui a pour longueur la taille du mot
		this.tab = new int [this.valeur.length()];
	}

	public void initTab() { // on initialise le tableau a 0
		for (int i=0 ; i < this.tab.length; i++)
		{
			this.tab[i] = 0;
		}
	}

	public int convertRomain () {
		this.setTab();
		this.initTab();
		int somme=0; //variable qui va retourner le resultat
		char lettre1; // on récupere la lettre
		
		// première boucle qui attribut dans le tableau la valeur de chaque lettre
		for (int i=0;i<this.tab.length;i++)
		{
			lettre1 = this.valeur.charAt(i);
		
			if (lettre1=='\u216F')
			{
				tab[i] =1000;
			}
		
			if (lettre1=='\u216E')
			{
				tab[i] =500;
			}
		
			if (lettre1=='\u216D')
			{
				tab[i] =100;
			}
		
			if (lettre1=='\u216C')
			{
				tab[i] =50;
			}
		
			if (lettre1=='\u2169')
			{
				tab[i] =10;
			}
		
			if (lettre1=='\u2164')
			{
				tab[i] = 5;
			}
		
			if (lettre1=='\u2160')
			{
				tab[i] =1;
			}
		}

		for (int y=0; y < this.tab.length-1 ; y++)
		{
			if (tab[y] < tab[y+1]) // si la valeur d'avant est plus petite que celle d'après elle devient négative
			{
				somme = somme - tab[y];
			}	
			else  // sinon elle reste positive
			{
				somme = somme + tab[y];	
			}	
		
		}

		somme = somme + this.tab[this.tab.length-1];
		return somme; // on retourne le résultat

	}

}
