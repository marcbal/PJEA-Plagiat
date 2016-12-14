package ppc.util;

import ppc.util.strat.*;
import ppc.util.Shape;

// compilation depuis le répertoier src : javac -d ../classes ./ppc/util/Player.java

public class Player{

	// ATTRIBUTS
	/* contains the player's strategy */
	private String name;
	
        /* contains the player's strategy */
	private Strategy s;
	
	/* contains the player score */
	private int score;


	// METHODES

	/**
	 * Builds a new object of type Player with the strategy which will be used in the game. 
	 * 
	 * @param strat
	 *            the Strategy object to be the strategy used the game.
	 * @param name
	 *            the String object to be the name of the player.
	 */
	public Player(Strategy strat, String name){
		this.s = strat;
		this.name = name;
		this.score = 0;
	}

	
	/**
	 * Gets the player strategy.
	 * 
	 * @return <code>this.s</code> of type Strategy the player's strategy.
	 */
	public Strategy getStrategy(){
		return this.s;
	}
	
	
	/**
	 * Gets the player's name.
	 * 
	 * @return <code>this.name</code> of type String the player's name.
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Gets the player's score.
	 * 
	 * @return <code>this.score</code> of type int the player's score.
	 */
	public int getScore(){
		return this.score;
	}
	
	
	/**
	 * Adds 1 point to the game score.
	 * 
	 * @return <code>score</code> of type int the new player's score (which has been incremented by 1).
	 */
	public int addScore(){
		this.score +=1;
		return this.score;
	}	


	/**
	 * Plays one shape of this player.
	 * 
	 * @return <code>a shape</code>
	 *            a Shape object played by this player according to his/her strategy.
	 */
	public Shape play(){
		Strategy playerStrat = this.getStrategy();
		Shape coup = playerStrat.get();
		return coup;
	}
	
	
	/**
	 * Compares a given object to this Player.
	 * 
	 * @param <code>o</code>
	 *            an Object object compared to this Player.
	 * 
	 * @return <code>bool</code>
	 *            a boolean corresponding to the comparison result. It is true if the two object are two equivalent Player objects. Else it is false.
	 */
	public boolean equals(Object o){
		try{
			Player other = (Player)o;
			boolean equalStrat = this.getStrategy().equals(other.getStrategy());
			boolean equalName = this.getName().equals(other.getName());
			boolean equalScore = this.getScore() == other.getScore();
			return equalStrat && equalName && equalScore;
		}
		catch(ClassCastException e){
			return false;
		}
	}

}
