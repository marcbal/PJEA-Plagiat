package game;
import game.Strategy;
/**
* @version 1.0
*/
/**
 *class of rock-paper strategy
 */

public class RockPaperStrategy implements Strategy{
    private Strategy Rock;
    private Strategy Paper;
    boolean b;
    /**
     *constructor
     */
    public RockPaperStrategy(){
	this.b=true;
	this.Paper = new PaperStrategy();
	this.Rock= new RockStrategy();
    }
    /**
     *methode that returns the shape chosen by the player
     *@return <code>Shape</code>
     */
    public Shape get(){
	if(b){
	    b=!b;
	    return this.Paper.get();}
	else{
	    b=!b;
	    return this.Rock.get();
	}
    }
}	
