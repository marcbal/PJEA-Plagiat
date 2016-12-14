package pfc;

import pfc.Shape;
import java.util.Random;

public class RandomStrategy implements Strategy {


	public Shape get(){
		java.util.Random rand = new Random();
		return Shape.values()[rand.nextInt(Shape.values().length)];
	}


}
