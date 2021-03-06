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
    private World world;
    private boolean paused;
    private boolean multiplayerStarted;
    private boolean gameEnded=false;
    private int idWinner;
    
    //Chat grupal
    JTextArea chatText = new JTextArea();
    
    public DemoServerRMIImpl() throws RemoteException {
        world=new World("res/worlds/world4.xml", gameCamera, enemies, objects, npcs, items);
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
    public boolean isPaused() throws RemoteException{
        return paused;
    }
    
    @Override
    public void setPause(boolean p) throws RemoteException{
        paused=p;
    }
    
    @Override
    public boolean multiplayerStarted() throws RemoteException{
        return multiplayerStarted;
    }
    
    @Override
    public void setMultiplayer(boolean m) throws RemoteException{
        multiplayerStarted=m;
    }
    
    @Override
    public JTextArea getChatText() throws RemoteException{
        return chatText;
    }
    
    @Override
    public void addChatText(String s) throws RemoteException{
        chatText.append(s);
    }
    
    @Override
    public void setChatText(JTextArea c) throws RemoteException{
        this.chatText=c;
    
    }
    @Override
    public void deletePlayer(int i) throws RemoteException{
        players.set(i, null);
    }

    @Override
    public ArrayList<Enemy> getEnemies() throws RemoteException {
        return enemies;
    }

    @Override
    public void setEnemies(ArrayList<Enemy> enemies) throws RemoteException {
        this.enemies=enemies;
    }

    @Override
    public ArrayList<Entity> getObjects() throws RemoteException {
        return objects;
    }

    @Override
    public void setObjects(ArrayList<Entity> objects) throws RemoteException {
        this.objects=objects;
    }

    @Override
    public ArrayList<NonPlayerCharacter> getNpcs() throws RemoteException {
        return npcs;
    }

    @Override
    public void setNpcs(ArrayList<NonPlayerCharacter> npcs) throws RemoteException {
        this.npcs=npcs;
    }

    @Override
    public ArrayList<Item> getItems() throws RemoteException {
        return items;
    }

    @Override
    public void setItems(ArrayList<Item> items) throws RemoteException {
        this.items=items;
    }

    @Override
    public boolean gameEnded() throws RemoteException {
        return gameEnded;
    }

    @Override
    public void setGameEnded(boolean g) throws RemoteException {
        gameEnded=g;
    }

    @Override
    public int getIdWinner() throws RemoteException {
        return idWinner;
    }

    @Override
    public void setIdWinner(int id) throws RemoteException {
        this.idWinner=id;
    }

    @Override
    public void setWorld(World w) throws RemoteException {
        this.world=w;
    }
    
    
}
