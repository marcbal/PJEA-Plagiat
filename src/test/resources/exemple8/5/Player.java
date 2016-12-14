package game;
import game.*;

public class Player{

    //Attributes
    private Strategy strategy;
    private int score;
    private String name;
    
    //Constructor
    public Player(Strategy s, String name){
        this.strategy = s;
        this.score =0;
        this.name = name;
    }
    
    //Methods
    /**
    * A player score is defined by the number of round won
    * @return int corresponding to the player score
    */
    public int getScore(){
        return this.score;
    }
    
    /**
    * A player name
    * @return String representing the player's name
    */
    public String getName(){
        return this.name;
    }
    
    /**
    * A player strategy
    * @return Strategy following by player
    */
    public Strategy getStrategy(){
        return this.strategy;
    }
    
    /**
    * String representation of a player
    */
    public String toString(){
        return this.name+"s score is"+this.score+"with "+this.strategy; 
    }
    
    /**
    * Two players are equals whenever they have the same name, score and using the same strategy
    * @return boolean corresponding to the test if the player is equals to another one
    */
    public boolean equals(Object o){
        if (o instanceof Player){
            Player p2 = (Player) o;
            return this.name.equals(p2.name) && this.score == p2.score && this.strategy.equals(p2.strategy);
        }
        else{
            return false;
        }
    }
    
    /**
    * The score increasing by 1 if the move is nil, 2 if winning or 0 if losing
    * @param s the score earn by the move
    */
    public void incrementScore(int s){
        this.score+=s;
    }
    
    /**
    * Playing is the same as choosing a strategy and play a shape according to its
    * @return Shape played by the player following his stragegy
    */
    public Shape play(){
        return this.strategy.get();
    }
}
