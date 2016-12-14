package pfc;

import util.Input;
import pfc.Shape;

public class HumanStrategy implements Strategy {


	public Shape get(){
		int intLu;
		try {
			System.out.print(" Shape ? 0 for Paper, 1 for Rock, 2 for scissors ");
			intLu = Input.readInt();
			System.out.println("lu  => " + intLu);
		} catch (java.io.IOException e) {
		    //"exception... : la saisie n'est pas un entier";
		    // on peut par exemple ici fournir une valeur par défaut : 
		    intLu = 0;
			System.out.println("corrigé  => " + intLu);
		}
		switch(intLu){
			case 0 : return Shape.PAPER ;
			case 1 : return Shape.ROCK ;
			case 2 : return Shape.SCISSORS ;
			default : return Shape.PAPER;
		}
	}

}
