/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.game.states;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.game.Game;
import pe.edu.pucp.game.Launcher;
import pe.edu.pucp.game.attacks.Attack;
import pe.edu.pucp.game.chat.ChatFrame;
import pe.edu.pucp.game.display.Display;
import pe.edu.pucp.game.entities.creatures.Creature;
import pe.edu.pucp.game.entities.creatures.Player;
import pe.edu.pucp.game.gfx.Assets;

public class LobbyState extends State {

    public Rectangle startButton = new Rectangle(150, 300, 80, 40);
    public Rectangle chatButton = new Rectangle(250, 300, 80, 40);
    public ArrayList<Rectangle> playerFrames = new ArrayList<Rectangle>();
    public int width = 400, height = 400, nPlayers = 8;
    public int nPlayer;

    public LobbyState(Game game) {
        super(game);
        for (int i = 0; i < nPlayers / 2; i++) {
            Rectangle frame = new Rectangle(50, 50 + i * 30, 150, 30);
            playerFrames.add(frame);
        }
        for (int i = 4; i < nPlayers; i++) {
            Rectangle frame = new Rectangle(200, 50 + (i - 4) * 30, 150, 30);
            playerFrames.add(frame);
        }
        
        Player player1 = new Player(1, 1);
        Attack attack1 = new Attack(null, 1, "Hyper Beam", 30);
        Attack attack2 = new Attack(null, 1, "Nuclear Bomb", 10);
        Attack attack3 = new Attack(null, 1, "Omnislash", 5);
        Attack attack4 = new Attack(null, 1, "Splash", 1);
        ArrayList<Attack> attacks = new ArrayList<Attack>();
        attacks.add(attack1);
        attacks.add(attack2);
        attacks.add(attack3);
        attacks.add(attack4);
        player1.setAttacks(attacks);
        try {
            Launcher.proxy.addPlayer(player1);
            nPlayer = Launcher.proxy.getNPlayers();
            game.setMultiplayerState(new MultiplayerState(game,nPlayer));
        } catch (RemoteException ex) {
            Logger.getLogger(LobbyState.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void tick() {
        try {
            if (buttonTick(startButton)) {
                Launcher.proxy.setMultiplayer(true);
            }
            if (buttonTick(chatButton)) {
                try {
                    ChatFrame window = new ChatFrame();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (Launcher.proxy.multiplayerStarted()) {
                State.setState(game.getMultiplayerState());
            }
        } catch (RemoteException ex) {
            Logger.getLogger(LobbyState.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void render(Display display) {
        Graphics g = display.getCanvas().getBufferStrategy().getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        try {
            for (int i = 0; i < Launcher.proxy.getPlayers().size(); i++) {
                g2d.drawImage(Assets.player4, playerFrames.get(i).x, playerFrames.get(i).y, 30, 30, null);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(LobbyState.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < playerFrames.size(); i++) {
            g2d.draw(playerFrames.get(i));
        }

        
        g2d.draw(startButton);
        g2d.draw(chatButton);
        g.drawString("Start",startButton.x, startButton.y);
        g.drawString("Chat",chatButton.x, chatButton.y);
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
