package pe.edu.pucp.game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.game.Game;
import pe.edu.pucp.game.Launcher;
import pe.edu.pucp.game.attacks.Attack;
import pe.edu.pucp.game.display.Display;
import pe.edu.pucp.game.entities.Entity;
import static pe.edu.pucp.game.entities.creatures.Creature.DEFAULT_CREATURE_HEIGHT;
import static pe.edu.pucp.game.entities.creatures.Creature.DEFAULT_CREATURE_WIDTH;
import pe.edu.pucp.game.entities.creatures.NonPlayerCharacter;
import pe.edu.pucp.game.entities.creatures.Player;
import pe.edu.pucp.game.entities.creatures.enemies.Enemy;
import pe.edu.pucp.game.entities.items.Item;
import pe.edu.pucp.game.entities.objects.Boulder;
import pe.edu.pucp.game.gfx.Assets;
import pe.edu.pucp.game.gfx.GameCamera;
import pe.edu.pucp.game.states.State;
import pe.edu.pucp.game.threads.MapThread;
import pe.edu.pucp.game.tile.DoorTile;
import pe.edu.pucp.game.tile.GrassTile;
import pe.edu.pucp.game.tile.RockTile;
import pe.edu.pucp.game.tile.SeaTile;
import pe.edu.pucp.game.tile.SeaTileReflected;
import pe.edu.pucp.game.tile.Tile;
import pe.edu.pucp.game.worlds.World;

@SuppressWarnings("serial")
public class MultiplayerState extends State implements Serializable {

    //private ArrayList<Player> players;
    Player player;
    private int nEnemies;
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<Entity> objects = new ArrayList<Entity>();
    private ArrayList<NonPlayerCharacter> npcs = new ArrayList<NonPlayerCharacter>();
    private ArrayList<Item> items = new ArrayList<Item>();
    //private World world;
    private boolean playerPressed = false;
    public boolean levelComplete = false;
    public int timeLeft = 15;
    public int nPlayer;

