package pe.edu.pucp.game.tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Tile implements Serializable{
	
	//Static stuff
	
	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new GrassTile(0);
	public static Tile rockTile = new RockTile(1);
	public static Tile doorTile = new DoorTile(2);
	public static Tile groundTile = new GroundTile(3);
        public static Tile seaTile = new GroundTile(4);
	
	public boolean solid=false;
	
	public static Tile[] getTiles() {
		return tiles;
	}

	public static void setTiles(Tile[] tiles) {
		Tile.tiles = tiles;
	}

	public static Tile getGrassTile() {
		return grassTile;
	}

	public static void setGrassTile(Tile grassTile) {
		Tile.grassTile = grassTile;
	}

	public static Tile getRockTile() {
		return rockTile;
	}

	public static void setRockTile(Tile rockTile) {
		Tile.rockTile = rockTile;
	}
        
        public static Tile getSeaTile(){
            return seaTile;
        }
        
        public static void setSeaTile(Tile seaTile){
            Tile.seaTile=seaTile;
        }
        

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	public static int getTilewidth() {
		return TILEWIDTH;
	}

	public static int getTileheight() {
		return TILEHEIGHT;
	}

	//Class
	public static final int TILEWIDTH=24,TILEHEIGHT=24;
	
	protected BufferedImage texture;
	protected final int id;
	public Tile(BufferedImage texture,int id){
		this.texture=texture;
		this.id=id;
		tiles[id]=this;
	}
	public Tile(){
		id=0;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g,int x, int y){
		g.drawImage(texture,x,y,TILEWIDTH,TILEHEIGHT,null);
	}
	
	public boolean isSolid(){
		return solid;
	}
	
	public void setSolid(boolean solid){
		this.solid=solid;
	}
	
	public int getId(){
		return id;
	}
	
	
}
