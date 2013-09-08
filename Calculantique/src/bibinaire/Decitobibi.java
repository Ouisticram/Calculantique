//algo de conversion du decimal a la numeration bibinaire
package bibinaire;
public class Decitobibi {
	  public static int cpt;	//cpt : compteur utilise pour toute la class d'ou le public

	  //methode permettant de compter le nombre de chiffre dans un nombre
	  static int size(long n) {
		  int length = 0;			//longueur retourner initialise a 0

		  if(n == 0) {				//si le nombre est 0 on retourne 1
		    return 1;
		  }

		  while(n != 0) {			//sinon tant que le nombre est different de 0
		    n /= 10;				//on le divise par 10
		    ++length;				//On incremente la longueure de 1
		  }

		  return length;			//on retourne la longueure
		}

	//methode de conversion d'un nombre decimal dans une autre base (ameliore pour prendre en compte les nombres negatif)
	public static long[] Conversion(long nb, long base) {
		
		//creation des variables et tableaux dont on va se servir
		int j, e;
		long nb1, reste, tmp;
		long tabi[] = new long[size(nb)*2];
		long tabi2[] = new long[size(nb)*2];
		
		//on initialise le tout
		e = 0;
		nb1 = nb;
		cpt = 0;
		
		//Si le nombre est negatif
		if (nb<0){
			nb1 = nb1 *(-1);			//on transforme le nombre en son oppose 
			while (nb1 > 0)				//tant qu'il est superieur a 0
			{
				reste = nb1 % base;		//on recupere le reste de la division par la base entree en parametre
				tabi[e] = -reste;		//on met l'inverse du reste dans un tableau
				e++;					//incrementation d'un variable
				cpt++;					//incrementation du compteur (qui je vous le rappel est public)
				nb1 = nb1/base;			//On avance dans notre boucle
			}
			for(int i = 0; i <= cpt-1; i++)			//On place le tout dans un autre tableau
			{
				tabi2[i] = tabi[i];
			}
			j = cpt-1;								//initialisation de la variable
			for(int i = 0; i <= (cpt-1)/2; i++)		//boucle pour remettre le tout dans le bon ordre
			{
				tmp = tabi2[j];						//Bon, on a bouffe de cette algo pas besoin de preciser ...
				tabi2[j] = tabi2[i];
				tabi2[i] = tmp;
				j--;
			}			
		}
		else{										//Sinon (si le nombre est positif)
			//On fait la meme chose mais sans faire l'oppose du nombre.
			while (nb1 > 0)
			{
				reste = nb1 % base;
				tabi[e] = reste;
				e++;
				cpt++;
				nb1 = nb1/base;
				
			}
			for(int i = 0; i <= cpt-1; i++)
			{
				tabi2[i] = tabi[i];
			}
			j = cpt-1;
			for(int i = 0; i <= (cpt-1)/2; i++)
			{
				tmp = tabi2[j];
				tabi2[j] = tabi2[i];
				tabi2[i] = tmp;
				j--;
			}
		}
		return (tabi2);				//On retourne notre tableau
	}
	
