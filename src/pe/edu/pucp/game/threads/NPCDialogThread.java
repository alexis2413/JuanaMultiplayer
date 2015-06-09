package pe.edu.pucp.game.threads;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.game.Game;
import pe.edu.pucp.game.entities.creatures.NonPlayerCharacter;
import pe.edu.pucp.game.states.GameState;

public class NPCDialogThread extends Thread {

    public Game game;
    public NonPlayerCharacter npc;
    public int pause = 1000;
    public static int WIDTH = 50;
    public Rectangle rectangle = new Rectangle(20, 300, 300, 80);

    public NPCDialogThread(NonPlayerCharacter npc, Game game) {
        super();
        this.game = game;
        this.npc = npc;
    }

    public void run() {
        //System.out.print(game.getKeyManager().space);
        for (int i = 0; i < npc.getDialog().size(); i++) {
            render(npc.getDialog().get(i));
            try {
                Thread.sleep(pause);
            } catch (InterruptedException ex) {
                Logger.getLogger(NPCDialogThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        game.setDialogue(false);
    }

    public void render(String dialog) {
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
        g.drawString(dialog, rectangle.x + 19, rectangle.y + 30);
        bs.show();
        bs.getDrawGraphics().dispose();
    }
}
