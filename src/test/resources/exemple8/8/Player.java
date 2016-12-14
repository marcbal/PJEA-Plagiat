package game.util;

import game.util.strategies.*;
import game.*;

public class Player{


	private int score;
	private Strategy strategy;

	/*
	Creates a new player
	@param strategy The strategy of the player
	*/
	public Player(Strategy strategy){
		this.score=0;
		this.strategy=strategy;
	}

	/*
	Gets the score of the player
	@return score of the player
	*/
	public int getScore(){
		return this.score;
	}
	
	/*
	Player play a shape
	@return The shape the player has played
	*/
	public Shape play(){
		return this.strategy.get();
	}

	/*
	Update the score of the player
	@param points The points the player has won
	*/
	public void addScore(int points){
		this.score+=points;
	}

	/*
	Initialize the score at zero
	*/
	public void iniScore(){
		this.score=0;
	}
	
	

}
