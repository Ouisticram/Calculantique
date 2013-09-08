package shadok;
import java.util.*;

public class Shad_to_Deci
{

public static void main (String[] args)
{
 Scanner clavier = new Scanner(System.in);
 Shadok mot;
 mot = new Shadok("GA");
 System.out.println("Entrer le mot Shadok à convertir :");
 String motUser = clavier.nextLine();
 mot.setVal(motUser);
 System.out.println(mot.convDec(motUser));
 System.out.println(mot.convDec(motUser));
 System.out.println(mot.convDec(motUser));
}
}
