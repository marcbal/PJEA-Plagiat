package PFC_pack;

import PFC_pack.Strategy;
import PFC_pack.Shape;

/**
 * class Player
 *
 * @version 1.0
 */

public class Player {

	// Attribute of the class Player

	private String name;
	private Strategy strategy;
	private int score; 

	// Constructor

	public Player(String name, Strategy strategy) {
		this.name = name;
		this.strategy = strategy;
		this.score = 0;
	}

	// Methods of the class Player

	/**
	 * Gives the name of the player
	 * @return the name of the player
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * returns the shape the player played 
	 * @return the shape that has been played 
	**/
	public Shape getShape() {
		return this.strategy.get();
	}

	/**
	 * returns an int that is the score of the player
	 * @return the score of the player
	**/
	public int getScore() {
		return this.score;
	}

	/**
	 * changes the score of the Player
	 * @param int the score that must be added to the score
	**/
	public void addScore(int i) {
		this.score += i;
	}

	/**
	 * returns a string that is describing the player
	 * @return the description of the player
	**/
	public String toString() {
		return "The player " + this.name + " is currently using " + this.strategy + " and has a score of "+ this.score + " points.";
	}
}
