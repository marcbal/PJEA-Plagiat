package PFC_pack;

import PFC_pack.Player;
import PFC_pack.Strategy;
import PFC_pack.Shape;

/**
 * class Game
 *
 * @version 1.0
 */

public class Game {

	// Attributes of the class Game
	private Player player1;
	private Player player2;	
	
	// Constructor
	
	public Game(String name1, Strategy strategy1, String name2, Strategy strategy2) {
		this.player1 = new Player(name1, strategy1);
		this.player2 = new Player(name2, strategy2);	
	}

	// Methods of the class Game
	
	/**
	 * Prints the state of the round that has just been played
	 */
	public void playOneRound() {
		Shape play1 = this.player1.getShape();
		Shape play2 = this.player2.getShape();
		if ((play1.compare(play2)) > 0) {
			givePoints(1);
			System.out.println(this.getPlayer(1).getName() + " wins.");
		}
		if ((play1.compare(play2)) < 0) {
			givePoints(2);
			System.out.println(this.getPlayer(2).getName() + " wins.");
		}
		if ((play1.compare(play2)) == 0) {
			givePoints(0);
			System.out.println("Draw");
		}
	}

	/**
	 * Gives a number of points to the player(s) who won the round
	 * @param win the player that has won the round, 0 if both won the round
	 */
	public void givePoints(int win) throws IllegalArgumentException {
		if (win == 0) {
			this.player2.addScore(1);
			this.player1.addScore(1);
		}
		if (win == 1) {
			this.player1.addScore(2);
		}
		if (win == 2) {
			this.player2.addScore(2);
		}
	}

	/**
	 * Returns the player associated to the number one or two
	 * @param the int associated to the player
	 * @return the player associated to the nummber
	 */
	public Player getPlayer(int i) throws IllegalArgumentException {
		if (i == 1) {
			return this.player1;
		} 
		if (i == 2) {
			return this.player2;
		} else {
			throw new IllegalArgumentException("There are only two players");
		}
	}

	/**
	 * Returns the winner of the game or a string if there's none
	 * @return the winner of the game or a string if there's none
	 */
	public Player getWinner() {
		int score1 = this.player1.getScore();
		int score2 = this.player2.getScore();
		if (score1 > score2){
			return this.player1;
		}
		if (score2 > score1){
			return this.player2;
		} else {
			return this.player1;
		}
	}	
}
