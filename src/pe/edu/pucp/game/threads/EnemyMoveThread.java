/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.game.threads;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.game.Game;
import pe.edu.pucp.game.entities.creatures.Creature;
import pe.edu.pucp.game.states.GameState;

/**
 *
 * @author inf250
 */
public class EnemyMoveThread extends Thread {

    Random rand = new Random();
    int xMove, yMove, pause = 500;
    Game game;
    ArrayList<Creature> enemyList = new ArrayList<Creature>();
    ArrayList<Creature> npcList = new ArrayList<Creature>();

    public EnemyMoveThread(Game game) {
        super();
        this.game = game;
        if (((GameState) game.getGameState()).getEnemies() == null) {
            System.out.println("nulo");
        }
        for (int i = 0; i < ((GameState) game.getGameState()).getEnemies().size(); i++) {
            enemyList.add(((GameState) game.getGameState()).getEnemies().get(i));
        }
        for (int i = 0; i < ((GameState) game.getGameState()).getNpcs().size(); i++) {
            npcList.add(((GameState) game.getGameState()).getNpcs().get(i));
        }
    }

    @Override
    public void run() {
        while (true) {
            if (enemyList.size() == ((GameState) game.getGameState()).getEnemies().size()) {
                for (int i = 0; i < enemyList.size(); i++) {
                    move(enemyList.get(i), i);
                }
            } else {
                enemyList = new ArrayList<>();
                for (int i = 0; i < ((GameState) game.getGameState()).getEnemies().size(); i++) {
                    enemyList.add(((GameState) game.getGameState()).getEnemies().get(i));
                }
            }
            if (npcList.size() == ((GameState) game.getGameState()).getNpcs().size()) {
                for (int i = 0; i < npcList.size(); i++) {
                    move(npcList.get(i), i);
                }
            } else {
                npcList = new ArrayList<>();
                for (int i = 0; i < ((GameState) game.getGameState()).getNpcs().size(); i++) {
                    npcList.add(((GameState) game.getGameState()).getNpcs().get(i));
                }
            }
            try {
                Thread.sleep(pause);
            } catch (InterruptedException ex) {
                Logger.getLogger(EnemyMoveThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
    public void move(Creature c, int pos) {

        int prob = rand.nextInt(100);
        if (prob <= 50) {
            randomMove();
            if (c.isValidMove(xMove, yMove)) {
                c.setX(c.getX() + xMove);
                c.setY(c.getY() + yMove);
            }
        }
    }

    public void randomMove() {
        xMove = rand.nextInt(3) - 1;
        yMove = rand.nextInt(3) - 1;
    }

}
