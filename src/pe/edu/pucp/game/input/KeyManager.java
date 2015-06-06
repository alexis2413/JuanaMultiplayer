package pe.edu.pucp.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

@SuppressWarnings("serial")
public class KeyManager implements KeyListener, Serializable{

	private boolean[] keys;
	public boolean up,down,left,right,space=false,enter,p;
	public boolean one,two,three,four;
	
	public KeyManager(){
		keys=new boolean[256];
	}
	
	public void tick(){
		up=keys[KeyEvent.VK_W];
		down=keys[KeyEvent.VK_S];
		left=keys[KeyEvent.VK_A];
		right=keys[KeyEvent.VK_D];
		space=keys[KeyEvent.VK_SPACE];
		enter=keys[KeyEvent.VK_ENTER];
		p=keys[KeyEvent.VK_P];
		one=keys[KeyEvent.VK_1];
		two=keys[KeyEvent.VK_2];
		three=keys[KeyEvent.VK_3];
		four=keys[KeyEvent.VK_4];
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()!=KeyEvent.VK_SPACE)
			keys[e.getKeyCode()]=true;
		//else
			//keys[e.getKeyCode()]=false;
		//System.out.println("Presed!");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(!keys[e.getKeyCode()])
			if(e.getKeyCode()==KeyEvent.VK_SPACE){
				try {
					Thread.sleep(1000);
					keys[e.getKeyCode()]=!keys[e.getKeyCode()];
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else
				keys[e.getKeyCode()]=false;
		else
			keys[e.getKeyCode()]=false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
