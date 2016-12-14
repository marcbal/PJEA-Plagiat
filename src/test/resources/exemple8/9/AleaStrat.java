/**
 * Alea implement strategie struct
 * Alea return a random coup
 */
package rpc.strategies;
import rpc.*;
import rpc.util.*;


public class AleaStrat implements Strategie{
	private String[] possible={"rock","paper","scissors"};
	public Coup get(){
		Coup res=Coup.Rock;
		int alea=(int)(Math.random()*3);
		String rep=possible[alea];
		for(Coup c : Coup.values()){
			if(c.toString().equals(rep)){
				res=c;
			}
		}
		return res;
	}
}

