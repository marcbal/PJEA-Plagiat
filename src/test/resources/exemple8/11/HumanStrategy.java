package game;
import game.Shape;
import game.Player;
import java.util.*;
/**
* @version 1.0
*/

public class HumanStrategy implements Strategy{
    Scanner scanner = new Scanner(System.in);


   /**
    * Constructor
    *The player can shoose from ROCK PAPER SCISSOR
    *p refers to PAPER
    *r refers to ROCK
    *s refers to SCISSOR
    */
    public Shape get(){
	while(true){
	    System.out.println("A votre tour de jouer, tapez p, s ou r pour paper, scissors ou rock");
	    String input = scanner.next();
	    if (input.equals("p")){
		return Shape.PAPER;
	    }
	    else if (input.equals("s")){
		return Shape.SCISSOR;
	    }
	    else if (input.equals("r")){
		return Shape.ROCK;
	    }
	    System.out.println("p, s et r sont les seuls entrées valides, p= paper, s=scissors, r=rock");
	}
    }
}
