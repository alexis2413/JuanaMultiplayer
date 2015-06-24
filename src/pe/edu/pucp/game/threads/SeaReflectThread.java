/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.game.threads;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.game.Game;
import pe.edu.pucp.game.Launcher;
import pe.edu.pucp.game.entities.creatures.Player;
import pe.edu.pucp.game.gfx.Assets;
import static pe.edu.pucp.game.gfx.Assets.sea1Juana;
import pe.edu.pucp.game.gfx.ImageLoader;
import pe.edu.pucp.game.states.GameState;
import pe.edu.pucp.game.worlds.World;

/**
 *
 * @author alulab14
 */
public class SeaReflectThread extends Thread {

    Game game;
    int pause = 100;

    public SeaReflectThread(Game game) {
        super();
        this.game = game;
    }

    public void run() {
        while (true) {
            if (playerRefect()) {
                setReflect();
            }
            refreshSea();
            try {
                Thread.sleep(pause);
            } catch (InterruptedException ex) {
                Logger.getLogger(SeaReflectThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean playerRefect() {
        if (game.getGameState() != null) {
            double x = ((GameState) game.getGameState()).getPlayer().getX();
            double y = ((GameState) game.getGameState()).getPlayer().getY();
            World world = ((GameState) game.getGameState()).getWorld();
            int tile = Integer.parseInt("" + (world.getTiles()[(int) y + 1]).charAt((int) x));
            if (tile == 4) {
                return true;
            } else {
                return false;
            }
        } else {
            try {
                for (int i = 0; i < Launcher.proxy.getPlayers().size(); i++) {
                    double x = Launcher.proxy.getPlayers().get(i).getX();
                    double y = Launcher.proxy.getPlayers().get(i).getY();
                    World world = Launcher.proxy.getWorld();
                    int tile = Integer.parseInt("" + (world.getTiles()[(int) y + 1]).charAt((int) x));
                    if (tile == 4) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(SeaReflectThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean ReflectAt(int x, int y) {
        if (game.getGameState() != null) {
            World world = ((GameState) game.getGameState()).getWorld();
            int tile = Integer.parseInt("" + (world.getTiles()[y + 1]).charAt(x));
            if (tile == 5) {
                double playerX = ((GameState) game.getGameState()).getPlayer().getX();
                double playerY = ((GameState) game.getGameState()).getPlayer().getY();
                if (x == (int) playerX && y == (int) playerY) {
                    return true;
                }

            }
            return false;
        } else {
            try {
                World world = Launcher.proxy.getWorld();
                int tile = Integer.parseInt("" + (world.getTiles()[y + 1]).charAt(x));
                if (tile == 5) {
                    for (int i = 0; i < Launcher.proxy.getPlayers().size(); i++) {
                        double playerX = Launcher.proxy.getPlayers().get(i).getX();
                        double playerY = Launcher.proxy.getPlayers().get(i).getY();
                        if (x == (int) playerX && y == (int) playerY) {
                            return true;
                        }
                    }
                }
                return false;
            } catch (RemoteException ex) {
                Logger.getLogger(SeaReflectThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public void setReflect() {
        if (game.getGameState() != null) {
            double x = ((GameState) game.getGameState()).getPlayer().getX();
            double y = ((GameState) game.getGameState()).getPlayer().getY();
            World world = ((GameState) game.getGameState()).getWorld();
            //int tile = Integer.parseInt("" + (world.getTiles()[(int) y + 1]).charAt((int) x));
            String row = world.getTiles()[(int) y + 1];
            String newRow = row.substring(0, (int) x) + '5' + row.substring((int) x + 1);
            String[] newTiles = world.getTiles();
            newTiles[(int) y + 1] = newRow;
            world.setTiles(newTiles);
            if (((GameState) game.getGameState()).getPlayer().getDirection() == 1) {
                Assets.sea1Juana = sea1Juana = ImageLoader.loadImage("/textures/sea1Juana.png");
            } else if (((GameState) game.getGameState()).getPlayer().getDirection() == 2) {

                Assets.sea1Juana = sea1Juana = ImageLoader.loadImage("/textures/sea1Juana.png");
            } else if (((GameState) game.getGameState()).getPlayer().getDirection() == 3) {
                Assets.sea1Juana = Assets.sea2Juana;
            } else if (((GameState) game.getGameState()).getPlayer().getDirection() == 4) {
                Assets.sea1Juana = Assets.sea3Juana;
            }
        } else {
            try {
                for (int i = 0; i < Launcher.proxy.getPlayers().size(); i++) {
                    Player player = Launcher.proxy.getPlayers().get(i);
                    double x = player.getX();
                    double y = player.getY();
                    World world = Launcher.proxy.getWorld();
                    //int tile = Integer.parseInt("" + (world.getTiles()[(int) y + 1]).charAt((int) x));
                    String row = world.getTiles()[(int) y + 1];
                    String newRow = row.substring(0, (int) x) + '5' + row.substring((int) x + 1);
                    String[] newTiles = world.getTiles();
                    newTiles[(int) y + 1] = newRow;
                    world.setTiles(newTiles);
                    Launcher.proxy.setWorld(world);
                    if (player.getDirection() == 1) {
                        Assets.sea1Juana = sea1Juana = ImageLoader.loadImage("/textures/sea1Juana.png");
                    } else if (player.getDirection() == 2) {
                        Assets.sea1Juana = sea1Juana = ImageLoader.loadImage("/textures/sea1Juana.png");
                    } else if (player.getDirection() == 3) {
                        Assets.sea1Juana = Assets.sea2Juana;
                    } else if (player.getDirection() == 4) {
                        Assets.sea1Juana = Assets.sea3Juana;
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(SeaReflectThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void refreshSea() {
        World world;
        if (game.getGameState() != null) {
            world = ((GameState) game.getGameState()).getWorld();
        } else {
            try {
                world = Launcher.proxy.getWorld();
            } catch (RemoteException ex) {
                Logger.getLogger(SeaReflectThread.class.getName()).log(Level.SEVERE, null, ex);
                world = new World();
            }
        }
        boolean change = false;
        for (int i = 0; i < world.getTiles().length; i++) {
            String row = world.getTiles()[i];
            for (int j = 0; j < row.length(); j++) {
                if (row.charAt(j) == '5') {
                    if (!ReflectAt(j, i - 1)) {
                        String newRow = row.substring(0, (int) j) + '4' + row.substring((int) j + 1);
                        row = newRow;
                        change = true;
                    }
                }
            }
            if (change) {
                String[] newTiles = world.getTiles();
                newTiles[i] = row;
                world.setTiles(newTiles);
            }
        }
        try {
            Launcher.proxy.setWorld(world);
        } catch (RemoteException ex) {
            Logger.getLogger(SeaReflectThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
