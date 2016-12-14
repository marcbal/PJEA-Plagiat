package game;
import game.Strategy;
/**
* @version 1.0
*/
/**
 *class of rock strategy
 */

public class RockStrategy implements Strategy{
    /**
     *methode that returns the shape chosen by the player
     *@return <code>Shape</code>
     */
    public Shape get(){
	return Shape.ROCK;
    }
}
