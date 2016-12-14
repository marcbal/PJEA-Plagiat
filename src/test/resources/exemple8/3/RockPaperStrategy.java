package pfc;

import pfc.Shape;

public class RockPaperStrategy implements Strategy {

	private boolean b;

	public RockPaperStrategy(){
		this.b=true;
	}

	public Shape get(){
		if (b){
			b=!b;
			return Shape.PAPER;
		}
		else{
			b=!b;
			return Shape.ROCK;
		}
	}


}
