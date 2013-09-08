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

		if (lettre=='M')
					{		
					this.cptM++;	
					if (this.cptM > 10) { valide=false;} // on "dit" qu'on ne peux pas avoir plus de 10 M
					}
		else if (lettre=='D')
					{		
					this.cptD++;	
					if (this.cptD > 1) { valide=false;} // on peut avoir au maximum un D
					}	
	
		else if (lettre=='C')
					{		
					this.cptC++;	
					if (this.cptC > 4) { valide=false;} // on ne peux avoir plus de quatre C
					}	
		else if (lettre=='L')
					{		
					this.cptL++;	
					if (this.cptL > 1) { valide=false;}// on ne peux avoir plus de un L
					}	
		else if (lettre=='X')
					{		
					this.cptX++;	
					if (this.cptX > 4) { valide=false;}// on ne peux avoir plus de quatre X
					}	
		else if (lettre=='V')
					{		
					this.cptV++;	
					if (this.cptV > 1) { valide=false;}// on ne peux avoir plus de un V
					}
		else if (lettre=='I')
					{		
					this.cptI++;	
					if (this.cptI > 4) { valide=false;} // on ne peux avoir plus de quatre I
					}			
		return valide;
	}

	public boolean validLettre(char lettre1) { //Permet de controler une lettre
		boolean valide=true;
		if (lettre1!='M' && lettre1 !='D' && lettre1!='C' && lettre1!='L' && lettre1!='X' && lettre1!='V' && lettre1!='I' && lettre1!=' ')
		{valide=false;}
		return valide;
	}

	public boolean validOrdre(char lettre1, char lettre2){ //Permet de controler deux lettres
		boolean valide=true;
		if (lettre1=='D')
		{
			if (lettre2!='C' && lettre2!='L' && lettre2!='X' && lettre2!='V' && lettre2!='I')
			{
				valide=false;
			}
		}
		else if (lettre1=='C')
		{
			if (lettre2!='D' && lettre2!='C' && lettre2!='L' && lettre2!='X' && lettre2!='V' && lettre2!='I')
			{
				valide=false;
			}
		}
		else if (lettre1=='L')
		{
			if (lettre2!='X' && lettre2!='V' && lettre2!='I')
			{
				valide=false;
			}
		}
		else if (lettre1=='X')
		{
			if (lettre2!='C' && lettre2!='L' && lettre2!='X' && lettre2!='V' && lettre2!='I')
			{
				valide=false;
			}
		}
		else if (lettre1=='V')
		{
			if (lettre2!='I')
			{
				valide=false;
			}
		}
		else if (lettre1=='I')
		{
			if (lettre2!='X' && lettre2!='V' && lettre2!='I')
			{
				valide=false;
			}
		}
	return valide;
	}

	public boolean validOrdre3lettre (char lettre1,char lettre2,char lettre3) { // Permet de controler 3 lettres en même temps
	boolean valide=true;

		if(lettre1=='M') // si on a un M
		{
			if(lettre2!='C' && lettre2!='M' && lettre3=='M') // on ne peut avoir que MCM ou MMM
			{
				valide=false;
			}

		}
		else if (lettre1=='D') // si on a un D
		{
			if (lettre2!='C' && lettre2!='L' && lettre2!='X' && lettre2!='V' && lettre2!='I') // on peut tout avoir en 2ème lettre
			{
				valide=false;
			}
		}
		
		else if (lettre1=='C') // si on a un C
		{
			if (lettre2!='D' && lettre2!='C' && lettre2!='L' && lettre2!='X' && lettre2!='V' && lettre2!='I') //controle des lettres
			{
				valide=false;
			}
			
			if(lettre2!='X' && lettre2!='C' && lettre3=='C') // on ne peut avoir CLC,CIC,CVC,CMC,CDC
			{
				valide = false;
			}
		}
		else if (lettre1=='L') // si on a un L
		{
			if (lettre2!='X' && lettre2!='V' && lettre2!='I') // derrière un L on en peut avoir que des X,V,ou I
			{
				valide=false;
			}
		}
		else if (lettre1=='X') // si on a un X
		{
			if (lettre2!='C' && lettre2!='L' && lettre2!='X' && lettre2!='V' && lettre2!='I') // controle des lettres
			{
				valide=false;
			}
			if (lettre2=='C' || lettre2=='M' || lettre2=='L' && lettre3=='X') // on ne peux pas avoir XMX,XLX,XCX
			{
				valide=false;
			}
			if(lettre2=='C' && lettre3=='V'|| lettre3=='I') // on peut avoir que XCI ou XCV
			{
				valide=true;
			}
			if (lettre2=='X' && lettre3!='X' && lettre3!='I' && lettre3!='V')//si on a deux X on peut avoir que des X, un V ou des I
			{
				valide=false;
			}
		}
		else if (lettre1=='V') // si on a un V
		{
			if (lettre2!='I') // derriere un V on ne peut avoir que un I
			{
				valide=false;
			}
		}
		else if (lettre1=='I') // si on a un I
		{
			if (lettre2!='X'&& lettre2!='V' && lettre2!='I') // derriere un I on ne peut avoir que des X,V,I
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
			if (this.validCompteur(lettre1)==false) {System.out.println("J'ai l'impression que la lettre est fausse"); valide = false; break;}//si cette lettre est fausse on sort & renvoie faux
			if (this.valeur.length() == 1 ) // sinon si la taille du mot est de 1
			{
				if(this.validLettre(this.valeur.charAt(0))==false) // on re-test la validité de la premiere lettre
				{
					valide=false;break; // si elle est fausse on sort et renvoie faux
				}
			}
			else if (this.valeur.length()==2) // si elle est de 2
			{
				if (this.validLettre(lettre1)==false) // on test la validité de la 1 ere lettre
				{
					valide=false; break;// si elle est fausse on sort, et on renvoie faux
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
								valide=false;break; // si faux on sort et renvoie faux
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
	this.cptM=0;this.cptD=0;this.cptC=0;this.cptL=0;this.cptX=0;this.cptV=0;this.cptI=0; // on remet le scompteur a zéro 
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
		
			if (lettre1=='M')
			{
				tab[i] =1000;
			}
		
			if (lettre1=='D')
			{
				tab[i] =500;
			}
		
			if (lettre1=='C')
			{
				tab[i] =100;
			}
		
			if (lettre1=='L')
			{
				tab[i] =50;
			}
		
			if (lettre1=='X')
			{
				tab[i] =10;
			}
		
			if (lettre1=='V')
			{
				tab[i] = 5;
			}
		
			if (lettre1=='I')
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
