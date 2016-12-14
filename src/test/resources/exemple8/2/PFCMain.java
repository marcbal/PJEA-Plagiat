package PFC_pack;

import PFC_pack.*;
import util.*;

/**
 * class PFCMain
 *
 * @version 1.0
 */

public class PFCMain {

	public static void main (String[] argv) {

		System.out.println("Entrez votre nom :");
		String name = Input.readString();
		int stratNb = Integer.parseInt(argv[0]);

		Strategy strategy = null;

		if (stratNb == 0) {
			strategy = new RandomStrategy();
		}
		if (stratNb == 1) {
			strategy = new PaperStrategy();
		}
		if (stratNb == 2) {
			strategy = new ScissorsStrategy();
		}
		if (stratNb == 3) {
			strategy = new RockStrategy();
		}
			
		Game game = new Game(name, new HumanStrategy(), "Computer", strategy);

		System.out.println("Choose a shape by writing its name (scissors, paper or rock).");
		System.out.println("The winner of a round will gets 2 points. The two players get 1 point");
		System.out.println("if there is a draw. There are 10 rounds to play.");
		System.out.println();

		for(int i = 0; i < 10; i++) {
			game.playOneRound();
			System.out.println();
		}

		System.out.println("The Winner is " + game.getWinner().getName() + " with a score of " + game.getWinner().getScore() + " points");
	}
}
