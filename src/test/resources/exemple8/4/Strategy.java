package shifumi.strategy;

import shifumi.*;

public interface Strategy{

    /**
     * Return the next shape played according to the strategy
     *
     * @return The shape that will be played
     */
    public Shape getShape();
}
