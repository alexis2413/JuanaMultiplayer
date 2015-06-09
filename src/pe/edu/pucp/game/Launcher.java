package pe.edu.pucp.game;
import java.io.Serializable;
@SuppressWarnings("serial")
public class Launcher implements Serializable {
    public Launcher(){}
    public static void main(String[] args) {
        Game game = new Game("Los Juanes de Juana", 400, 400, 1);
        game.start();
    }
}
