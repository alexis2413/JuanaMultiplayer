package pe.edu.pucp.game.entities.creatures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import pe.edu.pucp.game.Game;
import pe.edu.pucp.game.entities.Entity;
import pe.edu.pucp.game.entities.items.Item;
import pe.edu.pucp.game.states.GameState;

@SuppressWarnings("serial")
public abstract class Creature extends Entity implements Serializable{
//public abstract class Creature extends Entity {

	public static final int DEFAULT_HEALTH=50;
	public static final double DEFAULT_SPEED=1;
	public static final int DEFAULT_CREATURE_WIDTH=DEFAULT_ENTITY_WIDTH,
							DEFAULT_CREATURE_HEIGHT=DEFAULT_ENTITY_HEIGHT;
	public static final int DEFAULT_DELAY=20;
	Random rand = new Random();
	protected int moveCounter;
	protected int delay;
	private int id;
	
	protected int health;
	protected int maxHealth;
	protected double speed;
	protected double xMove,yMove;
	
	private int direction=2;//1 arriba 2 abajo 3 izquierda 4 derecha
        
	public Creature(Game game,double x, double y, int width,int height){
		super(game,x, y, width, height);
		// TODO Auto-generated constructor stub
		health=DEFAULT_HEALTH;
		maxHealth=DEFAULT_HEALTH;
		speed=DEFAULT_SPEED;
		delay=DEFAULT_DELAY;
		xMove=0;
		yMove=0;
	}
	
	public Creature(double x, double y, int width,int height){
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		health=DEFAULT_HEALTH;
		maxHealth=DEFAULT_HEALTH;
		speed=DEFAULT_SPEED;
		xMove=0;
		yMove=0;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public Creature(){}

	public void move(){
		
	}
	
	public boolean isValidMove(){
		ArrayList<Entity> objects = ((GameState)game.getGameState()).getObjects();
		for(int i=0;i<objects.size();i++)//collisioned an object
			if(x+xMove==objects.get(i).getX()&&y+yMove==objects.get(i).getY()){
				objects.get(i).setCollisioned(true);
				return false;
			}
			else
				objects.get(i).setCollisioned(false);
		
		ArrayList<NonPlayerCharacter> npcs =  ((GameState)game.getGameState()).getNpcs();
		for(int i=0;i<npcs.size();i++)
			if(x+xMove==npcs.get(i).getX()&&y+yMove==npcs.get(i).getY()){
				npcs.get(i).setCollisioned(true);
				return false;
			}
                
                ArrayList<Item> items =  ((GameState)game.getGameState()).getItems();
		for(int i=0;i<items.size();i++)
			if(x+xMove==items.get(i).getX()&&y+yMove==items.get(i).getY()){
				items.get(i).setCollisioned(true);
				return false;
			}
		
		if(((GameState)game.getGameState()).getWorld().getTile((int)(x+xMove),(int)(y+yMove)).isSolid())
			return false;
		
		if(x+xMove==((GameState)game.getGameState()).getPlayer().getX() && 
				y+yMove==((GameState)game.getGameState()).getPlayer().getY())
			return false;
		return true;
	}
        
        public boolean isValidMove(int xMove, int yMove){
		ArrayList<Entity> objects = ((GameState)game.getGameState()).getObjects();
		for(int i=0;i<objects.size();i++)//collisioned an object
			if(x+xMove==objects.get(i).getX()&&y+yMove==objects.get(i).getY()){
				objects.get(i).setCollisioned(true);
				return false;
			}
			else
				objects.get(i).setCollisioned(false);
		
		ArrayList<NonPlayerCharacter> npcs =  ((GameState)game.getGameState()).getNpcs();
		for(int i=0;i<npcs.size();i++)
			if(x+xMove==npcs.get(i).getX()&&y+yMove==npcs.get(i).getY()){
				npcs.get(i).setCollisioned(true);
				return false;
			}
                
                ArrayList<Item> items =  ((GameState)game.getGameState()).getItems();
		for(int i=0;i<items.size();i++)
			if(x+xMove==items.get(i).getX()&&y+yMove==items.get(i).getY()){
				items.get(i).setCollisioned(true);
				return false;
			}
		
		if(((GameState)game.getGameState()).getWorld().getTile((int)(x+xMove),(int)(y+yMove)).isSolid())
			return false;
		
		if(x+xMove==((GameState)game.getGameState()).getPlayer().getX() && 
				y+yMove==((GameState)game.getGameState()).getPlayer().getY())
			return false;
		return true;
	}
	
	public void randomMove(){
		xMove=rand.nextInt(3)-1;
		yMove=rand.nextInt(3)-1;
	}
	
	public int getHealth() {return health;}
	public void setHealth(int health) {this.health = health;}
	public double getSpeed() {return speed;}
	public void setSpeed(double speed) {this.speed = speed;}
	public double getxMove() {return xMove;}
	public void setxMove(double xMove) {this.xMove = xMove;}
	public double getyMove() {return yMove;}
	public void setyMove(double yMove) {this.yMove = yMove;}
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public int getDirection(){return direction;}
        public void setDirection(int direction){this.direction=direction;}
}
