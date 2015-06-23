package pe.edu.pucp.game.states;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

//import org.json.JSONArray;
//import org.json.JSONObject;
import pe.edu.pucp.game.Game;
import pe.edu.pucp.game.display.Display;
import pe.edu.pucp.game.entities.creatures.NonPlayerCharacter;
import pe.edu.pucp.game.entities.creatures.enemies.Chicken;
import pe.edu.pucp.game.entities.items.Item;
import pe.edu.pucp.game.entities.objects.Boulder;
import pe.edu.pucp.game.gfx.Assets;
import pe.edu.pucp.game.utils.Data;
import pe.edu.pucp.game.utils.SaveState;
import pe.edu.pucp.game.worlds.World;

@SuppressWarnings("serial")
public class LoadGameState extends State {

    public Rectangle rect1 = new Rectangle(50, 120, 50, 50);
    public Rectangle rect2 = new Rectangle(110, 120, 50, 50);
    public Rectangle rect3 = new Rectangle(170, 120, 50, 50);
    public Rectangle rect4 = new Rectangle(230, 120, 50, 50);

    public Rectangle rect5 = new Rectangle(50, 200, 50, 50);
    public Rectangle rect6 = new Rectangle(110, 200, 50, 50);
    public Rectangle rect7 = new Rectangle(170, 200, 50, 50);
    public Rectangle rect8 = new Rectangle(230, 200, 50, 50);

    public Rectangle backButton = new Rectangle(145, 275, 80, 40);
    public Rectangle loadButton = new Rectangle(145, 40, 80, 40);

    public Rectangle textRect = new Rectangle(20, 330, 360, 60);

    private SaveState saveState = new SaveState(((GameState) game.getGameState()).getnEnemies());
    private Data data = new Data();
    private int slotClicked = 0;

    public LoadGameState(Game game) {
        super(game);
        /*for(int i=0;i<8;i++)
         data.getFiles().add("Empty");*/
        //saveDataToXml();
        loadDataFromXml();
        // TODO Auto-generated constructor stub
    }

    public LoadGameState() {
        loadDataFromXml();
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        int xClicked = game.getMouseManager().mX;
        int yClicked = game.getMouseManager().mY;

        //DISPLAYA TEXTRECT
        if ((xClicked >= 50 && xClicked <= 100)
                && (yClicked >= 120 && yClicked <= 170)) {

            slotClicked = 1;
        }
        if ((xClicked >= 110 && xClicked <= 160)
                && (yClicked >= 120 && yClicked <= 170)) {

            slotClicked = 2;
        }
        if ((xClicked >= 170 && xClicked <= 220)
                && (yClicked >= 120 && yClicked <= 170)) {

            slotClicked = 3;
        }
        if ((xClicked >= 230 && xClicked <= 280)
                && (yClicked >= 120 && yClicked <= 170)) {

            slotClicked = 4;
        }

        if ((xClicked >= 50 && xClicked <= 100)
                && (yClicked >= 200 && yClicked <= 250)) {

            slotClicked = 5;
        }
        if ((xClicked >= 110 && xClicked <= 160)
                && (yClicked >= 200 && yClicked <= 250)) {

            slotClicked = 6;
        }
        if ((xClicked >= 170 && xClicked <= 220)
                && (yClicked >= 200 && yClicked <= 250)) {

            slotClicked = 7;
        }
        if ((xClicked >= 230 && xClicked <= 280)
                && (yClicked >= 200 && yClicked <= 250)) {

            slotClicked = 8;
        }
        //BACK BUTTON
        if ((xClicked >= 145 && xClicked <= 245)
                && (yClicked >= 275 && yClicked <= 325)) {
            if (game.hasStarted == false) {
                State.setState(game.getMenuState());
            } else {
                State.setState(game.getGameState());
            }
            game.getMouseManager().mX = 0;
            game.getMouseManager().mY = 0;
        }
        //LOAD BUTTON
        if ((xClicked >= 145 && xClicked <= 245)
                && (yClicked >= 40 && yClicked <= 90)) {
            if (slotClicked != 0) {
                loadGame(data.getFiles().get(slotClicked - 1));
                State.setState(game.getGameState());
            }

        }
    }

