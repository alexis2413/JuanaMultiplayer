package pe.edu.pucp.game.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import pe.edu.pucp.game.Game;
import pe.edu.pucp.game.display.Display;
import pe.edu.pucp.game.entities.creatures.enemies.Enemy;
import pe.edu.pucp.game.gfx.Assets;

@SuppressWarnings("serial")
public class CombatState extends State{
	
	private Enemy enemy;
	private boolean playerTurn=true, playerAttacked=false;
	//private Attack attack;
	int delayEnemy=0,delayPlayer=0,maxDelay=30;
	public CombatState(Game game){
		super(game);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		//Turno
		if(playerTurn){
			if(playerAttacked==false){	
				if(game.getKeyManager().one){
					enemy.setHealth(enemy.getHealth()-1>0?enemy.getHealth()-1:0);
					if(enemy.getHealth()<=0){	//Finalizacion
						State.setState(game.getGameState());
					}
					game.getKeyManager().one=false;
					playerAttacked=true;
				}
				if(game.getKeyManager().two){
					enemy.setHealth(enemy.getHealth()-2>0?enemy.getHealth()-2:0);
					if(enemy.getHealth()<=0){	//Finalizacion
						State.setState(game.getGameState());
					}
					game.getKeyManager().two=false;
					playerAttacked=true;
				}
				if(game.getKeyManager().three){
					enemy.setHealth(enemy.getHealth()-3>0?enemy.getHealth()-3:0);
					if(enemy.getHealth()<=0){	//Finalizacion
						State.setState(game.getGameState());
					}
					game.getKeyManager().three=false;
					playerAttacked=true;
				}
				if(game.getKeyManager().four){
					enemy.setHealth(enemy.getHealth()-20>0?enemy.getHealth()-20:0);
					if(enemy.getHealth()<=0){	//Finalizacion
						State.setState(game.getGameState());
					}
					game.getKeyManager().four=false;
					playerAttacked=true;
				}
			}else{
				delayPlayer++;
				if(delayPlayer==maxDelay){
					playerAttacked=false;
					playerTurn=false;
					delayPlayer=0;
				}
			}
			if(enemy.getHealth()<=0){	//Finalizacion
				State.setState(game.getGameState());
			}
		}
		else{
			delayEnemy++;
			if(delayEnemy==maxDelay){
				playerTurn=true;
				delayEnemy=0;
				((GameState) game.getGameState()).getPlayer().setHealth
				(((GameState) game.getGameState()).getPlayer().getHealth()-2);
				if(((GameState) game.getGameState()).getPlayer().getHealth()<=0)//Game Over
					game.getDisplay().getFrame().dispatchEvent(new WindowEvent(game.getDisplay().getFrame(), WindowEvent.WINDOW_CLOSING));
			}
			if(((GameState) game.getGameState()).getPlayer().getHealth()<=0)//Game Over
				game.getDisplay().getFrame().dispatchEvent(new WindowEvent(game.getDisplay().getFrame(), WindowEvent.WINDOW_CLOSING));		
		}
	}

	@Override
	public void render(Display display) {
		// TODO Auto-generated method stub
		
		Graphics g= display.getCanvas().getBufferStrategy().getDrawGraphics();
		g.drawImage(Assets.juanaCombat,20,150,100,100,null);	
		double playerHealth=((GameState) game.getGameState()).getPlayer().getHealth();
		double playerMaxHealth=((GameState) game.getGameState()).getPlayer().getMaxHealth();
		double playerFraction=playerHealth/playerMaxHealth;		
		double enemyHealth=enemy.getHealth();
		double enemyMaxHealth=enemy.getMaxHealth();
		double enemyFraction=enemyHealth/enemyMaxHealth;
		g.setColor(Color.RED);
		g.fillRect(20, 130, (int)(playerFraction*100), 10);
		g.setColor(Color.BLACK);
		g.drawString("HP "+((GameState) game.getGameState()).getPlayer().getHealth(),20,130);
		g.drawImage(Assets.chickenCombat,200,150,100,100,null);
		g.drawString("HP "+enemy.getHealth(),200,130);
		g.setColor(Color.RED);
		g.fillRect(200, 130, (int)(enemyFraction*100), 10);
		g.setColor(Color.BLACK);
		
		if(playerTurn){
			g.drawString("Your Turn ",50,50);
			if(playerAttacked){
				g.drawString("Player used attack X",50,300);
				g.drawImage(Assets.angryJuana,20,150,100,100,null);
			}
		}
		else{//animacion del ataque del enemigo
			g.drawString("Enemy's Turn",50,50);
			g.drawString("Enemy used attack X",200,300);
			//g.drawImage(Assets.angryChicken,200,150,100,100,null);
			g.drawImage(Assets.fireChicken,200,150,100,100,null);		
		}
	}
	
	public Enemy getEnemy() {
		return enemy;
	}

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}

}
