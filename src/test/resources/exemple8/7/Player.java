/**
 * Player class of TP7
 * @version 09/03/16
 */

package rps.util ;
import rps.util.* ;

public class Player implements PlayerInterface {

    // ATTRIBUTS
	private String name ;
	private int points ;


    // CONSTRUCTEUR
    /**
    *
    */
	public Player (String name) {
		this.name = name ;
		this.points = 0 ;
	}

   // METHODES
	/**
	*  @return ROCK, PAPER or SCISSORS
	*/
	public Choice play() {
		return Choice.randomChoice() ;
	}

	/**
	* @param n the number of points we want to add
	* add n points to the player object
	*/
	public void addPoints(int n) {
		this.points += n ;
	}

	/**
	*  @return the number of points of the player object
	*/
	public int getPoints() {
		return this.points ;
	}

	/**
	*  @return the name of the player object
	*/
	public String getName() {
		return this.name ;
	}
}
