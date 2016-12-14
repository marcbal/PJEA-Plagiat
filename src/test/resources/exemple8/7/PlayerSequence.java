/**
 * Player class of TP7
 * @version 09/03/16
 */

package rps.util ;
import rps.util.* ;

public class PlayerSequence  implements PlayerInterface {

    // ATTRIBUTS
	private String name ;
	private int points ;
	private Choice lastPlay ;


    // CONSTRUCTEUR
    /**
    *
    */
	public PlayerSequence (String name) {
		this.name = name ;
		this.points = 0 ;
		this.lastPlay = Choice.ROCK;
	}

   // METHODES
	/**
	*  @return ROCK, PAPER or SCISSORS
	*/
	public Choice play() {
		if (this.lastPlay == Choice.ROCK){
			this.lastPlay = Choice.PAPER;
			return Choice.PAPER;
		}
		else{
			if (this.lastPlay == Choice.PAPER){
				this.lastPlay = Choice.SCISSORS;
				return Choice.SCISSORS;
			}
			else {
				this.lastPlay = Choice.ROCK;
				return Choice.ROCK ;
			}
		}
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

	/**
	*  @return the last play of the player object
	*/
	public Choice getLastPlay(){
		return this.lastPlay;
	}
}
