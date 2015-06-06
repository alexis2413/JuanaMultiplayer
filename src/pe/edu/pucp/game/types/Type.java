package pe.edu.pucp.game.types;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Type implements Serializable{
	private int id;
	protected int weakness;
	public Type(int id){
		this.id=id;
	}
	public Type(){}
	//Fuego 1
	//Agua 2
	//Aire 3
	//Tierra 4
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWeakness() {
		return weakness;
	}
	public void setWeakness(int weakness) {
		this.weakness = weakness;
	}
	
}
