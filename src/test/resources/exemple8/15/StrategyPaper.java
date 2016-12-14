package ppc.util.strat;

import ppc.util.Shape;
import ppc.util.strat.Strategy;

// compilation depuis le répertoier src : javac -d ../classes ./ppc/util/strat/StrategyPaper.java

public class StrategyPaper implements Strategy{


	// METHODES
	/*
	 * Builds an instance of type StrategyPaper.
	 */
	//public StrategyPaper();
	
	
	/**
	 * Gets the resulting shape according to this strategy.
	 * 
	 * @return <code>a shape</code> of type Shape the shape obtained by this Strategy.
	 */
	public Shape get(){
		return Shape.getShape("paper");
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
		return (o instanceof StrategyPaper );
	}
}
