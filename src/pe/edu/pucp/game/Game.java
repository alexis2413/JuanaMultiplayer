package pe.edu.pucp.game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

import pe.edu.pucp.game.display.Display;
import pe.edu.pucp.game.gfx.Assets;
import pe.edu.pucp.game.gfx.GameCamera;
import pe.edu.pucp.game.input.KeyManager;
import pe.edu.pucp.game.input.MouseManager;
import pe.edu.pucp.game.states.ChooseCharacterState;
//import pe.edu.pucp.game.states.CombatState;
import pe.edu.pucp.game.states.GameState;
import pe.edu.pucp.game.states.HelpState;
import pe.edu.pucp.game.states.LoadGameState;
import pe.edu.pucp.game.states.LobbyState;
import pe.edu.pucp.game.states.MenuState;
import pe.edu.pucp.game.states.MultiplayerState;
import pe.edu.pucp.game.states.OptionState;
import pe.edu.pucp.game.states.SaveGameState;
import pe.edu.pucp.game.states.State;
import pe.edu.pucp.game.threads.EnemyMoveThread;

@SuppressWarnings("serial")
public class Game implements Runnable, Serializable {
//public class Game implements Serializable{

    private transient Display display;
    private int width, height;
    public String title;

    public boolean hasStarted = false;
    private boolean running = false;
    private transient Thread thread;
    private int numberPlayer;

    private BufferStrategy bs;
    private Graphics g;

    //States
    private State gameState = null;
    private State menuState;
    private State helpState;
    private State optionState;
    //private State combatState;
    private State chooseCharacterState;
    private State loadGameState;
    private State saveGameState;
    private State multiplayerState;
    private State lobbyState;
    //Input
    private KeyManager keyManager;
    private MouseManager mouseManager;
    //Camera
    private GameCamera gameCamera;

    private boolean dialogue = false;
    private boolean item = false;

    public Game(String title, int width, int height, int numberPlayer) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();

        this.numberPlayer = numberPlayer;
    }

    public Game() {
    }

    private void init() throws RemoteException {//se ejecuta cada vez que se llama a run
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getCanvas().addMouseListener(mouseManager);

        Assets.init(numberPlayer);

        gameCamera = new GameCamera(width, height, 0, 0);
        //gameState = new GameState(this);
        menuState = new MenuState(this);
        helpState = new HelpState(this);        
        //combatState=new CombatState(this);
        chooseCharacterState = new ChooseCharacterState(this);       
        //multiplayerState = new MultiplayerState(this);        
        State.setState(menuState);
        
    }

    private void tick() {
        //if (dialogue == false && item == false) {
            keyManager.tick();
            mouseManager.tick();
            if (State.getState() != null) {
                State.getState().tick();
            }
        //}
    }

    private void render() {
        //if (dialogue == false && item == false) {
            bs = display.getCanvas().getBufferStrategy();
            if (bs == null) {
                display.getCanvas().createBufferStrategy(3);
                return;
            }
            //Clear Screen	
            bs.getDrawGraphics().clearRect(0, 0, width, height);

            //Draw here
            if (State.getState() != null) {
                State.getState().render(display);
            }

            //End drawing
            bs.show();
            bs.getDrawGraphics().dispose();
        //}
    }

    public void run() {//se llama cada vez que se inicia un thread
        
        try {
            init();
        } catch (RemoteException ex) {
            System.out.println("Excepcion rara");
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        int fps = 13;
        double timePerTick = 1000000000 / fps;//El tiempo maximo por tick par alcanzar 60fps
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();//retorna la cantidad en nanonsegundos en la que corre el CPU
        long timer = 0;
        //int ticks=0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                //ticks++;
                delta--;
            }
            if (timer >= 1000000000) {
                //System.out.println("Ticks and Frames: "+ticks);
                //ticks=0;
                timer = 0;
            }
        }

        stop();
    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public BufferStrategy getBs() {
        return bs;
    }

    public void setBs(BufferStrategy bs) {
        this.bs = bs;
    }

    public Graphics getG() {
        return g;
    }

    public void setG(Graphics g) {
        this.g = g;
    }

    public State getGameState() {
        return gameState;
    }

    public void setGameState(State gameState) {
        this.gameState = gameState;
    }

    public State getMenuState() {
        return menuState;
    }

    public void setMenuState(State menuState) {
        this.menuState = menuState;
    }

    public void setKeyManager(KeyManager keyManager) {
        this.keyManager = keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public void setMouseManager(MouseManager mouseManager) {
        this.mouseManager = mouseManager;
    }

    public State getHelpState() {
        return helpState;
    }

    public void setHelpState(State helpState) {
        this.helpState = helpState;
    }

    public int getNumberPlayer() {
        return numberPlayer;
    }

    public void setNumberPlayer(int numberPlayer) {
        this.numberPlayer = numberPlayer;
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public State getChooseCharacterState() {
        return chooseCharacterState;
    }

    public void setChooseCharacterState(State chooseCharacter) {
        this.chooseCharacterState = chooseCharacter;
    }

    public State getLoadGameState() {
        return loadGameState;
    }

    public void setLoadGameState(State loadGameState) {
        this.loadGameState = loadGameState;
    }

    public State getSaveGameState() {
        return saveGameState;
    }

    public void setSaveGameState(State saveGameState) {
        this.saveGameState = saveGameState;
    }
    //public State getCombatState() {return combatState;}
    //public void setCombatState(State combatState) {this.combatState = combatState;}

    public State getOptionState() {
        return optionState;
    }

    public void setOptionState(State optionState) {
        this.optionState = optionState;
    }

    public boolean getDialogue() {
        return dialogue;
    }

    public void setDialogue(boolean dialogue) {
        this.dialogue = dialogue;
    }
    
    public boolean getDialogueItem() {
        return item;
    }

    public void setDialogueItem(boolean item) {
        this.item = item;
    }

    public State getMultiplayerState(){
        return multiplayerState;
    }
    
    public void setMultiplayerState(State mps){
        this.multiplayerState=mps;
    }
    
    public State getLobbyState(){
        return lobbyState;
    }
    
    public void setLobbyState(State ls){
        this.lobbyState=ls;
    }
}
