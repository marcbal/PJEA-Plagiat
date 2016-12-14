package game;
import game.strats.*;

public class PFCMain {

    public static void main(String[] args) {
	if (args.length < 1) {
	    System.out.println("You need to choose a strategy between 0 and 3.") ;
	    java.lang.System.exit(1) ;
	}
	try {
	    Integer.parseInt(args[0]);
	}
	catch (Exception e){
	    System.out.println("This isn't a valid strategy.") ;
	    java.lang.System.exit(1) ;
	}
	int index = Integer.parseInt(args[0]);
	System.out.println("Hello there !") ;
    	System.out.println("You are going to play Rock-Paper-Scissors") ;
	HumanStrat strat1 = new HumanStrat();
	String Human = "Bertrand" ;
	if (args.length == 2)
	    Human = args[1] ;
	Player player = new Player(Human, strat1) ;
	StrategyInterface strat2 = getStrat(index);
	Player brandon = new Player("Brandon",strat2);
	Game agame = new Game(player, brandon, 10);
	agame.playAGame();
    }

    /**
     * This method gives the Strategy chosen by the player.
     *
     * @param index The number of the Strategy you want to play.
     * @return The strategy chosen.
     */
    private static StrategyInterface getStrat(int index) {
	switch(index) {
	case 0: return new RandomStrat();
	case 1: return new allPaperStrat();
	case 2: return new allRockStrat();
	case 3: return new allScissorsStrat();
	case 4: return new cycleStrat();
	case 5: return new HumanStrat();
	default: return new RandomStrat();
	}
    }
}
