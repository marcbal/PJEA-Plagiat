/**
 * PaperStrat implement strategie struct
 * PaperStrat return paper
 */
package rpc.strategies;
import rpc.*;
import rpc.util.*;


public class PaperStrat implements Strategie{
	public Coup get(){
		Coup res=Coup.Paper;
		return res;
	}
}
