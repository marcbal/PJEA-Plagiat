package PFC_pack;

import PFC_pack.Shape;

/**
 * class PaperStrategy
 *
 * @version 1.0
 */


public class PaperStrategy implements Strategy {

	/**
	 * returns the shape inherent to the strategy
	 * @return the shape that'll always be used by the strategy
	 */
	public Shape get() {
		return Shape.PAPER;
	}
}
