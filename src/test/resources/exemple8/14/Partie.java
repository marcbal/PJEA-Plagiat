/** La classe Partie
 *
 */

package pfc;
import pfc.strategie.*;

/** La classe Partie cree des instances de parties */

public class Partie {
    
    //ATTRIBUTS
    private int nbTours;
    private Joueur j1;
    private Joueur j2;
    public static final int POINTS_GAGNANT = 2;
    public static final int POINTS_PERDANT = 0;
    public static final int POINTS_EGALITE = 1;

    //CONSTRUCTEUR
    /** crée une nouvelle partie entre deux joueurs durant un nombre de tours
     * determines
     * @param nbTours le nombre de tours de la partie
     * @param j1 le joueur 1
     * @param j2 le joueur 2
     */
    public Partie(int nbTours,Joueur j1,Joueur j2) {
	this.nbTours = nbTours;
	this.j1 = j1;
	this.j2 = j2;
    }

    //METHODES
    /** retourne le joueur gagnant de la partie
     * @return j1 si le joueur1 a un score plus eleve, j2 si le joueur2 a un score
     * plus eleve, (j1,j2) si les joueurs sont egalite
     */
    public Joueur gagnant() {
	if (this.j1.getScore() > this.j2.getScore()) {
	    return this.j1;
	}
	else if (this.j1.getScore() < this.j2.getScore()) {
	    return this.j2;
	}
	else {
	    System.out.println(this.j1.getNom() + " " + this.j2.getNom() + " sont egalites.");
	    return this.j1; /* probleme au niveau du retour d'un joueur en cas d'egalite donc retour de j1 */
	}
    }

    /** joue un tour entre les 2 joueurs et affiche sur la console le resultat du tour c'est a dire soit le 
     * joueur 1 gagne, soit le joueur 2 gagne ou soit ils sont egalite
     */
    public void jouerUnTour() {
	Pfc Coup1 = this.j1.Coup();
	Pfc Coup2 = this.j2.Coup();
	System.out.println(this.j1.getNom() + " joue : " + Coup1 + " et " + this.j2.getNom() + " joue : " + Coup2 + ".");
	if (Coup1.comparer(Coup2) == 1) {
	    this.j1.addPoint(POINTS_GAGNANT);
	    this.j2.addPoint(POINTS_PERDANT);
	    System.out.println(this.j1.getNom() + " gagne le tour.");
	}
	    else if (Coup1.comparer(Coup2) == -1) {
	    this.j1.addPoint(POINTS_PERDANT);
	    this.j2.addPoint(POINTS_GAGNANT);
	    System.out.println(this.j2.getNom() + " gagne le tour.");
	}
	else {
	    this.j1.addPoint(POINTS_EGALITE);
	    this.j2.addPoint(POINTS_EGALITE);
	    System.out.println("Les deux joueurs " + this.j1.getNom() + " et  " + this.j2.getNom() + " sont egalite : tour nul.");
	}
    }
    /** joue une partie complete entre 2 joueurs pour un nombre de tours determines et affiche sur la console le resultat
     * de la partie c'est a dire soit le joueur 1 gagne, soit le joueur 2 gagne ou soit les 2 joueurs sont egalites donc
     * la partie est un match nul
     */
    public void jouerUnePartie() {
	int i = 1;
        while (i <= this.nbTours) {
	    System.out.println("Tour numero : " + i);
	    this.jouerUnTour();
	    i++;
	}
	if (this.j1.getScore() > this.j2.getScore()) {
	    System.out.println(this.j1.getNom() + " gagne la partie avec " + this.j1.getScore() + " points contre " + this.j2.getScore()+ " .");
	}
	else if (this.j1.getScore() < this.j2.getScore()) {
	    System.out.println(this.j2.getNom() + " gagne la partie avec " + this.j2.getScore() + " points contre " + this.j1.getScore() + " .");
	}
	else {
	    System.out.println("Egalite : " + this.j1.getScore() + " - " + this.j2.getScore() + " .");
	}
    }
    /** methode main executant une partie de 3 tours entre 2 joueurs
     * adoptant tout les deux une strategie aleatoire
     */
    public static void main(String args[]) {
	Strategie sAleatoire = new StrategieAleatoire();
	Joueur joueur1 = new Joueur("J1",sAleatoire);
	Joueur joueur2 = new Joueur("J2",sAleatoire);
	Partie unePartie = new Partie(3,joueur1,joueur2);
	unePartie.jouerUnePartie();
    }
}
