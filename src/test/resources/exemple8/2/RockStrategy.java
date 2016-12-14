package PFC_pack;

import PFC_pack.Shape;

/**
 * class RockStrategy
 *
 * @version 1.0
 */


public class RockStrategy implements Strategy {

	/**
	 * returns the shape inherent to the strategy
	 * @return the shape that'll always be used by the strategy
	 */
	public Shape get() {
		return Shape.ROCK;
	}
}
