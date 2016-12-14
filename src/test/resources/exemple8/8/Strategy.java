package game.util;
import game.util.strategies.*;
import game.Shape;

public interface Strategy{


	/*
	Get the shape according to a strategy
	@return The shape getted by the strategy
	*/
	public Shape get();

}
