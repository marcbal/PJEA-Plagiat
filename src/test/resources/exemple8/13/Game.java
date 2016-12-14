package pfc;

import pfc.stroke.Stroke;
import pfc.player.Player;

import static pfc.stroke.Stroke.*;


/**
 */

public class Game {

    Player player1;
    Player player2;

    public Game() {
        Player[] config = GameConfig.askConfig();
        this.player1 = config[0];
        this.player2 = config[1];
    }


    public void playOneRound() {
        this.player1.chooseStroke();
        this.player2.chooseStroke();
        switch (compareStroke(this.player1.play(), this.player2.play())) {
            case 1: this.player1.win(); this.player2.lose(); displayRoundWinner(this.player1, this.player2); break;
            case -1: this.player1.lose(); this.player2.win(); displayRoundWinner(this.player2, this.player1); break;
            default: this.player1.draw(); this.player2.draw(); displayRoundDraw(); break;
        }
    }

    private int compareStroke(Stroke stroke1, Stroke stroke2) {
        if (stroke1 == stroke2) {
            return 0;
        } else {
            switch (stroke1) {
                case Paper:
                    if (stroke2 == Stone) {
                        return 1;
                    } else {
                        return -1;
                    }
                case Stone:
                    if (stroke2 == Scissors) {
                        return 1;
                    } else {
                        return -1;
                    }
                case Scissors:
                    if (stroke2 == Paper) {
                        return 1;
                    } else {
                        return -1;
                    }
                default: return 0;
            }
        }
    }

    public static void displayRoundWinner(Player player1, Player player2) {
        System.out.println(player1.name()+" gagne avec "+player1.play()+" contre "+player2.play());
    }

    public void displayRoundDraw() {
        System.out.println("Match nul !");
    }

    public void displayMatchWinner() {
        switch (this.player1.compareResult(this.player2)) {
            case 1: System.out.println(this.player1.name()+" gagne la partie contre "+this.player2.name()); break;
            case -1: System.out.println(this.player2.name()+" gagne la partie contre "+this.player1.name()); break;
            default: System.out.println(this.player2.name()+" fait match nul contre "+this.player1.name()); break;
        }
    }

    public void displayResult() {
        switch (this.player1.compareResult(this.player2)) {
            case 1: System.out.println("Resultat : "+this.player1.getResult()+" / "+this.player2.getResult()); break;
            case -1: System.out.println("Resultat : "+this.player2.getResult()+" / "+this.player1.getResult()); break;
            default: System.out.println("Resultat : "+this.player2.getResult()+" à égalité"); break;
        }
    }

    public void displayFinal() {
        displayMatchWinner();
        displayResult();
    }
}
