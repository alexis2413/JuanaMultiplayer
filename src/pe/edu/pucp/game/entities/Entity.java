package pe.edu.pucp.game.entities;

import java.awt.Graphics;
import java.io.Serializable;

import pe.edu.pucp.game.Game;
import pe.edu.pucp.game.states.GameState;


@SuppressWarnings("serial")
public abstract class Entity implements Serializable{
//public abstract class Entity{
	
	protected Game game;
        
	protected double x,y;
	protected int width,height;
	private boolean collisioned=false;
	private String description;
	public static final int DEFAULT_ENTITY_WIDTH=24,
			DEFAULT_ENTITY_HEIGHT=24;
	public Entity(Game game,double x, double y,int width,int height){
		this.game=game;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
	
	public Entity(double x, double y,int width,int height){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
	
	public Entity(){}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);


	public boolean collision(){
		if(x==((GameState) game.getGameState()).getPlayer().getX() &&
				y==((GameState) game.getGameState()).getPlayer().getY())		
			return true;
		else
			return false;
	}
	
	public boolean playerContact(){
		//from above
		if(((GameState) game.getGameState()).getPlayer().getX()==x &&
				((GameState) game.getGameState()).getPlayer().getY()==y-1)
			if(game.getKeyManager().down)
				return true;
		
		//from bellow
		if(((GameState) game.getGameState()).getPlayer().getX()==x &&
				((GameState) game.getGameState()).getPlayer().getY()==y+1)
			if(game.getKeyManager().up)
				return true;
		
		//from the left
		if(((GameState) game.getGameState()).getPlayer().getX()==x-1 &&
				((GameState) game.getGameState()).getPlayer().getY()==y)
			if(game.getKeyManager().right)
				return true;
		
		//from the right
		if(((GameState) game.getGameState()).getPlayer().getX()==x+1 &&
				((GameState) game.getGameState()).getPlayer().getY()==y)
			if(game.getKeyManager().left)
				return true;
		return false;
	}
	
	public boolean playerIsNextTo(){
		//player above boulder
		if(((GameState) game.getGameState()).getPlayer().getX() == x &&
				((GameState) game.getGameState()).getPlayer().getY() == y-1)
			return true;
		//player bellow boulder
		else if(((GameState) game.getGameState()).getPlayer().getX() == x &&
				((GameState) game.getGameState()).getPlayer().getY() == y+1)
			return true;
		//player left to boulder
		else if(((GameState) game.getGameState()).getPlayer().getX() == x-1 &&
				((GameState) game.getGameState()).getPlayer().getY() == y)
			return true;
		//player right to boulder
		else if(((GameState) game.getGameState()).getPlayer().getX() == x+1 &&
				((GameState) game.getGameState()).getPlayer().getY() == y)
			return true;
		else 
			return false;
	}
	
	public double getX() {return x;}
	public void setX(double x) {this.x = x;}
	public double getY() {return y;}
	public void setY(double y) {this.y = y;}
	public int getWidth() {return width;}
	public void setWidth(int width) {this.width = width;}
	public int getHeight() {return height;}
	public void setHeight(int height) {this.height = height;}
	public boolean isCollisioned() {return collisioned;}
	public void setCollisioned(boolean collisioned) {this.collisioned = collisioned;}
	public Game getGame() {return game;}
	public void setGame(Game game) {this.game = game;}

	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	
}
