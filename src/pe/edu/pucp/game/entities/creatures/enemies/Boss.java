package pe.edu.pucp.game.entities.creatures.enemies;

import java.io.Serializable;

import pe.edu.pucp.game.Game;
import pe.edu.pucp.game.types.Type;

@SuppressWarnings("serial")
public class Boss extends Enemy implements Serializable{
//public class Boss extends Enemy{

	private char boss;
	private Type type;
	
	public Boss(Game game, double x, double y, int width, int height) {
		super(game, x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	
	public Boss(double x, double y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	public Boss(){}
	
	public char getBoss() {
		return boss;
	}
	public void setBoss(char boss) {
		this.boss = boss;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}

}
