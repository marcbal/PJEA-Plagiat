package pfc;

import pfc.Player;
import pfc.Shape;
import pfc.Game;
import pfc.Strategy;

import util.Input;

public class PfcMain {

public static void main(String[] args) {

	String name1;
	String name2;
	Strategy strat2 = new RandomStrategy();
	int strat = Integer.parseInt(args[0]);
	switch(strat){
		case 3 : strat2 = new ScissorsStrategy(); 
		case 2 : strat2 = new RockStrategy();
		case 1 : strat2 = new PaperStrategy(); 
		case 4 : strat2 = new RockPaperStrategy(); 
		case 0 : strat2 = new RandomStrategy(); 
	}
	//Demande du prenom 
	System.out.print(" Prénom du joueur 1 ? ");
	name1 = Input.readString();
	System.out.println("lue  => " + name1);
	name2 = "AI";
	int nbTours=10;
	//Creation du jeu
	HumanStrategy strat1 = new HumanStrategy();
	Game jeu = new Game(nbTours,name1,strat1,name2,strat2 );
	// jeu
	int rang = 0;
	while(rang < nbTours){
		System.out.println("Tour "+ rang);
		jeu.playOneRound();
		rang++;
	}
	System.out.println("The winner is :" +jeu.getWinner());
}

}
