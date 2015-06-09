package pe.edu.pucp.game.worlds;

import java.awt.Graphics;
import java.io.Serializable;
import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import pe.edu.pucp.game.entities.Entity;
import pe.edu.pucp.game.entities.creatures.NonPlayerCharacter;
import pe.edu.pucp.game.entities.creatures.enemies.Chicken;
import pe.edu.pucp.game.entities.creatures.enemies.Enemy;
import pe.edu.pucp.game.entities.items.Item;
import pe.edu.pucp.game.entities.objects.Boulder;
import pe.edu.pucp.game.gfx.GameCamera;
import pe.edu.pucp.game.tile.Tile;
import pe.edu.pucp.game.tile.DoorTile;
import pe.edu.pucp.game.tile.RockTile;
import pe.edu.pucp.game.tile.GrassTile;
import pe.edu.pucp.game.tile.SeaTile;
//import pe.edu.pucp.game.utils.Utils;

@SuppressWarnings("serial")
@XmlRootElement(name = "World")
@XmlType(propOrder = {"width", "height", "spawnX", "spawnY","nDoors","doors",
		"doorTo","tiles","door","gameCamera","path","nEnemies","nObjects",
		"enemies","objects","nNpcs","npcs","nItems","items"})
public class World implements Serializable{
//public class World{
	private int width,height;
	private int spawnX,spawnY;
	private int nDoors;
	private int[][] doors;
	private int[] doorTo;
	private String[] tiles;
	private boolean door;
	private String path;
	
	private int nEnemies;
	private int nObjects;
	private int [][] enemies;
	private int [][] objects;
	
	private int nNpcs;
	private int [][] npcs;
        
        private int nItems;
        private int [][] items;
	@XmlElement
	private GameCamera gameCamera;
	
	public World(){};
	
	public World(String path,GameCamera gameCamera, ArrayList<Enemy> enemies,
			ArrayList<Entity> objects,  ArrayList<NonPlayerCharacter> npcs,
                        ArrayList<Item> items){
		this.gameCamera=gameCamera;
		loadFromXml(path,enemies,objects,npcs,items);
		this.path=path;
		//loadWorld(path);
	}

	public void tick(){
		if(this.openDoors())
			door=true;
	}
	
	public boolean openDoors(){
		for(int y=0;y<height;y++)
			for(int x=0;x<width;x++)
				if(getTile(x,y).getId()==2)
					if(((DoorTile)getTile(x,y)).isOpen()){
						((DoorTile)getTile(x,y)).closeDoor();
						return true;
					}
		return false;
					
	}
	
	public void render(Graphics g){
                Tile.rockTile = new RockTile(1);
                Tile.grassTile=new GrassTile(0);
                Tile.seaTile=new SeaTile(4);
		for(int y=0;y<height;y++){
			for(int x=0;x<width;x++){
				getTile(x,y).render(g, (int)(x*Tile.TILEWIDTH-gameCamera.getxOffset()),
						(int)(y*Tile.TILEHEIGHT-gameCamera.getyOffset()));
				
			}
		}
	}
	
	public Tile getTile(int x,int y){
		//Tile t=Tile.tiles[tiles[x][y]];
		Tile t=Tile.tiles[Integer.parseInt(""+(tiles[y]).charAt(x))];
		if(t==null)
			return Tile.grassTile;
		return t;
	}

	public int getWidth() {return width;}
	public void setWidth(int width) {this.width = width;}
	public int getHeight() {return height;}
	public void setHeight(int height) {this.height = height;}
	public int getSpawnX() {return spawnX;}
	public void setSpawnX(int spawnX) {this.spawnX = spawnX;}
	public int getSpawnY() {return spawnY;}
	public void setSpawnY(int spawnY) {this.spawnY = spawnY;}
	public String[] getTiles() {return tiles;}
	public void setTiles(String[] tiles) {this.tiles = tiles;}
	public boolean isDoor() {return door;}
	public boolean getDoor(){return door;}
	public void setDoor(boolean door) {this.door = door;}
	public int getnDoors() {return nDoors;}
	public void setnDoors(int nDoors) {this.nDoors = nDoors;}
	public int[][] getDoors() {return doors;}
	public void setDoors(int[][] doors) {this.doors = doors;}
	public int[] getDoorTo() {return doorTo;}
	public void setDoorTo(int[] doorTo) {this.doorTo = doorTo;}
	public String getPath() {return path;}
	public void setPath(String path) {this.path = path;}
	
