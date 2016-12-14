package PPC.Strategie;
import PPC.*;
import java.util.Random;

public class StrategieAleatoire implements Strategie{
	private Random rand = new Random();
	/** methode getCoup
	* renvoi un Coup aleatoire
	* @return Coups
	* @see Coups
	* @see Strategie
	*/	
	public Coups getCoup() {
		Coups c = Coups.PIERRE;
		switch (this.rand.nextInt() % 3) {
			case 0:
				break;
			case 1:
				c = Coups.PAPIER;
				break;
			case 2:
				c = Coups.CISEAUX;
				break;
		}
		return c;
	}
	public static void main(String[] args) {
		Strategie test = new StrategieAleatoire();
		for (int i = 0 ; i < 10 ; i++){
			System.out.println(""+test.getCoup());
		}
	}
			
}
