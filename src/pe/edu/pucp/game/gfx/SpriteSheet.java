package pe.edu.pucp.game.gfx;

import java.awt.image.BufferedImage;
import java.io.Serializable;

@SuppressWarnings("serial")
public class SpriteSheet implements Serializable{
	private BufferedImage sheet;
	public SpriteSheet(BufferedImage sheet){
		this.sheet=sheet;
	}
	
	public SpriteSheet(){}
	
	public BufferedImage crop(int x, int y, int width, int height){
		return sheet.getSubimage(x, y, width, height);
	}
}
