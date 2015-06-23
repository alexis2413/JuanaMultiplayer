/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.game.chat;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import pe.edu.pucp.game.Launcher;

/**
 *
 * @author alulab14
 */
public class ChatThread extends Thread {

    int pause = 500;
    JTextArea chatText;    

    public ChatThread(JTextArea chatText) {
        super();
        this.chatText = chatText;
    }

    public void run() {
        while (true) {
            try {
                chatText.setText(Launcher.proxy.getChatText().getText());
                Thread.sleep(pause);
            } catch (InterruptedException ex) {
                Logger.getLogger(ChatThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                Logger.getLogger(ChatThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
