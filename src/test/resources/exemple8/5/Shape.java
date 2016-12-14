package game;
import java.lang.*;

public enum Shape {
    //Constructor
    Rock,Scissor,Paper;

    //Methods
    /**
     * Compare two shapes
     * @param s - the Shape to compare
     * @return 1 if the current shape wins against the shape in parameter, -1 otherwise. If they are the same , returns 0
     */
     
    public int compare(Shape s) {
	if (this.ordinal()-s.ordinal() == -1) {
	    return 1;
	}else {
	    if(this.ordinal()-s.ordinal() == 0) {
	    return 0;
	    } else {
                if(this.ordinal() - s.ordinal() == 2) {return 1;}
                else{
                    return -1;
                    }
	    }
	}
    }
    
    /**
    * Test if the shape is a Shape
    * @param shape - the string to compare
    * @return boolean, True if shape is an element of Shape or False
    */
    public static boolean isShape(String shape){
        if(shape.equals(Shape.Rock.name()) || shape.equals(Shape.Paper.name()) || shape.equals(Shape.Scissor.name()) ){
            return true;
        }
        else{
        return false;
        }
    }

    /**
     * Returns a representation of a shape
     * @return a String representation of a shape
     */
    public String toString() {
	int i = this.ordinal();
	if (i==0) {
	    return "Rock";
	} else {
	    if (i==1) {
		return "Scissor";
	    } else {
		return "Paper";
	    }
	}
    }
}
