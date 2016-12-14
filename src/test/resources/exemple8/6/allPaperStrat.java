package game.strats;

import game.*;

public class allPaperStrat implements StrategyInterface{
    /**
     * @see game.strats.StrategyInterface#get
     */
    public Shape get() {
	return Shape.PAPER;
    }
}
