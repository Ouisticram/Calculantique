package babylonien;
public class Chevron {

	private long valeur;
	
	public Chevron(int val){
		this.valeur=val;		
	}
	
	public Chevron()
	{
		this(10);
	}
	
	public long getValeur() {
		return this.valeur;
	}
	
	public String nbChevron1(long nombre){
		
		long i = nombre / 10;
		String nombre2 = "";
		
		while ( i > 0)
		{
			nombre2 = nombre2 + "\uD808\uDF0B";
			i--;
		}
		return nombre2;
	}
	
	public String nbChevron60 (int nombre){
		
		int i = nombre / 60;
		String nombre2 ="";
		
		while (i>0) 
		{
			nombre2 = nombre2 + "\uD808\uDF0B";
			i--;
		}
		return nombre2;
	}
	
}
