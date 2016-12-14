package game;
import game.*;

public class Game{

    //Attributes
    private Player[] players;
    
    //Constructor
    /**
    * A game is defined by a array of 2 players
    * @param p1 the first player
    * @param p2 the second player
    */
    public Game(Player p1, Player p2){
        this.players = new Player[2];
        this.players[0]= p1;
        this.players[1]= p2;
    }

    //Methods
    /**
    * The array of the players of the game
    * @return A Player array
    */
    public Player[] getPlayers(){
        return this.players;
    }
    
    /**
    * Player 1 in the game is the 1st element of the players
    * @return String representing the name of the player1
    */
    public String getPlayer1Name(){
        return this.players[0].getName();
    }

    /**
    * Player 2 in the game is the 2nd element of the players
    * @return String representing the name of the player2
    */
    public String getPlayer2Name(){
        return this.players[1].getName();
    }    
    
    /**
    * Plays one round only between the 2players
    * The two players play and we increment the winner score
    * @return the Shape of the winner if there is or null otherwise
    */
    public Shape playOneRound(){
        Shape s1 = this.players[0].play();
        Shape s2 = this.players[1].play();
        int res = s1.compare(s2);
        if(res==1){
            System.out.println(this.getPlayer1Name()+" : "+s1.toString()+" wins against "+this.getPlayer2Name()+" : "+s2.toString());
            this.players[0].incrementScore(2);
            return s1;
        }
        if(res==-1){
            System.out.println(this.getPlayer2Name()+" : "+s2.toString()+" wins against "+this.getPlayer1Name()+" : "+s1.toString());
            this.players[1].incrementScore(2);
            return s2;
        }
        else{
            System.out.println(s1.toString()+" "+s2.toString()+" tie");
            this.players[0].incrementScore(1);
            this.players[1].incrementScore(1);
            return null;
        }
    }
    
    /**
    * Plays a RPS game for a given number of rounds
    * @param nbRound the choosen number of rounds
    * @return the Player if there is, or null if its a tie game
    */
    public Player playGame(int nbRound){
        for(int i=0; i<nbRound; i++){
            this.playOneRound();
        }
        return this.getWinner();
    }
    
    /**
    * The winner is the player with the bigger score at the end of the game
    * @return the Player if there is, or null if its a tie game
    */
    public Player getWinner(){
        if(this.players[0].getScore() < this.players[1].getScore()){
            return this.players[1];
        }
        if(this.players[0].getScore() > this.players[1].getScore()){
            return this.players[0];
        }
        else return null;
    }
}
