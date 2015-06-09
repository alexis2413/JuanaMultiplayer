package pe.edu.pucp.game.entities.creatures;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import pe.edu.pucp.game.Game;
import pe.edu.pucp.game.gfx.Assets;
import pe.edu.pucp.game.states.GameState;
import pe.edu.pucp.game.threads.NPCDialogThread;

@SuppressWarnings("serial")
public class NonPlayerCharacter extends Creature {

    ArrayList<String> dialog = new ArrayList<String>();

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
        NPCDialogThread npct = new NPCDialogThread(this,game);
        npct.start();
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
        }
    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
        g.drawImage(Assets.npc1, (int) (x * width - game.getGameCamera().getxOffset()),
                (int) (y * height - game.getGameCamera().getyOffset()), width, height, null);
    }

    public ArrayList<String> getDialog(){return dialog;}
    public void setDialog(ArrayList<String> dialog){this.dialog=dialog;}
}
