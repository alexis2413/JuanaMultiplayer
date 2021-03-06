package pe.edu.pucp.game.entities.creatures.enemies;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import pe.edu.pucp.game.Game;
import pe.edu.pucp.game.attacks.Attack;
import pe.edu.pucp.game.entities.creatures.Creature;
import pe.edu.pucp.game.states.CombatState;
import pe.edu.pucp.game.states.State;

@SuppressWarnings("serial")
public abstract class Enemy extends Creature implements Serializable{
//public abstract class Enemy extends Creature{
        private ArrayList<Attack> attacks = new ArrayList<>();
    
	public Enemy(Game game, double x, double y, double width,double height) {
		super(game,x,y,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT);
		// TODO Auto-generated constructor stub
                Attack attack1=new Attack(null,1,"Hyper Beam",3);
                Attack attack2=new Attack(null,1,"Nuclear Bomb",2);
                Attack attack3=new Attack(null,1,"Omnislash",1);
                Attack attack4=new Attack(null,1,"Splash",0);
                ////////////////////////
                attacks.add(attack1);
                attacks.add(attack2);
                attacks.add(attack3);
                attacks.add(attack4);
	}
	
	public Enemy(double x, double y, double width,double height) {
		super(x,y,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT);
		// TODO Auto-generated constructor stub
                Attack attack1=new Attack(null,1,"Hyper Beam",3);
                Attack attack2=new Attack(null,1,"Nuclear Bomb",2);
                Attack attack3=new Attack(null,1,"Omnislash",1);
                Attack attack4=new Attack(null,1,"Splash",0);
                ////////////////////////
                attacks.add(attack1);
                attacks.add(attack2);
                attacks.add(attack3);
                attacks.add(attack4);
	}
 
	public Enemy(){}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		/*moveCounter++;
		if(moveCounter==delay){
			randomMove();
			move();
			moveCounter=0;	
		}*/
		if(collision()){
                    //((CombatState) game.getCombatState()).setEnemy(this);
                    CombatState combatState=new CombatState(game);
                    combatState.setEnemy(this);
                    State.setState(combatState);
		}
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	

	public int getMoveCounter() {return moveCounter;}
	public void setMoveCounter(int moveCounter) {this.moveCounter = moveCounter;}
	public int getDelay() {return delay;}
	public void setDelay(int delay) {this.delay = delay;}
        public ArrayList<Attack> getAttacks(){return attacks;}
        public void setAttacks(ArrayList<Attack> attacks){this.attacks=attacks;}

}
