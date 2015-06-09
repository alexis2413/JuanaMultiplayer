package pe.edu.pucp.game.entities.creatures;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

import pe.edu.pucp.game.Game;
import pe.edu.pucp.game.states.GameState;
import pe.edu.pucp.game.states.State;
import pe.edu.pucp.game.attacks.Attack;
import pe.edu.pucp.game.entities.items.Item;
import pe.edu.pucp.game.gfx.Assets;
import pe.edu.pucp.game.tile.DoorTile;

@SuppressWarnings("serial")
public class Player extends Creature implements Serializable {

    int position = 1;
    int contRight = 0, contLeft = 0, contUp = 0, contDown = 0;
    private boolean isDialoging = false;

    ArrayList<Attack> attacks = new ArrayList<Attack>();
    ArrayList<Item> items = new ArrayList<Item>();

    public Player(Game game, float x, float y) {
        super(game, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        this.setDescription("Juana, a woman who wants to cure her child from a disease");
    }

    public Player() {
        this.setDescription("Juana, a woman who wants to cure her child from a disease");
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        getInput();
        if (!isDialoging) {
            if (isValidMove()) {
                x += xMove;
                y += yMove;
            }
        }
        game.getGameCamera().centerOnEntity(this);
        if (((GameState) game.getGameState()).getWorld().getTile((int) x, (int) (y)).getId() == 2) {
            ((DoorTile) ((GameState) game.getGameState()).getWorld().getTile((int) x, (int) (y))).openDoor();
        }
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;
        if (game.getKeyManager().up) {
            if (!(game.getKeyManager().up && game.getKeyManager().right)
                    && !(game.getKeyManager().up && game.getKeyManager().left)) {
                if (getDirection() == 1) {
                    yMove = -speed;
                    position = 10;
                    contUp++;
                    if (contUp % 3 == 0) {
                        position = 10;
                    }
                    if (contUp % 3 == 1) {
                        position = 11;
                    }
                    if (contUp % 3 == 2) {
                        position = 12;
                    }
                    contDown = contLeft = contRight = 0;
                } else {
                    setDirection(1);
                    position = 10;
                }
            }
            return;
        }

        if (game.getKeyManager().down) {
            if (!(game.getKeyManager().down && game.getKeyManager().right)
                    && !(game.getKeyManager().down && game.getKeyManager().left)) {
                if (getDirection() == 2) {
                    yMove = speed;
                    position = 2;
                    contDown++;
                    if (contDown % 3 == 0) {
                        position = 1;
                    }
                    if (contDown % 3 == 1) {
                        position = 2;
                    }
                    if (contDown % 3 == 2) {
                        position = 3;
                    }
                    contUp = contLeft = contRight = 0;
                } else {
                    setDirection(2);
                    position = 2;
                }
            }
            return;
        }

        if (game.getKeyManager().left) {
            if (!(game.getKeyManager().left && game.getKeyManager().up)
                    && !(game.getKeyManager().left && game.getKeyManager().down)) {
                if (getDirection() == 3) {
                    xMove = -speed;
                    position = 4;
                    contLeft++;
                    if (contLeft % 3 == 0) {
                        position = 4;
                    }
                    if (contLeft % 3 == 1) {
                        position = 5;
                    }
                    if (contLeft % 3 == 2) {
                        position = 6;
                    }
                    contDown = contUp = contRight = 0;
                } else {
                    setDirection(3);
                    position = 4;
                }
            }
            return;
        }

        if (game.getKeyManager().right) {
            if (!(game.getKeyManager().right && game.getKeyManager().up)
                    && !(game.getKeyManager().right && game.getKeyManager().down)) {
                if (getDirection() == 4) {
                    xMove = speed;
                    position = 9;
                    contRight++;
                    if (contRight % 3 == 0) {
                        position = 7;
                    }
                    if (contRight % 3 == 1) {
                        position = 8;
                    }
                    if (contRight % 3 == 2) {
                        position = 9;
                    }
                    contDown = contLeft = contUp = 0;
                } else {
                    setDirection(4);
                    position = 9;
                }
            }
            return;
        }

        if (game.getKeyManager().enter || game.getKeyManager().p) {
            State.setState(game.getOptionState());
        }

    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
        if (position == 1) {
            g.drawImage(Assets.player1, (int) (x * DEFAULT_CREATURE_WIDTH - game.getGameCamera().getxOffset()),
                    (int) (y * DEFAULT_CREATURE_HEIGHT - game.getGameCamera().getyOffset()), width, height, null);
        }

        if (position == 2) {
            g.drawImage(Assets.player2, (int) (x * DEFAULT_CREATURE_WIDTH - game.getGameCamera().getxOffset()),
                    (int) (y * DEFAULT_CREATURE_HEIGHT - game.getGameCamera().getyOffset()), width, height, null);
        }
        if (position == 3) {
            g.drawImage(Assets.player3, (int) (x * DEFAULT_CREATURE_WIDTH - game.getGameCamera().getxOffset()),
                    (int) (y * DEFAULT_CREATURE_HEIGHT - game.getGameCamera().getyOffset()), width, height, null);
        }
        if (position == 4) {
            g.drawImage(Assets.player4, (int) (x * DEFAULT_CREATURE_WIDTH - game.getGameCamera().getxOffset()),
                    (int) (y * DEFAULT_CREATURE_HEIGHT - game.getGameCamera().getyOffset()), width, height, null);
        }
        if (position == 5) {
            g.drawImage(Assets.player5, (int) (x * DEFAULT_CREATURE_WIDTH - game.getGameCamera().getxOffset()),
                    (int) (y * DEFAULT_CREATURE_HEIGHT - game.getGameCamera().getyOffset()), width, height, null);
        }
        if (position == 6) {
            g.drawImage(Assets.player6, (int) (x * DEFAULT_CREATURE_WIDTH - game.getGameCamera().getxOffset()),
                    (int) (y * DEFAULT_CREATURE_HEIGHT - game.getGameCamera().getyOffset()), width, height, null);
        }
        if (position == 7) {
            g.drawImage(Assets.player7, (int) (x * DEFAULT_CREATURE_WIDTH - game.getGameCamera().getxOffset()),
                    (int) (y * DEFAULT_CREATURE_HEIGHT - game.getGameCamera().getyOffset()), width, height, null);
        }
        if (position == 8) {
            g.drawImage(Assets.player8, (int) (x * DEFAULT_CREATURE_WIDTH - game.getGameCamera().getxOffset()),
                    (int) (y * DEFAULT_CREATURE_HEIGHT - game.getGameCamera().getyOffset()), width, height, null);
        }
        if (position == 9) {
            g.drawImage(Assets.player9, (int) (x * DEFAULT_CREATURE_WIDTH - game.getGameCamera().getxOffset()),
                    (int) (y * DEFAULT_CREATURE_HEIGHT - game.getGameCamera().getyOffset()), width, height, null);
        }
        if (position == 10) {
            g.drawImage(Assets.player10, (int) (x * DEFAULT_CREATURE_WIDTH - game.getGameCamera().getxOffset()),
                    (int) (y * DEFAULT_CREATURE_HEIGHT - game.getGameCamera().getyOffset()), width, height, null);
        }
        if (position == 11) {
            g.drawImage(Assets.player11, (int) (x * DEFAULT_CREATURE_WIDTH - game.getGameCamera().getxOffset()),
                    (int) (y * DEFAULT_CREATURE_HEIGHT - game.getGameCamera().getyOffset()), width, height, null);
        }
        if (position == 12) {
            g.drawImage(Assets.player12, (int) (x * DEFAULT_CREATURE_WIDTH - game.getGameCamera().getxOffset()),
                    (int) (y * DEFAULT_CREATURE_HEIGHT - game.getGameCamera().getyOffset()), width, height, null);
        }

    }

    public boolean isDialoging() {
        return isDialoging;
    }

    public void setDialoging(boolean isDialoging) {
        this.isDialoging = isDialoging;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Attack> getAttacks() {
        return attacks;
    }

    public void setAttacks(ArrayList<Attack> attacks) {
        this.attacks = attacks;
    }
}
