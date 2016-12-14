/**
 * Choice class of TP7
 * @version 09/03/16
 */

package rps.util ;
import rps.util.* ;
import java.util.Random;

public enum Choice {
	ROCK, PAPER, SCISSORS;




	/**
	*  @param c the Choice we want to compare to the choice object
	*  @return 1 if c defeats the choice object, 0 if the c and the choice object are the same, -1 else
	*/
	public int compare(Choice c) {
		if ((c==ROCK && this==PAPER) || (c== PAPER && this==SCISSORS) || (c==SCISSORS && this==ROCK)){
			return -1;
		} else {
			if ((this==ROCK && c==PAPER) || (this== PAPER && c==SCISSORS) || (this==SCISSORS && c==ROCK)){
				return 1 ;
			} else {
				return 0 ;
			}
		}
	}

	/**
	*  @return a Choice selected randomly
	*/
	public static Choice randomChoice() {
		Choice choiceTab[] = {ROCK, PAPER, SCISSORS} ;
		Random rand = new Random();
		int nb = rand.nextInt(3) ;
		return choiceTab[nb] ;
	}

}
