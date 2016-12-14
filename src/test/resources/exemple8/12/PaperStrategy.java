package game;
import game.Strategy;
/**
* @version 1.0
*/

/**
 *class of paper strategy
 */

public class PaperStrategy implements Strategy{
    /**
     *methode that returns the shape chosen by the player
     *@return <code>Shape</code>
     */
    public Shape get(){
	return Shape.PAPER;
    }
}
