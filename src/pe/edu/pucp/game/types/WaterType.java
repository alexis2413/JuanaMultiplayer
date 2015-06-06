package pe.edu.pucp.game.types;

import java.io.Serializable;

@SuppressWarnings("serial")
public class WaterType extends Type implements Serializable{

	//private float waterVolume,caudal;
	public WaterType(int id) {
		super(id);
		this.setWeakness(4);
		// TODO Auto-generated constructor stub
	}
	public WaterType(){}
	
}
