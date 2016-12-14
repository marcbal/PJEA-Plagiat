package pfc;
import pfc.Strategy;
import pfc.Shape;

public class RockStrategy implements Strategy{
  public Shape get(){
    return Shape.ROCK;
  }
}
