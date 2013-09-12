package bibinaire;
//algo de conversion de la numeration bibinaire au decimal
public class Bibitodeci {
	
	public static boolean VerifBibi(String valeur)
	{
	char lettre1,lettre2;
	boolean valide=false;

		for (int i=0 ; i < valeur.length()-1; i=i+2)
		{
			lettre1 = valeur.charAt(i);
				if(lettre1=='H')
				{
					lettre2 = valeur.charAt(i+1);
					if (lettre2=='O' || lettre2=='A' || lettre2=='E' || lettre2=='I')
					{
						valide=true;
					}
					else {valide=false;break;}
				}
				else if(lettre1=='B')
				{
					lettre2 = valeur.charAt(i+1);
					if (lettre2=='O' || lettre2=='A' || lettre2=='E' || lettre2=='I')
					{
						valide=true;
					}
					else {valide=false;break;}
				}
				else if(lettre1=='K')
				{
					lettre2 = valeur.charAt(i+1);
					if (lettre2=='O' || lettre2=='A' || lettre2=='E' || lettre2=='I')
					{
						valide=true;
					}
					else {valide=false;break;}
				}
				else if(lettre1=='D')
				{
					lettre2 = valeur.charAt(i+1);
					if (lettre2=='O' || lettre2=='A' || lettre2=='E' || lettre2=='I')
					{
						valide=true;
					}
					else {valide=false;break;}
				}
				else {valide=false; break;}
		}		
		return valide;
	}

	//methode de conversion de la base 16 a la base 10
	static long Conversion16to10(long[] tabi, String bibi){
		long tmp, nb2;
		int taille = bibi.length();						//length, pratique pour connaitre la longueur d'une chaine de caractere
		tmp = 0;
		nb2 = 0;
		for(int i = 0; i <= ((taille/2)-1); i++)
		{
			tmp = tabi[i]*((int)Math.pow(16,i));		//Math.pow = puissance voila.
			nb2 = nb2 + tmp;
		}
		return nb2;
	}
	
