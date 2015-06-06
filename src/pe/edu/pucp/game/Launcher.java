package pe.edu.pucp.game;

import java.io.Serializable;
//import java.util.Scanner;

@SuppressWarnings("serial")
public class Launcher implements Serializable{

	public Launcher(){}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game game=new Game("Los Juanes de Juana",400,400,1);
		game.start();
	}

}
