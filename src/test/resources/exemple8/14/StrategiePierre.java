/** La classe StrategiePierre
 *
 */

package pfc.strategie;
import pfc.Pfc;
import pfc.strategie.Strategie;

/** La Strategie Pierre */

public class StrategiePierre implements Strategie {
    /** cree un nouveau coup Pierre
     */
    public Pfc getCoup() {
	return Pfc.PIERRE;
    }
}
