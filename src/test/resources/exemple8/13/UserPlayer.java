package pfc.player;

import pfc.stroke.Stroke;
import pfc.util.Input;

/**
 */
public class UserPlayer implements Player {

    private int result;
    private Stroke currentStroke;
    private String name;

    public UserPlayer(String name) {
        this.result = 0;
        this.currentStroke=null;
        this.name = name;
    }

    public void chooseStroke() {
        System.out.println(this.name+", que voulez-vous jouer ? (P - Pierre, F - Feuille, C - Ciseaux)");
        switch (Input.readString()) {
            case "P": this.currentStroke = Stroke.Stone; break;
            case "F": this.currentStroke = Stroke.Paper; break;
            case "C": this.currentStroke = Stroke.Scissors; break;
            default: System.out.println("Entrée non valide..."); chooseStroke(); break;
        }
    }

    public Stroke play() {
        return this.currentStroke;
    }

    public void win() {
        this.result += 2;
    }

    public void lose() {}

    public void draw() {
        this.result += 1;
    }

    public int getResult() {
        return this.result;
    }

    public int compareResult(Player other) {
        if (this.result > other.getResult()) {
            return 1;
        } else if (this.result < other.getResult()) {
            return -1;
        } else { return 0; }
    }

    public String name() {
        return this.name;
    }
}
