package PFC_pack;

import java.lang.Enum;

/**
 * class Shape
 *
 * @version 1.0
 */

public enum Shape {

	SCISSORS ("scissors"),
	PAPER ("paper"),
	ROCK ("rock");

	// Attributes of the class Shape
	private String name;

	// Constructor
	Shape(String name) {
		this.name = name;
	}

	// Methods of the class Shape

	/**
	 * returns 1 if the shape tested is stronger than the current one, 0 if they're the same, -1 if the one tested is weaker than the current shape
	 * @param shape the shape that has to be tested
	 * @return int the result of the comparison stated above
	 **/
	public int compare(Shape shape) {
		int result = 5;		
		if (this.name == "scissors") {
			switch(shape.name) {
				case "scissors" :
					result = 0;
					break;
				case "paper" :
					result = 1;
					break;
				case "rock" :
					result = -1;
					break;
			}
		}
		if (this.name == "paper") {
			switch(shape.name) {
				case "scissors" :
					result = -1;
					break;
				case "paper" :
					result = 0;
					break;
				case "rock" :
					result = 1;
					break;
			}
		}
		if (this.name == "rock") {
			switch(shape.name) {
				case "scissors" :
					result = 1;
					break;
				case "paper" :
					result = -1;
					break;
				case "rock" :
					result = 0;
					break;
			}
		}
		return result;
	}
	
	/**
	 * returns a string that is giving the name of the shape
	 * @return the name of the shape
	**/
	public String toString() {
		return this.name;
	}
}
