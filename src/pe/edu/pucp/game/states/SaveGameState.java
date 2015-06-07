package pe.edu.pucp.game.states;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

//import org.json.JSONArray;
//import org.json.JSONObject;

import javax.xml.bind.Unmarshaller;

import pe.edu.pucp.game.Game;
import pe.edu.pucp.game.display.Display;
import pe.edu.pucp.game.utils.Data;
import pe.edu.pucp.game.utils.SaveState;

@SuppressWarnings("serial")
public class SaveGameState extends State{
	public Rectangle rect1 = new Rectangle(50,120,50,50);
	public Rectangle rect2 = new Rectangle(110,120,50,50);
	public Rectangle rect3 = new Rectangle(170,120,50,50);
	public Rectangle rect4 = new Rectangle(230,120,50,50);
	
	public Rectangle rect5 = new Rectangle(50,200,50,50);
	public Rectangle rect6 = new Rectangle(110,200,50,50);
	public Rectangle rect7 = new Rectangle(170,200,50,50);
	public Rectangle rect8 = new Rectangle(230,200,50,50);
	
	public Rectangle backButton = new Rectangle(145,275,100,50);
	
	private SaveState saveState=new SaveState(((GameState) game.getGameState()).getnEnemies());
	private Data data=new Data();
	
	public SaveGameState(Game game) {
		super(game);
		loadDataFromXml();
		// TODO Auto-generated constructor stub
	}
	
	public SaveGameState(){
		loadDataFromXml();
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		if((game.getMouseManager().mX>=50 && game.getMouseManager().mX<=100)&&
				(game.getMouseManager().mY>=120 && game.getMouseManager().mY<=170)){
			saveGame(1);			
		}
		if((game.getMouseManager().mX>=110 && game.getMouseManager().mX<=160)&&
				(game.getMouseManager().mY>=120 && game.getMouseManager().mY<=170)){
			saveGame(2);				
		}
		if((game.getMouseManager().mX>=170 && game.getMouseManager().mX<=220)&&
				(game.getMouseManager().mY>=120 && game.getMouseManager().mY<=170)){

			saveGame(3);				
		}
		if((game.getMouseManager().mX>=230 && game.getMouseManager().mX<=280)&&
				(game.getMouseManager().mY>=120 && game.getMouseManager().mY<=170)){
			saveGame(4);				
		}
		
		if((game.getMouseManager().mX>=50 && game.getMouseManager().mX<=100)&&
				(game.getMouseManager().mY>=200 && game.getMouseManager().mY<=250)){
			saveGame(5);					
		}
		if((game.getMouseManager().mX>=110 && game.getMouseManager().mX<=160)&&
				(game.getMouseManager().mY>=200 && game.getMouseManager().mY<=250)){
			saveGame(6);				
		}
		if((game.getMouseManager().mX>=170 && game.getMouseManager().mX<=220)&&
				(game.getMouseManager().mY>=200 && game.getMouseManager().mY<=250)){
			saveGame(7);					
		}
		if((game.getMouseManager().mX>=230 && game.getMouseManager().mX<=280)&&
				(game.getMouseManager().mY>=200 && game.getMouseManager().mY<=250)){
			saveGame(8);				
		}
		
		if((game.getMouseManager().mX>=145 && game.getMouseManager().mX<=245)&&
				(game.getMouseManager().mY>=275 && game.getMouseManager().mY<=325)){				
			State.setState(game.getOptionState());
		}
	}

	@Override
	public void render(Display display) {
		// TODO Auto-generated method stub
		Graphics g= display.getCanvas().getBufferStrategy().getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		// TODO Auto-generated method stub
		
		Font fnt1=new Font("arial",Font.BOLD,20);
		g.setFont(fnt1);
		g.drawString(data.getFiles().get(0),rect1.x+15,rect1.y+30);
		g2d.draw(rect1);
		g.drawString(data.getFiles().get(1),rect2.x+15,rect2.y+30);
		g2d.draw(rect2);
		g.drawString(data.getFiles().get(2),rect3.x+15,rect3.y+30);
		g2d.draw(rect3);
		g.drawString(data.getFiles().get(3),rect4.x+15,rect4.y+30);
		g2d.draw(rect4);
		g.drawString(data.getFiles().get(4),rect5.x+15,rect5.y+30);
		g2d.draw(rect5);
		g.drawString(data.getFiles().get(5),rect6.x+15,rect6.y+30);
		g2d.draw(rect6);
		g.drawString(data.getFiles().get(6),rect7.x+15,rect7.y+30);
		g2d.draw(rect7);
		g.drawString(data.getFiles().get(7),rect8.x+15,rect8.y+30);
		g2d.draw(rect8);
		
		Font fnt2=new Font("arial",Font.BOLD,30);
		g.setFont(fnt2);
		g.drawString("Back",backButton.x+19,backButton.y+30);
		g2d.draw(backButton);
	
		
	}
	
