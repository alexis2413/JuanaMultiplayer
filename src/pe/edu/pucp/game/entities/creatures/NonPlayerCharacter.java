package pe.edu.pucp.game.entities.creatures;

import java.awt.Graphics;
import java.util.ArrayList;

import pe.edu.pucp.game.Game;
import pe.edu.pucp.game.gfx.Assets;
import pe.edu.pucp.game.states.GameState;

@SuppressWarnings("serial")
public class NonPlayerCharacter extends Creature{

	ArrayList<ArrayList<String>> dialog=new ArrayList<ArrayList<String>>();
	
	public  NonPlayerCharacter(Game game,double x, double y){
		super(game,x, y, DEFAULT_ENTITY_WIDTH, DEFAULT_ENTITY_HEIGHT);
		this.setDescription("NPC just walking around");
	}
	
	public  NonPlayerCharacter(double x, double y){
		super(x, y, DEFAULT_ENTITY_WIDTH, DEFAULT_ENTITY_HEIGHT);
		this.setDescription("NPC just walking around");
	}
	
	public NonPlayerCharacter(){
		this.setDescription("NPC just walking around");
	}
	
	public void beginDialog(){
		System.out.println("Hola noob");
		if(game.getKeyManager().space){
			System.out.println("Adios noob");
			((GameState) game.getGameState()).getPlayer().setDialoging(false);
		}
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		if(!((GameState) game.getGameState()).getPlayer().isDialoging()){
			if(playerIsNextTo() && game.getKeyManager().space){
				((GameState) game.getGameState()).getPlayer().setDialoging(true);
				(game.getKeyManager().space)=false;
				beginDialog();
			}
		
		moveCounter++;
		if(moveCounter==delay){
			randomMove();
			move();
			moveCounter=0;	
		}	
		}else
			beginDialog();
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(Assets.npc1,(int) (x*width-game.getGameCamera().getxOffset()),
				(int)(y*height-game.getGameCamera().getyOffset()),width,height,null);
	}

}
