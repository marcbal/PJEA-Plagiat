package game.strats;

import game.*;

public class cycleStrat implements StrategyInterface{
    private int index;

    /**
     * @see game.strats.StrategyInterface#get
     */
    public Shape get() {
	Shape shape = Shape.values()[index];
	this.index = (this.index+1)%Shape.values().length;
	return shape;
    }
}
