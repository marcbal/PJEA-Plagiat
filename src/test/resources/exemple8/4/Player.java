package shifumi;

import shifumi.strategy.*;

public class Player {

    private String name;
    private Strategy strat;
    private int score;

    /**
     * Create a new player
     *
     * @param name The name of the player
     *
     * @param strat The strategy of the player
     */
    public Player(String name, Strategy strat) {
	this.score = 0;
	this.name = name;
	this.strat = strat;
    }

    /**
     * Return the name of the player
     *
     * @return The name of the player
     */
    public String getName() {
	return this.name;
    }

    /**
     * Return the shape played by the player
     *
     * @return The shape
     */
    public Shape getShape() {
	return this.strat.getShape();
    }

    /**
     * Return the score of the player
     *
     * @return The score of the player
     */
    public int getScore() {
	return this.score;
    }

    /**
     * Add <code>n</code> points to the player
     *
     * @param n The number of point to add
     */
    public void addScore(int n) {
	this.score += n;
    }
}
