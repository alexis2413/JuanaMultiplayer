package pe.edu.pucp.game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import pe.edu.pucp.game.Game;
import pe.edu.pucp.game.display.Display;
import pe.edu.pucp.game.gfx.Assets;

@SuppressWarnings("serial")
public class OptionState extends State{
	private static int WIDTH=50;	
	public Rectangle returnButton = new Rectangle(WIDTH/2 ,150,100,50);
	public Rectangle saveButton = new Rectangle(WIDTH/2  ,250,100,50);	
	public Rectangle loadButton = new Rectangle(WIDTH/2  ,350,100,50);
	
	public OptionState(Game game){
		super(game);
	}
	
	public OptionState(){}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		if((game.getMouseManager().mX>=25 && game.getMouseManager().mX<=125)&&
				(game.getMouseManager().mY>=150 && game.getMouseManager().mY<=200)){				
			game.getGameState().setNumberPlayer(numberPlayer);
			State.setState(game.getGameState());		
			game.getMouseManager().mX=0;
			game.getMouseManager().mY=0;
		}
		if((game.getMouseManager().mX>=25 && game.getMouseManager().mX<=125)&&
				(game.getMouseManager().mY>=250 && game.getMouseManager().mY<=320)){				
			//loadGame();
			game.getMouseManager().mX=0;
			game.getMouseManager().mY=0;
			State.setState(game.getLoadGameState());
		}
		if((game.getMouseManager().mX>=25 && game.getMouseManager().mX<=125)&&
				(game.getMouseManager().mY>=350 && game.getMouseManager().mY<=400)){				
			//saveGame();
			game.getMouseManager().mX=0;
			game.getMouseManager().mY=0;
			State.setState(game.getSaveGameState());
		}
	}

	@Override
	public void render(Display display) {
		// TODO Auto-generated method stub
		Graphics g= display.getCanvas().getBufferStrategy().getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		// TODO Auto-generated method stub
		Font fnt0=new Font("arial",Font.BOLD,30);
		g.setFont(fnt0);
		g.setColor(Color.black);
		g.drawString("OPTIONS", WIDTH/4,70);
		Font fnt1=new Font("arial",Font.BOLD,30);
		g.setFont(fnt1);		
		//Stats
		g.drawString("HP"+((GameState) game.getGameState()).getPlayer().getHealth(),200,100);		
		//Button	
		g.drawImage(Assets.load,WIDTH/2 ,250,100,50,null);
		g.drawImage(Assets.save,WIDTH/2  ,350,100,50,null);
		
		g.drawString("Return",returnButton.x,returnButton.y+30);
		g2d.draw(returnButton);
		/*g.drawString("Load",saveButton.x,saveButton.y+30);
		g2d.draw(saveButton);
		g.drawString("Save",loadButton.x,loadButton.y+30);
		g2d.draw(loadButton);*/
	}
	


}
