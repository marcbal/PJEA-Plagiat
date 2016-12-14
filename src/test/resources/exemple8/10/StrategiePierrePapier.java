package PPC.Strategie;;
import PPC.*;

public class StrategiePierrePapier implements Strategie {
	private int aux = 0;
	/** methode getCoup
	* renvoi alternativement pierre, papier
	* @return Coups
	* @see Coups 
	* @see Strategie
	*/	
	public Coups getCoup(){
		if (aux % 2 == 0) {
			aux++;
			return Coups.PIERRE;
		}else{
			aux++;
			return Coups.PAPIER;
			}
	}
}
