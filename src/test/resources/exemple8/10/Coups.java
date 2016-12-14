
package PPC;

public enum Coups{
PIERRE, PAPIER, CISEAUX;

  public String toString(){
    switch(this){
      case PIERRE : return "Pierre";
      case PAPIER : return "Papier";
      default : return "Ciseaux";
    }
  }

  /**	method compare
  *	Retourne un int suivant la comparaison du coup
  *	@param c un coup a comparer
  *	@return -1, 0, 1 respectivements si le coup courant est inferieur, egal ou    	*	superieur a c
  */
public int compare( Coups c){

    switch(this){
      case PIERRE :
	switch(c){
	  case PIERRE : return 0;
	  case PAPIER : return -1;
	  default : return 1;
	}
      case PAPIER :
	switch(c){
	  case PIERRE : return 1;
	  case PAPIER : return 0;
	  default : return -1;
	}
      default :
	switch(c){
	  case PIERRE : return -1;
	  case PAPIER : return 1;
	  default : return 0;
	}
    }
  }

}
