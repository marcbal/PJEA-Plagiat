package shifumi.strategy;

import shifumi.*;
import shifumi.util.Input;

public class HumanStrategy implements Strategy{

    /**
     * @see shifumi.strategy.Strategy#getShape
     */
    public Shape getShape(){
	System.out.println("Valid input : 'r' for ROCK, 's' for SCISSORS, 'p' for PAPER");
	System.out.println("Chose a Shape : ");
	String shapeChose = Input.readString();
	if (shapeChose.equals("r")){
	    return Shape.ROCK;
	} else if (shapeChose.equals("s")){
	    return Shape.SCISSORS;
	} else if (shapeChose.equals("p")){
	    return Shape.PAPER;
	} else {
	    System.out.println("Wrong input, select a valid input");
	    return this.getShape();
	}
    }
}