	//methode de conversion du bibinaire vers decimal
	public static long Conversionbibito10 (String bibi){
		int length = bibi.length();
		long tabi[] = new long[length];
		long tabi2[] = new long[length];
		long nb2 =0;
		int l, k;
		if (bibi.charAt(0) == '-')						//si le nombre commence par un -
		{
				//On initialise les tableaux a 0	
				for(int i = 0; i <= length/2; i++)			
				{
					tabi[i] = 0;
				}
				for(int i = 0; i <= length/2; i++)
				{
					tabi2[i] = 0;
				}
				l = 0;
				//On verifie les caracteres 2 par 2 et on remplace par l'equivalent en decimal
				for(int i = 0; i <= ((length/2)-1); i++)
				{
					if (bibi.charAt(l+1) == 'H' && bibi.charAt(l+2) == 'O')
					{
						tabi[i] = 0;
					}
					else if (bibi.charAt(l+1) == 'H' && bibi.charAt(l+2) == 'A')
					{
						tabi[i] = -1;
					}
					else if (bibi.charAt(l+1) == 'H' && bibi.charAt(l+2) == 'E')
					{
						tabi[i] = -2;
					}
					else if (bibi.charAt(l+1) == 'H' && bibi.charAt(l+2) == 'I')
					{
						tabi[i] = -3;
					}
					else if (bibi.charAt(l+1) == 'B' && bibi.charAt(l+2) == 'O')
					{
						tabi[i] = -4;
					}
					else if (bibi.charAt(l+1) == 'B' && bibi.charAt(l+2) == 'A')
					{
						tabi[i] = -5;
					}
					else if (bibi.charAt(l+1) == 'B' && bibi.charAt(l+2) == 'E')
					{
						tabi[i] = -6;
					}
					else if (bibi.charAt(l+1) == 'B' && bibi.charAt(l+2) == 'I')
					{
						tabi[i] = -7;
					}
					else if (bibi.charAt(l+1) == 'K' && bibi.charAt(l+2) == 'O')
					{
						tabi[i] = -8;
					}
					else if (bibi.charAt(l+1) == 'K' && bibi.charAt(l+2) == 'A')
					{
						tabi[i] = -9;
					}
					else if (bibi.charAt(l+1) == 'K' && bibi.charAt(l+2) == 'E')
					{
						tabi[i] = -10;
					}
					else if (bibi.charAt(l+1) == 'K' && bibi.charAt(l+2) == 'I')
					{
						tabi[i] = -11;
					}
					else if (bibi.charAt(l+1) == 'D' && bibi.charAt(l+2) == 'O')
					{
						tabi[i] = -12;
					}
					else if (bibi.charAt(l+1) == 'D' && bibi.charAt(l+2) == 'A')
					{
						tabi[i] = -13;
					}
					else if (bibi.charAt(l+1) == 'D' && bibi.charAt(l+2) == 'E')
					{
						tabi[i] = -14;
					}
					else if (bibi.charAt(l+1) == 'D' && bibi.charAt(l+2) == 'I')
					{
						tabi[i] = -15;
					}
					l = l+2;
				}
				k = (length/2)-1;
				for(int i = 0; i <= ((length/2)-1); i++)					//On replace le tout dans le bon sens
				{
					tabi2[i] = tabi[k];
					k = k - 1;
				}
					nb2 = Conversion16to10(tabi2, bibi);					//On convertie notre nombre de la base 16 a la base 10				
			}
		else											//Sinon (si le nombre est positif)
		{
			//pareil sans les -
			for(int i = 0; i <= length/2; i++)
			{
				tabi[i] = 0;
			}
			for(int i = 0; i <= length/2; i++)
			{
				tabi2[i] = 0;
			}
				l = -1;
				for(int i = 0; i <= ((length/2)-1); i++)
				{
				if (bibi.charAt(l+1) == 'H' && bibi.charAt(l+2) == 'O')
				{
					tabi[i] = 0;
				}
				else if (bibi.charAt(l+1) == 'H' && bibi.charAt(l+2) == 'A')
				{
					tabi[i] = 1;
				}
				else if (bibi.charAt(l+1) == 'H' && bibi.charAt(l+2) == 'E')
				{
					tabi[i] = 2;
				}
				else if (bibi.charAt(l+1) == 'H' && bibi.charAt(l+2) == 'I')
				{
					tabi[i] = 3;
				}
				else if (bibi.charAt(l+1) == 'B' && bibi.charAt(l+2) == 'O')
				{
					tabi[i] = 4;
				}
				else if (bibi.charAt(l+1) == 'B' && bibi.charAt(l+2) == 'A')
				{
					tabi[i] = 5;
				}
				else if (bibi.charAt(l+1) == 'B' && bibi.charAt(l+2) == 'E')
				{
					tabi[i] = 6;
				}
				else if (bibi.charAt(l+1) == 'B' && bibi.charAt(l+2) == 'I')
				{
					tabi[i] = 7;
				}
				else if (bibi.charAt(l+1) == 'K' && bibi.charAt(l+2) == 'O')
				{
					tabi[i] = 8;
				}
				else if (bibi.charAt(l+1) == 'K' && bibi.charAt(l+2) == 'A')
				{
					tabi[i] = 9;
				}
				else if (bibi.charAt(l+1) == 'K' && bibi.charAt(l+2) == 'E')
				{
					tabi[i] = 10;
				}
				else if (bibi.charAt(l+1) == 'K' && bibi.charAt(l+2) == 'I')
				{
					tabi[i] = 11;
				}
				else if (bibi.charAt(l+1) == 'D' && bibi.charAt(l+2) == 'O')
				{
					tabi[i] = 12;
				}
				else if (bibi.charAt(l+1) == 'D' && bibi.charAt(l+2) == 'A')
				{
					tabi[i] = 13;
				}
				else if (bibi.charAt(l+1) == 'D' && bibi.charAt(l+2) == 'E')
				{
					tabi[i] = 14;
				}
				else if (bibi.charAt(l+1) == 'D' && bibi.charAt(l+2) == 'I')
				{
					tabi[i] = 15;
				}
				l = l+2;
				}
				k = (length/2)-1;
				for(int i = 0; i <= ((length/2)-1); i++)
				{
					tabi2[i] = tabi[k];
					k = k - 1;
				}
				nb2 = Conversion16to10(tabi2, bibi);
		}
		return nb2;				//On retourne notre resultat
	}

			public static long Main(String bibi) {
				return Conversionbibito10(bibi);				//On appel notre methode et on renvoie le resultat
			}
}

