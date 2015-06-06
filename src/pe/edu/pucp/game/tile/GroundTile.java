package pe.edu.pucp.game.tile;


import java.io.Serializable;

import pe.edu.pucp.game.gfx.Assets;

@SuppressWarnings("serial")
public class GroundTile extends Tile implements Serializable{

	public GroundTile(int id) {
		super(Assets.ground, id);
		solid=false;
		// TODO Auto-generated constructor stub
	}
	
	public GroundTile(){}
	
	@Override
	public boolean isSolid(){
		return solid;
	}
}
