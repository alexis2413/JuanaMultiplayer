package pe.edu.pucp.game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.WindowEvent;
import java.io.Serializable;

import pe.edu.pucp.game.Game;
import pe.edu.pucp.game.display.Display;
import pe.edu.pucp.game.gfx.Assets;

@SuppressWarnings("serial")
public class MenuState extends State implements Serializable {

    public static int WIDTH = 50;
    public Rectangle playButton = new Rectangle(WIDTH / 2 + 120, 130, 100, 50);
    public Rectangle loadButton = new Rectangle(WIDTH / 2 + 120, 190, 100, 50);
    public Rectangle helpButton = new Rectangle(WIDTH / 2 + 120, 260, 100, 50);
    public Rectangle quitButton = new Rectangle(WIDTH / 2 + 120, 330, 100, 50);
    public Rectangle chooseCharacterButton = new Rectangle(280, 150, 115, 25);

    public MenuState(Game game) {
        super(game);
    }

    public MenuState() {
    }

    @Override
    public void tick() {
		// TODO Auto-generated method stub
        //Play
        if ((game.getMouseManager().mX >= 145 && game.getMouseManager().mX <= 245)
                && (game.getMouseManager().mY >= 130 && game.getMouseManager().mY <= 180)) {
            game.getGameState().setNumberPlayer(numberPlayer);
            game.hasStarted=true;
            State.setState(game.getGameState());
            game.getMouseManager().mX = 0;
            game.getMouseManager().mY = 0;
        }
        //Load
        if ((game.getMouseManager().mX >= 145 && game.getMouseManager().mX <= 245)
                && (game.getMouseManager().mY >= 190 && game.getMouseManager().mY <= 240)) {
            //State.setState(game.getLoadAvatarState());
            State.setState(game.getLoadGameState());
            game.getMouseManager().mX = 0;
            game.getMouseManager().mY = 0;

        }
        //Help
        if ((game.getMouseManager().mX >= 145 && game.getMouseManager().mX <= 245)
                && (game.getMouseManager().mY >= 260 && game.getMouseManager().mY <= 310)) {
            State.setState(game.getHelpState());
            game.getMouseManager().mX = 0;
            game.getMouseManager().mY = 0;
        }
        //Exit
        if ((game.getMouseManager().mX >= 145 && game.getMouseManager().mX <= 245)
                && (game.getMouseManager().mY >= 330 && game.getMouseManager().mY <= 380)) {
            game.getMouseManager().mX = 0;
            game.getMouseManager().mY = 0;
            game.getDisplay().getFrame().dispatchEvent(new WindowEvent(game.getDisplay().getFrame(), WindowEvent.WINDOW_CLOSING));
        }

        //choose character
        if ((game.getMouseManager().mX >= 280 && game.getMouseManager().mX <= 395)
                && (game.getMouseManager().mY >= 150 && game.getMouseManager().mY <= 175)) {
            State.setState(game.getChooseCharacterState());
            game.getMouseManager().mX = 0;
            game.getMouseManager().mY = 0;
        }
    }

    @Override
    public void render(Display display) {

        Graphics g = display.getCanvas().getBufferStrategy().getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        // TODO Auto-generated method stub
        g.drawImage(Assets.juanaBackground, 0, 0, game.getHeight(), game.getWidth(), null);

        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.black);

        g.drawImage(Assets.load, WIDTH / 2 + 120, 190, 100, 50, null);
		//g.drawImage(Assets.save,WIDTH/2 + 120,190,100,50,null);

        Font fnt1 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt1);
        g.drawString("Play", playButton.x + 19, playButton.y + 30);
        g2d.draw(playButton);
        g.drawString("Help", helpButton.x + 19, helpButton.y + 30);
        g2d.draw(helpButton);
        g.drawString("Quit", quitButton.x + 19, quitButton.y + 30);
        g2d.draw(quitButton);
		//g.drawString("Load",loadButton.x+19,loadButton.y+30);
        //g2d.draw(loadButton);
        Font fnt2 = new Font("arial", Font.BOLD, 12);
        g.setFont(fnt2);
        g.drawString("Choose Character", chooseCharacterButton.x + 10, chooseCharacterButton.y + 15);
        g2d.draw(chooseCharacterButton);
    }

}
