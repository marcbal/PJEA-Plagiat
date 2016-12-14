package game.strats;

import game.*;

public class allRockStrat implements StrategyInterface{
    /**
     * @see game.strats.StrategyInterface#get
     */
    public Shape get() {
	return Shape.ROCK;
    }
}
