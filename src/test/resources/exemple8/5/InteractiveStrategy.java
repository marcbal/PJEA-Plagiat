
package game;
import game.*;
import util.*;


public class InteractiveStrategy implements Strategy{

    //Methods
    public Shape get(){
	System.out.println("You can choose between: Rock, Scissor, Paper");
        System.out.println("Choose your shape : ");
	Input inp = new Input();
        String s = inp.readString();
	if(Shape.Rock.isShape(s)){
	    return Shape.valueOf(s);
	} else {	    
	    System.out.println("YOU GAVE AN INVALID SHAPE NAME, PLEASE BE SERIOUS !");
	    return this.get();
	}
    }	
}

