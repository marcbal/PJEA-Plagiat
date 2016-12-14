package pfc;

public enum Shape {
	ROCK(1),
	PAPER(2),
	SCISSORS(3);

	private int value;

	/**
	* Constructor of Shape class
	*
	* @param value the value of the shape
	**/
	private Shape(int value){
		this.value=value;
	}

	/**
	* return the best shape
	*
	* @param s an other shape
	* @return -1 if the current shape is better than s, 1 if s if better than the current shape, and 0 if they are equals
	**/
	public int compare(Shape s){
		if (this.value == s.value){
			return 0;
		}
		else if ((this.value ==1 && s.value == 2)||(s.value ==1 && this.value == 2)){
			if (this.value == 1){
				return 1;
			}
			else {
				return -1;
			}
		}
		else if ((this.value ==1 && s.value == 3)||(s.value ==1 && this.value == 3)){
			if (this.value == 1){
				return -1;
			}
			else {
				return 1;
			}
		}
		else if ((this.value ==2 && s.value == 3)||(s.value ==2 && this.value == 3)){
			if (this.value == 3){
				return 	-1;
			}
			else {
				return 1;
			}
		}
		else {
			return 0;
		}
	}

}
