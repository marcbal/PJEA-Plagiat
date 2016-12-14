/**
 * BoucleStrat implement strategie struct
 * BoucleStrat return rock then paper then scissors
 */
package rpc.strategies;
import rpc.*;
import rpc.util.*;


public class BoucleStrat implements Strategie{
	/**
	 * memorise previous coup
	 */
	private String prev="scissors";
	public Coup get(){
		Coup res=Coup.Rock;
		String rep="";
		if(prev=="rock"){
			rep="paper";
		}else if(prev=="paper"){
			rep="scissors";
		}else{
			rep="rock";
		}
		this.prev=rep;
		for(Coup c : Coup.values()){
			if(c.toString().equals(rep)){
				res=c;
			}
		}
		return res;
	}
}

