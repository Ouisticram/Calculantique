package egyptien;

public class Egyptodeci {
	private int nbBaton;
	private int nbPont;
	private int nbCorde;
	private int nbLotus;
	private int nbIndex;
	private int nbTetard;
	private int nbDieu;
	private long[] tab;
	private String Nombre;
	static int nbgroupe=1;
	
	public Egyptodeci(String nombre) {
		this.Nombre = nombre;
		this.nbBaton = 0;
		this.nbPont = 0;
		this.nbCorde = 0;
		this.nbLotus = 0;
		this.nbIndex = 0;
		this.nbTetard = 0;
		this.nbDieu = 0;
		this.tab = new long[0];
	}
	
	public int getGroup (){
		char lettre=' ';		

		for(int i=0; i < this.Nombre.length();i++)
		{
			lettre = this.Nombre.charAt(i);
			if(lettre==' ')
			{this.nbgroupe++;}
		}
		return this.nbgroupe;
	}
	
	public void setTab(int taille) {
		this.tab= new long[taille];
	}
	
	//Vérifie si le nombre est valide
	public boolean validationEgypt(){
		boolean valide=true;
		boolean[] tab = new boolean[7];
		for(int i=0; i < 7; i++){
			tab[i] = true;
		}
		for (int i=0 ; i < this.Nombre.length()-1; i=i+2)
		{
			// B = 1 -> b�ton, p = 10 -> pont, c = 100 -> corde, l = 1000 -> lotus, i = 10000 -> index, t = 100000 -> tetard, d = 1000000 -> dieu
			// "\uD80C\uDFFA" -> b�ton, "\uD80C\uDF86" -> pont, "\uD80C\uDF62" -> corde, "\uD80C\uDDBC", "\uD80C\uDCAD", "\uD80C\uDD8F", "\uD80C\uDC4F"
			if ((this.Nombre.charAt(i) == '\uD80C' && this.Nombre.charAt(i+1) == '\uDFFA') || (this.Nombre.charAt(i) == '\uD80C' && this.Nombre.charAt(i+1) == '\uDF86') || (this.Nombre.charAt(i) == '\uD80C' && this.Nombre.charAt(i+1) == '\uDF62') || (this.Nombre.charAt(i) == '\uD80C' && this.Nombre.charAt(i+1) == '\uDDBC') || (this.Nombre.charAt(i) == '\uD80C' && this.Nombre.charAt(i+1) == '\uDCAD') || (this.Nombre.charAt(i) == '\uD80C' && this.Nombre.charAt(i+1) == '\uDD8F') || (this.Nombre.charAt(i) == '\uD80C' && this.Nombre.charAt(i+1) == '\uDC4F')) { 
				;
			}
			else{return false;}
		}
		if (this.Nombre.length() <= 2){ ;}
		else{
		int i=0;
		while (i < this.Nombre.length()-1){
		if (i < this.Nombre.length()-1 && this.Nombre.charAt(i) == '\uD80C' && this.Nombre.charAt(i+1) == '\uDFFA' && tab[0]){
			while (i < this.Nombre.length()-1 && this.Nombre.charAt(i) == '\uD80C' && this.Nombre.charAt(i+1) == '\uDFFA'){
				nbBaton++;
				i=i+2;
			}
			tab[0] = false;
		}
		else if (i < this.Nombre.length()-1 && this.Nombre.charAt(i) == '\uD80C' && this.Nombre.charAt(i+1) == '\uDF86' && tab[1]){
			while (i < this.Nombre.length()-1 && this.Nombre.charAt(i) == '\uD80C' && this.Nombre.charAt(i+1) == '\uDF86'){
				nbPont++;
				i=i+2;
			}
			tab[1] = false;
		}
		else if (i < this.Nombre.length()-1 && this.Nombre.charAt(i) == '\uD80C' && this.Nombre.charAt(i+1) == '\uDF62' && tab[2]){
			while (i < this.Nombre.length()-1 && this.Nombre.charAt(i) == '\uD80C' && this.Nombre.charAt(i+1) == '\uDF62'){
				nbCorde++;
				i=i+2;
			}
			tab[2] = false;
		}
		else if (i < this.Nombre.length()-1 && this.Nombre.charAt(i) == '\uD80C' && this.Nombre.charAt(i+1) == '\uDDBC' && tab[3]){
			while (i < this.Nombre.length()-1 && this.Nombre.charAt(i) == '\uD80C' && this.Nombre.charAt(i+1) == '\uDDBC'){
				nbLotus++;
				i=i+2;
			}
			tab[3] = false;
		}
		else if (i < this.Nombre.length()-1 && this.Nombre.charAt(i) == '\uD80C' && this.Nombre.charAt(i+1) == '\uDCAD' && tab[4]){
			while (i < this.Nombre.length()-1 && this.Nombre.charAt(i) == '\uD80C' && this.Nombre.charAt(i+1) == '\uDCAD'){
				nbIndex++;
				i=i+2;
			}
			tab[4] = false;
		}
		else if (i < this.Nombre.length()-1 && this.Nombre.charAt(i) == '\uD80C' && this.Nombre.charAt(i+1) == '\uDD8F' && tab[5]){
			while (i < this.Nombre.length()-1 && this.Nombre.charAt(i) == '\uD80C' && this.Nombre.charAt(i+1) == '\uDD8F'){
				nbTetard++;
				i=i+2;
			}
			tab[5] = false;
		}
		else if (i < this.Nombre.length()-1 && this.Nombre.charAt(i) == '\uD80C' && this.Nombre.charAt(i+1) == '\uDC4F' && tab[6]){
			while (i < this.Nombre.length()-1 && this.Nombre.charAt(i) == '\uD80C' && this.Nombre.charAt(i+1) == '\uDC4F'){
				nbDieu++;
				i=i+2;
			}
			tab[6] = false;
		}
		else{return false;}
		}
		}
		return valide;
	}
	
