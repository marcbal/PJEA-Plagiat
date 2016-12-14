/**
 * This is the main class which execute all methode requiered for rps game
 * 
 */
package rpc;
import rpc.*;
import rpc.strategies.*;
import rpc.util.*;
import java.io.IOException;
 

public class MainRPC{
	/**
	* This function ask utilisator to his strategie
	*/
	private static Strategie strat(String strategieNb) throws IOException{
		Strategie res=(Strategie)new HumanStrat();
		if(strategieNb.equals("0")){
			res=(Strategie)new AleaStrat();
		}else if(strategieNb.equals("1")){
			res=(Strategie)new PaperStrat();
		}else if(strategieNb.equals("2")){
			res=(Strategie)new BoucleStrat();
		}
		return res;
	}
	/**
	* This is the main procedure og rpc game
	*/
	public static void main(String args[]) throws IOException{
			int nb=10;
			String name1="Player";
			Player player1=new Player(name1,strat("human"));
			String name2="Computer";
			Player player2=new Player(name2,strat(args[0]));
			Game game= new Game(player1,player2);
			for(int i=0;i<nb;i++){
				game.playOneRound();
			}
			int score1=game.getPlayer1().getScore();
			int score2=game.getPlayer2().getScore();
			if(score1>score2){
				System.out.println(game.getPlayer1().getName()+" a gagné !");
			}else if(score1<score2){
				System.out.println(game.getPlayer2().getName()+" a gagné !");
			}else{
				System.out.println(" égualité.");
			}
		}
}
