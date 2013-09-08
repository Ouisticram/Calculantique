package fonts;

import java.awt.Font;

public class LoadFontBaby {
	public Font loadFont() {
        try {
        	return Font.createFont(0, getClass().getResourceAsStream("/SantakkuM.ttf")).deriveFont(Font.BOLD,30);  
        }
        catch(Exception e){
            return new Font ("Serif", Font.BOLD, 25);
        }
    }
    public static Font change(Font f) {
        f = new LoadFontBaby().loadFont();
        return f;
    }
}
