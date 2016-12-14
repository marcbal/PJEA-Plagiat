/** La classe StrategieCiseau
 *
 */

package pfc.strategie;
import pfc.Pfc;
import pfc.strategie.Strategie;

/** La strategie Ciseaux */

public class StrategieCiseaux implements Strategie {
    /** cree un nouveau coup Ciseau
     */
    public Pfc getCoup() {
	return Pfc.CISEAUX;
    }
}
