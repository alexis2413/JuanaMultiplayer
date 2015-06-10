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
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.game.Game;
import pe.edu.pucp.game.entities.items.Item;

/**
 *
 * @author alulab14
 */
public class GotItemThread extends Thread {

    Item item;
    Game game;
    public Rectangle rectangle = new Rectangle(20, 300, 300, 80);
    int pause=1000;

    public GotItemThread(Game game, Item item) {
        super();
        this.item = item;
        this.game = game;
    }

    public void run() {
        game.setDialogueItem(true);
        render(item.getDescription());
        try {
            Thread.sleep(pause);
        } catch (InterruptedException ex) {
            Logger.getLogger(GotItemThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        game.setDialogueItem(false);
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
        Font fnt1 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt1);
        g2d.draw(rectangle);
        g.drawString("You got a "+description, rectangle.x + 19, rectangle.y + 30);
        bs.show();
        bs.getDrawGraphics().dispose();
    }
}
