package pe.edu.pucp.game.entities.items;

import java.awt.Graphics;

import pe.edu.pucp.game.entities.Entity;
import pe.edu.pucp.game.gfx.Assets;

@SuppressWarnings("serial")
public class Item extends Entity{

	private int id;
	private String description;
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		//POR TIPO DE ID
		g.drawImage(Assets.berry,(int)(x*DEFAULT_ENTITY_WIDTH-game.getGameCamera().getxOffset()),
				(int)(y*DEFAULT_ENTITY_HEIGHT-game.getGameCamera().getyOffset()),width,height,null);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}

}
