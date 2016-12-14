package PPC.Strategie;
import PPC.*;


public class StrategieAleatoire implements Strategie{
	private Random rand = new Random();
	/** methode getCoup
	* renvoi un Coup aleatoire
	* @return Coups
	* @see Coups 
	* @see Strategie
	*/	
private int RetourneCoup(Coups c){
	if ( c = Coups.PIERRE){
		return 0;};
	if ( c = Coups.PAPIER){
		return 1;};
		return 2;
}


	public Coups getCoup() {
		Coups j2 = this.j2.getCoup() ;
	switch (RetourneCoup (j2)) {
			case 0:
				j2 = Coups.PAPIER;
				break;
			case 1:
				j2 = Coups.CISEAUX;
				break;
			case 2:
				j2 = Coups.PIERRE;
				break;
		}
		return j2;
	}
}
