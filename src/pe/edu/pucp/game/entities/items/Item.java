package pe.edu.pucp.game.entities.items;

import java.awt.Graphics;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import pe.edu.pucp.game.Game;
import pe.edu.pucp.game.Launcher;
import pe.edu.pucp.game.Sound;
import pe.edu.pucp.game.entities.Entity;
import pe.edu.pucp.game.gfx.Assets;
import pe.edu.pucp.game.states.GameState;
import pe.edu.pucp.game.states.MultiplayerState;
import pe.edu.pucp.game.threads.GotItemThread;

@SuppressWarnings("serial")
public class Item extends Entity {

    private int id;
    private String description;

    public Item(Game game, double x, double y) {
        super(game, x, y, DEFAULT_ENTITY_WIDTH, DEFAULT_ENTITY_HEIGHT);
        this.setDescription("A berry");
    }

    public Item(double x, double y) {
        super(x, y, DEFAULT_ENTITY_WIDTH, DEFAULT_ENTITY_HEIGHT);
        this.setDescription("A berry");
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        if (game.getGameState() != null) {
            if (playerIsNextTo() && game.getKeyManager().space == true) {
                Sound sounds = new Sound("/sounds/gotItem.au");
                sounds.playSoundOnce();
                //sounds.playSound();
                GotItemThread git = new GotItemThread(game, this);
                git.start();
                ((GameState) game.getGameState()).getPlayer().getItems().add(this);
                ((GameState) game.getGameState()).getItems().remove(this);
            }
        }
        else{
            if (playerIsNextTo(((MultiplayerState) game.getMultiplayerState()).getPlayer()) && game.getKeyManager().space == true) {
                Sound sounds = new Sound("/sounds/gotItem.au");
                sounds.playSoundOnce();
                //sounds.playSound();
                GotItemThread git = new GotItemThread(game, this);
                git.start();
                ((MultiplayerState) game.getMultiplayerState()).getPlayer().getItems().add(this);
                try {
                    ArrayList<Item> items = Launcher.proxy.getItems();
                    for(int i=0;i<items.size();i++){
                        if(this.getX()==items.get(i).getX() && this.getY()==items.get(i).getY())
                            items.remove(i);
                    }
                    Launcher.proxy.setItems(items);
                } catch (RemoteException ex) {
                    Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
        //POR TIPO DE ID
        g.drawImage(Assets.berry, (int) (x * DEFAULT_ENTITY_WIDTH - game.getGameCamera().getxOffset()),
                (int) (y * DEFAULT_ENTITY_HEIGHT - game.getGameCamera().getyOffset()), width, height, null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
