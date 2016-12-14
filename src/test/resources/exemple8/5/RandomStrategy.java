package game;
import game.*;
import java.util.Random;

public class RandomStrategy implements Strategy{

    //Methods
    public Shape get(){
        Random rand = new Random();
        int ind = rand.nextInt(Shape.values().length);
        return Shape.values()[ind];
    }
}
