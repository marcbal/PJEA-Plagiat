package pfc;

import pfc.util.Input;

import java.io.IOException;

/**
 */
public class Main {

    public static void main (String[] args) {
        Game game = new Game();
        int roundNumber = askRoundNumber();
        for (int i=0; i < roundNumber; i++) {
            game.playOneRound();
        }
        game.displayFinal();
    }

    private static int askRoundNumber(){
        int roundNumber = -1;
        System.out.println("En combien de manches se jouera cette partie de Pierre-Feuille-Ciseaux ?");
        while (roundNumber <= 0) {
            try {
                roundNumber = Input.readInt();
                if (roundNumber <= 0) {
                    System.out.println("Le nombre n'est valide, il doit être strictement supérieur à 0.");
                }
            } catch (IOException e) {
                System.out.println("Ceci n'est pas un nombre");
            }
        }
        return roundNumber;
    }

}
