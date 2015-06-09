package pe.edu.pucp.game.entities.creatures;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import pe.edu.pucp.game.Game;
import pe.edu.pucp.game.gfx.Assets;
import pe.edu.pucp.game.states.GameState;

@SuppressWarnings("serial")
public class NonPlayerCharacter extends Creature {

    ArrayList<ArrayList<String>> dialog = new ArrayList<ArrayList<String>>();

    public NonPlayerCharacter(Game game, double x, double y) {
        super(game, x, y, DEFAULT_ENTITY_WIDTH, DEFAULT_ENTITY_HEIGHT);
        this.setDescription("NPC just walking around");
    }

    public NonPlayerCharacter(double x, double y) {
        super(x, y, DEFAULT_ENTITY_WIDTH, DEFAULT_ENTITY_HEIGHT);
        this.setDescription("NPC just walking around");
    }

    public NonPlayerCharacter() {
        this.setDescription("NPC just walking around");
    }

    public void beginDialog() {
        System.out.println("Hola noob");
        /*try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(NonPlayerCharacter.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        System.out.print(game.getKeyManager().space);
        while (game.getKeyManager().space==true) {            
            game.getKeyManager().tick();
            //game.getKeyManager().space = game.getKeyManager().getKeys()[KeyEvent.VK_SPACE];
        }
        System.out.print(game.getKeyManager().space);
        ((GameState)game.getGameState()).setDialogue(false);
        System.out.println("Adios noob");
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        if (playerIsNextTo() && game.getKeyManager().space) {
            ((GameState)game.getGameState()).setDialogue(true);
            beginDialog();
        }

        moveCounter++;
        if (moveCounter == delay) {
            randomMove();
            move();
            moveCounter = 0;
        } else {
            //beginDialog();
        }

    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
        g.drawImage(Assets.npc1, (int) (x * width - game.getGameCamera().getxOffset()),
                (int) (y * height - game.getGameCamera().getyOffset()), width, height, null);
    }

}
