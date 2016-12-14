/**
 * Player class of TP7
 * @version 09/03/16
 */

package rps.util ;

public class PlayerInteractif implements PlayerInterface {

    // ATTRIBUTS
	private String name ;
	private int points ;
	private Choice lastPlay ;


    // CONSTRUCTEUR
    /**
    *
    */
	public PlayerInteractif (String name) {
		this.name = name ;
		this.points = 0 ;
		this.lastPlay = Choice.ROCK;
	}

   // METHODES
	/**
	*  @return ROCK, PAPER or SCISSORS
	*/
	public Choice play()  {
			System.out.print(this.name + " : Rock/Paper/Scissors ?");
			String answer = Input.readString();
			Choice res = Choice.ROCK;
			switch (answer) {
				case "Rock":
					this.lastPlay = Choice.ROCK;
					res = Choice.ROCK;
					break;
				case "Paper":
					this.lastPlay = Choice.PAPER;
					res= Choice.PAPER;
					break;
				case "Scissors":
					this.lastPlay = Choice.SCISSORS;
					res= Choice.SCISSORS ;
					break;
			}
			return res ;
	}

	/**
	* @param n the number of points we want to add
	* add n points to the player object
	*/
	public void addPoints(int n) {
		this.points += n ;
	}

	/**
	*  @return the number of points of the player object
	*/
	public int getPoints() {
		return this.points ;
	}

	/**
	*  @return the name of the player object
	*/
	public String getName() {
		return this.name ;
	}


}
