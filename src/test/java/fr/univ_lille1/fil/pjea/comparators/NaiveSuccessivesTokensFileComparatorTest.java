package fr.univ_lille1.fil.pjea.comparators;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.univ_lille1.fil.pjea.PlagiatVEMPTest;
import fr.univ_lille1.fil.pjea.data.Java8File;

public class NaiveSuccessivesTokensFileComparatorTest {
	
	@SuppressWarnings("static-method")
	@Test
	public void testComputeDifferenceSameFile() throws Exception {
		double res = new NaiveSuccessivesTokensFileComparator(
				new Java8File(PlagiatVEMPTest.TEST_PACK_1[0]), // file1.java
				new Java8File(PlagiatVEMPTest.TEST_PACK_1[1]))// file1copy.java
						.computeDifference();
		assertEquals(1, res, 0.01);
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void testComputeDifferenceDiffFile() throws Exception {
		double res = new NaiveSuccessivesTokensFileComparator(
				new Java8File(PlagiatVEMPTest.TEST_FILE_NB_TOKEN_12),
				new Java8File(PlagiatVEMPTest.TEST_FILE_NB_TOKEN_8))
						.computeDifference();
		assertEquals(0.5, res, 0.2); // plus ou moins 20%
	}
	
}
