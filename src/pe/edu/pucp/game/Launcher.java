package pe.edu.pucp.game;

import java.io.Serializable;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.game.rmi.IServices;
//import pe.edu.pucp.game.rmi.IServices;

@SuppressWarnings("serial")
public class Launcher implements Serializable {

    public Launcher() {
    }
    public static Registry reg = null;
    public static IServices proxy = null;

    static {
        try {
            reg = LocateRegistry.getRegistry("192.168.205.112", 1099);
            proxy = (IServices) reg.lookup("MyRMIServer");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        Game game = new Game("Los Juanes de Juana", 400, 400, 1);
        game.start();
    }
}
