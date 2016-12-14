/**
 * HumanStrat implement strategie struct
 * HumanStrat ask to player his coup
 */
package rpc.strategies;
import rpc.*;
import rpc.util.*;
import java.io.IOException;


public class HumanStrat implements Strategie{
	public Coup get() throws IOException{
		Coup res=Coup.Rock;
		String rep="";
		while(!rep.equals("rock") && !rep.equals("paper") && !rep.equals("scissors")){
			System.out.println("Entrez votre coup (rock,paper,scissors) : ");
			rep=Input.readString();
		}
		for(Coup c : Coup.values()){
			if(c.toString().equals(rep)){
				res=c;
			}
		}
		return res;
	}
}
