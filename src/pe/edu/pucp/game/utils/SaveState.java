package pe.edu.pucp.game.utils;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings("serial")
@XmlRootElement(name = "SaveState")
@XmlType(propOrder = {"nEnemies", "playerX", "playerY", "playerHealth",
		"enemyX", "enemyY", "enemyHealth", "enemyId", "path","nObjects",
		"objectX", "objectY","nNpcs", "npcX", "npcY", "nItems", "itemX", "itemY"})

public class SaveState implements Serializable{
        private static final long serialVersionUID = 6529685098267757690L;
	public SaveState(int nEnemies){
		this.nEnemies=nEnemies;
		this.enemyX=new ArrayList<Double>();
		this.enemyY=new ArrayList<Double>();
		this.enemyHealth=new ArrayList<Integer>();
		this.enemyId=new ArrayList<Integer>();
		this.npcX=new ArrayList<Double>();
		this.npcY=new ArrayList<Double>();
                this.itemX=new ArrayList<Double>();
                this.itemY=new ArrayList<Double>();
				
	}
	
	public SaveState(){}
	//Player
	private double playerX,playerY;
	private int playerHealth;
	
	//Enemies
	
	private int nEnemies;
	private ArrayList<Double> enemyX,enemyY;
	private ArrayList<Integer> enemyHealth;
	private ArrayList<Integer> enemyId;
	
	//Objects
	private int nObjects;
	private ArrayList<Double> objectX, objectY;
	
	//NPCs
	private int nNpcs;
	private ArrayList<Double> npcX, npcY;
        
        //Items
        private int nItems;
        private ArrayList<Double> itemX, itemY;
	
	//World
	private String path;

	public double getPlayerX() {return playerX;}
	public void setPlayerX(double d) {this.playerX = d;}
	public double getPlayerY() {return playerY;}
	public void setPlayerY(double playerY) {this.playerY = playerY;}
	public int getPlayerHealth() {return playerHealth;}
	public void setPlayerHealth(int playerHealth) {this.playerHealth = playerHealth;}

	public String getPath() {return path;}
	public void setPath(String path) {this.path = path;}

	public int getnEnemies() {return nEnemies;}
	public void setnEnemies(int nEnemies) {this.nEnemies = nEnemies;}
	
	public ArrayList<Double> getEnemyX() {return enemyX;}
	public void setEnemyX(ArrayList<Double> enemyX) {this.enemyX = enemyX;}
	public ArrayList<Double> getEnemyY() {return enemyY;}
	public void setEnemyY(ArrayList<Double> enemyY) {this.enemyY = enemyY;}
	public ArrayList<Integer> getEnemyHealth() {return enemyHealth;}
	public void setEnemyHealth(ArrayList<Integer> enemyHealth) {this.enemyHealth = enemyHealth;}

	public ArrayList<Integer> getEnemyId() {return enemyId;}
	public void setEnemyId(ArrayList<Integer> enemyId) {this.enemyId = enemyId;}

	public int getnObjects() {return nObjects;}
	public void setnObjects(int nObjects) {this.nObjects = nObjects;}
	public ArrayList<Double> getObjectX() {return objectX;}
	public void setObjectX(ArrayList<Double> objectX) {this.objectX = objectX;}
	public ArrayList<Double> getObjectY() {return objectY;}
	public void setObjectY(ArrayList<Double> objectY) {this.objectY = objectY;}

	public int getnNpcs() {return nNpcs;}
	public void setnNpcs(int nNpcs) {this.nNpcs = nNpcs;}
	public ArrayList<Double> getNpcX() {return npcX;}
	public void setNpcX(ArrayList<Double> npcX) {this.npcX = npcX;}
	public ArrayList<Double> getNpcY() {return npcY;}
	public void setNpcY(ArrayList<Double> npcY) {this.npcY = npcY;}

        public int getnItems() {return nItems;}
        public void setnItems(int nItems) {this.nItems=nItems;}
        public ArrayList<Double> getItemX() {return itemX;}
        public void setItemX(ArrayList<Double> itemX) {this.itemX = itemX;}
        public ArrayList<Double> getItemY() {return itemY;}
        public void setItemY(ArrayList<Double> itemY) {this.itemY = itemY;}
	
}
