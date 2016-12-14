package shifumi.strategy;

import shifumi.*;

public class PaperStrategy implements Strategy{

    /**
     * @see shifumi.strategy.Strategy#getShape
     */
    public Shape getShape(){
	return Shape.PAPER;
    }
}
