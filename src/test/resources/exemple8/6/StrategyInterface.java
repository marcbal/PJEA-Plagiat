package game.strats;

import game.*;

public interface StrategyInterface {

    /**
     * Gets the Shape of the Strategy
     *
     * @return The Shape which should be played for the given Strategy
     */
    public Shape get();
}
