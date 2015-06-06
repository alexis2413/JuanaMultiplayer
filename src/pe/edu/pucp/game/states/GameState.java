package pe.edu.pucp.game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

import pe.edu.pucp.game.Game;
import pe.edu.pucp.game.display.Display;
import pe.edu.pucp.game.entities.Entity;
import pe.edu.pucp.game.entities.creatures.NonPlayerCharacter;
import pe.edu.pucp.game.entities.creatures.Player;
import pe.edu.pucp.game.entities.creatures.enemies.Enemy;
import pe.edu.pucp.game.entities.items.Item;
import pe.edu.pucp.game.worlds.World;

@SuppressWarnings("serial")
public class GameState extends State implements Serializable{

	private Player player;
	private int nEnemies;
	private ArrayList<Enemy> enemies= new ArrayList<Enemy>();
	private ArrayList<Entity> objects = new ArrayList<Entity>();
	private ArrayList<NonPlayerCharacter> npcs = new ArrayList<NonPlayerCharacter>();
        private ArrayList<Item> items = new  ArrayList<Item>();
	private World world;
	private boolean playerPressed=false;
	private boolean[] objectPressed, npcPressed, enemyPressed;
	
	public GameState(Game game){
		super(game);
		world=new World("res/worlds/world1.xml",game.getGameCamera(),enemies,objects,npcs,items);
		//world=new World("res/worlds/world1.txt",game.getGameCamera());
		//world.saveToXml(1);
		player=new Player(game,world.getSpawnX(),world.getSpawnY());
		setGame();
		objectPressed=new boolean[objects.size()];
		npcPressed=new boolean[npcs.size()];
		enemyPressed=new boolean[enemies.size()];
	}

	public GameState(){}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		if(world.openDoors()){			
			int i=0,newWorld;
			while(player.getX()!=world.getDoors()[i][0] || player.getY()!=world.getDoors()[i][1]){
				i++;
			}
			newWorld=world.getDoorTo()[i];
			world=new World("res/Worlds/world"+ newWorld +".xml",game.getGameCamera(),enemies,objects,npcs,items);
			//world=new World("res/Worlds/world"+ newWorld +".txt",game.getGameCamera());
			//world.saveToXml(newWorld);
			player.setX(world.getSpawnX());
			player.setY(world.getSpawnY());
			setGame();
		}
		world.tick();
		player.tick();
		
		for(int i=0;i<npcs.size();i++){
			npcs.get(i).tick();
			if((game.getMouseManager().mX>=npcs.get(i).getX()*24-game.getGameCamera().getxOffset() 
					&& game.getMouseManager().mX<=npcs.get(i).getX()*24-game.getGameCamera().getxOffset()+24)&&
					(game.getMouseManager().mY>=npcs.get(i).getY()*24-game.getGameCamera().getyOffset() &&
					game.getMouseManager().mY<=npcs.get(i).getY()*24-game.getGameCamera().getyOffset()+24)){
				npcPressed[i]=true;
			}
			else
				npcPressed[i]=false;
		}
			
		if(enemies.size()>0)
			for(int j=0;j<enemies.size();j++){
				if((game.getMouseManager().mX>=enemies.get(j).getX()*24-game.getGameCamera().getxOffset() 
						&& game.getMouseManager().mX<=enemies.get(j).getX()*24-game.getGameCamera().getxOffset()+24)&&
						(game.getMouseManager().mY>=enemies.get(j).getY()*24-game.getGameCamera().getyOffset() &&
						game.getMouseManager().mY<=enemies.get(j).getY()*24-game.getGameCamera().getyOffset()+24)){
					enemyPressed[j]=true;
				}
				else
					enemyPressed[j]=false;
				enemies.get(j).tick();
				if(enemies.get(j).getHealth()<=0){
					enemies.remove(j);
					nEnemies--;
				}
			}
		
		if(objects.size()>0)
			for(int j=0;j<objects.size();j++){
                            objects.get(j).tick();
                            if((game.getMouseManager().mX>=objects.get(j).getX()*24-game.getGameCamera().getxOffset() 
				&& game.getMouseManager().mX<=objects.get(j).getX()*24-game.getGameCamera().getxOffset()+24)&&
					(game.getMouseManager().mY>=objects.get(j).getY()*24-game.getGameCamera().getyOffset() &&
                                    game.getMouseManager().mY<=objects.get(j).getY()*24-game.getGameCamera().getyOffset()+24)){
                                    objectPressed[j]=true;
				}else
                                    objectPressed[j]=false;
			}
                
