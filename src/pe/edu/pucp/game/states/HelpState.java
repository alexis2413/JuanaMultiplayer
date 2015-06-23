package pe.edu.pucp.game.states;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;

import pe.edu.pucp.game.Game;
import pe.edu.pucp.game.display.Display;
import pe.edu.pucp.game.gfx.Assets;

@SuppressWarnings("serial")
public class HelpState extends State implements Serializable {

    public Rectangle textBox = new Rectangle(3, 3, 397, 195);
    public Rectangle backButton = new Rectangle(145, 290, 100, 50);

    public HelpState(Game game) {
        super(game);
        // TODO Auto-generated constructor stub
    }

    public HelpState() {
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        if ((game.getMouseManager().mX >= 145 && game.getMouseManager().mX <= 245)
                && (game.getMouseManager().mY >= 290 && game.getMouseManager().mY <= 340)) {
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
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.85f));
        g.drawImage(Assets.juanaBackground, 0, 0, game.getHeight(), game.getWidth(), null);
        //BACK! 
        g.drawImage(Assets.button1, 145, 290, 33, 50, null);
        g.drawImage(Assets.button2, 178, 290, 33, 50, null);
        g.drawImage(Assets.button3, 211, 290, 34, 50, null);
        //TEXTBOX! 0, 0, 400, 200       
        g.setColor(Color.orange);
        g.fillRect(3, 3 , 397, 195);        
        g.drawRect(3, 3, 397, 195);
                
        g.drawImage(Assets.corner1, 0, 0, 21, 21, null);
        g.drawImage(Assets.corner2, 379, 0, 21, 21, null);
        g.drawImage(Assets.corner3, 0, 179, 21, 21, null);
        g.drawImage(Assets.corner4, 379, 179, 21, 21, null);
        
        g.drawImage(Assets.barTop, 10,0, 371, 11, null);
        g.drawImage(Assets.barBot, 10, 189, 371, 11, null);
        g.drawImage(Assets.barLeft, 0, 17, 11, 163, null);
        g.drawImage(Assets.barRight, 390, 17, 11, 163, null);
        
        Font fnt1 = new Font("arial", Font.BOLD, 12);
        g.setFont(fnt1);
        g.setColor(Color.black);
        g.drawString("Press W ,A ,S ,D to move", textBox.x + 19, textBox.y + 30);
        g.drawString("Press P to enter the menu", textBox.x + 19, textBox.y + 80);
        
        Font fnt2 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt2);
        g.drawString("Back", backButton.x + 15, backButton.y + 34);
    }

}
