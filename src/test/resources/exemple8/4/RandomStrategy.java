package shifumi.strategy;

import java.lang.Math;
import shifumi.*;

public class RandomStrategy implements Strategy{

    /**
     * @see shifumi.strategy.Strategy#getShape
     */
    public Shape getShape(){
	double rdm = Math.random()*3;
	if(rdm < 1){
	    return Shape.PAPER;
	} else if (rdm < 2){
	    return Shape.ROCK;
	} else {
	    return Shape.SCISSORS;
	}
    }
}