                if(items.size()>0)
                    for(int i=0;i<items.size();i++)
                        items.get(i).tick();
                    
		
		if((game.getMouseManager().mX>=player.getX()*24-game.getGameCamera().getxOffset() 
				&& game.getMouseManager().mX<=player.getX()*24-game.getGameCamera().getxOffset()+24)&&
				(game.getMouseManager().mY>=player.getY()*24-game.getGameCamera().getyOffset() &&
				game.getMouseManager().mY<=player.getY()*24-game.getGameCamera().getyOffset()+24)){
			playerPressed=true;
		}else
			playerPressed=false;

	}

	@Override
	public void render(Display display) {
		// TODO Auto-generated method stub
		Graphics g= display.getCanvas().getBufferStrategy().getDrawGraphics();
		Font fnt0=new Font("arial",Font.BOLD,10);
		g.setFont(fnt0);
		g.setColor(Color.black);
		if(player.getX()==1 && player.getY()==1)
			world.render(g);
		else
			world.render(g);
		player.render(g);
		if(playerPressed){
			g.drawString(player.getDescription(),(int)(player.getX()*24-game.getGameCamera().getxOffset()),
					(int)(player.getY()*24-game.getGameCamera().getyOffset()));
		}
		if(enemies.size()>0)
			for(int j=0;j<enemies.size();j++){
				enemies.get(j).render(g);
				if(enemyPressed[j]){
					g.drawString(enemies.get(j).getDescription(),(int)(enemies.get(j).getX()*24-game.getGameCamera().getxOffset()),
							(int)(enemies.get(j).getY()*24-game.getGameCamera().getyOffset()));
				}
			}
		if(objects.size()>0)
			for(int j=0;j<objects.size();j++){
				objects.get(j).render(g);
				if(objectPressed[j]){
					g.drawString(objects.get(j).getDescription(),(int)(objects.get(j).getX()*24-game.getGameCamera().getxOffset()),
							(int)(objects.get(j).getY()*24-game.getGameCamera().getyOffset()));
				}
			}
		
		for(int i=0;i<npcs.size();i++){
			npcs.get(i).render(g);
			if(npcPressed[i]){
				g.drawString(npcs.get(i).getDescription(),(int)(npcs.get(i).getX()*24-game.getGameCamera().getxOffset()),
						(int)(npcs.get(i).getY()*24-game.getGameCamera().getyOffset()));
			}
		}
                
                for(int i=0;i<items.size();i++){
                    items.get(i).render(g);
                }
	}

	public void setGame(){
		nEnemies=enemies.size();
		for(int i=0;i<enemies.size();i++)
			enemies.get(i).setGame(game);
		for(int i=0;i<objects.size();i++)
			objects.get(i).setGame(game);
		for(int i=0;i<npcs.size();i++)
			npcs.get(i).setGame(game);
                for(int i=0;i<items.size();i++)
			items.get(i).setGame(game);
	}
	
	public Player getPlayer() {return player;}
	public void setPlayer(Player player) {this.player = player;}
	public int getnEnemies() {return nEnemies;}
	public void setnEnemies(int nEnemies) {this.nEnemies = nEnemies;}
	public ArrayList<Enemy> getEnemies() {return enemies;}
	public void setEnemies(ArrayList<Enemy> enemies) {this.enemies = enemies;}
	public World getWorld(){return world;}
	public void setWorld(World world){this.world=world;}
	public ArrayList<Entity> getObjects() {return objects;}
	public void setObjects(ArrayList<Entity> objects) {this.objects = objects;}
	public ArrayList<NonPlayerCharacter> getNpcs() {return npcs;}
	public void setNpcs(ArrayList<NonPlayerCharacter> npcs) {this.npcs = npcs;}
        public ArrayList<Item> getItems() {return items;}
	public void setItems(ArrayList<Item> items) {this.items = items;}
	
	
}
