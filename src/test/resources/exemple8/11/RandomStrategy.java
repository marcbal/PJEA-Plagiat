package game;
import game.Strategy;
import java.util.Random;
/**
* @version 1.0
*/

/**
 *class for random choice
 */

public class RandomStrategy implements Strategy{

    /**
     *methode that returns the random shape 
     *@return <code>Shape</code>
     */

    public Shape get(){

	 Random rand = new Random();

	 rand.nextInt(10);

	 Shape choice =Shape.values()[rand.nextInt(Shape.values().length)];
	 return choice;

    }
}	 

	 

	

   
	
    
