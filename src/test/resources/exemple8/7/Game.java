/**
 * Game class of TP7
 * @version 09/03/16
 */

package rps ;
import rps.util.* ;

public class Game {

    // ATTRIBUTS
	private int nbTours ;
	private PlayerInterface p1 ;
	private PlayerInterface p2 ;



    // CONSTRUCTEUR
    /**
    *
    */
	public Game (int nbTours, PlayerInterface p1, PlayerInterface p2) {
		this.nbTours = nbTours ;
		this.p1 = p1 ;
		this.p2 = p2 ;
	}

   // METHODES
	/**
	* simulates one round of the game
	*/
	private void playOneRound() {
		Choice c1 = this.p1.play() ;
		Choice c2 = this.p2.play() ;
		int cmp = c2.compare(c1) ;
		System.out.print(this.p1.getName()+ " plays " + c1.toString() + " and "+this.p2.getName()+" plays " + c2.toString()+ "\n");
		givePoints(cmp);
		if (cmp == 1){
			System.out.print(this.p1.getName()+" wins this round"+ "\n");
			System.out.print("Score : "+ this.p1.getName() +" "+ this.p1.getPoints() + "-" + this.p2.getPoints() +" "+ this.p2.getName() + "\n");
		}
		 else {
			 if (cmp == -1){
			System.out.print(this.p2.getName()+" wins this round"+ "\n");
			System.out.print("Score : "+ this.p1.getName() +" "+ this.p1.getPoints() + "-" + this.p2.getPoints() +" "+ this.p2.getName() + "\n");
			 }
			else {
			System.out.print("Draw"+ "\n");
			System.out.print("Score : "+ this.p1.getName() +" "+ this.p1.getPoints() + "-" + this.p2.getPoints() +" "+ this.p2.getName() + "\n");
			}
		}
	}

	/**
	* add points to the players depending on the result of the round
	* @param cmp the result of the method compareTo between the choices of the players
	*/
	private void givePoints (int cmp) {
		switch (cmp) {
			case 1:
				this.p1.addPoints(2) ;
				break;
			case -1:
				this.p2.addPoints(2);
				break;
			case 0:
					this.p1.addPoints(1) ;
					this.p2.addPoints(1) ;
					break;
		}
	}

	/**
	* @return the player who won
	*/
	public PlayerInterface getWinner() throws IllegalStateException {
		if (this.p1.getPoints() > this.p2.getPoints()) {
			return this.p1;
		}
		else {
			if (this.p2.getPoints() > this.p1.getPoints()) {
				return this.p2;
			}
			else {
				return new Player("Draw");
			}
		}
	}

	/**
	* Simulates an entire game
	*/
	public void play() {
		for (int i = 0 ; i < this.nbTours ; i++ ) {
			this.playOneRound() ;
		}
		if (this.getWinner().getName() != "Draw"){
			System.out.printf(this.getWinner().getName()+ " wins !\n");
		}
		else {
			System.out.printf(this.getWinner().getName()+" no winner this time \n");
		}
	}


	public static void main (String[] args) {
		System.out.print("Name of Player 1 : ?");
		PlayerInterface p1 = new PlayerInteractif(Input.readString());
		System.out.println("lue  => " + p1.getName());
		PlayerInterface p2;
		switch (args[0]) {
				case "0":
					p2 =  new Player("IA") ;
					break;
				case "1":
					p2 =   new PlayerPaper("IA");
					break;
				case "2":
					p2 = new PlayerRock("IA");
						break;
				case "3":
				 p2 = new PlayerScissors("IA");
						break;
				case "4":
					p2 = new PlayerSequence("IA");
						break;
				default :
				  p2 = new Player("IA");
				}
			Game g = new Game (10, p1, p2) ;
		g.play();
	}
}
