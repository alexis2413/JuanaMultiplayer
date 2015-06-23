/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.game.chat;

import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import pe.edu.pucp.game.Launcher;

/**
 *
 * @author alulab14
 */
public class ChatFrame extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextArea textArea;
    private JScrollPane scrollPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ChatFrame frame = new ChatFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ChatFrame() {
        initialize();
    }

    private void initialize() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setBounds(141, 19, 224, 23);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblMessage = new JLabel("Message");
        lblMessage.setBounds(67, 23, 64, 14);
        contentPane.add(lblMessage);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(43, 127, 362, 97);
        contentPane.add(scrollPane);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        scrollPane.setViewportView(textArea);

        JButton btnSend = new JButton("Send");
        btnSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    if (!textField.getText().isEmpty()) {
                        Launcher.proxy.addChatText(textField.getText() + "\n");
                        //textArea.append(textField.getText() + "\n");
                        //textArea = Launcher.proxy.getChatText();
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        btnSend.setBounds(169, 78, 89, 23);
        contentPane.add(btnSend);

        ChatThread ct = new ChatThread(textArea);
        ct.start();
    }
}
