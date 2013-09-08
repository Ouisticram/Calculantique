package japonais;
public class Decitojapo {

	//mÃ©thode permettant de compter le nombre de chiffre dans un nombre
	  static int size(long n) {
		  int length = 0;			//longueur retourner initialisÃ© Ã  0

		  if(n == 0) {				//si le nombre est 0 on retourne 1
		    return 1;
		  }

		  while(n != 0) {			//sinon tant que le nombre est diffÃ©rent de 0
		    n /= 10;				//on le divise par 10
		    ++length;				//On incrémente la longueure de 1
		  }

		  return length;			//on retourne la longueure
	  }	  
	
	static long[] conversionJap(long nb)
	{
		long[] tabi = new long[size(nb)];
		long nb1 = nb;
		for(int i = 0; i <= size(nb)-1; i++){
			long tmp = nb1 % 10;
			tabi[i] = tmp;
			nb1 = nb1 / 10;
		}		
		return tabi;
	}
	
	static char zeroAneuf(long nb){
		char jap = 0;
			if (nb == 0){
				jap = '\u3007';
				//jap = 'ã€‡';
			}
			else if (nb == 1){
				jap = '\u4E00';
				//jap = 'ä¸€';
			}
			else if (nb == 2){
				jap = '\u4E8C';
				//jap = 'äºŒ';
			}
			else if (nb == 3){
				jap = '\u4E09';
				//jap = 'ä¸‰';
			}
			else if (nb == 4){
				jap = '\u56DB';
				//jap = 'å››';
			}
			else if (nb == 5){
				jap = '\u4E94';
				//jap = 'äº”';
			}
			else if (nb == 6){
				jap = '\u516D';
				//jap = 'å…­';
			}
			else if (nb == 7){
				jap = '\u4E03';
				//jap = 'ä¸ƒ';
			}
			else if (nb == 8){
				jap = '\u516B';
				//jap = 'å…«';
			}
			else if (nb == 9){
				jap = '\u4E5D';
				//jap = 'ä¹�';
			}
			return jap;
	}
	
	static char deuxAneuf(long nb){
		char jap = 0;
			if (nb == 2){
				jap = '\u4E8C';
				//jap = 'äºŒ';
			}
			else if (nb == 3){
				jap = '\u4E09';
				//jap = 'ä¸‰';
			}
			else if (nb == 4){
				jap = '\u56DB';
				//jap = 'å››';
			}
			else if (nb == 5){
				jap = '\u4E94';
				//jap = 'äº”';
			}
			else if (nb == 6){
				jap = '\u516D';
				//jap = 'å…­';
			}
			else if (nb == 7){
				jap = '\u4E03';
				//jap = 'ä¸ƒ';
			}
			else if (nb == 8){
				jap = '\u516B';
				//jap = 'å…«';
			}
			else if (nb == 9){
				jap = '\u4E5D';
				//jap = 'ä¹�';
			}
			return jap;
	}
	
	static String dixcentmille(long nb, long nb1){
		String jap = "";
			if (nb != 0 && nb%4 == 1){
				jap = jap + '\u5341';
				//jap = jap + 'å��';
			}					
			if (nb != 0 && nb%4 == 2){
				jap = jap + '\u767E';
				//jap = jap + 'ç™¾';
			}
			if (nb != 0 && nb1 == 1 && nb%4 == 3){
				jap = jap + '\u5343' + '\u4E00';
				//jap = jap + 'å�ƒ' + 'ä¸€';
			}
			else if (nb != 0 && nb%4 == 3){
				jap = jap + '\u5343';
				//jap = jap + 'å�ƒ';
			}
			return jap;
		}
	
