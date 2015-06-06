package pe.edu.pucp.game.states;

import java.io.Serializable;

import pe.edu.pucp.game.Game;
import pe.edu.pucp.game.display.Display;

@SuppressWarnings("serial")
public abstract class State implements Serializable{
//public abstract class State{
	
	private static State currentState=null;
	protected int numberPlayer=1;
	
	public int getNumberPlayer() {
		return numberPlayer;
	}

	public void setNumberPlayer(int numberPlayer) {
		this.numberPlayer = numberPlayer;
	}

	public static void setState(State state){
		currentState=state;
	}
	
	public static State getState(){
		return currentState;
	}
	
	//CLASS
	protected Game game;
	public State(Game game){
		this.game=game;
	}
	public State(){}
	public abstract void tick();
	public abstract void render(Display display);
}
