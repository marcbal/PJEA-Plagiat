/**
 * This class implement the rps Game
 */

package rpc;
import rpc.*;
import rpc.strategies.*;
import rpc.util.*;
import java.io.IOException;

public class Game{
	/**
	 * the first player
	 */
	private Player player1;
	/**
	 * the second player
	 */
	private Player player2;
	/**
	 * the Game constructor
	 */
	public Game(Player p1,Player p2){
		this.player1=p1;
		this.player2=p2;
	}
	/**
	 * @return the first player
	 */
	public Player getPlayer1(){
		return this.player1;
	}
	/**
	 * @return the second player
	 */
	public Player getPlayer2(){
		return this.player2;
	}
	/**
	 * play One round and inscremente scores
	 */
	public void playOneRound() throws IOException{
		int cmp=0;
		Coup c1,c2;
		while(cmp==0){
			System.out.println("C'est le tour de "+this.player1.getName()+" :");
			c1=player1.play();
			System.out.println("C'est le tour de "+this.player2.getName()+" :");
			c2=player2.play();
			System.out.println(this.player1.getName()+" a joué "+c1.toString());
			System.out.println(this.player2.getName()+" a joué "+c2.toString());
			cmp=c1.comp(c2);
			if(cmp<0){
				player2.increment();
				System.out.println(player2.getName()+" remporte la manche.");
			}else if(cmp>0){
				player1.increment();
				System.out.println(player1.getName()+" remporte la manche.");
			}else{
				System.out.println("la manche continue.");
			}
		}
	}
	
}
