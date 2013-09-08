package fonts;
import java.awt.Font;

public class LoadFontMaya {
	    public Font loadFont() {
	        try {
	        	return Font.createFont(0, getClass().getResourceAsStream("/Maya.ttf")).deriveFont(Font.BOLD,50);  
	        }
	        catch(Exception e){
	            return new Font ("Serif", Font.BOLD, 25);
	        }
	    }
	    public static Font change(Font f) {
	        f = new LoadFontMaya().loadFont();
	        return f;
	    }
}
