/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.game.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.swing.JTextArea;
import pe.edu.pucp.game.entities.Entity;
import pe.edu.pucp.game.entities.creatures.NonPlayerCharacter;
import pe.edu.pucp.game.entities.creatures.Player;
import pe.edu.pucp.game.entities.creatures.enemies.Enemy;
import pe.edu.pucp.game.entities.items.Item;
import pe.edu.pucp.game.worlds.World;

public interface IServices extends Remote {

    public ArrayList<Player> getPlayers() throws RemoteException;
    public void setPlayerAtI(int i, Player p) throws RemoteException;
    public int getNPlayers() throws RemoteException;
    public void setNPlayers(int n) throws RemoteException;
    public World getWorld() throws RemoteException;
    public void setWorld(World w) throws RemoteException;
    public void addPlayer(Player p) throws RemoteException;
    public boolean isPaused() throws RemoteException;
    public void setPause(boolean p) throws RemoteException;
    public boolean multiplayerStarted() throws RemoteException;
    public void setMultiplayer(boolean m) throws RemoteException;
    public JTextArea getChatText()throws RemoteException;
    public void addChatText(String s) throws RemoteException;
    public void setChatText(JTextArea c) throws RemoteException;
    public void deletePlayer(int i) throws RemoteException;    
    public ArrayList<Enemy> getEnemies() throws RemoteException;
    public void setEnemies(ArrayList<Enemy> enemies) throws RemoteException;    
    public ArrayList<Entity> getObjects() throws RemoteException;
    public void setObjects(ArrayList<Entity> objects) throws RemoteException;    
    public ArrayList<NonPlayerCharacter> getNpcs() throws RemoteException;
    public void setNpcs(ArrayList<NonPlayerCharacter> npcs) throws RemoteException;
    public ArrayList<Item> getItems() throws RemoteException;
    public void setItems(ArrayList<Item> items) throws RemoteException;
    public boolean gameEnded() throws RemoteException;
    public void setGameEnded(boolean g) throws RemoteException;
    public int getIdWinner() throws RemoteException;
    public void setIdWinner(int id) throws RemoteException;
}
