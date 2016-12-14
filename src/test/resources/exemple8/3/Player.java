package pfc;

import pfc.Shape;

public class Player {

	private int score;
	private String name;
	private Strategy strat;

	/**
	* Constructor of Player class
	* @param name, the name of the player
	* @param strategy the strategy of the player
	**/
	public Player(String name,Strategy strategy){
		this.score = 0;
		this.strat = strategy;
		this.name = name;
	}


	/**
	* return the score of the player
	*
	* @return the score of the player
	**/
	public int getScore(){
		return this.score;
	}

	/**
	* return the name of the player
	*
	* @return the name of the player
	**/
	public String getName(){
		return this.name;
	}

	/**
	* add some points to the score of the player
	*
	* @param points the points to add to the score of the player
	**/
	public void addScore(int points){
		this.score+=points;
	}

	/**
	* return the shape of the player
	*
	* @return the shape of the player
	**/
	public Shape play(){
		return this.strat.get();
	}

	/**
	*
	* @return a string representing the player and his points
	*
	**/
	public String toString(){
		return this.name + "("+this.score+")";
	}

}
