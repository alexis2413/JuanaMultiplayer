package pe.edu.pucp.game.entities.creatures.enemies;

import java.awt.Graphics;
import java.io.Serializable;

import pe.edu.pucp.game.Game;
import pe.edu.pucp.game.entities.creatures.Creature;
import pe.edu.pucp.game.gfx.Assets;
import pe.edu.pucp.game.types.Type;

@SuppressWarnings("serial")
public class Chicken extends Boss implements Serializable{

	
	public Chicken(Game game, double x, double y) {
		super(game,x,y,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT);
		// TODO Auto-generated constructor stub
		Type type=new Type(3);
		this.setType(type);
		this.setId(1);
		this.setDescription("A little chicken that gives you the meat to make a juane");
	}
	
	public Chicken(double x, double y) {
		super(x,y,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT);
		// TODO Auto-generated constructor stub
		Type type=new Type(3);
		this.setType(type);
		this.setId(1);
		this.setDescription("A little chicken that gives you the meat to make a juane");
	}
	
	
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(Assets.chicken,(int)(x*DEFAULT_CREATURE_WIDTH-game.getGameCamera().getxOffset()),
				(int)(y*DEFAULT_CREATURE_HEIGHT-game.getGameCamera().getyOffset()),width,height,null);
	}
}
