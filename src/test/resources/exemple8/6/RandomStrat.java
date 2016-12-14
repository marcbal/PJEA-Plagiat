package game.strats;

import game.*;
import java.util.Random;

public class RandomStrat implements StrategyInterface {
    /**
     * @see game.strats.StrategyInterface#get
     */
    public Shape get() {
	Random rnd = new Random();
	return Shape.values()[rnd.nextInt(Shape.values().length)];
    }
}
