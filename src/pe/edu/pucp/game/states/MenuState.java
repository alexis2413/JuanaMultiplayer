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

    public Rectangle playButton = new Rectangle(145, 130, 100, 50);
    public Rectangle loadButton = new Rectangle(145, 190, 100, 50);
    public Rectangle helpButton = new Rectangle(145, 260, 100, 50);
    public Rectangle quitButton = new Rectangle(145, 330, 100, 50);
    public Rectangle chooseCharacterButton = new Rectangle(280, 150, 115, 25);

    public MenuState(Game game) {
        super(game);
    }

    public MenuState() {
    }

    @Override
    public void tick() {
        if (buttonTick(playButton)){
            State.setState(game.getGameState());
            game.getGameState().setNumberPlayer(numberPlayer);
            game.hasStarted = true;
        }
        if (buttonTick(loadButton))
            State.setState(game.getLoadGameState());        
        if (buttonTick(helpButton))
            State.setState(game.getHelpState());        
        if (buttonTick(quitButton))
            game.getDisplay().getFrame().dispatchEvent(new WindowEvent(game.getDisplay().getFrame(), WindowEvent.WINDOW_CLOSING));      
        if (buttonTick(chooseCharacterButton))
            State.setState(game.getChooseCharacterState()); 
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

        g.drawImage(Assets.load, 50 / 2 + 120, 190, 100, 50, null);
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

    public boolean buttonTick(Rectangle button) {
        if ((game.getMouseManager().mX >= button.x
                && game.getMouseManager().mX <= button.x + button.width)
                && (game.getMouseManager().mY >= button.y
                && game.getMouseManager().mY <= button.y + button.height)) {
            game.getMouseManager().mX = 0;
            game.getMouseManager().mY = 0;
            return true;
        } else {
            return false;
        }
    }
}
