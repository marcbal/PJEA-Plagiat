package pfc.player;

import pfc.stroke.Stroke;

/**
 */
public class ScissorsPlayer implements Player {

    private int result;
    private Stroke currentStroke;

    public ScissorsPlayer() {
        this.result = 0;
        this.currentStroke = Stroke.Scissors;
    }

    public void chooseStroke() {}

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
        return "ScissorsPlayer";
    }

}
