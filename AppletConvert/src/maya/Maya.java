package maya;

public class Maya {
	private int nbRond;
	private int nbPoint;
	private int nbTrait;

	public Maya(int nbR,int nbP,int nbT){
		this.nbRond = nbR;
		this.nbPoint = nbP;
		this.nbTrait = nbT;
	}

	public Maya(){
		this(0,0,0);
	}

	public void setRond(int nbR){
		this.nbRond = nbR;
	}

	public void setPoint(int nbP){
		this.nbPoint=nbP;
	}

	public void setTrait(int nbT){
		this.nbTrait=nbT;
	}

	public String getRond (){
		String attribut="";
		for (int i=0;i<this.nbRond;i++)
		{
			attribut = attribut +"\n\uF7FB";
		}
		return attribut;
	}

	public String getPoint (){
		String attribut="";
		for (int i=0;i<this.nbPoint;i++)
		{
			attribut = attribut +".";
		}
		return attribut;
	}

	public String getTrait (){
		String attribut="";
		for (int i=0;i<this.nbTrait;i++)
		{
			attribut = attribut +"\n-";
		}
		return attribut;
	}
	
	public String convertNombre(int nb){
		String attribut="";
		int quotient,n=5;
		int nb1=0, nb5=0;

		while (n>=0) // on boucle sur les puissances de 20 jusqu'Ã  20â�°
		{
			if (nb==0) 
			{
				this.nbTrait = 0; // on a donc pas de trait
				this.nbPoint = 0; // ni de point
				this.nbRond = 1; // mais un rond	
			}

			else
			{
				quotient = nb/((int)Math.pow(20,n));
				if (quotient !=0 ) // si le quotient est diffÃ©rent de 0 
				{
				nb5 = quotient/5;  // on recupÃ¨re le quotient du nb par 5
				quotient = quotient % 5; // quotient prend la valeur du reste
				nb1 = quotient/1; // on divise a nouveau le quotient pour savoir combien il y a de 1
				this.nbTrait = nb5; //on stock le nombre de 5 dans le nombre de Trait
				this.nbPoint = nb1; //on stock le nombre de 1 dans le nombre de Point
				this.nbRond = 0; // on a donc pas de 0
				}
				else // si le quotient est Ã©gale a 0
				{
				this.nbTrait = 0; // on a donc pas de trait
				this.nbPoint = 0; // ni de point
				this.nbRond =  1; // mais un rond
				}
			}
			
			if (this.nbTrait!=0 || this.nbPoint!=0 || this.nbRond != 0) 
			{
			attribut = attribut+"\n"+ this.getRond() +this.getPoint() + this.getTrait()+"\n";
			}

			nb = nb%((int)Math.pow(20,n)); // nb prend la valeur du reste dans la division par 20^n
			n--; // on dÃ©crement la puissance
		}
		return attribut;
	}

}

