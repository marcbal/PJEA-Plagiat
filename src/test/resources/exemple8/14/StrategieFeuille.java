/** La classe StrategieFeuille
 *
 */

package pfc.strategie;
import pfc.Pfc;
import pfc.strategie.Strategie;

/** La strategie Feuille */

public class StrategieFeuille implements Strategie {
    /** cree un nouveau coup Feuille
     */
    public Pfc getCoup() {
	return Pfc.FEUILLE;
    }
}
