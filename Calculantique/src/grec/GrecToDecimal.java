package grec;


public class GrecToDecimal
{
	
	// equivalents char - int
	static int grecdeci(char nb)
	{ int rendu = 0;
		if(nb == '\u03B1') {rendu = 1;}
		else if (nb == '\u03B2') {rendu = 2;}
		else if (nb == '\u03B3') {rendu = 3;}
		else if (nb == '\u03B4') {rendu = 4;}
		else if (nb == '\u03B5') {rendu = 5;}
		else if (nb == '\u03DD') {rendu = 6;}
		else if (nb == '\u03B6') {rendu = 7;}
		else if (nb == '\u03B7') {rendu = 8;}
		else if (nb == '\u03B8') {rendu = 9;}
		else if (nb == '\u03B9') {rendu = 10;}
		else if (nb == '\u03BA') {rendu = 20;}
		else if (nb == '\u03BB') {rendu = 30;}
		else if (nb == '\u03BC') {rendu = 40;}
		else if (nb == '\u03BD') {rendu = 50;}
		else if (nb == '\u03BE') {rendu = 60;}
		else if (nb == '\u03BF') {rendu = 70;}
		else if (nb == '\u03C0') {rendu = 80;}
		else if (nb == '\u03DF') {rendu = 90;}
		else if (nb == '\u03C1') {rendu = 100;}
		else if (nb == '\u03C3') {rendu = 200;}
		else if (nb == '\u03C4') {rendu = 300;}
		else if (nb == '\u03C5') {rendu = 400;}
		else if (nb == '\u03C6') {rendu = 500;}
		else if (nb == '\u03C7') {rendu = 600;}
		else if (nb == '\u03C8') {rendu = 700;}
		else if (nb == '\u03C9') {rendu = 800;}
		else if (nb == '\u03E1') {rendu = 900;}
		return rendu;
	}
	
	// conversion char - int :
	static int conversion(String nb)
	{
		int retour = 0;
		int nbchiffre = nb.length();
		for (int i = 0; i<nbchiffre;i++)
		{
			if (i==0)
			{
				if(nb.charAt(i) == '\u02B9') {continue;}
				else {retour = retour + grecdeci(nb.charAt(i));}
			}
			else
			{
				if(nb.charAt(i-1) == '\u02B9') {retour = retour + 1000*grecdeci(nb.charAt(i));}
				else {retour = retour + grecdeci(nb.charAt(i));}
			}
		}
		return retour;
	}
	
	/* verifications : attention ca se complique !
	 * un nombre doit avoir au max 5 caracteres. (facile !)
	 * On pose "le premier caractere est celui le plus a droite".
	 * s'il a cinq caracteres, le cinquieme doit absolument etre une ' et le quatrieme un chiffre des unites.
	 * Le troisieme doit etre un chiffre des centaines,
	 * le deuxieme un chiffre des dizaines
	 * le premier un chiffre des unites.
	 * 
	 * Ah bah non, c'est encore plus complique que ca. AAARH.
	 * 
	 */
	static boolean verification(String nb)
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

	public static int main(String nb)
	{
		int deci = conversion(nb);
		return deci;
	}

}
