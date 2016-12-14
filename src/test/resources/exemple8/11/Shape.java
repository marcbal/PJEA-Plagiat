package game;
/**
* @version 1.0
*/
/**
 *enum Shape describes the choice if it's : paper or rock or scissor
 */

public enum Shape{
    
    PAPER,SCISSOR,ROCK;
    
    
    /**
     *methode compares choices of two players
     *@param o <code>Shape</code>
     *@return int the result of one round of the game
     */
    
    public int compare(Shape o){
	if (this.equals(o)){
	    return 0;
	}
	if(this.equals(ROCK)){
	    if (o.equals(SCISSOR)){
		return 1;
	    }
	    else {
		return -1;
	    }
	}
	else if (this.equals(SCISSOR)){
	    if (o.equals(ROCK)){
		return -1;
	    }
	    else{
		return 1;
	    }
	}
	else {
	    if (o.equals(ROCK)){
		return 1;
	    }
	    else{
		return -1;
	    }
	}	
    }
}


    

    
