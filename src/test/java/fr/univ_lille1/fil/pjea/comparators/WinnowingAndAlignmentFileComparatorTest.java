package fr.univ_lille1.fil.pjea.comparators;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.univ_lille1.fil.pjea.PlagiatVEMPTest;
import fr.univ_lille1.fil.pjea.data.Java8File;

public class WinnowingAndAlignmentFileComparatorTest {
	
	@SuppressWarnings("static-method")
	@Test
	public void testComputeDifference() throws Exception {
		double res = new WinnowingAndAlignmentFileComparator(
				new Java8File(PlagiatVEMPTest.TEST_PACK_1[0]), // file1.java
				new Java8File(PlagiatVEMPTest.TEST_PACK_1[1])) // file1copy.java
						.computeDifference();
		assertEquals(1, res, 0.01);
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void testComputeDifference2() throws Exception {
		double res = new WinnowingAndAlignmentFileComparator(
				new Java8File(PlagiatVEMPTest.TEST_PACK_1[0]), // file1.java
				new Java8File(PlagiatVEMPTest.TEST_FILE_NB_TOKEN_12))
						.computeDifference();
		assertEquals(0, res, 0.01);
	}
	
}
