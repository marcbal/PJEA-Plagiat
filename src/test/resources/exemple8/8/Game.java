package game;

import game.util.*;
import game.util.strategies.*;



public class Game{
	public static Strategy[] strategies={new RandomShape(),new Paper(),new HumanPlay(),new Rock(),new Scissors()};
	private Player player1;
	private Player player2;

	/*
	creates a new game with 2 players who has his own strategy
	@param: stratP1 the strategy of player 1
	@param stratP2 the strategy of player 2
	*/
	public Game(Strategy stratP1,Strategy stratP2){
		this.player1=new Player(stratP1);
		this.player2=new Player(stratP2);
	}

	/*
	Plays one round of the game
	*/
	public void playOneRound(){
		System.out.println("Score de joueur 1: "+this.player1.getScore());
		System.out.println("Score de joueur 2: "+this.player2.getScore());
		Shape sh1=player1.play();
		Shape sh2=player2.play();
		System.out.println("Joueur 1 a joue "+sh1.name());
		System.out.println("Joueur 2 a joue "+sh2.name());
		this.compareShapes(sh1,sh2);
	}

	/*
	Plays the entire game
	@return A string which announce who has won or there is a null match
	*/
	public String playGame(){
		for(int i=0;i<10;i++){
			this.playOneRound();
		}
		if(this.player1.getScore()>this.player2.getScore()) return "Joueur 1 a gagné";
		else if(this.player2.getScore()>this.player1.getScore()) return "Joueur 2 a gagné";
		else return "égalité";
	}

	/*
	Compares the shapes of the player and update the scores
	@param sh1 The shape of Player 1
	@param sh2 The shape of Player 2
	*/
	public void compareShapes(Shape sh1,Shape sh2){
		if(sh1.ordinal()==(sh2.ordinal()+1)%3) this.player2.addScore(2);
		else if(sh2.ordinal()==(sh1.ordinal()+1)%3) this.player1.addScore(2);
		else{
			this.player1.addScore(1);
			this.player2.addScore(1);
		}
	}
	
	/*
	Gets Player 1
	@return Player 1
	*/
	public Player getPlayer1(){
		return this.player1;
	}
	
	
	/*
	Gets Player 2
	@return Player 2
	*/
	public Player getPlayer2(){
		return this.player2;
	}

	
	/*
	User choose the strategy of the other player and play the entire game
	@param args[0] the strategy the user wants for the other player
	*/
	public static void main(String[] args){
		int n=-1;
		String rule="Pour choisir la stratégie du 2éme joueur, entrez un chiffre compris entre 0 et 4:\n";
		String r0="-0 pour que l'ordinateur joue un coup au hasard\n";
		String r1="-1 pour que l'ordinateur joue papier\n";
		String r2="-2 pour qu'un autre joueur humain joue contre vous\n";
		String r3="-3 pour que l'ordinateur joue pierre\n";
		String r4="-4 pour que l'ordinateur joue ciseaux";
		System.out.println(rule+r0+r1+r2+r3+r4);
		try{
			n=Integer.parseInt(args[0]);
		}
		catch(Exception e){
			System.out.println(rule+r0+r1+r2+r3+r4);
			System.exit(1);
		}
		Game game=new Game(strategies[2],strategies[n]);
		String result=game.playGame();
		System.out.println(result);
		}
}
