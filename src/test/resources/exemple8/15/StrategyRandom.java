package ppc.util.strat;

import java.util.Random;
import ppc.util.Shape;
import ppc.util.strat.Strategy;

// compilation depuis le répertoier src : javac -d ../classes ./ppc/util/strat/StrategyRandom.java

public class StrategyRandom implements Strategy{


	// METHODES
	
	/*
	 * Builds an instance of type StrategyRandom.
	 */
	//public StrategyRandom();
	
	
	/**
	 * Gets the resulting shape according to this strategy.
	 * Warning : if you change the elements avalable in the game, you will have to change some variables into this function!
	 * 
	 * @return <code>a shape</code> of type Shape the shape obtained by this Strategy.
	 */
	public Shape get(){
		Random rand = new Random();
		int alea = rand.nextInt(3);
		switch (alea){
			case 0 : return Shape.getShape("paper");
			case 1 : return Shape.getShape("rock");
			case 2 : return Shape.getShape("scissors");
		}
		return null;
	}
	

	/**
	 * Compares a given object to this Strategy.
	 * 
	 * @param <code>o</code>
	 *            an Object object compared to this Strategy.
	 * 
	 * @return <code>bool</code>
	 *            a boolean corresponding to the comparison result. It is true if the two object are two equivalent Strategy objects. Else it is false.
	 */
	public boolean equals(Object o){
		return (o instanceof StrategyRandom);
	}
}
