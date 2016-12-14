package pfc.player;

import pfc.stroke.Stroke;

/**
 */
public class LoopPlayer implements Player {

    private int result;
    private int currentStrokeIndex;
    private Stroke[] loop;

    public LoopPlayer(Stroke... loop) {
        this.result = 0;
        this.currentStrokeIndex = -1;
        this.loop = loop;
    }

    public void chooseStroke() {
        this.currentStrokeIndex = (this.currentStrokeIndex + 1)%(loop.length);
    }

    public Stroke play() {
        return this.loop[currentStrokeIndex];
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
        return "LoopPlayer";
    }
}
