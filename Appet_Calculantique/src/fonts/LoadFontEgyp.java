package fonts;
import java.awt.Font;

public class LoadFontEgyp {
	public Font loadFont() {
        try {
        	return Font.createFont(0, getClass().getResourceAsStream("/Aegyptus_B.ttf")).deriveFont(Font.BOLD,40);  
        }
        catch(Exception e){
            return new Font ("Serif", Font.BOLD, 25);
        }
    }
    public static Font change(Font f) {
        f = new LoadFontEgyp().loadFont();
        return f;
    }
}
