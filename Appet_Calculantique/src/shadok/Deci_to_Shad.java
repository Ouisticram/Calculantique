package shadok;
import java.util.*;

public class Deci_to_Shad
{

public static void main (String[] args)
{
 Scanner clavier = new Scanner(System.in);
 Decimal nombre;
 nombre = new Decimal(0);
 System.out.println("Entrer le nombre à convertir :");
 long valUser = clavier.nextLong();
 nombre.setVal(valUser);
 System.out.println(nombre.ConvSha(valUser)); 
 System.out.println(nombre.ConvSha(valUser)); 
 System.out.println(nombre.ConvSha(valUser)); 
}
}
