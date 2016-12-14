package game;
import game.*;
import util.*;

public class Main {
    
    public static void main(String[] args) {
	
	Strategy[] strategies = {new RandomStrategy(),new PaperStrategy(),new RockStrategy(), new ScissorStrategy()};
	
	if (args.length != 1) {
	    System.out.println("Usage: java -jar game.jar <numéro de stratégie>");
	    System.out.println("0->Random Strategy, 1->Paper Strategy, 2->RockStrategy, 3->Scissor Strategy");
	} else {
            try{
                Integer nbStrat = Integer.parseInt(args[0]);
                if (0<=nbStrat && nbStrat<4) {
                    Strategy choosenStrategy = strategies[nbStrat];
                    Player computer = new Player(choosenStrategy,"Computer");
                    Player human = new Player(new InteractiveStrategy(),"Human");
                    Game game = new Game(human,computer);
                    game.playGame(10);
		    if (game.getWinner()!=null){
					
                    System.out.println("The winner is : " + game.getWinner().getName());
		    } else {
			System.out.println("The game is tie");
		    }
                }else {
		System.out.println("You should enter a valid strategy number for the computer strategy");	 
                }
            }catch (NumberFormatException e){
               System.out.println("You should give a number");
	    }
	}
    }
}
