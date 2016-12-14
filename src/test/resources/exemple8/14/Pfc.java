/** Le type Pfc
 *
 */

package pfc;

/** Le type Pfc */

public enum Pfc {
    PIERRE,FEUILLE,CISEAUX;

    public int comparer(Pfc c) {
	if (this == PIERRE && c == CISEAUX || this == CISEAUX && c == FEUILLE || this == FEUILLE && c == PIERRE) {
	    return 1;
	}
	else if (this == PIERRE && c == FEUILLE  || this == CISEAUX && c == PIERRE || this == FEUILLE && c == CISEAUX) {
	    return -1;
	}
	else {
	    return 0;
	}
    }

    public String toString() {
	if (this == PIERRE) {
	    return "PIERRE";
	}
	else if (this == CISEAUX) {
	    return "CISEAUX";
	}
	else {
	    return "FEUILLE";
	}
    }
}
