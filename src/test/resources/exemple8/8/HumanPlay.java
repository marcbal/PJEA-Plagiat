package game.util.strategies;
import game.util.Strategy;
import game.Shape;
import java.util.Scanner;

public class HumanPlay implements Strategy{


	

	
	/*
	@see Strategy
	*/
	public Shape get(){
		Scanner inp=new Scanner(System.in);
		System.out.println("Jouez un coup;");
		String shape;
		shape=(inp.next()).toUpperCase();
		try{
			return Shape.valueOf(shape);
		}catch (Exception e){
			System.out.println("Entrez un coup correct;");
			return this.get();
		}
	}

}