    public MultiplayerState(Game game, int nPlayer) throws RemoteException {
        super(game);
        //world = new World("res/worlds/world1.xml", game.getGameCamera(), enemies, objects, npcs, items);
        //world=new World("res/worlds/world1.txt",game.getGameCamera());
        //world.saveToXml(1);
        //player = new Player(game, world.getSpawnX(), world.getSpawnY());
        player = new Player(game, 1, 1);

        setGame();
        MapThread mt = new MapThread(game);
        mt.start();
        this.nPlayer = nPlayer;
        //SeaReflectThread srt = new SeaReflectThread(game);
        //srt.start();
        game.getDisplay().getFrame().addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                try {
                    Launcher.proxy.deletePlayer(nPlayer - 1);
                } catch (RemoteException ex) {
                    Logger.getLogger(MultiplayerState.class.getName()).log(Level.SEVERE, null, ex);
                }
                game.getDisplay().getFrame().dispose();
                System.exit(0);
            }
        });
    }

    public MultiplayerState() {
    }

    @Override
    public void tick() {
        try {
            if (Launcher.proxy.isPaused() == false) {
                /*if (world.openDoors()) {
                 levelComplete=true;
                 game.setDialogue(true);
                 int i = 0, newWorld;
                 while (player.getX() != world.getDoors()[i][0] || player.getY() != world.getDoors()[i][1]) {
                 i++;
                 }
                 newWorld = world.getDoorTo()[i];
                 world = new World("res/Worlds/world" + newWorld + ".xml", game.getGameCamera(), enemies, objects, npcs, items);
                 //world=new World("res/Worlds/world"+ newWorld +".txt",game.getGameCamera());
                 //world.saveToXml(newWorld);
                 player.setX(world.getSpawnX());
                 player.setY(world.getSpawnY());
                 setGame();
                 timeLeft=15;
                 }*/
                try {
                    //world.render(g);
                    Launcher.proxy.getWorld().tick();
                } catch (RemoteException ex) {
                    Logger.getLogger(MultiplayerState.class.getName()).log(Level.SEVERE, null, ex);
                }
                //player.tick();
                try {
                    //for (int i = 0; i < Launcher.proxy.getPlayers().size(); i++) {
                    //Player p = Launcher.proxy.getPlayers().get(0);
                    //p.setGameCamera(game.getGameCamera());
                    //p.setKeyManager(game.getKeyManager());
                    //p.setGame(game);
                    //movePlayer(p);
                    //player.tick();
                    movePlayer(player);
                    if (game.getKeyManager().p == true) {
                        Launcher.proxy.setPause(!Launcher.proxy.isPaused());
                    }
                    Player player1 = new Player(player.getX(), player.getY(), player.getPosition(),
                            player.getContUp(), player.getContDown(), player.getContRight(), player.getContLeft());
                    Launcher.proxy.setPlayerAtI(nPlayer - 1, player1);
                    //}
                } catch (RemoteException ex) {
                    Logger.getLogger(MultiplayerState.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (int i = 0; i < npcs.size(); i++) {
                    npcs.get(i).tick();
                }

                for (int j = 0; j < enemies.size(); j++) {
                    enemies.get(j).tick();
                    if (enemies.get(j).getHealth() <= 0) {
                        enemies.remove(j);
                        nEnemies--;
                    }
                }
                //System.out.println(Launcher.proxy.getObjects().size()+"");
                ArrayList<Entity> objects1 = Launcher.proxy.getObjects();
                for (int j = 0; j < objects1.size(); j++) {                    
                    boulderTick((Boulder)objects1.get(j),player);
                }
                Launcher.proxy.setObjects(objects1);

                if (items.size() > 0) {
                    for (int i = 0; i < items.size(); i++) {
                        items.get(i).tick();
                    }
                }

                if ((game.getMouseManager().mX >= player.getX() * 24 - game.getGameCamera().getxOffset()
                        && game.getMouseManager().mX <= player.getX() * 24 - game.getGameCamera().getxOffset() + 24)
                        && (game.getMouseManager().mY >= player.getY() * 24 - game.getGameCamera().getyOffset()
                        && game.getMouseManager().mY <= player.getY() * 24 - game.getGameCamera().getyOffset() + 24)) {
                    playerPressed = true;
                } else {
                    playerPressed = false;
                }
            } else {
                if (game.getKeyManager().p == true) {
                    Launcher.proxy.setPause(!Launcher.proxy.isPaused());
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(MultiplayerState.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void render(Display display) {
        try {
            // TODO Auto-generated method stub
            Graphics g = display.getCanvas().getBufferStrategy().getDrawGraphics();
            Graphics2D g2d = (Graphics2D) g;
            Font fnt0 = new Font("arial", Font.BOLD, 10);
            g.setFont(fnt0);
            g.setColor(Color.black);
            try {
                //world.render(g);
                drawWorld(Launcher.proxy.getWorld(), game.getGameCamera(), g);
            } catch (RemoteException ex) {
                Logger.getLogger(MultiplayerState.class.getName()).log(Level.SEVERE, null, ex);
            }
            //player.render(g);

            System.out.println("cantidad de jugadores: " + Launcher.proxy.getPlayers().size());
            for (int i = 0; i < Launcher.proxy.getPlayers().size(); i++) {
                if (Launcher.proxy.getPlayers().get(i) != null) {
                    double x = Launcher.proxy.getPlayers().get(i).getX();
                    double y = Launcher.proxy.getPlayers().get(i).getY();
                    int position = Launcher.proxy.getPlayers().get(i).getPosition();
                    int contU = Launcher.proxy.getPlayers().get(i).getContUp();
                    int contD = Launcher.proxy.getPlayers().get(i).getContDown();
                    int contR = Launcher.proxy.getPlayers().get(i).getContRight();
                    int contL = Launcher.proxy.getPlayers().get(i).getContLeft();
                    Player playeri = new Player(game, x, y, position, contU, contD, contR, contL);
                    playeri.render(g);
                    int cx = (int) Launcher.proxy.getPlayers().get(i).getX();
                    int ci = (int) Launcher.proxy.getPlayers().get(i).getY();
                    System.out.println(cx + " " + ci);
                }
            }

            if (playerPressed) {
                g.drawString(player.getDescription(), (int) (player.getX() * 24 - game.getGameCamera().getxOffset()),
                        (int) (player.getY() * 24 - game.getGameCamera().getyOffset()));
            }
            if (enemies.size() > 0) {
                for (int j = 0; j < enemies.size(); j++) {
                    enemies.get(j).render(g);
                }
            }
            if (Launcher.proxy.getObjects().size() > 0) {
                for (int j = 0; j < Launcher.proxy.getObjects().size(); j++) {
                    drawObject(Launcher.proxy.getObjects().get(j), g);
                }
            }

            for (int i = 0; i < npcs.size(); i++) {
                npcs.get(i).render(g);
            }

            for (int i = 0; i < items.size(); i++) {
                items.get(i).render(g);
            }

            fnt0 = new Font("arial", Font.BOLD, 20);
            g.setFont(fnt0);
            g.setColor(Color.black);

            try {
                if (Launcher.proxy.isPaused() == true) {
                    g.drawString("Paused", 20, 20);
                }
                //g.drawString("Time left to complete level: " + timeLeft, 20, 20);
            } catch (RemoteException ex) {
                Logger.getLogger(MultiplayerState.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(MultiplayerState.class.getName()).log(Level.SEVERE, null, ex);
        }
        //player.render(g);
    }

    public void setGame() {
        nEnemies = enemies.size();
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).setGame(game);
        }
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).setGame(game);
        }
        for (int i = 0; i < npcs.size(); i++) {
            npcs.get(i).setGame(game);
        }
        for (int i = 0; i < items.size(); i++) {
            items.get(i).setGame(game);
        }
    }

    public int getnEnemies() {
        return nEnemies;
    }

    public void setnEnemies(int nEnemies) {
        this.nEnemies = nEnemies;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    /*public World getWorld() {
     return world;
     }

     public void setWorld(World world) {
     this.world = world;
     }*/
    public ArrayList<Entity> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<Entity> objects) {
        this.objects = objects;
    }

    public ArrayList<NonPlayerCharacter> getNpcs() {
        return npcs;
    }

    public void setNpcs(ArrayList<NonPlayerCharacter> npcs) {
        this.npcs = npcs;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void drawWorld(World world, GameCamera gameCamera, Graphics g) {
        Tile.rockTile = new RockTile(1);
        Tile.grassTile = new GrassTile(0);
        Tile.seaTile = new SeaTile(4);
        Tile.seaTileReflected = new SeaTileReflected(5);
        for (int y = 0; y < world.getHeight(); y++) {
            for (int x = 0; x < world.getWidth(); x++) {
                world.getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - gameCamera.getxOffset()),
                        (int) (y * Tile.TILEHEIGHT - gameCamera.getyOffset()));
            }
        }
    }

    public void drawObject(Entity o, Graphics g) {
        g.drawImage(Assets.boulder, (int) (o.getX() * o.getWidth() - game.getGameCamera().getxOffset()),
                (int) (o.getY() * o.getHeight() - game.getGameCamera().getyOffset()), o.getWidth(), o.getHeight(), null);
    }

    public void movePlayer(Player player) {
        try {
            player.getInput();
            if (player.isValidMultiplayerMove(Launcher.proxy.getObjects(), Launcher.proxy.getNpcs(), Launcher.proxy.getItems(), Launcher.proxy.getWorld())) {
                player.setX(player.getX() + player.getxMove());
                player.setY(player.getY() + player.getyMove());
            }
            game.getGameCamera().centerOnEntity(player);
            /**
             * if (((GameState) game.getGameState()).getWorld().getTile((int) x,
             * (int) (y)).getId() == 2) { ((DoorTile) ((GameState)
             * game.getGameState()).getWorld().getTile((int) x, (int)
             * (y))).openDoor(); }
             */
        } catch (RemoteException ex) {
            Logger.getLogger(MultiplayerState.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void boulderTick(Boulder boulder, Player player) {
        playerNextTo(boulder, player);
        if (playerContact(player, boulder) && boulder.isCollisioned()) {
            try {
                if (!Launcher.proxy.getWorld().getTile((int) (boulder.getX() + boulder.getxMove()), (int) (boulder.getY() + boulder.getyMove())).isSolid()
                        && boulder.isValidMultiplayerMove(boulder.getxMove(), boulder.getyMove(),
                                Launcher.proxy.getObjects(), Launcher.proxy.getNpcs(), Launcher.proxy.getItems(), Launcher.proxy.getWorld())) {
                    boulder.setX(boulder.getX() + boulder.getxMove());
                    boulder.setY(boulder.getY() + boulder.getyMove());
                }
            } catch (RemoteException ex) {
                Logger.getLogger(MultiplayerState.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void playerNextTo(Boulder boulder, Player player) {
        //player above boulder
        if (player.getX() == boulder.getX()
                && player.getY() == boulder.getY() - 1) {
            boulder.setyMove(1);
        } //player bellow boulder
        else if (player.getX() == boulder.getX()
                && player.getY() == boulder.getY() + 1) {
            boulder.setyMove(-1);
        } //player left to boulder
        else if (player.getX() == boulder.getX() - 1
                && player.getY() == boulder.getY()) {
            boulder.setxMove(1);
        } //player right to boulder
        else if (player.getX() == boulder.getX() + 1
                && player.getY() == boulder.getY()) {
            boulder.setxMove(-1);
        } else if (!collision(player, boulder)) {
            boulder.setxMove(0);
            boulder.setyMove(0);
        }
    }

    public boolean collision(Player player, Boulder boulder) {
        if (boulder.getX() == (player.getX())
                && boulder.getY() == (player.getY())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean playerContact(Player player, Boulder boulder) {
        //from above
        if (player.getX() == boulder.getX()
                && player.getY() == boulder.getY() - 1) {
            if (game.getKeyManager().down) {
                return true;
            }
        }
        //from bellow
        if (player.getX() == boulder.getX()
                && player.getY() == boulder.getY() + 1) {
            if (game.getKeyManager().up) {
                return true;
            }
        }

        //from the left
        if (player.getX() == boulder.getX() - 1
                && player.getY() == boulder.getY()) {
            if (game.getKeyManager().right) {
                return true;
            }
        }

        //from the right
        if (player.getX() == boulder.getX() + 1
                && player.getY() == boulder.getY()) {
            if (game.getKeyManager().left) {
                return true;
            }
        }
        return false;
    }
}
