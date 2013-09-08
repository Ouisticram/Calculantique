package shadok;
public class Shadok
{
String Sha;

public Shadok(String leShad)
{
 this.Sha=leShad;
}

//Méthode de récupération de valeur. (inutile en dehors des tests)
public String getVal()
{
 return this.Sha;
}

//Méthode de modification de valeur.
public void setVal(String mot)
{
 this.Sha=mot;
}

//M�thode de verification du mot shadok
public boolean VerifShadok(String valeur)
{
	boolean valide=false; // booleen retourner
	char lettre1,lettre2,lettre3; // verifie les lettres par 3
	
	if (valeur.length() == 1) {valide=false;} // pas de mot de 1 lettre
	else if (valeur.length() == 2) // si la longueur du mot est de 2 lettres on doit avori soit GA,BU ou ZO
		{
			if(valeur.equals("GA") || valeur.equals("BU") || valeur.equals("ZO"))
			{
				valide=true;
			}
		}
	else if(valeur.length()==3)
		{
		if (valeur.equals("MEU"))
			{
				valide=true;
			}			
		}
	else if (valeur.length()>3) // si la longueur du mot est >2 on doit comparer lettre par lettre pour savori si c'est bon !
		{
			for (int i=0; i<valeur.length()-1;i+=2) // on boucle jusqu a longueur -1 et on va de deux en deux
			{
				lettre1=valeur.charAt(i); //on recupere la premiere lettre
				if (lettre1=='G' || lettre1=='B' || lettre1=='Z' || lettre1=='M') // elle doit etre soit un G, un B, un Z ou un M
				{
					lettre2=valeur.charAt(i+1); // on recupere la deuxieme lettre
					if (lettre2=='A' || lettre2=='U' || lettre2=='O' || lettre2=='E') // la deuxieme lettre est soit un A,U,O,E
					{
					
						if(lettre1=='M' && lettre2=='E') // si on a un M on doit alors avoir MEU
						{
							lettre3=valeur.charAt(i+2);
							if(lettre3=='U')
							{
							valide=true;
							i++;
							}
							else {valide=false; break;}
						}
						else if(lettre1=='M' && lettre2!='E') {valide=false;break;}
						else if(lettre1=='G' && lettre2!='A') {valide=false;break;}
						else if(lettre1=='B' && lettre2!='U') {valide=false;break;}
						else if(lettre1=='Z' && lettre2!='O') {valide=false;break;}
						else valide=true;
					}
					else {valide=false; break;}
				}
				else {valide=false;break;}
			}//fin pour
	}	
	return valide;
}

//Méthode de conversion vers le décimal.
public long convDec(String Sha)
{
 long D = 0;
 String sub ="";
 int rang = 0;
 int pos = Sha.length();
 do {
      sub = ("" + Sha.charAt(pos-2) + Sha.charAt(pos-1));
      if (sub.equals("GA"))
	{
	 D = D+0*(long)(Math.pow(4,rang));
	 pos = pos-2;
	}
      else if (sub.equals("BU"))
	{
	 D = D+1*(long)(Math.pow(4,rang));
	 pos = pos-2;
	}
      else if (sub.equals("ZO"))
	{
	 D = D+2*(long)(Math.pow(4,rang));
	 pos = pos-2;
	}
      else if (sub.equals("EU"))
	{
	 D = D+3*(long)(Math.pow(4,rang));
	 pos = pos-3;
	}
      rang=rang+1;
    } while (pos > 0);

 return D;

}

}
