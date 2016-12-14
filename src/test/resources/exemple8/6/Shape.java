package game;

public enum Shape {

    /* The shapes that can be played by the Players */
    ROCK, PAPER, SCISSORS ;

    /**
     * Compares two Shapes to decide which one won
     *
     * @param s The Shape you want to compare
     * @return 1 if the first Shape won, -1 if the second one won, and 0 if the Shapes are the same
     */
    public int compare(Shape s) {
        if (this.ordinal() - s.ordinal() == 0)
	    return 0;
	else if (this.ordinal() - s.ordinal()== -2)
	    return 1 ;
	else if (this.ordinal() - s.ordinal()== 2)
	    return -1 ;
	return this.ordinal() - s.ordinal() ;
    }

    /**
     * Gives a String representation of the Shape
     *
     * @return A String which represents the Shape
     */
    public String toString() {
	return this.name();
    }
}
