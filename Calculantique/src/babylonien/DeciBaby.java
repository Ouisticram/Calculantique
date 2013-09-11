package babylonien;

public class DeciBaby {
	private long valeur;
	static Chevron chevron;
	static Clou clou;
	
	public DeciBaby ( long saValeur){
		this.valeur = saValeur;
	}
	
	public String convDeciBaby(){
		long quotient=0;
		int n=5; // initilisation de la puissance a 5	
		String attribut=""; // initialisation de la variable a retourner a une chaine vide
		long nombre = this.valeur; // On initialise une variable nombre qui prend la valeur du nombre  a convertir
		this.chevron = new Chevron();
		this.clou = new Clou();

		while ( n >=0 ) // on boucle tant que la puissance est supérieur ou égale à 0
		{
		quotient = nombre / (int)Math.pow(60,n); //quotient prend la valeur du quotient du nombre par 60 à la puissance n
		attribut = attribut + this.chevron.nbChevron1(quotient) + this.clou.nbClou1(quotient); //on met dans attribut le nombre de clou et le nombre de chevron
		if((chevron.nbChevron1(quotient)=="") && (clou.nbClou1(quotient)=="") )
			{
				attribut = attribut +"";
			}
		else {attribut = attribut + " ";}

		nombre = nombre%(long)Math.pow(60, n);
		n--;
		}	
		return attribut;
	}


 }
