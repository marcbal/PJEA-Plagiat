package ppc;

import ppc.util.strat.*;
import ppc.util.Shape;
import ppc.util.Player;
import ppc.util.WrongNumberForThePlayerException;

// compilation depuis le répertoier src : javac -d ../classes ./ppc/Game.java


public class Game{
	
	// ATTRIBUTS
	
	/* contains the first player */
	private Player p1;
	
	/* contains the second player */
	private Player p2;
	
	/* contains the number of shapes needed to win the match */
	private int winning;
	

	// METHODES
	
	/**
	 * Builds a new object of type Game. Builds also the two player of the game.
	 * 
	 * @param <code>nameStratP1</code>
	 *            a Strategy object which is played by the first player in the new game.
	 * 
	 * @param <code>nameStratP2</code>
	 *            a Strategy object which is played by the second player in the new game.
	 */
	public Game(Strategy stratP1, String nameP1, Strategy stratP2, String nameP2, int win){
		this.p1 = new Player(stratP1, nameP1);
		this.p2 = new Player(stratP2, nameP2);
		this.winning = win;
	}
	
	
	/**
	 * Gets the wished player's name. 1 for the first player, 2 for the second one. 
	 * :UC: the param must be equal to 1 or 2, nothing else. An exception will be returned if it is a wrong integer.
	 * 
	 * @param <code>num</code> of type int the player's number.
	 * 
	 * @return <code>this.name</code> of type String the player's name.
	 */
	public String getName(int num) throws WrongNumberForThePlayerException{
		switch(num){
			case 1:
				return this.p1.getName();
			case 2:
				return this.p2.getName();
			default :
				throw new WrongNumberForThePlayerException();
		}
	}
	
	
	/**
	 * Gets the player corresponding to the number. 1for the first player, 2 for the second one. 
	 * :UC: the param must be equal to 1 or 2, nothing else. An exception will be returned if it is a wrong integer.
	 * 
	 * @param <code>num</code> of type int the player's number.
	 * 
	 * @return <code>this.name</code> of type String the player's name.
	 */
	public Player getPlayer(int num) throws WrongNumberForThePlayerException{
		switch(num){
			case 1: {
				return this.p1;
			}
			case 2: {
				return this.p2;
			}
			default : {
				throw new WrongNumberForThePlayerException();
			}
		}
	}
	
	
	/**
	 * Tells if this player is the winner of the current match.
	 *
	 * @param <code>p</code> of type Player the checked player.
	 * 
	 * @return <code>bool</code> of type bool the result. If the player's score is equals to the maximum of shape, boo is equal to true. Else bool is equal to false.
	 */
	public boolean isWinner(Player p){
		return p.getScore() == this.winning;
	}
	
	
	/**
	 * Plays one round of this game. This round contains the choice phase and the resolvation phase. 
	 * :Note: neither parameters nor returned value into this method.
	 * 
	 */
	public void oneRound(){
		Shape shapeOfP1 = this.p1.play();
		Shape shapeOfP2 = this.p2.play();
		switch (shapeOfP1.compare(shapeOfP2)){
			case -1 : {this.p2.addScore(); break;}
			case 1 : {this.p2.addScore(); break;}
			default : break; // normalement le dernier cas possible est 0, mais on ne sait jamais.
		}
	}
	
	
	/**
	 * Rules the match between player 1 and player 2. The match ends when one of the two player reaches the score given in parameter.
	 * 
	 * @return <code>winner</code> of type String the winner's name (P1 or P2).
	 */
	public String match(){
		while( ( this.isWinner(this.p1) ) || ( this.isWinner(this.p2) ) ){
			this.oneRound();
		}
		if(this.isWinner(this.p1)) return this.p1.getName();
		else return this.p2.getName();
	}
}
