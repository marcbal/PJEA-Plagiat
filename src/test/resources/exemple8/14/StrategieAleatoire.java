/** La classe StrategieAleatoire
 *
 */

package pfc.strategie;
import pfc.Pfc;
import pfc.strategie.Strategie;
import java.util.Random;

/** La Strategie aleatoire */

public class StrategieAleatoire implements Strategie {
    /** cree un nouveau coup Aleatoire
     */
    public Pfc getCoup() {
	Random alea = new Random();
	return Pfc.values()[alea.nextInt(Pfc.values().length)];
    }
}

