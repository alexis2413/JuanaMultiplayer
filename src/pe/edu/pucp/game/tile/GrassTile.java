package pe.edu.pucp.game.tile;

import java.io.Serializable;

import pe.edu.pucp.game.gfx.Assets;

@SuppressWarnings("serial")
public class GrassTile extends Tile implements Serializable{
	public GrassTile(int id){
		super(Assets.grass,id);
		solid=false;
	}
	public GrassTile(){}
	
	@Override
	public boolean isSolid(){
		return solid;
	}
}
