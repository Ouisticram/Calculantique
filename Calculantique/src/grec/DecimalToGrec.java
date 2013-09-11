package grec;


public class DecimalToGrec {
	

// donne le nombre de chiffre d'un nombre donne.
static int nbchiffre(long nb)
{
	int retour = String.valueOf(nb).length();
	return retour;
}


// equivalents int - string
static String decigrec(long nb)
{ String rendu = "";
	if(nb == 1) {rendu = "\u03B1";}
	else if (nb == 2) {rendu = "\u03B2";}
	else if (nb == 3) {rendu = "\u03B3";}
	else if (nb == 4) {rendu = "\u03B4";}
	else if (nb == 5) {rendu = "\u03B5";}
	else if (nb == 6) {rendu = "\u03DD";}
	else if (nb == 7) {rendu = "\u03B6";}
	else if (nb == 8) {rendu = "\u03B7";}
	else if (nb == 9) {rendu = "\u03B8";}
	else if (nb == 10) {rendu = "\u03B9";}
	else if (nb == 20) {rendu = "\u03BA";}
	else if (nb == 30) {rendu = "\u03BB";}
	else if (nb == 40) {rendu = "\u03BC";}
	else if (nb == 50) {rendu = "\u03BD";}
	else if (nb == 60) {rendu = "\u03BE";}
	else if (nb == 70) {rendu = "\u03BF";}
	else if (nb == 80) {rendu = "\u03C0";}
	else if (nb == 90) {rendu = "\u03DF";}
	else if (nb == 100) {rendu = "\u03C1";}
	else if (nb == 200) {rendu = "\u03C3";}
	else if (nb == 300) {rendu = "\u03C4";}
	else if (nb == 400) {rendu = "\u03C5";}
	else if (nb == 500) {rendu = "\u03C6";}
	else if (nb == 600) {rendu = "\u03C7";}
	else if (nb == 700) {rendu = "\u03C8";}
	else if (nb == 800) {rendu = "\u03C9";}
	else if (nb == 900) {rendu = "\u03E1";}
	return rendu;
}


// convertit nb en son equivalent grec.
static String conversion(long nb)
{
	int nbchiffre, diviseur, i;
	String retour = "";
	nbchiffre = nbchiffre(nb);
	diviseur = (int)Math.pow((double)10,(double)nbchiffre);
	for(i=10; i<= diviseur;i=i*10)
	{
		if(i<10000)
		{
			retour =  decigrec(nb%i) + retour;
			nb = nb - nb%i;
		}
		else
		{
			retour =  "\u02B9"+decigrec((nb/1000)%10) + retour;
			nb = nb - nb%i;
		}
	}
	return retour;	
}



// verification le chiffre est compris entre 1 et 9999:
static boolean verification(long nb)
{
	boolean verif = true;
	if (nb > 9999) {verif = false;}
	else if (nb == 0) {verif = false;}
	return verif;
}

// programme principal
public static String main(long nb)
	{
		return conversion(nb);
	}
	
}
    			
    			
