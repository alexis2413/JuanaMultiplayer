/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.game.threads;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.game.Game;
import pe.edu.pucp.game.states.GameState;

/**
 *
 * @author alulab14
 */
public class TimeThread extends Thread {

    Game game;
    int pause = 1000;
    public Rectangle rectangle = new Rectangle(20, 300, 300, 80);

    public TimeThread(Game game) {
        super();
        this.game = game;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(pause);
            } catch (InterruptedException ex) {
                Logger.getLogger(TimeThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            ((GameState) game.getGameState()).timeLeft--;
            if(((GameState) game.getGameState()).levelComplete==true){
                //game.setDialogue(true);
                render("You Won");
                try {
                    Thread.sleep(pause);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TimeThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                ((GameState) game.getGameState()).levelComplete=false;
                game.setDialogue(false);
            }
            
            if (((GameState) game.getGameState()).timeLeft == 0) {
                game.setDialogueItem(true);
                render("Time over, you Lost");
                try {
                    Thread.sleep(pause);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TimeThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                game.getDisplay().getFrame().dispatchEvent(new WindowEvent(game.getDisplay().getFrame(), WindowEvent.WINDOW_CLOSING));
            }
        }
    }

    public void render(String description) {
        BufferStrategy bs = game.getDisplay().getCanvas().getBufferStrategy();
        if (bs == null) {
            game.getDisplay().getCanvas().createBufferStrategy(3);
            return;
        }

        Graphics g = game.getDisplay().getCanvas().getBufferStrategy().getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        bs.getDrawGraphics().clearRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        Font fnt1 = new Font("arial", Font.BOLD, 25);
        g.setFont(fnt1);
        g2d.draw(rectangle);
        g.drawString(description, rectangle.x + 19, rectangle.y + 30);
        bs.show();
        bs.getDrawGraphics().dispose();
    }
}
