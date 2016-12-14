package game;
import game.strats.StrategyInterface;

public class Player {

    private int score;
    private String name;
    private StrategyInterface strat;

    /**
     * Constructor of Player's class
     *
     * @param name The name of the Player
     * @param strat The Strategy chosen by the player
     */
    public Player(String name , StrategyInterface strat) {
	this.score = 0;
	this.name = name;
	this.strat = strat;
    }

    /**
     * Adds the score to the Player
     *
     * @param points The number of points which will be added to the Player's score
     */
    public void addScore(int points) {
	this.score += points;
    }

    /**
     * Gives the score of the Player
     *
     * @return The score of the Player
     */
    public int getScore() {
	return this.score;
    }

    /**
     * Makes the Player play, choose a Shape
     *
     * @return The Shape which will be played by the Player
     */
    public Shape play() {
	return this.strat.get();
    }

    /**
     * Gives a String represention of the Player
     *
     * @return A String, which represents the Player
     */
    public String toString() {
	return this.name + " score : " + this.score;
    }

    /**
     * This method say if the two objects are equals.
     *
     * @param o The object you want to compare.
     *
     * @return true if the two objects are equals, false if not.
     */
    public boolean equals(Object o) {
	if (o instanceof Player) {
	    Player p = (Player) o;
	    return this.score == p.score && this.name.equals(p.name) && this.strat.equals(p.strat);
	}
	return false;
    }
}
