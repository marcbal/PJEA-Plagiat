package ppc.util;

import ppc.util.Player;
import ppc.util.InvalidValueOfElementException;

// compilation depuis le répertoier src : javac -d ../classes ./ppc/util/Shape.java


public enum Shape{

	// LIST OF POSSIBLE VALUES
	ROCK,PAPER,SCISSORS;
	

	// METHODES
	
	/**
	 * Gets the wished value from the Shape enum class.
	 * :UC: use only this values : rock,scissors,paper. (or eventually the others ones you set by yourself.)
	 * If the value is not valid, the function throwns an InvalidValueOfElementException exception.
	 * 
	 * @param e
	 *            the String object to be the element (rock,scissors,paper by default).
	 *
	 * @return sh
	 *            the Shape object to be the corresponding element to e.
	 */
	public static Shape getShape(String elem) throws InvalidValueOfElementException{
		try {
			elem.toUpperCase();
			return Shape.valueOf(elem);
		}
		catch (EnumConstantNotPresentException e){
			throw new InvalidValueOfElementException();
		}
		//catch (IllegalArgumentException e){
		//	throw new InvalidValueOfElementException();
		//}
	}

	
	/**
	 * Gets the number of elements in the enum Shape class.
	 * 
	 * @return <code>nb</code> of type int the number if elements in Elements.
	 */
	public static int getNbElements(){
		return Shape.values().length;
	}
	
	
	/**
	 * Gets the next element of enum Shape from the current one.
	 * 
	 * @return <code>element</code> the next element.
	 */
	public Shape nextElement() throws InvalidValueOfElementException{
		try{
			int ind = s.ordinal();
			return Shape.values()[ (ind + 1) % (Shape.values().length) ];
		}
		catch (EnumConstantNotPresentException e){
			throw new InvalidValueOfElementException();
		}
		//catch (IllegalArgumentException e){
		//	throw new InvalidValueOfElementException();
		//}
	}
	
	
	/**
	 * Gets the previous element of enum Shape from the current one.
	 * 
	 * @return <code>element</code> the previous element.
	 */
	public Shape previousElement() throws InvalidValueOfElementException{
		try{
			int ind = this.ordinal();
			return Shape.values()[ (ind - 1) % (Shape.values().length) ];
		}
		catch (EnumConstantNotPresentException e){
			throw new InvalidValueOfElementException();
		}
		//catch (IllegalArgumentException e){
		//	throw new InvalidValueOfElementException();
		//}
	}
	
	
	/**
	 * Compares this Shape with an other Shape given in parameters.
	 *
	 * @param <code>other</code> of type Shape the shape to compare with this one.
	 * 
	 * @return <code>bool</code> of type boolean the result of the comparison. It is true if this Shape has a winning element. It is false else.
	 */
	public int compare(Shape other) throws InvalidValueOfElementException{
		try{
			if (this.equals(other)){
				return 0;
			}
			else{
				Shape next = this.nextElement();
				if (other.equals(next)) {
					return 1;
				}
				else {
					return -1;
				}
			}
		}
		catch (EnumConstantNotPresentException e){
			throw new InvalidValueOfElementException();
		}
		//catch (IllegalArgumentException e){
		//	throw new InvalidValueOfElementException();
		//}
	}
	

}
