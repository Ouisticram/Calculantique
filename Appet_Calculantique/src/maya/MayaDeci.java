package maya;

public class MayaDeci {
	private String valeur;
	private int nbRond;
	private int nbPoint;
	private int nbTrait;
	public int nbgroupe=1;
	private long[] tab;

	public MayaDeci(String nombre) {
		this.valeur = nombre;
		this.nbRond=0;
		this.nbPoint=0;
		this.nbTrait=0;
		this.tab = new long[0];
	}

	public int getGroup (){
		char lettre='\n';		

		for(int i=0; i < this.valeur.length();i++)
		{
			lettre = this.valeur.charAt(i);
			if(lettre=='\n')
			{this.nbgroupe++;}
		}
		return this.nbgroupe;
	}

	public void setTab(int taille) {
		this.tab= new long[taille];
	}


	public boolean validMaya(){
		boolean valide=true;
		char lettre;
		int rendu = 0;
		int groupe = 0;
		boolean rondExist = false, traitExist = false, pointExist = false;
		for (int i=0 ; i<this.valeur.length(); i++)
		{
		lettre=this.valeur.charAt(i);
		if (lettre!='\uF7FB' && lettre!='.' && lettre!='-' && lettre!='\n') { valide=false; break;}
		}
		while (groupe < this.getGroup() && rendu < this.valeur.length()){
			if (this.valeur.charAt(rendu) == '\n'){
				rondExist = false;
				traitExist = false;
				pointExist = false;
				rendu++;
			}
			while (rendu < this.valeur.length() && this.valeur.charAt(rendu) != '\n'){
				if (this.valeur.charAt(rendu) == '\uF7FB' && !rondExist && !pointExist && !traitExist){
					rendu++;
					rondExist = true;
					pointExist = true;
					traitExist = true;
				}
				else if(this.valeur.charAt(rendu) =='-' && !traitExist){
					while (rendu < this.valeur.length() && this.valeur.charAt(rendu) =='-'){
						rendu++;
					}
					traitExist = true;
				}
				else if (this.valeur.charAt(rendu) =='.' && !pointExist && !traitExist){
					while (rendu < this.valeur.length() && this.valeur.charAt(rendu) =='.'){
						rendu++;
					}
					pointExist = true;
				}
				else{return false;}
			}
		}
		return valide;
	}

	public void initTab(){
		for(int i=0;i<this.tab.length;i++)
		{
			tab[i]=0;
		}
	}		


	public long convertDeci(){
		this.setTab(this.getGroup()); // on créer un tableau de la taille du nombre de groupe 
		this.initTab(); // et on initialise ce tableau
		int i=this.tab.length-1,j=0; // on initialise i a la taille du tableau pour compléter le tableau dans le sens ou insère la valeur des nombres
		long somme=0;
		char lettre;

		while ( i >= 0 && j < this.valeur.length()) // tant qu'on ne dépasse pas le tableau et la taille du mot on fait
		{	// on se situe a l'indice i 
			lettre = this.valeur.charAt(j); // on récpère la lettre dans la variable lettre
			if(lettre=='.') {this.tab[i]=this.tab[i]+1;} // si la lettre est un . on incrémente la valeur en i de 1
			else if (lettre=='-') { this.tab[i]=this.tab[i]+5;} // si la lettre est _ on incrémente la valeur en i de 5
			else if (lettre=='\uF7FB') { this.tab[i]=this.tab[i]+0;} // si on trouve un r on incrémente la valeur en i de 0
			else {i--;} // si on a un espace c'est a dire qu'on change de groupe donc on décremente i
			j++; // on passe a la lettre suivante
		}

		// après avoir complété le tableau on va faire la somme de toutes les cases
		for (int x=this.tab.length-1; x>=0 ;x--) // on pars de la derniere a la première
		{
			somme=somme+(this.tab[x]*(int)(Math.pow(20,x))); // on ajoute la somme + la valeur en tab(i)*2^i
		}

		return somme; // on retourne la somme
	}
}
