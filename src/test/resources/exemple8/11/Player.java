package game;
import java.util.Random;
import game.*;
/**
* @version 1.0
*/

public class Player {
    private int nbPoints;
    private Strategy choice;
    private String name;
    
    
    /**
     * Constructor
     *  
     */
	
    public Player (String name, Strategy strat){
	this.name = name;
	this.choice = strat;
	this.nbPoints = 0;
	
    }
    
    
    /**
	 * methode adds score to the player
	 * @param points to add int
	 *       
	 */
    public void addScore(int points){
	nbPoints+=points;
    }
    /**
     *@return int le nombre de points 
     */
    
    public int getScore(){
	return nbPoints;
    }
    /**
     * methode sets the number of the points
     * @param nbPoints
     *    
     */
    public void setNbPoints(int nbPoints){
	this.nbPoints= nbPoints;
    }
    
    /**
     *methode that add score to a player due to his movement
     *@return <code>Strategy</code> the strategy shosen by the player
     */
    public Strategy getStrategy(){
	return this.choice;
    }
    /**
     *this methode returns the name of the player
     * @return  String the name of the player 
     */
    public String getName(){
	return this.name;
    }
    
    /**
     * yes! we can set the name from time to time
     * @param name <code>String</code> , the name of the player 
     *  
     */
    public void setName(String name){
	this.name= name;
    }
    
    /**
     *the usual methode
     *@return String all the info we can need
     */
    public String toString(){
	return "Player [nbPoint=" +nbPoints+", choice=" +choice+", name=" + name +"]";
    }
    
}
