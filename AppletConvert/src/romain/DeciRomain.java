package romain;

public class DeciRomain {
	private int valeur;

	public DeciRomain(int saVal){
		this.valeur = saVal;
	}

	public boolean validDeci (){
		return this.valeur!=0;
	}

	public String convertQuo(String c, int quo){
		String attribut="";		
		for (int i=0; i < quo ; i++)
		{
		attribut=attribut+c;
		}
		return attribut;
	}
	
	public String convertDeci(){
		int nombre = this.valeur;
		int quotient;
		String attribut="";
		
		if(nombre/1000 != 0) // cas ou le nombre est divisible par 1000
			{
			quotient = nombre/1000; // on récupère le quotient du nombre divisé par 1000
			attribut = attribut + this.convertQuo("M",quotient); // on ajoute a la variable attribut le nombre de M correspondant
			nombre = nombre%1000; // nombre prend alors la valeur du reste par 1000
			}	

		if (nombre/900 !=0)
			{ 
			
			attribut = attribut +convertQuo("CM",1);// 900 s'ecrit CM, nombre prend la valeur de 0 pour arreter les condition
			nombre=nombre%900; // on termine les verif's
			} 

		if(nombre/500 != 0) // cas ou le nombre est divisible par 500
			{
			quotient = nombre/500; //on récupère le quotient de nombre par 500
			attribut = attribut + this.convertQuo("D",quotient); // on ajoute a la variable attribut le nombre de D correspondant (1 max)
			nombre = nombre%500; // nombre prend alors la valeur du reste par 500
			}

		if(nombre/100 != 0) // cas ou le nombre est divisible par 100 (4 max)
			{
			quotient = nombre/100; //on récupère le quotient de nombre par 100
			if (quotient==4) // cas ou le quotient = 4 permet d'affivher CD au lieu de CCCC
			{
			attribut = attribut + this.convertQuo("CD",1); // on ajoute a la variable attribut le nombre de CD correspondant (1 max)
			}
			else
			{
			attribut = attribut + this.convertQuo("C",quotient); // on ajoute a la variable attribut le nombre de C correspondant
			}
			nombre = nombre%100; // nombre prend alors la valeur du reste par 100
			}
		
		if (nombre/90!=0)
			{ 
			attribut = attribut +convertQuo("XC",1);// 90 s'ecrit XC, nombre prend la valeur de 0 pour arreter les condition
			nombre=nombre%90; // on termine les verif's
			} 



		if(nombre/50 != 0) // cas ou le nombre est divisible par 50
			{
			quotient = nombre/50; //on récupère le quotient de nombre par 50
			attribut = attribut + this.convertQuo("L",quotient); // on ajoute a la variable attribut le nombre de L correspondant (1 max)
			nombre = nombre%50; // nombre prend alors la valeur du reste par 50
			}

		if(nombre/10 != 0) // cas ou le nombre est divisible par 10 (4 max)
			{
			quotient = nombre/10; //on récupère le quotient de nombre par 10
			if (quotient==4) // cas ou le quotient = 4 permet d'affivher XL au lieu de XXXX
			{
			attribut = attribut + this.convertQuo("XL",1); // on ajoute a la variable attribut le nombre de XL correspondant (1 max)
			}
			else
			{
			attribut = attribut + this.convertQuo("X",quotient); // on ajoute a la variable attribut le nombre de C correspondant
			}
			nombre = nombre%10; // nombre prend alors la valeur du reste par 100
			}
		if (nombre/9 != 0)
			{ 
			attribut = attribut +convertQuo("IX",1);// 9 s'ecrit IX, nombre prend la valeur de 0 pour arreter les condition
			nombre=nombre%9; // on termine les verif's
			} 
			
		if(nombre/5 != 0) // cas ou le nombre est divisible par 5
			{
			quotient = nombre/5; //on récupère le quotient de nombre par 5
			attribut = attribut + this.convertQuo("V",quotient); // on ajoute a la variable attribut le nombre de V correspondant (1 max)
			nombre = nombre%5; // nombre prend alors la valeur du reste par 5
			}
			

		if(nombre/1 != 0) // cas ou le nombre est divisible par 1 (4 max)
			{
			quotient = nombre/1; //on récupère le quotient de nombre par 1
			if (quotient==4) // cas ou le quotient = 4 permet d'affivher IV au lieu de IIII
			{
			attribut = attribut + this.convertQuo("IV",1); // on ajoute a la variable attribut le nombre de IV correspondant (1 max)
			}
			else
			{
			attribut = attribut + this.convertQuo("I",quotient); // on ajoute a la variable attribut le nombre de I correspondant
			}
			nombre = nombre%1; // nombre prend alors la valeur du reste par 1
			}		
	
	return attribut;
	}

}
