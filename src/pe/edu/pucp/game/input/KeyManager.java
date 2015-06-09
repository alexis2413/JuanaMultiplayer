package pe.edu.pucp.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

@SuppressWarnings("serial")
public class KeyManager implements KeyListener, Serializable {

    private boolean[] keys;
    public boolean up, down, left, right, space = false, enter, p;
    public boolean one, two, three, four;

    public KeyManager() {
        keys = new boolean[256];
    }

    public void tick() {
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        space = keys[KeyEvent.VK_SPACE];
        enter = keys[KeyEvent.VK_ENTER];
        p = keys[KeyEvent.VK_P];
        one = keys[KeyEvent.VK_1];
        two = keys[KeyEvent.VK_2];
        three = keys[KeyEvent.VK_3];
        four = keys[KeyEvent.VK_4];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        if(e.getKeyCode()==KeyEvent.VK_SPACE)
            System.out.println("presiono");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        keys[e.getKeyCode()] = false;
        if(e.getKeyCode()==KeyEvent.VK_SPACE)
            System.out.println("libero");
    }

    @Override
    public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

    }

    public boolean[]getKeys(){return keys;}
}
