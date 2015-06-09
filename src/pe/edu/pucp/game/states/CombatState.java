package pe.edu.pucp.game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.WindowEvent;
import java.util.Random;
import pe.edu.pucp.game.Game;
import pe.edu.pucp.game.display.Display;
import pe.edu.pucp.game.entities.creatures.enemies.Enemy;
import pe.edu.pucp.game.gfx.Assets;

@SuppressWarnings("serial")
public class CombatState extends State {

    private Enemy enemy;
    private boolean playerTurn = true, playerAttacked = false;
    private int playerAttack,enemyAttack;
    int delayEnemy = 0, delayPlayer = 0, maxDelay = 30;
    Random rand = new Random();

    public static int WIDTH = 50;
    public Rectangle attack1 = new Rectangle(WIDTH / 2 + 20, 260, 100, 50);
    public Rectangle attack2 = new Rectangle(WIDTH / 2 + 20, 330, 100, 50);
    public Rectangle attack3 = new Rectangle(WIDTH / 2 + 150, 260, 100, 50);
    public Rectangle attack4 = new Rectangle(WIDTH / 2 + 150, 330, 100, 50);

    public CombatState(Game game) {
        super(game);
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        //Turno
        if (playerTurn) {
            if (playerAttacked == false) {
                if ((game.getMouseManager().mX >= 45 && game.getMouseManager().mX <= 145)
                        && (game.getMouseManager().mY >= 260 && game.getMouseManager().mY <= 310)) {
                    playerAttack = 0;
                    int damage = ((GameState) game.getGameState()).getPlayer().getAttacks().get(0).getDamage();
                    enemy.setHealth(enemy.getHealth() - damage > 0 ? enemy.getHealth() - damage : 0);
                    game.getKeyManager().one = false;
                    playerAttacked = true;
                    game.getMouseManager().mX = 0;
                    game.getMouseManager().mY = 0;
                }
                if ((game.getMouseManager().mX >= 45 && game.getMouseManager().mX <= 145)
                        && (game.getMouseManager().mY >= 330 && game.getMouseManager().mY <= 380)) {
                    playerAttack = 1;
                    int damage = ((GameState) game.getGameState()).getPlayer().getAttacks().get(1).getDamage();
                    enemy.setHealth(enemy.getHealth() - damage > 0 ? enemy.getHealth() - damage : 0);
                    game.getKeyManager().two = false;
                    playerAttacked = true;
                    game.getMouseManager().mX = 0;
                    game.getMouseManager().mY = 0;
                }
                if ((game.getMouseManager().mX >= 175 && game.getMouseManager().mX <= 275)
                        && (game.getMouseManager().mY >= 260 && game.getMouseManager().mY <= 310)) {
                    playerAttack = 2;
                    int damage = ((GameState) game.getGameState()).getPlayer().getAttacks().get(2).getDamage();
                    enemy.setHealth(enemy.getHealth() - damage > 0 ? enemy.getHealth() - damage : 0);
                    game.getKeyManager().three = false;
                    playerAttacked = true;
                    game.getMouseManager().mX = 0;
                    game.getMouseManager().mY = 0;
                }
                if ((game.getMouseManager().mX >= 175 && game.getMouseManager().mX <= 275)
                        && (game.getMouseManager().mY >= 330 && game.getMouseManager().mY <= 380)) {
                    playerAttack = 3;
                    int damage = ((GameState) game.getGameState()).getPlayer().getAttacks().get(2).getDamage();
                    enemy.setHealth(enemy.getHealth() - damage > 0 ? enemy.getHealth() - damage : 0);
                    game.getKeyManager().four = false;
                    playerAttacked = true;
                    game.getMouseManager().mX = 0;
                    game.getMouseManager().mY = 0;
                }
            } else {
                delayPlayer++;
                if (delayPlayer == maxDelay) {
                    playerAttacked = false;
                    playerTurn = false;
                    delayPlayer = 0;
                    if (enemy.getHealth() <= 0) {	//Finalizacion
                        State.setState(game.getGameState());
                    }
                }
            }
            if (enemy.getHealth() <= 0) {	//Finalizacion
                State.setState(game.getGameState());
            }
        } else {
            delayEnemy++;
            if (delayEnemy == maxDelay) {
                enemyAttack=rand.nextInt(4);
                playerTurn = true;
                delayEnemy = 0;
                int damage= enemy.getAttacks().get(enemyAttack).getDamage();
                ((GameState) game.getGameState()).getPlayer().setHealth(((GameState) game.getGameState()).getPlayer().getHealth() - damage);
                if (((GameState) game.getGameState()).getPlayer().getHealth() <= 0)//Game Over
                {
                    game.getDisplay().getFrame().dispatchEvent(new WindowEvent(game.getDisplay().getFrame(), WindowEvent.WINDOW_CLOSING));
                }
            }
            if (((GameState) game.getGameState()).getPlayer().getHealth() <= 0)//Game Over
            {
                game.getDisplay().getFrame().dispatchEvent(new WindowEvent(game.getDisplay().getFrame(), WindowEvent.WINDOW_CLOSING));
            }
        }
    }

