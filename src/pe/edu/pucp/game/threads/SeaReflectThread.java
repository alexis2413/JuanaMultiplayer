/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.game.threads;

import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.game.Game;
import pe.edu.pucp.game.gfx.Assets;
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
            //refreshSea();
            try {
                Thread.sleep(pause);
            } catch (InterruptedException ex) {
                Logger.getLogger(SeaReflectThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean playerRefect() {
        double x = ((GameState) game.getGameState()).getPlayer().getX();
        double y = ((GameState) game.getGameState()).getPlayer().getY();
        World world = ((GameState) game.getGameState()).getWorld();
        int tile = Integer.parseInt("" + (world.getTiles()[(int) y + 1]).charAt((int) x));
        if (tile == 4) {
            return true;
        } else {
            return false;
        }
    }

    public boolean ReflectAt(int x, int y) {
        World world = ((GameState) game.getGameState()).getWorld();
        int tile = Integer.parseInt("" + (world.getTiles()[y + 1]).charAt(x));
        if (tile == 5) {
            double playerX = ((GameState) game.getGameState()).getPlayer().getX();
            double playerY = ((GameState) game.getGameState()).getPlayer().getY();
            if(x!=(int)playerX && y!=(int)playerY-1)
                return true;
        }
        return false;        
    }

    public void setReflect() {
        double x = ((GameState) game.getGameState()).getPlayer().getX();
        double y = ((GameState) game.getGameState()).getPlayer().getY();
        World world = ((GameState) game.getGameState()).getWorld();
        //int tile = Integer.parseInt("" + (world.getTiles()[(int) y + 1]).charAt((int) x));
        String row = world.getTiles()[(int) y + 1];
        String newRow = row.substring(0, (int) x) + '5' + row.substring((int) x + 1);
        String[] newTiles = world.getTiles();
        newTiles[(int) y + 1] = newRow;
        world.setTiles(newTiles);
        for (int k = 0; k < world.getTiles().length; k++) {
            System.out.println(world.getTiles()[k]);
        }

    }

    public void refreshSea() {
        World world = ((GameState) game.getGameState()).getWorld();
        boolean change = false;
        for (int i = 0; i < world.getTiles().length; i++) {
            String row = world.getTiles()[i];
            for (int j = 0; j < row.length(); j++) {
                if (row.charAt(j) == '5') {
                    if (!ReflectAt(j, i)) {
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
    }
}
