package game;
import game.HumanStrategy;
import game.*;
import util.*;
/**
* @version 1.0
*/

public class Game {
    private Player player1;
    private Player player2;
    private Player winner = null;
    
    
    /**
     * Constructor
     */
    public Game (Player player1, Player player2){
	this.player1= player1;
	this.player2=player2;
    }
    
    /**
     * this methode starts a round of the game
     * 
     */
    public void playOneRound(){

	Shape ShapeOfplayer1 = player1.getStrategy().get();
	Shape ShapeOfplayer2 = player2.getStrategy().get();
	
	System.out.println(player1.getName() + " a joué " + ShapeOfplayer1);
	System.out.println(player2.getName() + " a joué " + ShapeOfplayer2);
	
	if(ShapeOfplayer1.compare(ShapeOfplayer2)> 0){
	    player1.addScore(3);
	    System.out.println(player1.getName() + " a gagné ");
	}
	    else if (ShapeOfplayer1.compare(ShapeOfplayer2)< 0){
		player2.addScore(3);
		System.out.println(player2.getName() + " a gagné ");
	    }
	    else {
		System.out.println(" match null ");
		player1.addScore(1);
		player2.addScore(1);
	    }
    }
    
    /**
     * this methode let the players play
     */
    public void play (){
	 int i=1;
	 while(player1.getScore()!=10 && player2.getScore()!=10){	
	     
	     System.out.println("round:"+i);
	     playOneRound();
	     i++;
	 }
	 if(player1.getScore()>player2.getScore()){
	     winner= player1;
	 }
	 else if (player1.getScore()<player2.getScore()){
	     winner= player2;
	 }
	 else{
	    winner=null;
	 }
    }

    
    /**
     * @return <code>Player</code> the winner of the game.
     *
     */
    public Player getWinner() {
	return winner;
    }
    
    /**
     *@return <code>Player</code> first  player 
     */
    public Player getPlayer1() {
	return player1;
    }
    
    /**
     * @return  <code>Player</code> first  player  
     */
public Player getPlayer2() {
    return player2;
}
    
    
    /**
     * Creation des deux joueurs, le joueur 1 fais un choix entre r,p,s et le joueur deux joue toujours SCISSORS,ROCK, PAPPER puis ROCK. 
     *   
 * @param args
 *       
 */
    public static void main(String[] args){
	Strategy S1 = new RandomStrategy();
	Strategy S2 = new RockStrategy();
	Strategy S3 = new PaperStrategy();
	Strategy S4 = new ScissorStrategy();
	Strategy S5 = new RockPaperStrategy();
	
	Strategy strat[]={S1,S2,S3,S4,S5};
	int i = Integer.parseInt(args[0]);
	Player player2= new Player("computer", strat[i-1]);//for computer
    
	System.out.println("Welcome to the game \n");
	System.out.println("her's the instructions:\n");
	System.out.println("the game ends when you or your oppenent gets 10 points \n");
	System.out.println("you have the shapes to choose from : PAPER--ROCK--SCISSOR\n");
	
	Player player1= new Player("joueur1", new HumanStrategy());// Human strategy
	
	
	Game game = new Game(player1,player2);
	game.play();
	
	System.out.println("the winner is:!!!!!!"+ game.getWinner());
    
    }
}
