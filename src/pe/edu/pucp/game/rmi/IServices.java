/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.game.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import pe.edu.pucp.game.entities.creatures.Player;
import pe.edu.pucp.game.worlds.World;


public interface IServices extends Remote{
    public ArrayList<Player> getPlayers() throws RemoteException;
    public void setPlayerAtI(int i, Player p) throws RemoteException;
    public int getNPlayers() throws RemoteException;
    public void setNPlayers(int n) throws RemoteException;
    public World getWorld() throws RemoteException;
    public void addPlayer(Player p) throws RemoteException;
}
