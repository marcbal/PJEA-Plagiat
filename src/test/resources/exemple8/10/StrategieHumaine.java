package PPC.Strategie;
import PPC.*;
import java.util.Scanner;


public class StrategieHumaine implements Strategie {
  public class MauvaisChiffre extends RuntimeException {}
  /** methode getCoup
  * renvoi un coup choisi par l'utilisateur
  * @return Coups
  * @see Coups 
  * @see Strategie
  */
  public Coups getCoup(){
    System.out.println("Pierre : 1 | Papier : 2 | Ciseaux : 3");
    System.out.println("Entrez un chiffre :");
    Scanner sc1 = new Scanner(System.in);
    int cp1 = sc1.nextInt();
    Coups cp2 = Coups.PIERRE;
    if (cp1 == 1){
      cp2 = Coups.PIERRE;
    } else if (cp1 == 2){
      cp2 = Coups.PAPIER;
    } else if (cp1 == 3){
      cp2 = Coups.CISEAUX;
    } else {
      throw new MauvaisChiffre();
    }
    return cp2;
  }
}
