package rps.util ;
import rps.util.* ;


public interface PlayerInterface {


  public Choice play();

  public void addPoints(int n);

  public int getPoints();

  public String getName();

}