	public void initTab(){ //initialisation du tableau pour placer les valeurs
		for(int i=0;i<this.tab.length;i++)
		{
			tab[i]=0; //On remplit chaque case par un zéro pour ne pas fausser l'addition de fin
		}
	}
	
	public long convertion(){
		this.setTab(this.nbgroupe); // on créer un tableau de la taille du nombre de groupe 
		this.initTab(); // et on initialise ce tableau
		int i=this.tab.length-1,j=0; // on initialise i a la taille du tableau pour compléter le tableau dans le sens ou insère la valeur des nombres
		long somme=0;
		String signe;

		while ( i >= 0 && j < this.Nombre.length()) // tant qu'on ne dépasse pas le tableau et la taille du mot on fait
		{	// on se situe a l'indice i 
			if (this.Nombre.charAt(j)=='\uD80C' && this.Nombre.charAt(j+1) == '\uDFFA'){
				this.tab[i]=this.tab[i]+1;
			}
			else if (this.Nombre.charAt(j)=='\uD80C' && this.Nombre.charAt(j+1) == '\uDF86'){
				this.tab[i]=this.tab[i]+10;
			}
			else if (this.Nombre.charAt(j)=='\uD80C' && this.Nombre.charAt(j+1) == '\uDF62'){
				this.tab[i]=this.tab[i]+100;
			}
			else if (this.Nombre.charAt(j)=='\uD80C' && this.Nombre.charAt(j+1) == '\uDDBC'){
				this.tab[i]=this.tab[i]+1000;
			}
			else if (this.Nombre.charAt(j)=='\uD80C' && this.Nombre.charAt(j+1) == '\uDCAD'){
				this.tab[i]=this.tab[i]+10000;
			}
			else if (this.Nombre.charAt(j)=='\uD80C' && this.Nombre.charAt(j+1) == '\uDD8F'){
				this.tab[i]=this.tab[i]+100000;
			}
			else if (this.Nombre.charAt(j)=='\uD80C' && this.Nombre.charAt(j+1) == '\uDC4F'){
				this.tab[i]=this.tab[i]+1000000;
			}
			else{
				i--;
			} // si on a un espace c'est a dire qu'on change de groupe donc on décremente i
			j= j + 2; // on passe a la lettre suivante
		}

		// après avoir complété le tableau on va faire la somme de toutes les cases
		for (int x=this.tab.length-1; x>=0 ;x--) // on pars de la derniere a la première
		{
			somme=somme+(this.tab[x]); // on ajoute la somme
		}

		return somme; // on retourne la somme
	}
}
