package shifumi;


public enum Shape {

    ROCK, PAPER, SCISSORS;

    /**
     * A comparison function between two shapes.
     * Return -1 if <code>shape</code> beats <code>this</code>, 0 if equality, 1 if <code>this</code> beats <code>shape</code>
     *
     * @param shape The shape to compare
     *
     * @return -1, 0 or 1
     */
    public int compare(Shape shape) {
	if (this.ordinal()-shape.ordinal() == 0){
	    return 0;
	}
	else if(this.ordinal()-shape.ordinal() == -2){
	    return 1;
	}
	else if(this.ordinal()-shape.ordinal() == 2){
	    return -1;
	}
	else{
	    return this.ordinal()-shape.ordinal();
	}
    }
}
