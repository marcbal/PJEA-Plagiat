package game;
import game.Strategy;
/**
* @version 1.0
*/
/**
 *classe of scissor strategy
 */
public class ScissorStrategy implements Strategy{
    /**
     *methode that returns the shape chosen by the player
     *@return <code>Shape</code>
     */
    public Shape get(){
	return Shape.SCISSOR;
    }
}
