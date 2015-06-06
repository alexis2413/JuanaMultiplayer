package pe.edu.pucp.game.entities.objects;

import java.awt.Graphics;

import pe.edu.pucp.game.Game;
import pe.edu.pucp.game.entities.Entity;
import pe.edu.pucp.game.gfx.Assets;
import pe.edu.pucp.game.states.GameState;

@SuppressWarnings("serial")
public class Boulder extends Entity{

	int xMove=0,yMove=0;
	
	
	public Boulder(Game game,double x, double y){
		super(game,x, y, DEFAULT_ENTITY_WIDTH, DEFAULT_ENTITY_HEIGHT);
		this.setDescription("A boulder that can be pushed");
	}
	
	public Boulder(double x, double y){
		super(x, y, DEFAULT_ENTITY_WIDTH, DEFAULT_ENTITY_HEIGHT);
		this.setDescription("A boulder that can be pushed");
	}
	
	public Boulder(){
		this.setDescription("A boulder that can be pushed");
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		playerNextTo();
		if(playerContact() && isCollisioned())
			if(!((GameState)game.getGameState()).getWorld().getTile((int)(x+xMove),(int)(y+yMove)).isSolid()){
				x+=xMove;
				y+=yMove;
			}
	}
	

	public void playerNextTo(){
		//player above boulder
		if(((GameState) game.getGameState()).getPlayer().getX() == x &&
				((GameState) game.getGameState()).getPlayer().getY() == y-1)
				yMove=1;
		//player bellow boulder
		else if(((GameState) game.getGameState()).getPlayer().getX() == x &&
				((GameState) game.getGameState()).getPlayer().getY() == y+1)
				yMove=-1;
		//player left to boulder
		else if(((GameState) game.getGameState()).getPlayer().getX() == x-1 &&
				((GameState) game.getGameState()).getPlayer().getY() == y)
				xMove=1;
		//player right to boulder
		else if(((GameState) game.getGameState()).getPlayer().getX() == x+1 &&
				((GameState) game.getGameState()).getPlayer().getY() == y)
				xMove=-1;
		else if(!collision()){
			xMove=yMove=0;
		}
	}
	
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(Assets.boulder,(int) (x*width-game.getGameCamera().getxOffset()),
				(int)(y*height-game.getGameCamera().getyOffset()),width,height,null);
	}

}
