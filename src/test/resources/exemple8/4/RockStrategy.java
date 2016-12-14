package shifumi.strategy;

import shifumi.*;

public class RockStrategy implements Strategy{

    /**
     * @see shifumi.strategy.Strategy#getShape
     */
    public Shape getShape(){
	return Shape.ROCK;
    }
}
