package fr.univ_lille1.fil.pjea.comparators;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.univ_lille1.fil.pjea.PlagiatVEMPTest;
import fr.univ_lille1.fil.pjea.data.ComparisonResult;
import fr.univ_lille1.fil.pjea.data.Java8File;

public class CommonQGramRangeFileComparatorTest {
	
	@SuppressWarnings("static-method")
	@Test
	public void testComputeDifference() throws Exception {
		ComparisonResult res = new CommonQGramRangeFileComparator(
				new Java8File(PlagiatVEMPTest.TEST_PACK_1[0]), // file1.java
				new Java8File(PlagiatVEMPTest.TEST_PACK_1[1]), // file1copy.java
				5, 10)
				.computeDifference();
		assertEquals(1, res.similarityRate, 0.01);
	}
	
}
