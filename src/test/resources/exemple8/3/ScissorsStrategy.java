package pfc;
import pfc.Strategy;
import pfc.Shape;

public class ScissorsStrategy implements Strategy{
  

    public Shape get(){
        return Shape.SCISSORS;
    }

}
