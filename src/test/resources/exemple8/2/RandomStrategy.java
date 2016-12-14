package PFC_pack;

import java.util.Random;
import PFC_pack.Shape;

/**
 * class RandomStrategy
 *
 * @version 1.0
 */


public class RandomStrategy implements Strategy {

	/**
	 * returns a random shape
	 * @return a random shape
	 */
	public Shape get() {
		Random rand = new Random();
		int res = rand.nextInt(3);
		if (res == 0) {
			return Shape.SCISSORS;
		}
		if (res == 1) {
			return Shape.PAPER;
		} else {
			return Shape.ROCK;
		}
	}
}
