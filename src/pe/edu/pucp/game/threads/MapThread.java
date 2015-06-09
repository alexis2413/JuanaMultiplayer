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
public class MapThread extends Thread{
    public boolean flag=true;
    public int delay=0;
    public int pause=100;

    public MapThread(){
        super();
    }
    @Override
    public void run(){
        while(true){
                if(flag){
                    Assets.rock=Assets.door;
                    flag=false;
                    try {
                        Thread.sleep(pause);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MapThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    Assets.rock=Assets.boulder;
                    flag=true;
                    try {
                        Thread.sleep(pause);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MapThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
 
    }
}

