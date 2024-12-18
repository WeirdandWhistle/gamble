package extra;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UTool {
	
	public BufferedImage scaleImage(BufferedImage og, int width, int height) {
		
		BufferedImage scaled = new BufferedImage(width,height,og.getType());
		Graphics2D g2d = scaled.createGraphics();
		g2d.drawImage(og,0,0,width,height,null);
		g2d.dispose();
		
		return scaled;
		
	}

}
