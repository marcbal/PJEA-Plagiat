package game;

public class Game {

    private Player player1;
    private Player player2;
    private int nb_coups;

    /**
     * Game's class constructor
     *
     * @param player1 The first player of the Game
     * @param player2 The second player of the Game
     * @param nb_coups The numbers of rounds which will be played during the Game
     */
    public Game(Player player1,Player player2,int nb_coups) {
	this.player1 = player1;
	this.player2 = player2;
	this.nb_coups = nb_coups;
    }

    /**
     * Called by the function play, this private method allows the Game to make one round
     */
    private void playOneRound() {
	Shape s1 = this.player1.play();
	Shape s2 = this.player2.play();
	int res = s1.compare(s2);
	this.setPoints(res);
	System.out.println(s1.toString() + " vs " + s2.toString());
	this.nb_coups--;
    }

    /**
     * This predicate tells if the Game is over
     *
     * @return <code>true</code> if the game if finished, <code>false</code> if not
     */
    private boolean isFinished() {
	return this.nb_coups == 0;
    }

    /**
     * Sets the points to the winner of the round, adds 2 points to the winner of the round, of 1 point to both players in case of equality
     *
     * @param comp The comparator, if 1, player1 won the round, if 0 it's an equality, if -1, means that player2 won the round
     */
    private void setPoints(int comp) {
	if (comp == 1) {
	    player1.addScore(2);
	}
	else if (comp == 0) {
	    player1.addScore(1);
	    player2.addScore(1);
	}
	else {
	    player2.addScore(2);
	}
    }


    /**
     * Displays the winner of the game (the player with the highest score)
     */
    private void displayWinner() {
	if (player1.getScore() > player2.getScore())
	    System.out.println(player1.toString());
	else if (player1.getScore() < player2.getScore())
	    System.out.println(player2.toString());
	else
	    System.out.println("C'est un match nul !!");
    }

    /**
     * This method runs the game of Rock-Paper-Scissors
     */
    public void playAGame() {
	while (! this.isFinished()) {
	    playOneRound();
	}
	displayWinner();
    }
}