	//methode qui permet de convertir un nombre en base 16 en bibinaire
	public static String Conversion16toBibi(long nb) {
		//creation de variables et vecteurs utilise dans cette methode
		long tabi[] = new long[cpt*2];
		String bibi = "";
		int j = -1;
		if (nb*1 == 0)			//On verifie que le mec s'amuse pas a mettre 000000000000000000 a la place d'un simple 0 et n renvoie "HO" si tel est le cas
		{
			bibi = "HO";
		}
		else if (nb<0){						//Sinon si le nombre est negatif
			tabi = Conversion(nb,16);		//On fait appel a notre methode precedente
			if (cpt == 1)					//Si le la valeur absolu du nombre est compris entre 0 et 15 compris On connait sa valeur (et oui le compteur etait dans la boucle des restes donc il nous dit combien de chiffre on a ^^')
			{
				if (tabi[0] == 0)
				{
					bibi = "HO";
				}
				else if (tabi[0] == -1)
				{
					bibi = "-HA";
				}
				else if (tabi[0] == -2)
				{
					bibi = "-HE";
				}
				else if (tabi[0] == -3)
				{
					bibi = "-HI";
				}
				else if (tabi[0] == -4)
				{
					bibi = "-BO";
				}
				else if (tabi[0] == -5)
				{
					bibi = "-BA";
				}
				else if (tabi[0] == -6)
				{
					bibi = "-BE";
				}
				else if (tabi[0] == -7)
				{
					bibi = "-BI";
				}
				else if (tabi[0] == -8)
				{
					bibi = "-KO";
				}
				else if (tabi[0] == -9)
				{
					bibi = "-KA";
				}
				else if (tabi[0] == -10)
				{
					bibi = "-KE";
				}
				else if (tabi[0] == -11)
				{
					bibi = "-KI";
				}
				else if (tabi[0] == -12)
				{
					bibi = "-DO";
				}
				else if (tabi[0] == -13)
				{
					bibi = "-DA";
				}
				else if (tabi[0] == -14)
				{
					bibi = "-DE";
				}
				else if (tabi[0] == -15)
				{
					bibi = "-DI";
				}
			}
			else									//Sinon (si la valeur absolu du nombre est superieur a 15 donc 16 et +)
			{
				bibi = bibi.concat("-");			//On commence par ajoute le - a la chaine de caractere
				for(int i = 0; i <= cpt-1; i++)		//Ensuite on fait une boucle pour concatener les chaines de caractere
				{
					if (tabi[i] == 0)
					{
						bibi = bibi.concat("HO");
					}
					else if (tabi[i] == -1)
					{
						bibi = bibi.concat("HA");
					}
					else if (tabi[i] == -2)
					{
						bibi = bibi.concat("HE");
					}
					else if (tabi[i] == -3)
					{
						bibi = bibi.concat("HI");
					}
					else if (tabi[i] == -4)
					{
						bibi = bibi.concat("BO");
					}
					else if (tabi[i] == -5)
					{
						bibi = bibi.concat("BA");
					}
					else if (tabi[i] == -6)
					{
						bibi = bibi.concat("BE");
					}
					else if (tabi[i] == -7)
					{
						bibi = bibi.concat("BI");
					}
					else if (tabi[i] == -8)
					{
						bibi = bibi.concat("KO");
					}
					else if (tabi[i] == -9)
					{
						bibi = bibi.concat("KA");
					}
					else if (tabi[i] == -10)
					{
						bibi = bibi.concat("KE");
					}
					else if (tabi[i] == -11)
					{
						bibi = bibi.concat("KI");
					}
					else if (tabi[i] == -12)
					{
						bibi = bibi.concat("DO");
					}
					else if (tabi[i] == -13)
					{
						bibi = bibi.concat("DA");
					}
					else if (tabi[i] == -14)
					{
						bibi = bibi.concat("DE");
					}
					else if (tabi[i] == -15)
					{
						bibi = bibi.concat("DI");
					}
					j = j + 2;						//On incremente de 2, car le bibinaire est fait de duo de caracteres
				}
			}
		}
		else if (nb>0)								//sinon si le nombre est positif (oui je sais j'aurais pu me contenter d'un else)
		{
			//meme chose, sans le moins au debut
			tabi = Conversion(nb,16);
			if (cpt == 1)
			{
				if (tabi[0] == 0)
				{
					bibi = "HO";
				}
				else if (tabi[0] == 1)
				{
					bibi = "HA";
				}
				else if (tabi[0] == 2)
				{
					bibi = "HE";
				}
				else if (tabi[0] == 3)
				{
					bibi = "HI";
				}
				else if (tabi[0] == 4)
				{
					bibi = "BO";
				}
				else if (tabi[0] == 5)
				{
					bibi = "BA";
				}
				else if (tabi[0] == 6)
				{
					bibi = "BE";
				}
				else if (tabi[0] == 7)
				{
					bibi = "BI";
				}
				else if (tabi[0] == 8)
				{
					bibi = "KO";
				}
				else if (tabi[0] == 9)
				{
					bibi = "KA";
				}
				else if (tabi[0] == 10)
				{
					bibi = "KE";
				}
				else if (tabi[0] == 11)
				{
					bibi = "KI";
				}
				else if (tabi[0] == 12)
				{
					bibi = "DO";
				}
				else if (tabi[0] == 13)
				{
					bibi = "DA";
				}
				else if (tabi[0] == 14)
				{
					bibi = "DE";
				}
				else if (tabi[0] == 15)
				{
					bibi = "DI";
				}
			}
			else
			{
				for(int i = 0; i <= cpt-1; i++)
				{
					if (tabi[i] == 0)
					{
						bibi = bibi.concat("HO");
					}
					else if (tabi[i] == 1)
					{
						bibi = bibi.concat("HA");
					}
					else if (tabi[i] == 2)
					{
						bibi = bibi.concat("HE");
					}
					else if (tabi[i] == 3)
					{
						bibi = bibi.concat("HI");
					}
					else if (tabi[i] == 4)
					{
						bibi = bibi.concat("BO");
					}
					else if (tabi[i] == 5)
					{
						bibi = bibi.concat("BA");
					}
					else if (tabi[i] == 6)
					{
						bibi = bibi.concat("BE");
					}
					else if (tabi[i] == 7)
					{
						bibi = bibi.concat("BI");
					}
					else if (tabi[i] == 8)
					{
						bibi = bibi.concat("KO");
					}
					else if (tabi[i] == 9)
					{
						bibi = bibi.concat("KA");
					}
					else if (tabi[i] == 10)
					{
						bibi = bibi.concat("KE");
					}
					else if (tabi[i] == 11)
					{
						bibi = bibi.concat("KI");
					}
					else if (tabi[i] == 12)
					{
						bibi = bibi.concat("DO");
					}
					else if (tabi[i] == 13)
					{
						bibi = bibi.concat("DA");
					}
					else if (tabi[i] == 14)
					{
						bibi = bibi.concat("DE");
					}
					else if (tabi[i] == 15)
					{
						bibi = bibi.concat("DI");
					}
					j = j + 2;
				}
			}
		}
		return bibi;								//On retourne la chaine de caractere
	}
	
	public static String Main(long nb10) {
		return (Conversion16toBibi(nb10));			//On appel notre methode et on renvoie la chaine de caractere
	}
}