	public int getnEnemies() {return nEnemies;}
	public void setnEnemies(int nEnemies) {this.nEnemies = nEnemies;}
	public int getnObjects() {return nObjects;}
	public void setnObjects(int nObjects) {this.nObjects = nObjects;}
	public int[][] getEnemies() {return enemies;}
	public void setEnemies(int[][] enemies) {this.enemies = enemies;}
	public int[][] getObjects() {return objects;}
	public void setObjects(int[][] objects) {this.objects = objects;}

	public int getnNpcs() {return nNpcs;}
	public void setnNpcs(int nNpcs) {this.nNpcs = nNpcs;}
	public int[][] getNpcs() {return npcs;}
	public void setNpcs(int[][] npcs) {this.npcs = npcs;}
        
        public int getnItems() {return nItems;}
        public void setnItems(int nItems) {this.nItems = nItems;}
        public int [][] getItems() {return items;}
        public void setItems(int[][]items){this.items=items;}

	public void saveToXml(int name){     
		try {
            JAXBContext context = JAXBContext.newInstance(World.class);
            Marshaller m = context.createMarshaller();
            //for pretty-print XML in JAXB
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
 
            // Write to System.out for debugging
             m.marshal(this, System.out);
 
            // Write to File
            m.marshal(this, new File("World"+name+".xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
	}
	
	
	public void loadFromXml(String path, ArrayList<Enemy> enemies, ArrayList<Entity> objects,
			ArrayList<NonPlayerCharacter> npcs, ArrayList<Item> items){
		try {
            JAXBContext context = JAXBContext.newInstance(World.class);
            Unmarshaller un = context.createUnmarshaller();
            World world = (World) un.unmarshal(new File(path));
            this.setDoor(world.getDoor());
            this.setDoors(world.getDoors());
            this.setDoorTo(world.getDoorTo());
            this.setHeight(world.getHeight());
            this.setnDoors(world.getnDoors());
            this.setPath(world.getPath());
            this.setSpawnX(world.getSpawnX());
            this.setSpawnY(world.getSpawnY());
            this.setTiles(world.getTiles());
            this.setWidth(world.getWidth());
            this.setnEnemies(world.getnEnemies());
            this.setEnemies(world.getEnemies());
            this.setnObjects(world.getnObjects());
            this.setObjects(world.getObjects());
            this.setnNpcs(world.getnNpcs());
            this.setNpcs(world.getNpcs());
            this.setnItems(world.getnItems());
            this.setItems(world.getItems());
            
            loadContentFromXml(enemies,objects,npcs,items);
            
        } catch (JAXBException e) {
            e.printStackTrace();
        }
	}
	
	public void loadContentFromXml(ArrayList<Enemy> enemyList, ArrayList<Entity> objectList,
			 ArrayList<NonPlayerCharacter> npcList, ArrayList<Item> itemList){
		enemyList.clear();
		objectList.clear();
		npcList.clear();
                itemList.clear();
		
		for(int i=0;i<nEnemies;i++)
			enemyList.add(new Chicken(enemies[i][1],enemies[i][2]));
		for(int i=0;i<nObjects;i++)
			objectList.add(new Boulder(objects[i][1],objects[i][2]));
		for(int i=0;i<nNpcs;i++)
			npcList.add(new NonPlayerCharacter(npcs[i][1],npcs[i][2]));
                for(int i=0;i<nItems;i++)
			itemList.add(new Item(items[i][1],items[i][2]));
                
	}
	///TXT
	/*
	private void loadWorld(String path, ArrayList<Enemy> enemies, ArrayList<Entity> objects){
		String file=Utils.loadFileAsString(path);
		String[] tokens=file.split("\\s+");
		width=Utils.parseInt(tokens[0]);
		height=Utils.parseInt(tokens[1]);
		spawnX=Utils.parseInt(tokens[2]);
		spawnY=Utils.parseInt(tokens[3]);
		nDoors=Utils.parseInt(tokens[4]);
		
		doors=new int[nDoors][2];
		doorTo=new int[nDoors];
		int actualDoor=0;
		for(int i=0;i<nDoors;i++){
			doorTo[i]=Utils.parseInt(tokens[5+i]);
		}
		

		tiles=new String[height];
		for(int y=0;y<height;y++){
			tiles[y]=tokens[5+y+nDoors];
			for(int x=0;x<width;x++){
				if(Integer.parseInt(""+(tiles[y]).charAt(x))==2){
					doors[actualDoor][0]=x;
					doors[actualDoor][1]=y;
					actualDoor++;
				}
			}		
		}
	}
	*/

}
