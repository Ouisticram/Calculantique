package babylonien;

public class Clou {
	private int valeur;
	
	public Clou(int saVal){
		this.valeur=saVal;
	}
	
	public Clou (){
		this(1);
	}

	public int getValeur(){
		return this.valeur;
	}
	
	public String nbClou1 (int nombre){
		
		int i = nombre%10;
		String nombre2 = "";
		
		while (i > 0)
		{
			nombre2 = nombre2 + "\uD808\uDC79";
			i--;
		}
		
		return nombre2;
	}
	
	public String nbClou60 (int nombre){
		
		int i = nombre % 60;
		String nombre2 ="";
		
		while (i>0) 
		{
			nombre2 = nombre2 + "\uD808\uDC79";
			i--;
		}
		return nombre2;
	}
}
