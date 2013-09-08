package egyptien;

public class Decitoegypt{
	private int nbBaton;
	private int nbPont;
	private int nbCorde;
	private int nbLotus;
	private int nbIndex;
	private int nbTetard;
	private int nbDieu;
	
	public Decitoegypt(int nbB,int nbP,int nbC,int nbL,int nbI,int nbT,int nbD){
		this.nbBaton = nbB;
		this.nbPont = nbP;
		this.nbCorde = nbC;
		this.nbLotus = nbL;
		this.nbIndex = nbI;
		this.nbTetard = nbT;
		this.nbDieu = nbD;
	}
	
	public Decitoegypt(){
		this(0,0,0,0,0,0,0);
	}

	public void setBaton(int nbB){
	this.nbBaton = nbB;
	}

	public void setPont(int nbP){
		this.nbPont=nbP;
	}

	public void setCorde(int nbC){
		this.nbCorde=nbC;
	}
		
	public void setLotus(int nbL){
		this.nbLotus=nbL;
	}
		
	public void setIndex(int nbI){
		this.nbIndex=nbI;
	}
		
	public void setTetard(int nbT){
		this.nbTetard=nbT;
	}
		
	public void setDieu(int nbD){
		this.nbDieu=nbD;
	}

	public String getBaton (){
		String chainechar="";
		for (int i=0;i<this.nbBaton;i++)
		{
			chainechar = chainechar +"\uD80C\uDFFA";
		}
		return chainechar;
	}

	public String getPont (){
		String chainechar="";
		for (int i=0;i<this.nbPont;i++)
		{
			chainechar = chainechar +"\uD80C\uDF86";
		}
		return chainechar;
	}
		
	public String getCorde (){
		String chainechar="";
		for (int i=0;i<this.nbCorde;i++)
		{
			chainechar = chainechar +"\uD80C\uDF62";
		}
		return chainechar;
	}

	public String getLotus (){
		String chainechar="";
		for (int i=0;i<this.nbLotus;i++)
		{
			chainechar = chainechar +"\uD80C\uDDBC";
		}
		return chainechar;
	}
		
	public String getIndex (){
		String chainechar="";
		for (int i=0;i<this.nbIndex;i++)
		{
			chainechar = chainechar +"\uD80C\uDCAD";
		}
		return chainechar;
	}
		
	public String getTetard (){
		String chainechar="";
		for (int i=0;i<this.nbTetard;i++)
		{
			chainechar = chainechar +"\uD80C\uDD8F";
		}
		return chainechar;
	}
		
	public String getDieu (){
		String chainechar="";
		for (int i=0;i<this.nbDieu;i++)
		{
			chainechar = chainechar +"\uD80C\uDC4F";
		}
		return chainechar;
	}
	
	public String convertionNombre(int nb){
		String chainechar="";
		int reste=0;
		int nb1=0, nb10=0, nb100=0, nb1000=0, nb10000=0, nb100000=0, nb1000000=0;
		
		if (nb==0){
			System.out.println("Le zéro n'existe pas en égyptien.");
		}
		
		else{
			nb1000000 = nb / 1000000;
			reste = nb % 1000000;
			nb100000 = reste / 100000;
			reste = reste % 100000;
			nb10000 = reste / 10000;
			reste = reste % 10000;
			nb1000 = reste / 1000;
			reste = reste % 1000;
			nb100 = reste / 100;
			reste = reste % 100;
			nb10 = reste / 10;
			reste = reste % 10;
			nb1 = reste / 1;
			
			this.nbBaton = nb1;
			this.nbPont = nb10;
			this.nbCorde = nb100;
			this.nbLotus = nb1000;
			this.nbIndex = nb10000;
			this.nbTetard = nb100000;
			this.nbDieu = nb1000000;
			
			chainechar = this.getBaton() + this.getPont() + this.getCorde() + this.getLotus() + this.getIndex() + this.getTetard() + this.getDieu();
		}
		return chainechar;
	}
}
