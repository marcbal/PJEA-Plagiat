package pfc;

import pfc.Player;
import pfc.Shape;

import pfc.Strategy;

public class Game{

	private int nbTours;
	private Player player1;
	private Player player2;

	/**
	* Constructor of Shape class
	*
	* @param nbtoursdejeu the number of tower of playing
	* @param name1 the name of the first player
	* @param strat1 the strategy of the first player
	* @param name2 the name of the second player
	* @param strat2 the stategy of the second player
	*
	**/
	public Game(int nbtoursdejeu, String name1, Strategy strat1, String name2, Strategy strat2 ){
		this.nbTours =nbtoursdejeu;
		this.player1 = new Player(name1,strat1);
		this.player2 = new Player(name2,strat2);
	}

	/**
	*
	* Play one round of the game
	*
	*/
	public void playOneRound(){
		Shape s1 = this.player1.play();
		Shape s2 = this.player2.play();
		int res = s1.compare(s2);
		this.givePoints(res);
	}

	/**
	*
	* Give the points to the players
	*
	* @param res the result of the comparison between the two shape
	*/
	public void givePoints(int res){
		if (res == 1){
			this.player2.addScore(2);
		}
		if (res==0){
			this.player2.addScore(1);
			this.player1.addScore(1);
		}
		if (res == -1){
			this.player1.addScore(2);
		}
	}

	/**
	*
	* Give the first player
	*
	* @return the first player
	*/
	public Player getPlayer1(){
		return this.player1;
	}

	/**
	*
	* Give the second player
	*
	* @return the second player
	*/
	public Player getPlayer2(){
		return this.player2;
	}

	/**
	*
	* return the name of the winner and his score
	*
	* @return the player (or null if nobody win)
	*
	*/
	public Player getWinner(){
		int score1 = this.player1.getScore();
		int score2 = this.player2.getScore();
		if (score1 < score2){
			return this.player2;
		}
		else if (score1 > score2){
			return this.player1;
		}
		else{
			return null;
		}
	}

}
