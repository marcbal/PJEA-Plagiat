package pfc.player;

import pfc.stroke.Stroke;

/**
 */
public interface Player {

    void chooseStroke();
    Stroke play();
    void win();
    void lose();
    void draw();
    int getResult();
    int compareResult(Player other);
    String name();

}