    @Override
    public void render(Display display) {
        // TODO Auto-generated method stub
        Graphics g = display.getCanvas().getBufferStrategy().getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        g.drawImage(Assets.juanaCombat, 20, 80, 100, 100, null);
        double playerHealth = ((GameState) game.getGameState()).getPlayer().getHealth();
        double playerMaxHealth = ((GameState) game.getGameState()).getPlayer().getMaxHealth();
        double playerFraction = playerHealth / playerMaxHealth;
        double enemyHealth = enemy.getHealth();
        double enemyMaxHealth = enemy.getMaxHealth();
        double enemyFraction = enemyHealth / enemyMaxHealth;
        g.setColor(Color.RED);
        g.fillRect(20, 60, (int) (playerFraction * 100), 10);
        g.setColor(Color.BLACK);
        g.drawString("HP " + ((GameState) game.getGameState()).getPlayer().getHealth(), 20, 60);
        g.drawImage(Assets.chickenCombat, 200, 80, 100, 100, null);
        g.drawString("HP " + enemy.getHealth(), 200, 60);
        g.setColor(Color.RED);
        g.fillRect(200, 60, (int) (enemyFraction * 100), 10);
        g.setColor(Color.BLACK);

        if (playerTurn) {
            g.drawString("Your Turn ", 50, 50);
            if (playerAttacked) {
                String name = ((GameState) game.getGameState()).getPlayer().getAttacks().get(playerAttack).getName();
                g.drawString("Player used " + name, 50, 220);
                g.drawImage(Assets.angryJuana, 20, 80, 100, 100, null);
            }
        } else {//animacion del ataque del enemigo
            g.drawString("Enemy's Turn", 50, 50);
            String name = enemy.getAttacks().get(enemyAttack).getName();
            g.drawString("Enemy used " + name, 200, 220);
            //g.drawImage(Assets.angryChicken,200,150,100,100,null);
            g.drawImage(Assets.angryChicken, 200, 80, 100, 100, null);
        }

        Font fnt1 = new Font("arial", Font.BOLD, 10);
        g.setFont(fnt1);
        g.drawString(((GameState) game.getGameState()).getPlayer().getAttacks().get(0).getName(), attack1.x + 19, attack1.y + 30);
        g2d.draw(attack1);
        g.drawString(((GameState) game.getGameState()).getPlayer().getAttacks().get(1).getName(), attack2.x + 19, attack2.y + 30);
        g2d.draw(attack2);
        g.drawString(((GameState) game.getGameState()).getPlayer().getAttacks().get(2).getName(), attack3.x + 19, attack3.y + 30);
        g2d.draw(attack3);
        g.drawString(((GameState) game.getGameState()).getPlayer().getAttacks().get(3).getName(), attack4.x + 19, attack4.y + 30);
        g2d.draw(attack4);
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

}
