package shadok;
public class Decimal
{

long valeur;
int A;
String Sha=""; //Initialisation la chaine de caractères.

//Constructeur.
public Decimal(long value)
{
 this.valeur=value;
}

//Méthode de récupération de valeur (inutile en dehors des tests).
public long getVal()
{
 return this.valeur;
}

//Méthode de modification de valeur.
public void setVal(long val)
{
 this.valeur=val;
}

//Méthode de conversion.
public String ConvSha(long nb)
{
 int Cpt=0;
 long [] tab= new long [10000000];
 long [] Itab = new long [10000000];
 do //Boucle de stockage des restes dans un vecteur.
 {
   long Reste = nb % 4;
   nb = nb/4;
   tab[Cpt]=Reste;
   Cpt= Cpt+1;
 } while(nb!=0);
 A = Cpt;
 for(int i=0; i<=Cpt; i++) //Boucle d'inversion du vecteur.
 {
   Itab[A] = tab[i];
	 A = A-1;
 }

 for (int i=1;i<=Cpt;i++) //Boucle d'identification.
 {
	 if (Itab[i]==0) //Si le reste stocké est 0, on ajoute "GA" � la chaine.
          {
	    Sha=Sha+"GA";
	  }
   	 else if (Itab[i]==1) //Si le reste stocké est 1, on ajoute "BU" � la chaine.
          {
	    Sha=Sha+"BU";
	  }
   	 else if (Itab[i]==2) //Si le reste stocké est 2, on ajoute "ZO" � la chaine.
          {
	    Sha=Sha+"ZO";
	  }
   	 else if (Itab[i]==3) //Si le reste stocké est 3, on ajoute "MEU" � la chaine.
          {
	    Sha=Sha+"MEU";
	  }
 }
 String val = Sha;
 Sha = "";
return val; //Renvoie du mot Shadok traduit.
}
}
