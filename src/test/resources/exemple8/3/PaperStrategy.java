package pfc;
import pfc.Strategy;
import pfc.Shape;

public class PaperStrategy implements Strategy{
  public Shape get(){
    return Shape.PAPER;
  }
}
