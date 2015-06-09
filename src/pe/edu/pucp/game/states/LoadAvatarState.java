package pe.edu.pucp.game.states;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;

import pe.edu.pucp.game.Game;
import pe.edu.pucp.game.display.Display;
import pe.edu.pucp.game.entities.creatures.Creature;
import pe.edu.pucp.game.gfx.Assets;

@SuppressWarnings("serial")
public class LoadAvatarState extends State implements Serializable {

    public Rectangle rect1 = new Rectangle(50, 120, 50, 50);
    public Rectangle rect2 = new Rectangle(110, 120, 50, 50);
    public Rectangle rect3 = new Rectangle(170, 120, 50, 50);
    public Rectangle rect4 = new Rectangle(230, 120, 50, 50);

    public Rectangle rect5 = new Rectangle(50, 200, 50, 50);
    public Rectangle rect6 = new Rectangle(110, 200, 50, 50);
    public Rectangle rect7 = new Rectangle(170, 200, 50, 50);
    public Rectangle rect8 = new Rectangle(230, 200, 50, 50);

    public Rectangle backButton = new Rectangle(145, 275, 100, 50);

    public LoadAvatarState(Game game) {
        super(game);
        // TODO Auto-generated constructor stub
    }

    public LoadAvatarState() {
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        if ((game.getMouseManager().mX >= 50 && game.getMouseManager().mX <= 100)
                && (game.getMouseManager().mY >= 120 && game.getMouseManager().mY <= 170)) {
            Assets.init(1);
            game.getGameState().setNumberPlayer(1);
        }
        if ((game.getMouseManager().mX >= 110 && game.getMouseManager().mX <= 160)
                && (game.getMouseManager().mY >= 120 && game.getMouseManager().mY <= 170)) {
            Assets.init(2);
            game.getGameState().setNumberPlayer(2);
        }
        if ((game.getMouseManager().mX >= 170 && game.getMouseManager().mX <= 220)
                && (game.getMouseManager().mY >= 120 && game.getMouseManager().mY <= 170)) {
            Assets.init(3);
            game.getGameState().setNumberPlayer(3);
        }
        if ((game.getMouseManager().mX >= 230 && game.getMouseManager().mX <= 280)
                && (game.getMouseManager().mY >= 120 && game.getMouseManager().mY <= 170)) {
            Assets.init(4);
            game.getGameState().setNumberPlayer(4);
        }

        if ((game.getMouseManager().mX >= 50 && game.getMouseManager().mX <= 100)
                && (game.getMouseManager().mY >= 200 && game.getMouseManager().mY <= 250)) {
            Assets.init(5);
            game.getGameState().setNumberPlayer(5);
        }
        if ((game.getMouseManager().mX >= 110 && game.getMouseManager().mX <= 160)
                && (game.getMouseManager().mY >= 200 && game.getMouseManager().mY <= 250)) {
            Assets.init(6);
            game.getGameState().setNumberPlayer(6);
        }
        if ((game.getMouseManager().mX >= 170 && game.getMouseManager().mX <= 220)
                && (game.getMouseManager().mY >= 200 && game.getMouseManager().mY <= 250)) {
            Assets.init(7);
            game.getGameState().setNumberPlayer(7);
        }
        if ((game.getMouseManager().mX >= 230 && game.getMouseManager().mX <= 280)
                && (game.getMouseManager().mY >= 200 && game.getMouseManager().mY <= 250)) {
            Assets.init(8);
            game.getGameState().setNumberPlayer(8);
        }

        if ((game.getMouseManager().mX >= 145 && game.getMouseManager().mX <= 245)
                && (game.getMouseManager().mY >= 275 && game.getMouseManager().mY <= 325)) {
            State.setState(game.getMenuState());
            game.getMouseManager().mX = 0;
            game.getMouseManager().mY = 0;
            
        }
    }

    @Override
    public void render(Display display) {
        // TODO Auto-generated method stub
        Graphics g = display.getCanvas().getBufferStrategy().getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
		// TODO Auto-generated method stub

        Font fnt1 = new Font("arial", Font.BOLD, 20);
        g.setFont(fnt1);
        g.drawString("1", rect1.x + 15, rect1.y + 30);
        g2d.draw(rect1);
        g.drawString("2", rect2.x + 15, rect2.y + 30);
        g2d.draw(rect2);
        g.drawString("3", rect3.x + 15, rect3.y + 30);
        g2d.draw(rect3);
        g.drawString("4", rect4.x + 15, rect4.y + 30);
        g2d.draw(rect4);
        g.drawString("5", rect5.x + 15, rect5.y + 30);
        g2d.draw(rect5);
        g.drawString("6", rect6.x + 15, rect6.y + 30);
        g2d.draw(rect6);
        g.drawString("7", rect7.x + 15, rect7.y + 30);
        g2d.draw(rect7);
        g.drawString("8", rect8.x + 15, rect8.y + 30);
        g2d.draw(rect8);

        Font fnt2 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt2);
        g.drawString("Back", backButton.x + 19, backButton.y + 30);
        g2d.draw(backButton);

        g2d.drawImage(Assets.player4, 310, 160, Creature.DEFAULT_CREATURE_WIDTH * 2, Creature.DEFAULT_CREATURE_HEIGHT * 2, null);
    }

}
