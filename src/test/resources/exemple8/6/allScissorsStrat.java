package game.strats;

import game.*;

public class allScissorsStrat implements StrategyInterface{
    /**
     * @see game.strats.StrategyInterface#get
     */
    public Shape get() {
	return Shape.SCISSORS;
    }
}
