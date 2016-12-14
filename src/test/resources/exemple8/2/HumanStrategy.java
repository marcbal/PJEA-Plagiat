package PFC_pack;

import PFC_pack.Shape;
import util.*;

/**
 * class PaperStrategy
 *
 * @version 1.0
 */


public class HumanStrategy implements Strategy {

	/**
	 * returns the shape chosen by the user
	 * @return Shape the shape chosen
	 */
	public Shape get() {
		String shapeChosen = Input.readString();
		if (shapeChosen.equals("scissors")) {
				return Shape.SCISSORS;
		}
		if (shapeChosen.equals("paper")) { 
				return Shape.PAPER;
		} else {
				return Shape.ROCK;
		}
	}
}
