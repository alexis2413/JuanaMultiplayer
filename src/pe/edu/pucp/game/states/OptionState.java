package pe.edu.pucp.game.states;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import pe.edu.pucp.game.Game;
import pe.edu.pucp.game.display.Display;
import pe.edu.pucp.game.gfx.Assets;

@SuppressWarnings("serial")
public class OptionState extends State {

    private static int WIDTH = 50;
    public Rectangle returnButton = new Rectangle(WIDTH / 2, 150, 100, 50);
    public Rectangle saveButton = new Rectangle(WIDTH / 2, 250, 100, 50);
    public Rectangle loadButton = new Rectangle(WIDTH / 2, 350, 100, 50);
    public Rectangle inventoryButton = new Rectangle(200, 150, 100, 50);

    public OptionState(Game game) {
        super(game);
    }

    public OptionState() {
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        if ((game.getMouseManager().mX >= 25 && game.getMouseManager().mX <= 125)
                && (game.getMouseManager().mY >= 150 && game.getMouseManager().mY <= 200)) {
            game.getGameState().setNumberPlayer(numberPlayer);
            State.setState(game.getGameState());
            game.getMouseManager().mX = 0;
            game.getMouseManager().mY = 0;
        }
        if ((game.getMouseManager().mX >= 25 && game.getMouseManager().mX <= 125)
                && (game.getMouseManager().mY >= 250 && game.getMouseManager().mY <= 320)) {
            //loadGame();
            game.getMouseManager().mX = 0;
            game.getMouseManager().mY = 0;
            State.setState(game.getLoadGameState());
        }
        if ((game.getMouseManager().mX >= 25 && game.getMouseManager().mX <= 125)
                && (game.getMouseManager().mY >= 350 && game.getMouseManager().mY <= 400)) {
            //saveGame();
            game.getMouseManager().mX = 0;
            game.getMouseManager().mY = 0;
            State.setState(game.getSaveGameState());
        }
    }

    @Override
    public void render(Display display) {
        // TODO Auto-generated method stub
        Graphics g = display.getCanvas().getBufferStrategy().getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.85f));
        // TODO Auto-generated method stub
        g.drawImage(Assets.juanaBackground, 0, 0, game.getHeight(), game.getWidth(), null);
        //RETURN! 
        g.drawImage(Assets.button1, 25, 150, 33, 50, null);
        g.drawImage(Assets.button2, 58, 150, 33, 50, null);
        g.drawImage(Assets.button3, 91, 150, 34, 50, null);
        //INVENTORY! 200,150,100,50
        g.drawImage(Assets.button1, 200, 150, 33, 50, null);
        g.drawImage(Assets.button2, 233, 150, 33, 50, null);
        g.drawImage(Assets.button3, 266, 150, 34, 50, null);
        //HEAL BAR! 100,100,100,50
        g.drawImage(Assets.bar1, 25, 90, 66, 30, null);
        g.drawImage(Assets.bar2, 91, 90, 66, 30, null);
        g.drawImage(Assets.bar3, 157, 90, 67, 30, null);
        //HEAL BAR! <-150-> 
        Font fnt0 = new Font("arial", Font.BOLD, 16);
        g.setFont(fnt0);
        g.setColor(Color.black);
        //g.drawString("OPTIONS", WIDTH / 4, 70);
        g.setFont(fnt0);
        //Stats
        int max = ((GameState) game.getGameState()).getPlayer().getMaxHealth();
        int actual = ((GameState) game.getGameState()).getPlayer().getHealth();
        int pix = (int) ((150.0 * actual) / max);
        if (actual == max) {
            g.drawImage(Assets.fillBar1, 48, 95, 43, 20, null);
            g.drawImage(Assets.fillBar2, 91, 95, 66, 20, null);
            g.drawImage(Assets.fillBar3, 157, 95, 43, 20, null);
        }else{
            if(pix<43){
                g.drawImage(Assets.fillBar1, 48, 95, pix, 20, null);
            }
            if(pix>=43&&pix<109){
                g.drawImage(Assets.fillBar1, 48, 95, 43, 20, null);
                g.drawImage(Assets.fillBar2, 91, 95, pix-43, 20, null);
            }
            if(pix>=109){
                g.drawImage(Assets.fillBar1, 48, 95, 43, 20, null);
                g.drawImage(Assets.fillBar2, 91, 95, 66, 20, null);
                g.drawImage(Assets.fillBar2, 157, 95, pix-109, 20, null);
            }
        }

        g.drawString("HP " + actual, 101, 111);
        //Button	
        g.drawImage(Assets.load, WIDTH / 2, 250, 100, 50, null);
        g.drawImage(Assets.save, WIDTH / 2, 350, 100, 50, null);
        Font fnt1 = new Font("arial", Font.BOLD, 24);
        g.setFont(fnt1);
        g.drawString("Return", returnButton.x + 12, returnButton.y + 33);
        Font fnt2 = new Font("arial", Font.BOLD, 16);
        g.setFont(fnt2);
        g.drawString("Inventory", inventoryButton.x + 12, inventoryButton.y + 31);
        //g2d.draw(returnButton);
        /*g.drawString("Load",saveButton.x,saveButton.y+30);
         g2d.draw(saveButton);
         g.drawString("Save",loadButton.x,loadButton.y+30);
         g2d.draw(loadButton);*/
    }

}
