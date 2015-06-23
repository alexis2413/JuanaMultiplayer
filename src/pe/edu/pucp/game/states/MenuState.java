package pe.edu.pucp.game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.awt.AlphaComposite;
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
    public Rectangle multiplayerButton=new Rectangle (280,190,115,25);
    public MenuState(Game game) {
        super(game);
    }

    public MenuState() {
    }

    @Override
    public void tick() {
        if (buttonTick(playButton)){
            //State.setState(game.getGameState());
            State.setState(game.getMultiplayerState());
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
        //if(buttonTick(multiplayerButton))            
            //Realizar RMI!;
    }

    @Override
    public void render(Display display) {

        Graphics g = display.getCanvas().getBufferStrategy().getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.85f));
        // TODO Auto-generated method stub
        g.drawImage(Assets.juanaBackground, 0, 0, game.getHeight(), game.getWidth(), null);
        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.black);
        
        //PLAY!
        g.drawImage(Assets.button1, 145, 130, 33,50,null);
        g.drawImage(Assets.button2, 178, 130, 33,50,null);
        g.drawImage(Assets.button3, 211, 130, 34,50,null);
        //HELP!
        g.drawImage(Assets.button1, 145, 260, 33,50,null);
        g.drawImage(Assets.button2, 178, 260, 33,50,null);
        g.drawImage(Assets.button3, 211, 260, 34,50,null);
        //QUIT!
        g.drawImage(Assets.button1, 145, 330, 33,50,null);
        g.drawImage(Assets.button2, 178, 330, 33,50,null);
        g.drawImage(Assets.button3, 211, 330, 34,50,null);
        //LOAD!
        g.drawImage(Assets.button1, 145, 190, 33,50,null);
        g.drawImage(Assets.button2, 178, 190, 33,50,null);
        g.drawImage(Assets.button3, 211, 190, 34,50,null);
        //CHOOSE CHARACTER!
        g.drawImage(Assets.button1, 280, 150, 38,25,null);
        g.drawImage(Assets.button2, 318, 150, 38,25,null);
        g.drawImage(Assets.button3, 356, 150, 39,25,null);
        //MULTIPLAYER!280,190,115,25
        g.drawImage(Assets.button1, 280, 190, 38,25,null);
        g.drawImage(Assets.button2, 318, 190, 38,25,null);
        g.drawImage(Assets.button3, 356, 190, 39,25,null);
        
        
        Font fnt1 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt1);
        
        g.drawString("Play", playButton.x + 19, playButton.y + 35);        
        g.drawString("Help", helpButton.x + 19, helpButton.y + 35);                
        g.drawString("Quit", quitButton.x + 19, quitButton.y + 35);                
        g.drawString("Load",loadButton.x+16,loadButton.y+35);        
        
        Font fnt2 = new Font("arial", Font.BOLD, 12);
        g.setFont(fnt2);
        g.drawString("Choose Character", chooseCharacterButton.x + 9, chooseCharacterButton.y + 16);
        g.drawString("Multiplayer!", multiplayerButton.x + 28, multiplayerButton.y + 16);
        
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
