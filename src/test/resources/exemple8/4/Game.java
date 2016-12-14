package shifumi;

public class Game {

    private Player player0;
    private Player player1;
    private int nbRounds;

    /**
     * Create a new game of shifumi
     *
     * @param player0 The first player
     *
     * @param player1 The second player
     *
     * @param rounds The number of rounds to play
     */
    public Game(Player player0, Player player1, int rounds) {
	this.player0 = player0;
	this.player1 = player1;
	this.nbRounds = rounds;
    }

    /**
     * Play one round of shifumi
     */
    private void playOneRound() {
	this.nbRounds += -1;
	Shape shape0 = this.player0.getShape();
	Shape shape1 = this.player1.getShape();
	int comp = shape0.compare(shape1);
	if(comp == 0){
	    this.player0.addScore(1);
	    this.player1.addScore(1);
	}
	else if(comp > 0){
	    this.player0.addScore(2);
	}
	else{
	    this.player1.addScore(2);
	}
	System.out.println(shape0+" vs "+shape1);
    }

    /**
     * Play a game of shifumi
     */
    public void play(){
	while(this.nbRounds != 0){
	    this.playOneRound();
	}
	if(this.player0.getScore()>this.player1.getScore()){
	    System.out.println(this.player0.getName()+" won !");
	}
	else if(this.player0.getScore()<this.player1.getScore()){
	    System.out.println(this.player1.getName()+" won !");
	}
	else{
	    System.out.println("Draw");
	}
    }

}
