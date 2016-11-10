package fr.univ_lille1.fil.pjea.comparators;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.univ_lille1.fil.pjea.Java8File;
import fr.univ_lille1.fil.pjea.PlagiatVEMPTest;

public class LineEndsFileComparatorTest {
	

	@SuppressWarnings("static-method")
	@Test
	public void testComputeDifferenceSameFile() throws Exception {
		// les deux fichiers file1 et file1copy contiennent tous les deux des espaces
		// en fin de certaines lignes (les mêmes lignes en plus)
		double res = new LineEndsFileComparator(
				new Java8File(PlagiatVEMPTest.TEST_PACK_SPACES[0]), // file1.java
				new Java8File(PlagiatVEMPTest.TEST_PACK_SPACES[1]))// file1copy.java
				.computeDifference();
		assertEquals(1, res, 0.01);
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void testComputeDifferenceDifferentFiles() throws Exception {
		// les deux fichiers file1 et file1copy contiennent tous les deux des espaces
		// en fin de certaines lignes (les mêmes lignes en plus)
		double res = new LineEndsFileComparator(
				new Java8File(PlagiatVEMPTest.TEST_PACK_SPACES[2]), // file1.java
				new Java8File(PlagiatVEMPTest.TEST_PACK_SPACES[3]))// file1copy.java
				.computeDifference();
		assertEquals(0.833, res, 0.001);
	}

}
