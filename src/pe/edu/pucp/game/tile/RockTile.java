package pe.edu.pucp.game.tile;

import java.io.Serializable;

import pe.edu.pucp.game.gfx.Assets;

@SuppressWarnings("serial")
public class RockTile extends Tile implements Serializable{

	public RockTile(int id) {
		super(Assets.rock,id);
		solid=true;
		// TODO Auto-generated constructor stub
	}
	
	public RockTile(){}
	
	@Override
	public boolean isSolid(){
		return solid;
	}

}
