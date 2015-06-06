package pe.edu.pucp.game.attacks;

import java.io.Serializable;

import pe.edu.pucp.game.types.FireType;
import pe.edu.pucp.game.types.Type;

@SuppressWarnings("serial")
public class Ember extends Attack implements Serializable{

	public Ember(Type type) {
		super(new FireType(1));
		// TODO Auto-generated constructor stub
		this.setDamage(40);
		this.setRadius(1);
	}
	
	public Ember(){}

}
