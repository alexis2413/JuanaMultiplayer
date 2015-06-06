package pe.edu.pucp.game.types;

import java.io.Serializable;

@SuppressWarnings("serial")
public class GroundType extends Type implements Serializable{

	//private float volume,material,ground;
	public GroundType(int id) {
		super(id);
		// TODO Auto-generated constructor stub
		this.setWeakness(3);
	}
	public GroundType(){}

}
