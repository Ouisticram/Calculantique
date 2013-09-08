package japonais;
public class Japotodeci {	
	private static int quatrez, huitz, douzez, seizez, vingtz = 0;
	private static boolean ok = true;
	
	static int NoSpace(String nb){
		int nbo;
		int taille = nb.length();
		nbo = 0;
		for(int i = 0; i <= taille-1; i++){
			char lettre = nb.charAt(i) ;
			if(lettre == ' ')
			{
				return 0;
			}
    		else
    		{
    			nbo++;
    		}
		}
		return nbo;
	}
	
	public static boolean verif(String jap){
		boolean ok = true;
		if (NoSpace(jap) == 0)
		{
			return false;
		}
		//'\u3007', '\u4E00', '\u4E8C', '\u4E09', '\u56DB', '\u4E94', '\u516D', '\u4E03', '\u516B', '\u4E5D', '\u5341', '\u767E', '\u5343', '\u4E07'
		for(int i = 0; i <= jap.length()-1; i++){
			if (jap.charAt(i) == '\u3007' || jap.charAt(i) == '\u4E00' || jap.charAt(i) == '\u4E8C' || jap.charAt(i) == '\u4E09' || jap.charAt(i) == '\u56DB' || jap.charAt(i) == '\u4E94' || jap.charAt(i) == '\u516D' || jap.charAt(i) == '\u4E03' || jap.charAt(i) == '\u516B' || jap.charAt(i) == '\u4E5D' || jap.charAt(i) == '\u5341' || jap.charAt(i) == '\u767E' || jap.charAt(i) == '\u5343' || jap.charAt(i) == '\u4E07'){
				;
			}
			else{return false;}
		}
		for(int i = 0; i < jap.length()-1; i++){
			if ((jap.charAt(i) == '\u4E00' || jap.charAt(i) == '\u4E8C' || jap.charAt(i) == '\u4E09' || jap.charAt(i) == '\u56DB' || jap.charAt(i) == '\u4E94' || jap.charAt(i) != '\u516D' || jap.charAt(i) == '\u4E03' || jap.charAt(i) == '\u516B' || jap.charAt(i) == '\u4E5D') && (jap.charAt(i+1) == '\u3007' || jap.charAt(i+1) == '\u4E00' || jap.charAt(i+1) == '\u4E8C' || jap.charAt(i+1) == '\u4E09' || jap.charAt(i+1) == '\u56DB' || jap.charAt(i+1) == '\u4E94' || jap.charAt(i+1) == '\u516D' || jap.charAt(i+1) == '\u4E03' || jap.charAt(i+1) == '\u516B' || jap.charAt(i+1) == '\u4E5D')){
				return false;
			}
			i++;
		}
		return ok;
	}
	
	static long [] Revertablong (long [] t)
    {
        int n = t.length;

        long [] rev;
        rev = new long [n];

        // On renverse le tableau
        for (int i=0; i<= n-1; i=i+1)
            rev[i] = t[n-1-i];

        return rev;
    }
	
	static char [] Coupetabchar (char [] tab){
		int n = 0;
		n = tab.length;
		char [] tabi = new char[n-1];
		
		for(int i = 0; i <= n-2; i++){
			tabi[i] = tab[i+1];
		}
		return tabi;
	}
	
	static long convert(char jap){
		long res;
		res = 0;
		if (jap == '\u3007'){
			res = 0;
		}
		else if (jap == '\u4E00'){
			res = 1;
		}
		else if (jap == '\u4E8C'){
			res = 2;
		}
		else if (jap == '\u4E09'){
			res = 3;
		}
		else if (jap == '\u56DB'){
			res = 4;
		}
		else if (jap == '\u4E94'){
			res = 5;
		}
		else if (jap == '\u516D'){
			res = 6;
		}
		else if (jap == '\u4E03'){
			res = 7;
		}
		else if (jap == '\u516B'){
			res = 8;
		}
		else if (jap == '\u4E5D'){
			res = 9;
		}	
		else if (jap == '\u5341'){
			res = 10;
		}
		else if (jap == '\u767E'){
			res = 100;
		}
		else if (jap == '\u5343'){
			res = 1000;
		}
		else if (jap == '\u4E07'){
			res = 10_000;
			quatrez = 1;
		}
		else if (jap == '\u5104'){
			res = 100_000_000;
			huitz = 1;
		}
		else if (jap == '\u5146'){
			res = (long)Math.pow(10,12);
			douzez = 1;
		}
		else if (jap == '\u4EAC'){
			res = (long)Math.pow(10,16);
			seizez = 1;
		}
		else if (jap == '\u5793'){
			res = (long)Math.pow(10,20);
			vingtz = 1;
		}
		else
		{
			ok = false;
		}
		return res;
	}
	