    @Override
    public void render(Display display) {
        // TODO Auto-generated method stub
        Graphics g = display.getCanvas().getBufferStrategy().getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.85f));
        // TODO Auto-generated method stub
        g.drawImage(Assets.juanaBackground, 0, 0, game.getHeight(), game.getWidth(), null);
        
        //TEXTBOX! 20, 330, 360, 60    
        g.setColor(Color.orange);
        g.fillRect(23, 333 , 357, 55);        
        g.drawRect(23, 333, 357, 55);
                
        g.drawImage(Assets.corner1, 20, 330, 21, 21, null);
        g.drawImage(Assets.corner2, 359, 330, 21, 21, null);
        g.drawImage(Assets.corner3, 20, 369, 21, 21, null);
        g.drawImage(Assets.corner4, 359, 369, 21, 21, null);
        
        g.drawImage(Assets.barTop, 30,330, 330, 11, null);
        g.drawImage(Assets.barBot, 30, 379, 330, 11, null);
        g.drawImage(Assets.barLeft, 20, 350, 11, 25, null);
        g.drawImage(Assets.barRight, 370, 350, 11, 25, null);
                
        g.drawImage(Assets.slot, 50, 120, 50, 50, null);
        g.drawImage(Assets.slot, 110, 120, 50, 50, null);
        g.drawImage(Assets.slot, 170, 120, 50, 50, null);
        g.drawImage(Assets.slot, 230, 120, 50, 50, null);
        g.drawImage(Assets.slot, 50, 200, 50, 50, null);
        g.drawImage(Assets.slot, 110, 200, 50, 50, null);
        g.drawImage(Assets.slot, 170, 200, 50, 50, null);
        g.drawImage(Assets.slot, 230, 200, 50, 50, null);
        //BACK! 
        g.drawImage(Assets.button1, 145, 275, 33, 50, null);
        g.drawImage(Assets.button2, 178, 275, 33, 50, null);
        g.drawImage(Assets.button3, 211, 275, 34, 50, null);
        //LOAD! 145, 40, 80, 40
        g.drawImage(Assets.button1, 145, 40, 33, 50, null);
        g.drawImage(Assets.button2, 178, 40, 33, 50, null);
        g.drawImage(Assets.button3, 211, 40, 34, 50, null);
        
        
        Font fnt1 = new Font("arial", Font.BOLD, 20);
        g.setFont(fnt1);
        g.setColor(Color.white);
        g.drawString("1", rect1.x + 18, rect1.y + 32);
        g.drawString("2", rect2.x + 18, rect2.y + 32);
        g.drawString("3", rect3.x + 18, rect3.y + 32);
        g.drawString("4", rect4.x + 18, rect4.y + 32);
        g.drawString("5", rect5.x + 18, rect5.y + 32);
        g.drawString("6", rect6.x + 18, rect6.y + 32);
        g.drawString("7", rect7.x + 18, rect7.y + 32);
        g.drawString("8", rect8.x + 18, rect8.y + 32);

        Font fnt2 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt2);
        g.setColor(Color.black);
        g.drawString("Back", backButton.x + 15, backButton.y + 35);        
        g.drawString("Load", loadButton.x + 15, loadButton.y + 35);
        

        Font fnt3 = new Font("arial", Font.BOLD, 14);
        g.setFont(fnt2);
        if (slotClicked != 0) {
            g.drawString("                     ", 40, 350);
            g.drawString("Nombre: " + data.getFiles().get(slotClicked - 1), 40, 370);
        }

    }

    public void loadGame(String fileName) {
        loadFromBinary(fileName);//load binary file to the savestate class
        //loadFromJson();
        //Load atributes from gamestate to the classes
        ((GameState) game.getGameState()).setWorld(new World(saveState.getPath(), game.getGameCamera(),
                ((GameState) game.getGameState()).getEnemies(), ((GameState) game.getGameState()).getObjects(),
                ((GameState) game.getGameState()).getNpcs(), ((GameState) game.getGameState()).getItems()));

        ((GameState) game.getGameState()).getPlayer().setX(saveState.getPlayerX());
        ((GameState) game.getGameState()).getPlayer().setY(saveState.getPlayerY());
        ((GameState) game.getGameState()).getPlayer().setHealth(saveState.getPlayerHealth());

        ((GameState) game.getGameState()).setnEnemies(saveState.getnEnemies());

        ((GameState) game.getGameState()).getEnemies().clear();
        ((GameState) game.getGameState()).getObjects().clear();
        ((GameState) game.getGameState()).getNpcs().clear();
        ((GameState) game.getGameState()).getItems().clear();
        if (saveState.getnEnemies() > 0) {
            for (int i = 0; i < saveState.getnEnemies(); i++) {
                //FALTA CLASIFICAR POR IDS
                Chicken enemy = new Chicken(game, saveState.getEnemyX().get(i), saveState.getEnemyY().get(i));
                enemy.setHealth(saveState.getEnemyHealth().get(i));
                ((GameState) game.getGameState()).getEnemies().add(enemy);
            }
        }
        for (int i = 0; i < saveState.getnObjects(); i++) {
            //FALTA CLASIFICAR POR IDS
            Boulder boulder = new Boulder(game, saveState.getObjectX().get(i), saveState.getObjectY().get(i));
            ((GameState) game.getGameState()).getObjects().add(boulder);
        }
        for (int i = 0; i < saveState.getnNpcs(); i++) {
            //FALTA CLASIFICAR POR IDS
            NonPlayerCharacter npc = new NonPlayerCharacter(game, saveState.getNpcX().get(i), saveState.getNpcY().get(i));
            ((GameState) game.getGameState()).getNpcs().add(npc);
        }
        for (int i = 0; i < saveState.getnItems(); i++) {
            //FALTA CLASIFICAR POR IDS
            Item item = new Item(game, saveState.getItemX().get(i), saveState.getItemY().get(i));
            ((GameState) game.getGameState()).getItems().add(item);
        }
    }

    public void loadFromBinary(String fileName) {
        try {
            FileInputStream fis;
            //fis = new FileInputStream("res/Data/GameState"+number+".bin");
            fis = new FileInputStream("res/Data/" + fileName + ".bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            saveState = (SaveState) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*
     public void loadFromJson(){
     String str = new String();
     File archivo = null;
     FileReader fr = null;
     BufferedReader br = null;
	    
     archivo = new File ("res/Data/GameState.json");
     try {
     fr = new FileReader (archivo);
     br = new BufferedReader(fr);
     // Lectura del archivo
     String linea;
     while((linea=br.readLine())!=null)
     str += linea;			
     } catch (Exception e) {
     e.printStackTrace();
     }
	    
     JSONObject obj = new JSONObject(str);
		
		
     double playerX = obj.getDouble("playerX");
     double playerY = obj.getDouble("playerY");
     int playerHealth = obj.getInt("playerHealth");
     int nEnemies = obj.getInt("nEnemies");
     String path = obj.getString("path");
		
     JSONArray enemies = obj.getJSONArray("enemies");
		
     saveState.setPlayerX((Double)playerX);
     saveState.setPlayerY((Double)playerY);
     saveState.setPlayerHealth(playerHealth);
     saveState.setPath(path);
		
     saveState.getEnemyX().clear();
     saveState.getEnemyY().clear();
     saveState.getEnemyHealth().clear();
     saveState.getEnemyId().clear();
     for(int i=0;i<nEnemies;i++){
     JSONObject enemy = enemies.getJSONObject(i);
     double enemyX = enemy.getDouble("enemyX");
     double enemyY = enemy.getDouble("enemyY");
     int enemyHealth = enemy.getInt("enemyHealth");
     int enemyId = enemy.getInt("enemyId");
     saveState.getEnemyX().add(enemyX);
     saveState.getEnemyY().add(enemyY);
     saveState.getEnemyHealth().add(enemyHealth);
     saveState.getEnemyId().add(enemyId);
     }  
     }
     */
    public void saveDataToXml() {
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

    public void loadDataFromXml() {
        try {
            JAXBContext context = JAXBContext.newInstance(Data.class);
            Unmarshaller un = context.createUnmarshaller();
            data = (Data) un.unmarshal(new File("res/Data/data.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void loadFromXml(String path) {
        try {
            JAXBContext context = JAXBContext.newInstance(SaveState.class);
            Unmarshaller un = context.createUnmarshaller();
            saveState = (SaveState) un.unmarshal(new File(path));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}
