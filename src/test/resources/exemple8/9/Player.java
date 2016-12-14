/**
 * Player class represent a rpc player which can to be real or an IA.
 */
package rpc;
import rpc.*;
import rpc.strategies.*;
import rpc.util.*;
import java.io.IOException;

public class Player{
	/**
	 * The player's name
	 */
	String name;
	/**
	 * The player's strategie
	 */
	Strategie strat;
	/**
	 * The player's score
	 */
	int score;
	/**
	 * Player's constructor
	 */
	public Player(String name, Strategie strat){
		this.name=name;
		this.score=0;
		this.strat=strat;
	}
	/**
	 * Increment player's score to 1
	 */
	public void increment(){
		this.score++;
	}
	/**
	 * @return the player's name
	 */
	public String getName(){
		return this.name;
	}
	/**
	 * @return the player's score
	 */
	public int getScore(){
		return this.score;
	}
	/**
	 * @return the player's next Coup
	 */
	public Coup play() throws IOException{
		return this.strat.get();
	}
}
