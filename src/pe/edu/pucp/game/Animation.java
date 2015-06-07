/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.edu.pucp.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.game.display.Display;
import pe.edu.pucp.game.gfx.Assets;

/**
 *
 * @author Alexis
 */
public class Animation implements Runnable{
    BufferedImage[] pictures = new BufferedImage[4];
    Display display;
    int totalPictures = 0;
    int current = 0;
    int pause = 500;

    public Animation(Display display){
        this.display=display;
       /* pictures[0]=Assets.juanaCombat;
        pictures[1]=Assets.angryJuana;
        pictures[2]=Assets.juanaCombat;
        pictures[3]=Assets.angryJuana;*/
    }
    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Graphics g= display.getCanvas().getBufferStrategy().getDrawGraphics();
        for(int i=0;i<4;i++){
            //for(int j=0;i<1000;j++)
            g.drawImage(pictures[i],20,150,100,100,null);
            Scanner in = new Scanner(System.in);
            String s = in.next();
            /*try {
                Thread.sleep(pause);
            } catch (InterruptedException ex) {
                Logger.getLogger(Animation.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        }
    }


}
