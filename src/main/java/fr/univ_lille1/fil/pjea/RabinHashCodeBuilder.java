package fr.univ_lille1.fil.pjea;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Class based on the Rabin fingerprint algorithm.
 * It provide optimisations for computing hashCodes
 * of QGrams when iterating over TokenReader.
 * 
 * This class works internally with {@link BigInteger} to
 * avoid overflow issues.
 */
public class RabinHashCodeBuilder {
	
	private BigInteger currentHashCode = BigInteger.valueOf(0);
	private Queue<Integer> singleHashCodes = new LinkedList<>();
	
	private final BigInteger base;
	private final int nbMaxElement;
	private final BigInteger basePowNbMaxElementMinus1;
	
	
	
	public RabinHashCodeBuilder(int base, int nbMaxElement, int... hashCodes) {
		this.base = BigInteger.valueOf(base);
		this.nbMaxElement = nbMaxElement;
		this.basePowNbMaxElementMinus1 = this.base.pow(nbMaxElement - 1);
		putHashCode(hashCodes);
	}
	
	
	
	public void putHashCode(int... hashCodes) {
		for (int hashCode : hashCodes)
			putUniqueHashCode(hashCode);
	}
	
	
	
	
	
	private void putUniqueHashCode(int hashCode) {
		if (hashCode < 0)
			hashCode ^= (1 << 31); // change sign bit to 0 to et as positive value
		if (singleHashCodes.size() == nbMaxElement) {
			int hashToRemove = singleHashCodes.poll();
			currentHashCode = currentHashCode.subtract(BigInteger.valueOf(hashToRemove).multiply(basePowNbMaxElementMinus1));
		}
		currentHashCode = currentHashCode
				.multiply(base)
				.add(BigInteger.valueOf(hashCode));
		singleHashCodes.add(hashCode);
	}
	
	
	
	
	public int getCurrentHashCode() {
		return currentHashCode.intValue();
	}
	
	

}
