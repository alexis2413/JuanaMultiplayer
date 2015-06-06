package pe.edu.pucp.game.states;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;

import pe.edu.pucp.game.Game;
import pe.edu.pucp.game.display.Display;

@SuppressWarnings("serial")
public class HelpState extends State implements Serializable{
	public Rectangle textBox = new Rectangle(0,0,400,200);
	public Rectangle backButton = new Rectangle(145,290,100,50);
	public HelpState(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}
	
	public HelpState(){}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		if((game.getMouseManager().mX>=145 && game.getMouseManager().mX<=245)&&
				(game.getMouseManager().mY>=290 && game.getMouseManager().mY<=340)){
			State.setState(game.getMenuState());
			game.getMouseManager().mX=0;
			game.getMouseManager().mY=0;
		}
	}

	@Override
	public void render(Display display) {
		// TODO Auto-generated method stub
		Graphics g= display.getCanvas().getBufferStrategy().getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		// TODO Auto-generated method stub
		
		Font fnt1=new Font("arial",Font.BOLD,12);
		g.setFont(fnt1);
		g.drawString("Press W ,A ,S ,D to move",textBox.x+19,textBox.y+30);
		g.drawString("Press P to enter the menu",textBox.x+19,textBox.y+80);
		g2d.draw(textBox);
		Font fnt2=new Font("arial",Font.BOLD,30);
		g.setFont(fnt2);
		g.drawString("Back",backButton.x+19,backButton.y+30);
		g2d.draw(backButton);
	}

}
