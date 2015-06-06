package pe.edu.pucp.game.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

@SuppressWarnings("serial")
public class MouseManager implements MouseListener, Serializable{

	public int mX,mY;
	public MouseManager(){}
	
	public void tick(){
		//System.out.println(mX);
		//System.out.println(mY);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		mX=e.getX();
		mY=e.getY();
		/*System.out.println(mX);
		System.out.println(mY);*/
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
