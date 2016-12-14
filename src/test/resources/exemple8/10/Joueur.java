
package PPC;
import PPC.Strategie.*;

public class Joueur{
private String nom;
private int score;
private Strategie strategie;

  /**	constructor Joueur
  *	Retourne un joueur avec name pour nom, str comme strategie et un score initialement nul
  *	@param name
  *		Un String pour le nom du joueur
  *	@param str
  *		Une Strategie a attribuer au joueur
  *     @see Strategie
  *	
  */
public Joueur(String nom, Strategie strategie){
this.nom = nom;
this.strategie = strategie;
this.score = 0;
}

  /**	method getCoup
  *	Retourne un coups
  *	@return Le coup qui va etre joue suivant la strategie du joueur
  */
public Coups getCoup(){
return strategie.getCoup();}

  /**	method getScore
  *	Retourne un int
  *	@return Le score du joueur courant
  */
public int getScore(){
return this.score;}

  /**	method getNom
  *	Retourne un String
  *	@return Le nom du joueur courant
  */
public String getNom(){
return this.nom;}

  /**	method addPoint
  *	Incremente le scrore du joueur courant
  *	@param n La valeur a ajouter au score du joueur
  */
public void addPoint(int n){
this.score = this.score + n; }

public String toString(){
return this.getNom() + " avec un score de " + this.getScore();}

}
