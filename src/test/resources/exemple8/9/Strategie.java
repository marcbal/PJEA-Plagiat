/**
 * Strategie is structure which represent a strategie
 * we can ask next kick.
 */
package rpc;
import rpc.*;
import rpc.strategies.*;
import rpc.util.*;
import java.io.IOException;

public interface Strategie{
	/**
	 * @return the player's next coup
	 */
	Coup get() throws IOException;
}