	public void saveGame(int slot){
		//save atributes to savestate
		saveState.setPlayerX(((GameState) game.getGameState()).getPlayer().getX());
		saveState.setPlayerY(((GameState) game.getGameState()).getPlayer().getY());
		saveState.setPlayerHealth(((GameState) game.getGameState()).getPlayer().getHealth());
		saveState.setnEnemies(((GameState) game.getGameState()).getnEnemies());
		saveState.setnObjects(((GameState) game.getGameState()).getObjects().size());
		saveState.setnNpcs(((GameState) game.getGameState()).getNpcs().size());
                saveState.setnItems(((GameState) game.getGameState()).getItems().size());
		
		saveState.setEnemyX(new ArrayList<Double>());
		saveState.setEnemyY(new ArrayList<Double>());
		saveState.setEnemyHealth(new ArrayList<Integer>());
		saveState.setEnemyId(new ArrayList<Integer>());
		
		saveState.setObjectX(new ArrayList<Double>());
		saveState.setObjectY(new ArrayList<Double>());
		
		saveState.setNpcX(new ArrayList<Double>());
		saveState.setNpcY(new ArrayList<Double>());
                
                saveState.setItemX(new ArrayList<Double>());
		saveState.setItemY(new ArrayList<Double>());
                
                saveState.setPath(((GameState) game.getGameState()).getWorld().getPath());
		
		for(int i=0;i<saveState.getnEnemies();i++){
			saveState.getEnemyX().add(((GameState) game.getGameState()).getEnemies().get(i).getX());
			saveState.getEnemyY().add(((GameState) game.getGameState()).getEnemies().get(i).getY());
			saveState.getEnemyHealth().add(((GameState) game.getGameState()).getEnemies().get(i).getHealth());
			saveState.getEnemyId().add(((GameState) game.getGameState()).getEnemies().get(i).getId());
		}
		
		for(int i=0;i<saveState.getnObjects();i++){
			saveState.getObjectX().add(((GameState) game.getGameState()).getObjects().get(i).getX());
			saveState.getObjectY().add(((GameState) game.getGameState()).getObjects().get(i).getY());
		}
		
		for (int i=0;i<saveState.getnNpcs();i++){
			saveState.getNpcX().add(((GameState) game.getGameState()).getNpcs().get(i).getX());
			saveState.getNpcY().add(((GameState) game.getGameState()).getNpcs().get(i).getY());
		}
                
                for (int i=0;i<saveState.getnItems();i++){
			saveState.getItemX().add(((GameState) game.getGameState()).getItems().get(i).getX());
			saveState.getItemY().add(((GameState) game.getGameState()).getItems().get(i).getY());
		}
		

		System.out.println("Nombre de la partida");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String fileName= (scanner.next());
		data.getFiles().set(slot-1,fileName);
		saveDataToXml();
		//save savestate to binary file
		saveToBinary(fileName);
		//saveToXml();
		//saveToJson();
		
		State.setState(game.getGameState());
	}
	
	public void saveToBinary(String fileName){
		try {
			FileOutputStream fout;
			//fout= new FileOutputStream("res/Data/GameState"+number+".bin");
			fout= new FileOutputStream("res/Data/"+fileName+".bin");
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(saveState);
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveToXml(){     
		try {
            JAXBContext context = JAXBContext.newInstance(SaveState.class);
            Marshaller m = context.createMarshaller();
            //for pretty-print XML in JAXB
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
 
            // Write to File
            m.marshal(saveState, new File("res/Data/GameState.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
	}
	
	public void saveDataToXml(){     
		try {
            JAXBContext context = JAXBContext.newInstance(Data.class);
            Marshaller m = context.createMarshaller();
            //for pretty-print XML in JAXB
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
 
            // Write to File
            m.marshal(data, new File("res/Data/data.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
	}
	
	public void loadDataFromXml(){
		try {
            JAXBContext context = JAXBContext.newInstance(Data.class);
            Unmarshaller un = context.createUnmarshaller();
            data = (Data) un.unmarshal(new File("res/Data/data.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
	}
	/*
	public void saveToJson(){
		JSONObject obj = new JSONObject();
		obj.put("nEnemies", new Integer(saveState.getnEnemies()));
		obj.put("playerX", new Double(saveState.getPlayerX()));
		obj.put("playerY", new Double(saveState.getPlayerY()));
		obj.put("playerHealth", new Integer(saveState.getPlayerHealth()));
		obj.put("path", saveState.getPath());
		JSONArray enemies= new JSONArray();
		for(int i=0;i<saveState.getnEnemies();i++){
			JSONObject enemy = new JSONObject();
			enemy.put("enemyX", new Double(saveState.getEnemyX().get(i)));
			enemy.put("enemyY", new Double(saveState.getEnemyY().get(i)));
			enemy.put("enemyHealth", new Integer(saveState.getEnemyHealth().get(i)));
			enemy.put("enemyId", new Integer(saveState.getEnemyId().get(i)));
			enemies.put(i,enemy);
		}
		obj.put("enemies",enemies);
		try {	 
			FileWriter file = new FileWriter("res/Data/GameState.json");
			file.write(obj.toString());
			file.flush();
			file.close();
	 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
}
