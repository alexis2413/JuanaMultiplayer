/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.game.threads;

import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.game.Game;
import pe.edu.pucp.game.entities.creatures.NonPlayerCharacter;
import pe.edu.pucp.game.states.GameState;

/**
 *
 * @author alulab14
 */
public class NPCDialogThread extends Thread {

    public Game game;
    public NonPlayerCharacter npc;
    public int pause=1000;

    public NPCDialogThread(NonPlayerCharacter npc, Game game) {
        super();
        this.game = game;
        this.npc = npc;
    }

    public void run() {
        //System.out.print(game.getKeyManager().space);
        for (int i = 0; i < npc.getDialog().size(); i++) {
            System.out.println(npc.getDialog().get(i));

            try {
                Thread.sleep(pause);
            } catch (InterruptedException ex) {
                Logger.getLogger(NPCDialogThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //System.out.print(game.getKeyManager().space);
        ((GameState) game.getGameState()).setDialogue(false);
    }
}
