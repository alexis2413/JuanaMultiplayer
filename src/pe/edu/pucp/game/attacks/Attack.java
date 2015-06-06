package pe.edu.pucp.game.attacks;

import java.io.Serializable;

import pe.edu.pucp.game.types.Type;

@SuppressWarnings("serial")
public class Attack implements Serializable{
//public class Attack{
	private Type type;
	private String name;
	private int damage;
	private int radius;
	private float multiplier=1;//para ventaja de tipos
	
	public Attack(Type type){
		this.type=type;		
	}
	
	public Attack(){}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public float getMultiplier() {
		return multiplier;
	}
	public void setMultiplier(float multiplier) {
		this.multiplier = multiplier;
	}
	
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	
	
}
