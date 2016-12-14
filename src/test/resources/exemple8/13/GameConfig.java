package pfc;

import pfc.player.*;
import pfc.stroke.Stroke;
import pfc.stroke.StrokeException;
import pfc.util.Input;

import java.io.IOException;

/**
 */
public class GameConfig {

    public static Player[] askConfig() {
        System.out.println("Qui jouera la partie ?");
        System.out.println("1 - Ordinateur / Ordinateur");
        System.out.println("2 - Joueur / Ordinateur");
        System.out.println("3 - Joueur / Joueur");
        int configNumber = -1;
        while (configNumber == -1) {
            try {
                configNumber = Input.readInt();
            } catch (IOException e) {
                System.out.println("Ceci n'est pas un nombre");
            }
        }
        switch (configNumber) {
            case 1: return new Player[]{askComputerConfig(), askComputerConfig()};
            case 2: return new Player[]{askComputerConfig(), askPlayerConfig()};
            case 3: return new Player[]{askPlayerConfig(), askPlayerConfig()};
            default: System.out.println("Nombre non valide. Veuillez recommencer..."); return askConfig();
        }
    }

    private static Player askPlayerConfig() {
        System.out.println("Quel est le nom du joueur ?");
        return new UserPlayer(Input.readString());
    }

    private static Player askComputerConfig() {
        System.out.println("Quel stratégie suivra l'ordinateur ? (Par défaut, il jouera aléatoirement)");
        System.out.println("1 - Uniquement pierre / 2 - Uniquement feuille / 3 - Uniquement ciseaux");
        System.out.println("4 - Jouer en boucle une séquence prédéfinie / 5 - Aléatoirement");
        int configNumber = -1;
        while (configNumber == -1) {
            try {
                configNumber = Input.readInt();
            } catch (IOException e) {
                System.out.println("Ceci n'est pas un nombre");
            }
        }
        switch (configNumber) {
            case 1: return new StonePlayer();
            case 2: return new PaperPlayer();
            case 3: return new ScissorsPlayer();
            case 4: Stroke[] loop = askLoop(); return new LoopPlayer(loop);
            default: return new RandomPlayer();
        }
    }

    private static Stroke[] askLoop() {
        System.out.println("Quel boucle sera effectué ?");
        System.out.println("L'entrée doit être de la forme PFCCFP (P - Pierre / F - Feuille / C - Ciseaux)");
        String[] input = Input.readString().split("");
        Stroke[] answer = new Stroke[input.length];
        try {
            for (int i = 0; i < input.length ; i++) {
                answer[i] = (convertStringToStroke(input[i]));
            }
        } catch (StrokeException e) {
            System.out.println(e.getMessage() + " Veuillez recommencer votre saisie...");
            answer =  askLoop();
        }
        return answer;
    }

    private static Stroke convertStringToStroke(String s) throws StrokeException {
        switch (s) {
            case "P": return Stroke.Stone;
            case "F": return Stroke.Paper;
            case "C": return Stroke.Scissors;
            default: throw new StrokeException("Lettre non valide.");
        }
    }

}
