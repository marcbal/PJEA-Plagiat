package game.util.strategies;

import java.util.*;
import game.util.Strategy;
import game.Shape;


public class RandomShape implements Strategy{


	


	

	/*
	@see Strategy
	*/
	public Shape get(){
		Shape[] tab;
		tab=Shape.values();
		Random random;
		random=new Random();
		return tab[random.nextInt(3)];
	}

}
		


