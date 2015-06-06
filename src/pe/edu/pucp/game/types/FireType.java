package pe.edu.pucp.game.types;

import java.io.Serializable;

@SuppressWarnings("serial")
public class FireType extends Type implements Serializable{

	public FireType(int id) {
		super(id);
		// TODO Auto-generated constructor stub
		this.setWeakness(2);
	}
	public FireType(){}

}
