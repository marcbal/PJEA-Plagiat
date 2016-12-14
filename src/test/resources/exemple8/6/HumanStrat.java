package game.strats;

import game.*;
import util.Input ;

public class HumanStrat implements StrategyInterface {
    /**
     * @see game.strats.StrategyInterface#get
     */
    public Shape get() {
	String input ;
	System.out.print("You can play : rock, paper or scissors.\nYou can also quit with : quit\n> ") ;
	input = Input.readString() ;
	if (input.equals("rock"))
	    return Shape.ROCK;
	else if (input.equals("paper"))
	    return Shape.PAPER;
	else if (input.equals("scissors"))
	    return Shape.SCISSORS ;
	else if (input.equals("quit"))
	    java.lang.System.exit(0) ;
	System.out.println("\nThis isn't a valid shape.") ;
	return get() ;
    }
}
