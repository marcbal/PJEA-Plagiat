/**
 * this class implement a game kick
 */

package rpc;
import rpc.*;
import rpc.strategies.*;
import rpc.util.*;

public enum Coup{
	Rock("rock"),
	Paper("paper"),
	Scissors("scissors");
	
	private String value;
	
	Coup(String coup){
		this.value=coup;
	}
	/**
	 * @return the String coup
	 */
	public String toString(){
		return this.value;
	}
	/**
	 * compare 2 coups
	 * @return 0 if coups are equals, 1 if fisrt is beter tha secon, -1 else
	 */
	public int comp(Coup other){
		if(this.value==other.toString()){
			return 0;
		}
		if(this.value==Paper.toString()){
			if(other.toString()==Rock.toString()){
				return 1;
			}
			if(other.toString()==Scissors.toString()){
				return -1;
			}
		}
		if(this.value==Rock.toString()){
			if(other.toString()==Paper.toString()){
				return -1;
			}
			if(other.toString()==Scissors.toString()){
				return 1;
			}
		}
		if(this.value==Scissors.toString()){
			if(other.toString()==Rock.toString()){
				return -1;
			}
			if(other.toString()==Paper.toString()){
				return 1;
			}
		}
		return 0;
	}

}
