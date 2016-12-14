package shifumi;

import shifumi.strategy.*;

public class Main{
    public static void main(String[] args){
	Strategy ia;
	if (Integer.parseInt(args[0]) == 0){
	    ia = new RandomStrategy();
	} else if(Integer.parseInt(args[0]) == 1){
	    ia = new PaperStrategy();
	} else if(Integer.parseInt(args[0]) == 2){
	    ia = new RockStrategy();
	} else {
	    System.out.println("You chose a wrong strategy for ia");
	    return;
	}
	Player player0 = new Player("Human", new HumanStrategy());
	Player player1 = new Player("Ia", ia);
	Game someGame = new Game(player0, player1, 10);
	someGame.play();
    }
}
