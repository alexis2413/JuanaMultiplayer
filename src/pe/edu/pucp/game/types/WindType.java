package pe.edu.pucp.game.types;

import java.io.Serializable;

@SuppressWarnings("serial")
public class WindType extends Type implements Serializable{

	//private float windDirection,windStrength;
	public WindType(int id) {
		super(id);
		// TODO Auto-generated constructor stub
		this.setWeakness(1);
	}
	public WindType(){}

}
