package pe.edu.pucp.game.tile;


import java.io.Serializable;

import pe.edu.pucp.game.gfx.Assets;

@SuppressWarnings("serial")
public class DoorTile extends Tile implements Serializable{

	private boolean open=false;
	private int worldTo;
	public DoorTile(int id) {
		super(Assets.door, id);
		solid=false;
		// TODO Auto-generated constructor stub
	}
	public DoorTile(){}
	
	@Override
	public boolean isSolid(){
		return solid;
	}
	
	public boolean isOpen(){
		return open;
	}
	
	public void openDoor(){
		open=true;
	}
	
	public void closeDoor(){
		open=false;
	}
	
	public int getWorldTo(){
		return worldTo;
	}
	
	public void setWorldTo(int worldTo){
		this.worldTo=worldTo;
	}
}
