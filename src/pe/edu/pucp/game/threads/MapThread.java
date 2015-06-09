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

/**
 *
 * @author Alexis
 */
public class MapThread extends Thread {

    public int delay = 0;
    public int pause = 300;

    public MapThread() {
        super();
    }

    @Override
    public void run() {
        while (true) {
            delay++;
            if (delay == 1) {
                Assets.sea1 = Assets.sea1;

                try {
                    Thread.sleep(pause);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MapThread.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (delay == 2) {
                Assets.sea1 = Assets.sea2;
                try {
                    Thread.sleep(pause);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MapThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (delay == 3) {
                Assets.sea1 = Assets.sea3;
                try {
                    Thread.sleep(pause);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MapThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (delay == 4) {
                Assets.sea1 = Assets.sea4;
                try {
                    Thread.sleep(pause);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MapThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                delay = 0;
            }

        }
    }
}
