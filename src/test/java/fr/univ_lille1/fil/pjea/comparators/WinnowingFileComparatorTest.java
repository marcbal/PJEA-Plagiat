package fr.univ_lille1.fil.pjea.comparators;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.univ_lille1.fil.pjea.PlagiatVEMPTest;
import fr.univ_lille1.fil.pjea.data.Java8File;

public class WinnowingFileComparatorTest {
	
	@SuppressWarnings("static-method")
	@Test
	public void testComputeDifference() throws Exception {
		double res = new WinnowingFileComparator(
				new Java8File(PlagiatVEMPTest.TEST_PACK_1[0]), // file1.java
				new Java8File(PlagiatVEMPTest.TEST_PACK_1[1]), // file1copy.java
				15, 0.3)
				.computeDifference();
		assertEquals(1, res, 0.01);
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void testComputeDifference2() throws Exception {
		double res = new WinnowingFileComparator(
				new Java8File(PlagiatVEMPTest.TEST_PACK_1[0]), // file1.java
				new Java8File(PlagiatVEMPTest.TEST_FILE_NB_TOKEN_12),
				15, 0.3)
				.computeDifference();
		assertEquals(0, res, 0.01);
	}
	
}
