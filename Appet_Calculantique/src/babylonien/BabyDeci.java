package babylonien;

public class BabyDeci {
	private String valeur;
	static long[] tab;
	static int nbClou;
	static int nbChevron;
	public int nbgroupe=1;


	public BabyDeci(String nombre){
		this.valeur = nombre;			
	}

	public int getGroupe(){
		char lettre;
		for (int i=0; i <this.valeur.length();i++)
		{
			lettre= this.valeur.charAt(i);
			if (lettre ==' ') { this.nbgroupe++;}		
		}
	return this.nbgroupe;
	}

	public void initTab(){
		for(int i=0;i<this.tab.length;i++)
		{
			this.tab[i]=0;
		}
	}

	public void setTab(int taille){
		this.tab = new long[taille];
	}

	public boolean validBaby() {
		boolean valide=false;
		int groupe = 0;
		int rendu = 0;
		boolean chevExist = false, clouExist = false, ordre = true;
		if (valeur.length() == 1 || valeur.length() == 2){
			if((this.valeur.charAt(0) =='\uD808' && this.valeur.charAt(1) == '\uDC79') || (this.valeur.charAt(0) =='\uD808' && this.valeur.charAt(1) == '\uDF0B')) 
			{
			valide=true;}
			else {return false;}	
		}
		else{
			for (int i=0; i < this.valeur.length()-1;i++)
			{
				if((this.valeur.charAt(i) =='\uD808' && this.valeur.charAt(i+1) == '\uDC79') || (this.valeur.charAt(i) =='\uD808' && this.valeur.charAt(i+1) == '\uDF0B') || this.valeur.charAt(i)==' ') {
					valide=true;
					if (this.valeur.charAt(i)==' '){i--;}
				}
				else {return false;}	
				i++;
			}
		}
		while (groupe < this.getGroupe() && rendu < this.valeur.length()){
			if (this.valeur.charAt(rendu) == ' '){
				chevExist = false;
				clouExist = false;
				ordre = true;
				groupe++;
				rendu++;
			}
			while (rendu < this.valeur.length()-1 && this.valeur.charAt(rendu) != ' '){
				if(this.valeur.charAt(rendu) =='\uD808' && this.valeur.charAt(rendu+1) == '\uDF0B' && !chevExist && ordre){
					while (rendu < this.valeur.length() && this.valeur.charAt(rendu) =='\uD808' && this.valeur.charAt(rendu+1) == '\uDF0B'){
						rendu = rendu+2;
					}
					chevExist = true;
				}
				else if (this.valeur.charAt(rendu) =='\uD808' && this.valeur.charAt(rendu+1) == '\uDC79' && !clouExist){
					while (rendu < this.valeur.length() && this.valeur.charAt(rendu) =='\uD808' && this.valeur.charAt(rendu+1) == '\uDC79'){
						rendu = rendu+2;
						ordre = false;
					}
					clouExist = true;
				}
				else{return false;}
			}
		}
		return valide;
	}

	public long convBaby(){
		this.setTab(this.getGroupe()); // on créer un table qui a pour taille le nombre de groupe
		this.initTab(); // on initialise ce tableau
		int i=this.tab.length-1,j=0; // on initialise l'indice a la taille-1 du tableau
		long somme=0;
		while ( i >= 0 && j < this.valeur.length()) // on boucle tant que l'indice est supérieur ou égale a 0
		{
			if(this.valeur.charAt(j) =='\uD808' && this.valeur.charAt(j+1) == '\uDF0B') { this.tab[i]=this.tab[i]+10;} // si la lettre est < on incrémente de 10
			else if (this.valeur.charAt(j) =='\uD808' && this.valeur.charAt(j+1) == '\uDC79') { this.tab[i]=this.tab[i]+1;} //si la lettre est | on incrément de 1
			else {i--;} // si la lettre est un espace on passe au groupe suivante on décremente donc i
			
			j=j+2; // on passe a la lettre suivante
		}
		// on boucle alors sur le tableau
		for (int x=this.tab.length-1; x>=0 ;x--)
		{
			somme=somme+(this.tab[x]*(long)(Math.pow(60,x))); // somme prend la valeur de somme+la valeur dans tab[x]*60 a la puissance x
		}

		return somme;
	}

}
