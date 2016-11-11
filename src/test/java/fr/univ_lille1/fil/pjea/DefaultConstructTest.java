package fr.univ_lille1.fil.pjea;

import org.junit.Test;

import fr.univ_lille1.fil.pjea.algos.LevenshteinDistanceAlgorithm;
import fr.univ_lille1.fil.pjea.algos.NeedlemanWunschAlignmentAlgorithm;

public class DefaultConstructTest {

	@SuppressWarnings({ "static-method", "unused" })
	@Test
	public void testUselessConstructors() {
		new PlagiatVEMP();
		new TokenUtils();
		new LevenshteinDistanceAlgorithm();
		new NeedlemanWunschAlignmentAlgorithm();
	}

}
