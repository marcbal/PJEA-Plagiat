package PPC.Strategie;
import PPC.*;


public class StrategiePapier implements Strategie {
	/** methode getCoup
	* renvoi toujours le coup pierre
	* @return Coups
	* @see Coups
	* @see Strategie
	*/
public Coups getCoup(){
return Coups.PAPIER;}
}