	public static long Conversionjapoto10 (String japo){
		int length = japo.length();
		char tabi[] = new char[length];
		long tabi2[] = new long[length];
		long tabi3[] = new long[length];
		String negatif = "";
		int var;
		int j = 0;
		int quatreZero = 0;
		int huitZero = 0;
		int douzeZero = 0;
		int seizeZero = 0;
		int vingtZero = 0;
		int underQuatrezero = 0;
		int repere = 0;
		int detect = 0;
		int arretou = 0;
		long jap;
		var = NoSpace(japo);
		jap = 0;
		for(int i = 0; i <= length-1; i++){
			tabi[i] = japo.charAt(i);
		}
		if (var == 0)
		{
			jap = 0;
		}
		else if (japo.charAt(0) == '-'){
			tabi = Coupetabchar(tabi);
			for (int i = 0; i <= length-2; i++){
				negatif = negatif + tabi[i];
			}
			jap = Conversionjapoto10(negatif) * (-1);
		}
		else
		{
			if (length == 1){
				tabi2[0] = convert(tabi[0]);
				jap = tabi2[0];
			}
			else
			{
				for(int i = 0; i <= length-1; i++){
					tabi2[i] = convert(tabi[i]);
				}
				tabi2 = Revertablong(tabi2);
				if (tabi2[0]>0 && tabi2[0]<10){
					underQuatrezero++;
				}
				int valhuit = 0, valdouze = 0, valseize = 0, valvingt = 0;
				for(int i = 0; i <= length-2; i++){
					if (tabi2[i]==10_000){
						quatreZero++;
						repere++;
						arretou = 1;
						int a = i;
						while(tabi2[a] != 100_000_000 && tabi2[a] != (long)Math.pow(10,12) && tabi2[a] != (long)Math.pow(10,16) && tabi2[a] != (long)Math.pow(10,20) && a < length-1){
							if(tabi2[a] == 10 || tabi2[a] == 100 || tabi2[a] == 1000){
								quatreZero++;
							}
							a++;
						}
					}
					else if (tabi2[i]==100_000_000){
						if (tabi2[i+1] > 0 && tabi2[i+1] < 10){
							valhuit++;
						}
						huitZero++;
						repere++;
						arretou = 2;
						int a = i;
						while(tabi2[a] != (long)Math.pow(10,12) && tabi2[a] != (long)Math.pow(10,16) && tabi2[a] != (long)Math.pow(10,20) && a < length-1){
							if(tabi2[a] == 10 || tabi2[a] == 100 || tabi2[a] == 1000){
								huitZero++;
							}
							a++;
						}
					}
					else if (tabi2[i]==(long)Math.pow(10,12)){
						if (tabi2[i+1] > 0 && tabi2[i+1] < 10){
							valdouze++;
						}
						douzeZero++;
						repere++;
						arretou = 3;
						int a = i;
						while(tabi2[a] != (long)Math.pow(10,16) && tabi2[a] != (long)Math.pow(10,20) && a < length-1){
							if(tabi2[a] == 10 || tabi2[a] == 100 || tabi2[a] == 1000){
								douzeZero++;
							}
							a++;
						}
					}
					else if (tabi2[i]==(long)Math.pow(10,16)){
						if (tabi2[i+1] > 0 && tabi2[i+1] < 10){
							valseize++;
						}
						seizeZero++;
						repere++;
						arretou = 4;
						int a = i;
						while(tabi2[a] != (long)Math.pow(10,20) && a < length-1){
							if(tabi2[a] == 10 || tabi2[a] == 100 || tabi2[a] == 1000){
								seizeZero++;
							}
							a++;
						}
					}
					else if (tabi2[i]==(long)Math.pow(10,20)){
						if (tabi2[i+1] > 0 && tabi2[i+1] < 10){
							valvingt++;
						}
						vingtZero++;
						repere++;
						arretou = 5;
						int a = i;
						while(a < length-1){
							if(tabi2[a] == 10 || tabi2[a] == 100 || tabi2[a] == 1000){
								vingtZero++;
							}
							a++;
						}
					}
					else if (repere == 0){
						if(tabi2[i] == 10 || tabi2[i] == 100 || tabi2[i] == 1000){
							underQuatrezero++;
						}
					}
					if (i < length-2){
						if (tabi2[i] > tabi2[i+1] && tabi2[i+1] > tabi2[i+2]){
							detect++;
						}
						else if(tabi2[i] > tabi2[i+1] && detect == 0){
								long acc = 0;
								acc = tabi2[i]*tabi2[i+1];
								detect++;
								tabi3[j] = acc;
								j++;
							}
							else if (tabi2[i] < tabi2[i+1] && detect == 0){
									tabi3[j] = tabi2[i];
									j++;
								}
								else{detect--;}
					}
					else{
						if(tabi2[i] > tabi2[i+1]){
							long acc = 0;
							acc = tabi2[i]*tabi2[i+1];
							detect++;
							tabi3[j] = acc;
							j++;
						}
						else if (tabi2[i] < tabi2[i+1] && detect == 0){
							tabi3[j] = tabi2[i];
							j++;
						}
						else{detect--;}
					}
				}
				if (tabi2[length-2] < tabi2[length-1]){
					if(arretou == 1){
						quatreZero++;
					}
					if(arretou == 2){
						huitZero++;
					}
					if(arretou == 3){
						douzeZero++;
					}
					if(arretou == 4){
						seizeZero++;
					}
					if(arretou == 5){
						vingtZero++;
					}
					tabi3[j] = tabi2[length-1];
				}
				if (huitz == 1 || douzez == 1 || seizez == 1 || vingtz == 1){
					quatreZero--;
				}
				if (quatrez == 1){
					for(int i = underQuatrezero+1; i <= underQuatrezero+quatreZero; i++){
						tabi3[i] = tabi3[i]*10_000;
					}
				}
				if (huitz == 1){
					for(int i = underQuatrezero+quatreZero+1+valhuit; i <= underQuatrezero+quatreZero+huitZero; i++){
						tabi3[i] = tabi3[i]*100_000_000;
					}
				}
				if (douzez == 1){
					for(int i = underQuatrezero+quatreZero+huitZero+1+valdouze; i <= underQuatrezero+quatreZero+huitZero+douzeZero; i++){
						tabi3[i] = tabi3[i]*(long)Math.pow(10,12);
					}
				}if (seizez == 1){
					for(int i = underQuatrezero+quatreZero+huitZero+douzeZero+1+valseize; i <= underQuatrezero+quatreZero+huitZero+douzeZero+seizeZero; i++){
						tabi3[i] = tabi3[i]*(long)Math.pow(10,16);
					}
				}
				if (vingtz == 1){
					for(int i = underQuatrezero+quatreZero+huitZero+douzeZero+seizeZero+1+valvingt; i <= underQuatrezero+quatreZero+huitZero+douzeZero+seizeZero+vingtZero; i++){
						tabi3[i] = tabi3[i]*(long)Math.pow(10,20);
					}
				}
				if (ok){
					for(int i = 0; i <= length-1; i++){
						jap = jap + tabi3[i];						
					}
				}				
				else{jap=0;}
			}	
		}	
		return jap;
	}
	
	public static long Main(String japo) {
		String japo2 = japo;
		japo2 = japo2.trim();
		return Conversionjapoto10(japo2);				//On appel notre m�thode et on renvoie le r�sultat
	}
}
