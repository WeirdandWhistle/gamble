package extra;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage ss;

	public SpriteSheet(BufferedImage ss) {
		this.ss = ss;
	}
	public BufferedImage getSprite(int col,int row,int width,int height) {
		BufferedImage img = ss.getSubimage((col * width), (row * height), width, height);
		return img;
	}
	
	
}