	static String conversion10toJap(long nb){
		long[] tab = new long[size(nb)];
		String jap = "";
		long tmp = nb;
		if (nb < 0){
			tmp = tmp * (-1);
			tab = conversionJap(tmp);		
		}
		else{
			tab = conversionJap(nb);
		}
		if (nb > 0 && nb%10 == 0)
		{
			for(int i = 0; i <= size(nb)-1; i++){
				switch(i){
				case 0 :
					break;
				case 1 :
					if (tab[i] != 0){
						jap = jap + '\u5341' + deuxAneuf(tab[i]) ;
						//jap = jap + 'å��' + deuxAneuf(tab[i]) ;
					}
					break;
				case 2 :
					if (tab[i] != 0){
						jap = jap + '\u767E' + deuxAneuf(tab[i]);
						//jap = jap + 'ç™¾' + deuxAneuf(tab[i]);
					}
					break;
				case 3 :
					if (tab[i] != 0){
						jap = jap + '\u5343' + deuxAneuf(tab[i]);
						//jap = jap + 'å�ƒ' + deuxAneuf(tab[i]);
					}
					break;
				case 4 :
					if (tab[i] != 0){
						jap = jap + '\u4E07' + deuxAneuf(tab[i]);
						//jap = jap + 'ä¸‡' + deuxAneuf(tab[i]);
					}
					if (tab[i] == 0 && (tab[i+1] != 0 || tab[i+2] != 0 || tab[i+3] != 0)){
						jap = jap + '\u4E07';
						//jap = jap + 'ä¸‡';
					}
					break;
				case 8 :
					if (tab[i] != 0 && tab[i] == 1){
						jap = jap + '\u5104' + '\u4E00' + deuxAneuf(tab[i]);
						//jap = jap + 'å„„' + 'ä¸€' + deuxAneuf(tab[i]);
					}
					else if (tab[i] != 0){
						jap = jap + '\u5104' + deuxAneuf(tab[i]);
						//jap = jap + 'å„„' + deuxAneuf(tab[i]);
					}
					if (tab[i] == 0 && (tab[i+1] != 0 || tab[i+2] != 0 || tab[i+3] != 0)){
						jap = jap + '\u5104';
						//jap = jap + 'å„„';
					}
					break;
				case 12 :
					if (tab[i] != 0 && tab[i] == 1){
						jap = jap + '\u5146' + '\u4E00' + deuxAneuf(tab[i]);
						//jap = jap + 'å…†' + 'ä¸€' + deuxAneuf(tab[i]);
					}
					else if (tab[i] != 0){
						jap = jap + '\u5146' + deuxAneuf(tab[i]);
						//jap = jap + 'å…†' + deuxAneuf(tab[i]);
					}
					if (tab[i] == 0 && (tab[i+1] != 0 || tab[i+2] != 0 || tab[i+3] != 0)){
						jap = jap + '\u5146';
						//jap = jap + 'å…†';
					}
					break;
				case 16 :
					if (tab[i] != 0 && tab[i] == 1){
						jap = jap + '\u4EAC' + '\u4E00' + deuxAneuf(tab[i]);
						//jap = jap + 'äº¬' + 'ä¸€' + deuxAneuf(tab[i]);
					}
					else if (tab[i] != 0){
						jap = jap + '\u4EAC' + deuxAneuf(tab[i]);
						//jap = jap + 'äº¬' + deuxAneuf(tab[i]);
					}
					if (tab[i] == 0 && (tab[i+1] != 0 || tab[i+2] != 0 || tab[i+3] != 0)){
						jap = jap + '\u4EAC';
						//jap = jap + 'äº¬';
					}
					break;
				case 20 :
					if (tab[i] != 0 && tab[i] == 1){
						jap = jap + '\u5793' + '\u4E00' + deuxAneuf(tab[i]);
					}
					else if (tab[i] != 0){
						jap = jap + '\u5793' + deuxAneuf(tab[i]);
					}
					if (tab[i] == 0 && (tab[i+1] != 0 || tab[i+2] != 0 || tab[i+3] != 0)){
						jap = jap + '\u5793';
					}	
					break;
				case 24 : 
					if (tab[i] != 0 && tab[i] == 1){
						jap = jap + '\u79ED' + '\u4E00' + deuxAneuf(tab[i]);
					}
					else if (tab[i] != 0){
						jap = jap + '\u79ED' + deuxAneuf(tab[i]);
					}
					if (tab[i] == 0 && (tab[i+1] != 0 || tab[i+2] != 0 || tab[i+3] != 0)){
						jap = jap + '\u79ED';
					}	
					break;
				case 28 : 
					if (tab[i] != 0 && tab[i] == 1){
						jap = jap + '\u7A63' + '\u4E00' + deuxAneuf(tab[i]);
					}
					else if (tab[i] != 0){
						jap = jap + '\u7A63' + deuxAneuf(tab[i]);
					}
					if (tab[i] == 0 && (tab[i+1] != 0 || tab[i+2] != 0 || tab[i+3] != 0)){
						jap = jap + '\u7A63';
					}	
					break;
				case 32 : 
					if (tab[i] != 0 && tab[i] == 1){
						jap = jap + '\u6E9D' + '\u4E00' + deuxAneuf(tab[i]);
					}
					else if (tab[i] != 0){
						jap = jap + '\u6E9D' + deuxAneuf(tab[i]);
					}
					if (tab[i] == 0 && (tab[i+1] != 0 || tab[i+2] != 0 || tab[i+3] != 0)){
						jap = jap + '\u6E9D';
					}	
					break;
				case 36 : 
					if (tab[i] != 0 && tab[i] == 1){
						jap = jap + '\u6F97' + '\u4E00' + deuxAneuf(tab[i]);
					}
					else if (tab[i] != 0){
						jap = jap + '\u6F97' + deuxAneuf(tab[i]);
					}
					if (tab[i] == 0 && (tab[i+1] != 0 || tab[i+2] != 0 || tab[i+3] != 0)){
						jap = jap + '\u6F97';
					}	
					break;
				case 40 : 
					if (tab[i] != 0 && tab[i] == 1){
						jap = jap + '\u6B63' + '\u4E00' + deuxAneuf(tab[i]);
					}
					else if (tab[i] != 0){
						jap = jap + '\u6B63' + deuxAneuf(tab[i]);
					}
					if (tab[i] == 0 && (tab[i+1] != 0 || tab[i+2] != 0 || tab[i+3] != 0)){
						jap = jap + '\u6B63';
					}	
					break;
				case 44 : 
					if (tab[i] != 0 && tab[i] == 1){
						jap = jap + '\u8F09' + '\u4E00' + deuxAneuf(tab[i]);
					}
					else if (tab[i] != 0){
						jap = jap + '\u8F09' + deuxAneuf(tab[i]);
					}
					if (tab[i] == 0 && (tab[i+1] != 0 || tab[i+2] != 0 || tab[i+3] != 0)){
						jap = jap + '\u8F09';
					}	
					break;
				case 48 : 
					if (tab[i] != 0 && tab[i] == 1){
						jap = jap + '\u6975' + '\u4E00' + deuxAneuf(tab[i]);
					}
					else if (tab[i] != 0){
						jap = jap + '\u6975' + deuxAneuf(tab[i]);
					}
					if (tab[i] == 0 && (tab[i+1] != 0 || tab[i+2] != 0 || tab[i+3] != 0)){
						jap = jap + '\u6975';
					}	
					break;
				case 52 : 
					if (tab[i] != 0 && tab[i] == 1){
						jap = jap + '\u6C99' + '\u6CB3' + '\u6052' + '\u4E00' + deuxAneuf(tab[i]);
					}
					else if (tab[i] != 0){
						jap = jap + '\u6C99' + '\u6CB3' + '\u6052' + deuxAneuf(tab[i]);
					}
					if (tab[i] == 0 && (tab[i+1] != 0 || tab[i+2] != 0 || tab[i+3] != 0)){
						jap = jap + '\u6C99' + '\u6CB3' + '\u6052';
					}	
					break;
				case 56 : 
					if (tab[i] != 0 && tab[i] == 1){
						jap = jap + '\u7947' + '\u50E7' + '\u963F' + '\u4E00' + deuxAneuf(tab[i]);
					}
					else if (tab[i] != 0){
						jap = jap + '\u7947' + '\u50E7' + '\u963F' + deuxAneuf(tab[i]);
					}
					if (tab[i] == 0 && (tab[i+1] != 0 || tab[i+2] != 0 || tab[i+3] != 0)){
						jap = jap + '\u7947' + '\u50E7' + '\u963F';
					}	
					break;
				case 60 : 
					if (tab[i] != 0 && tab[i] == 1){
						jap = jap + '\u591A' + '\u7531' + '\u90A3' + '\u002F' + '\u4ED6' + '\u7531' + '\u90A3' + '\u4E00' + deuxAneuf(tab[i]);
					}
					else if (tab[i] != 0){
						jap = jap + '\u591A' + '\u7531' + '\u90A3' + '\u002F' + '\u4ED6' + '\u7531' + '\u90A3' + deuxAneuf(tab[i]);
					}
					if (tab[i] == 0 && (tab[i+1] != 0 || tab[i+2] != 0 || tab[i+3] != 0)){
						jap = jap + '\u591A' + '\u7531' + '\u90A3' + '\u002F' + '\u4ED6' + '\u7531' + '\u90A3';
					}	
					break;
				case 64 : 
					if (tab[i] != 0 && tab[i] == 1){
						jap = jap + '\u8B70' + '\u601D' + '\u53EF' + '\u4E0D' + '\u4E00' + deuxAneuf(tab[i]);
					}
					else if (tab[i] != 0){
						jap = jap + '\u8B70' + '\u601D' + '\u53EF' + '\u4E0D' + deuxAneuf(tab[i]);
					}
					if (tab[i] == 0 && (tab[i+1] != 0 || tab[i+2] != 0 || tab[i+3] != 0)){
						jap = jap + '\u8B70' + '\u601D' + '\u53EF' + '\u4E0D';
					}	
					break;
				case 68 : 
					if (tab[i] != 0 && tab[i] == 1){
						jap = jap + '\u6570' + '\u5927' + '\u91CF' + '\u7121' + '\u4E00' + deuxAneuf(tab[i]);
					}
					else if (tab[i] != 0){
						jap = jap + '\u6570' + '\u5927' + '\u91CF' + '\u7121' + deuxAneuf(tab[i]);
					}
					if (tab[i] == 0 && (tab[i+1] != 0 || tab[i+2] != 0 || tab[i+3] != 0)){
						jap = jap + '\u6570' + '\u5927' + '\u91CF' + '\u7121';
					}
					break;
				default :
					if (tab[i] != 0){
						jap = jap + dixcentmille(i, tab[i]) + deuxAneuf(tab[i]);
					}					
					break;
				}
			}
		}
		else{
			for(int i = 0; i <= size(nb)-1; i++){
				switch(i){
				case 0 :
					jap = jap + zeroAneuf(tab[i]);
					break;
				case 1 :
					if (tab[i] != 0){
						jap = jap + '\u5341' + deuxAneuf(tab[i]) ;
						//jap = jap + 'å��' + deuxAneuf(tab[i]) ;
					}
					break;
				case 2 :
					if (tab[i] != 0){
						jap = jap + '\u767E' + deuxAneuf(tab[i]);
						//jap = jap + 'ç™¾' + deuxAneuf(tab[i]);
					}
					break;
				case 3 :
					if (tab[i] != 0){
						jap = jap + '\u5343' + deuxAneuf(tab[i]);
						//jap = jap + 'å�ƒ' + deuxAneuf(tab[i]);
					}
					break;
				case 4 :
					if (tab[i] != 0){
						jap = jap + '\u4E07' + deuxAneuf(tab[i]);
						//jap = jap + 'ä¸‡' + deuxAneuf(tab[i]);
					}
					if (tab[i] == 0 && (tab[i+1] != 0 || tab[i+2] != 0 || tab[i+3] != 0)){
						jap = jap + '\u4E07';
						//jap = jap + 'ä¸‡';
					}
					break;
				case 8 :
					if (tab[i] != 0 && tab[i] == 1){
						jap = jap + '\u5104' + '\u4E00' + deuxAneuf(tab[i]);
						//jap = jap + 'å„„' + 'ä¸€' + deuxAneuf(tab[i]);
					}
					else if (tab[i] != 0){
						jap = jap + '\u5104' + deuxAneuf(tab[i]);
						//jap = jap + 'å„„' + deuxAneuf(tab[i]);
					}
					if (tab[i] == 0 && (tab[i+1] != 0 || tab[i+2] != 0 || tab[i+3] != 0)){
						jap = jap + '\u5104';
						//jap = jap + 'å„„';
					}
					break;
				case 12 :
					if (tab[i] != 0 && tab[i] == 1){
						jap = jap + '\u5146' + '\u4E00' + deuxAneuf(tab[i]);
						//jap = jap + 'å…†' + 'ä¸€' + deuxAneuf(tab[i]);
					}
					else if (tab[i] != 0){
						jap = jap + '\u5146' + deuxAneuf(tab[i]);
						//jap = jap + 'å…†' + deuxAneuf(tab[i]);
					}
					if (tab[i] == 0 && (tab[i+1] != 0 || tab[i+2] != 0 || tab[i+3] != 0)){
						jap = jap + '\u5146';
						//jap = jap + 'å…†';
					}
					break;
				case 16 :
					if (tab[i] != 0 && tab[i] == 1){
						jap = jap + '\u4EAC' + '\u4E00' + deuxAneuf(tab[i]);
						//jap = jap + 'äº¬' + 'ä¸€' + deuxAneuf(tab[i]);
					}
					else if (tab[i] != 0){
						jap = jap + '\u4EAC' + deuxAneuf(tab[i]);
						//jap = jap + 'äº¬' + deuxAneuf(tab[i]);
					}
					if (tab[i] == 0 && (tab[i+1] != 0 || tab[i+2] != 0 || tab[i+3] != 0)){
						jap = jap + '\u4EAC';
						//jap = jap + 'äº¬';
					}
					break;
				case 20 :
					if (tab[i] != 0 && tab[i] == 1){
						jap = jap + '\u5793' + '\u4E00' + deuxAneuf(tab[i]);
					}
					else if (tab[i] != 0){
						jap = jap + '\u5793' + deuxAneuf(tab[i]);
					}
					if (tab[i] == 0 && (tab[i+1] != 0 || tab[i+2] != 0 || tab[i+3] != 0)){
						jap = jap + '\u5793';
					}
					break;
				case 24 :
					if (tab[i] != 0 && tab[i] == 1){
						jap = jap + '\u79ED' + '\u4E00' + deuxAneuf(tab[i]);
					}
					else if (tab[i] != 0){
						jap = jap + '\u79ED' + deuxAneuf(tab[i]);
					}
					if (tab[i] == 0 && (tab[i+1] != 0 || tab[i+2] != 0 || tab[i+3] != 0)){
						jap = jap + '\u79ED';
					}
					break;
				case 28 :
					if (tab[i] != 0 && tab[i] == 1){
						jap = jap + '\u7A63' + '\u4E00' + deuxAneuf(tab[i]);
					}
					else if (tab[i] != 0){
						jap = jap + '\u7A63' + deuxAneuf(tab[i]);
					}
					if (tab[i] == 0 && (tab[i+1] != 0 || tab[i+2] != 0 || tab[i+3] != 0)){
						jap = jap + '\u7A63';
					}
					break;
				case 32 :
					if (tab[i] != 0 && tab[i] == 1){
						jap = jap + '\u6E9D' + '\u4E00' + deuxAneuf(tab[i]);
					}
					else if (tab[i] != 0){
						jap = jap + '\u6E9D' + deuxAneuf(tab[i]);
					}
					if (tab[i] == 0 && (tab[i+1] != 0 || tab[i+2] != 0 || tab[i+3] != 0)){
						jap = jap + '\u6E9D';
					}
					break;
				case 36 :
					if (tab[i] != 0 && tab[i] == 1){
						jap = jap + '\u6F97' + '\u4E00' + deuxAneuf(tab[i]);
					}
					else if (tab[i] != 0){
						jap = jap + '\u6F97' + deuxAneuf(tab[i]);
					}
					if (tab[i] == 0 && (tab[i+1] != 0 || tab[i+2] != 0 || tab[i+3] != 0)){
						jap = jap + '\u6F97';
					}
					break;
				case 40 :
					if (tab[i] != 0 && tab[i] == 1){
						jap = jap + '\u6B63' + '\u4E00' + deuxAneuf(tab[i]);
					}
					else if (tab[i] != 0){
						jap = jap + '\u6B63' + deuxAneuf(tab[i]);
					}
					if (tab[i] == 0 && (tab[i+1] != 0 || tab[i+2] != 0 || tab[i+3] != 0)){
						jap = jap + '\u6B63';
					}
					break;
				case 44 :
					if (tab[i] != 0 && tab[i] == 1){
						jap = jap + '\u8F09' + '\u4E00' + deuxAneuf(tab[i]);
					}
					else if (tab[i] != 0){
						jap = jap + '\u8F09' + deuxAneuf(tab[i]);
					}
					if (tab[i] == 0 && (tab[i+1] != 0 || tab[i+2] != 0 || tab[i+3] != 0)){
						jap = jap + '\u8F09';
					}
					break;
				case 48 :
					if (tab[i] != 0 && tab[i] == 1){
						jap = jap + '\u6975' + '\u4E00' + deuxAneuf(tab[i]);
					}
					else if (tab[i] != 0){
						jap = jap + '\u6975' + deuxAneuf(tab[i]);
					}
					if (tab[i] == 0 && (tab[i+1] != 0 || tab[i+2] != 0 || tab[i+3] != 0)){
						jap = jap + '\u6975';
					}
					break;
				case 52 :
					if (tab[i] != 0 && tab[i] == 1){
						jap = jap + '\u6C99' + '\u6CB3' + '\u6052' + '\u4E00' + deuxAneuf(tab[i]);
					}
					else if (tab[i] != 0){
						jap = jap + '\u6C99' + '\u6CB3' + '\u6052' + deuxAneuf(tab[i]);
					}
					if (tab[i] == 0 && (tab[i+1] != 0 || tab[i+2] != 0 || tab[i+3] != 0)){
						jap = jap + '\u6C99' + '\u6CB3' + '\u6052';
					}
					break;
				case 56 :
					if (tab[i] != 0 && tab[i] == 1){
						jap = jap + '\u7947' + '\u50E7' + '\u963F' + '\u4E00' + deuxAneuf(tab[i]);
					}
					else if (tab[i] != 0){
						jap = jap + '\u7947' + '\u50E7' + '\u963F' + deuxAneuf(tab[i]);
					}
					if (tab[i] == 0 && (tab[i+1] != 0 || tab[i+2] != 0 || tab[i+3] != 0)){
						jap = jap + '\u7947' + '\u50E7' + '\u963F';
					}
					break;
				case 60 :
					if (tab[i] != 0 && tab[i] == 1){
						jap = jap + '\u591A' + '\u7531' + '\u90A3' + '\u002F' + '\u4ED6' + '\u7531' + '\u90A3' + '\u4E00' + deuxAneuf(tab[i]);
					}
					else if (tab[i] != 0){
						jap = jap + '\u591A' + '\u7531' + '\u90A3' + '\u002F' + '\u4ED6' + '\u7531' + '\u90A3' + deuxAneuf(tab[i]);
					}
					if (tab[i] == 0 && (tab[i+1] != 0 || tab[i+2] != 0 || tab[i+3] != 0)){
						jap = jap + '\u591A' + '\u7531' + '\u90A3' + '\u002F' + '\u4ED6' + '\u7531' + '\u90A3';
					}
					break;
				case 64 :
					if (tab[i] != 0 && tab[i] == 1){
						jap = jap + '\u8B70' + '\u601D' + '\u53EF' + '\u4E0D' + '\u4E00' + deuxAneuf(tab[i]);
					}
					else if (tab[i] != 0){
						jap = jap + '\u8B70' + '\u601D' + '\u53EF' + '\u4E0D' + deuxAneuf(tab[i]);
					}
					if (tab[i] == 0 && (tab[i+1] != 0 || tab[i+2] != 0 || tab[i+3] != 0)){
						jap = jap + '\u8B70' + '\u601D' + '\u53EF' + '\u4E0D';
					}
					break;
				case 68 :
					if (tab[i] != 0 && tab[i] == 1){
						jap = jap + '\u6570' + '\u5927' + '\u91CF' + '\u7121' + '\u4E00' + deuxAneuf(tab[i]);
					}
					else if (tab[i] != 0){
						jap = jap + '\u6570' + '\u5927' + '\u91CF' + '\u7121' + deuxAneuf(tab[i]);
					}
					if (tab[i] == 0 && (tab[i+1] != 0 || tab[i+2] != 0 || tab[i+3] != 0)){
						jap = jap + '\u6570' + '\u5927' + '\u91CF' + '\u7121';
					}
					break;
				default :
					if (tab[i] != 0){
						jap = jap.concat(dixcentmille(i, tab[i])) + deuxAneuf(tab[i]);
					}
					break;
				}
			}
		}
		if (nb < 0){
			jap = jap.concat("-");
		}
		String reverse = new StringBuffer(jap).reverse().toString();
		reverse = reverse.trim();
		return reverse;		
	}
	
	public static String Main(long nb10) {
		return (conversion10toJap(nb10));			//On appel notre méthode et on renvoie la chaine de caractère
	}
}