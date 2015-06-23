/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.game.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import javax.swing.JTextArea;
import pe.edu.pucp.game.entities.Entity;
import pe.edu.pucp.game.entities.creatures.NonPlayerCharacter;
import pe.edu.pucp.game.entities.creatures.Player;
import pe.edu.pucp.game.entities.creatures.enemies.Enemy;
import pe.edu.pucp.game.entities.items.Item;
import pe.edu.pucp.game.gfx.GameCamera;
import pe.edu.pucp.game.worlds.World;

/**
 *
 * @author alulab14
 */
public class DemoServerRMIImpl extends UnicastRemoteObject implements IServices {
    private ArrayList<Player> players = new ArrayList<Player>();
    private int nEnemies;
    private int nPlayers=0;
    private int width=400;
    private int height=400;
    private GameCamera gameCamera = new GameCamera(width, height, 0, 0);
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<Entity> objects = new ArrayList<Entity>();
    private ArrayList<NonPlayerCharacter> npcs = new ArrayList<NonPlayerCharacter>();
    private ArrayList<Item> items = new ArrayList<Item>();
    private World world=new World("res/worlds/world1.xml", gameCamera, enemies, objects, npcs, items);
    private boolean paused;
    private boolean multiplayerStarted;
    
    //Chat grupal
    JTextArea chatText = new JTextArea();
    
    public DemoServerRMIImpl() throws RemoteException {
    }

    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind("MyRMIServer", new DemoServerRMIImpl());
            System.out.println("Servidor iniciado :)");
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Player> getPlayers() throws RemoteException {
        return players;
    }
    
    
    @Override
    public int getNPlayers() throws RemoteException {
        return nPlayers;
    }

    @Override
    public void setNPlayers(int n) throws RemoteException {
        nPlayers=n;
    }

    @Override
    public World getWorld() throws RemoteException {
        return world;
    }

    @Override
    public void addPlayer(Player p) throws RemoteException {
        players.add(p);
        nPlayers++;
    }

    @Override
    public void setPlayerAtI(int i, Player p) throws RemoteException {
        players.set(i, p);
    }  
    
    @Override
    public boolean isPaused(){
        return paused;
    }
    
    @Override
    public void setPause(boolean p){
        paused=p;
    }
    
    @Override
    public boolean multiplayerStarted(){
        return multiplayerStarted;
    }
    
    @Override
    public void setMultiplayer(boolean m){
        multiplayerStarted=m;
    }
    
    @Override
    public JTextArea getChatText(){
        return chatText;
    }
    
    @Override
    public void addChatText(String s){
        chatText.append(s);
    }
    
    @Override
    public void setChatText(JTextArea c){
        this.chatText=c;
    }
}
