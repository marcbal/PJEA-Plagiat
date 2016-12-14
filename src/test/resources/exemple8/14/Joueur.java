/** La classe Joueur
 *
 */

package pfc;
import pfc.strategie.*;

/** La classe Joueur cree des instances de joueurs */

public class Joueur {
    
    //ATTRIBUTS

    private String nom;
    private int score;
    private Strategie strategie;

    //CONSTRUCTEUR
    
    /** cree un joueur ayant un nom et une strategie predefini ainsi
     * qu'un score initialise a 0
     * @param nom = le nom du joueur
     * @param strategie = la strategie du joueur
     */
    public Joueur(String nom,Strategie strategie) {
	this.nom = nom;
	this.score = 0;
	this.strategie = strategie;
    }

    //METHODES
    
    /** retourne le nom du joueur
     * @return une chaine de caracteres correspondant au nom du joueur
     */
    public String getNom() {
	return this.nom;
    }

    /** retourne le score du joueur
     * @return un entier correspondant au score du joueur
     */
    public int getScore() {
	return this.score;
    }

    /** retourne le coup de la strategie du joueur
     * @return un coup de type Pfc
     */
    public Pfc Coup() {
	return this.strategie.getCoup();
    }

    /** augmente le score du joueur d'un point
     */
    public void addPoint(int point) {
	this.score = this.score + point; 
    }

    /** donne des informations sur le joueur
     * @return une chaine de caracteres comportant les attributs du joueur
     */
    public String toString() {
	return "Le joueur " + this.nom + " a " + this.score + " points et opte pour la strategie " + this.strategie + ".";
    }
}
