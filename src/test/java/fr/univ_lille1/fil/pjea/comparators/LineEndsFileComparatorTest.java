package fr.univ_lille1.fil.pjea.comparators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

import fr.univ_lille1.fil.pjea.PlagiatVEMPTest;
import fr.univ_lille1.fil.pjea.data.ComparisonResult;
import fr.univ_lille1.fil.pjea.data.Java8File;

public class LineEndsFileComparatorTest {
	
	
	@SuppressWarnings("static-method")
	@Test
	public void testComputeDifferenceSameFile() throws Exception {
		// les deux fichiers file1 et file1copy contiennent tous les deux des espaces
		// en fin de certaines lignes (les mêmes lignes en plus)
		ComparisonResult res = new LineEndsFileComparator(
				new Java8File(PlagiatVEMPTest.TEST_PACK_SPACES[0]), // file1.java
				new Java8File(PlagiatVEMPTest.TEST_PACK_SPACES[0]))// file1copy.java
				.computeDifference();
		assertEquals(1, res.similarityRate, 0.001);
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void testComputeDifferenceDifferentFiles() throws Exception {
		ComparisonResult res = new LineEndsFileComparator(
				new Java8File(PlagiatVEMPTest.TEST_PACK_SPACES[2]),
				new Java8File(PlagiatVEMPTest.TEST_PACK_SPACES[3]))
				.computeDifference();
		assertEquals(0, res.similarityRate, 0.001); // pas assez de fin de ligne pour avoir un score > 0
	}
	
	
	@SuppressWarnings("static-method")
	@Test
	public void testComputeDifferenceNoSpaceBothFile() throws Exception {
		// les deux fichiers ne contiennent aucun espace
		ComparisonResult res = new LineEndsFileComparator(
				new Java8File(PlagiatVEMPTest.TEST_PACK_1[0]), // file1.java
				new Java8File(PlagiatVEMPTest.TEST_PACK_2[1])) // file1copy.java
				.computeDifference();
		assertEquals(0, res.similarityRate, 0.001);
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void testComputeDifferenceNoSpaceFirstFile() throws Exception {
		ComparisonResult res = new LineEndsFileComparator(
				new Java8File(PlagiatVEMPTest.TEST_PACK_1[0]), // file1.java
				new Java8File(PlagiatVEMPTest.TEST_PACK_SPACES[1])) // file1copy.java
				.computeDifference();
		assertEquals(0, res.similarityRate, 0.001);
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void testComputeDifferenceNoSpaceSecondFile() throws Exception {
		ComparisonResult res = new LineEndsFileComparator(
				new Java8File(PlagiatVEMPTest.TEST_PACK_SPACES[0]), // file1.java
				new Java8File(PlagiatVEMPTest.TEST_PACK_1[1])) // file1copy.java
				.computeDifference();
		assertEquals(0, res.similarityRate, 0.001);
	}
	
	
	@SuppressWarnings("static-method")
	@Test
	public void testGetLastIndex() throws Exception {
		assertEquals(0, LineEndsFileComparator.getLastIndex(""));
		assertEquals(0, LineEndsFileComparator.getLastIndex("  		 "));
		assertEquals(0, LineEndsFileComparator.getLastIndex(" "));
		assertEquals(1, LineEndsFileComparator.getLastIndex("s "));
		assertEquals(1, LineEndsFileComparator.getLastIndex("t"));
		assertEquals(4, LineEndsFileComparator.getLastIndex("toto"));
		assertEquals(6, LineEndsFileComparator.getLastIndex("tititi  "));
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void testRandomSpace() throws Exception {
		ComparisonResult res = new LineEndsFileComparator(
				new Java8File(PlagiatVEMPTest.TEST_PACK_SPACES[4]), // file1.java
				new Java8File(PlagiatVEMPTest.TEST_PACK_SPACES[5])) // file1copy.java
				.computeDifference();
		assertNotSame(1.0, res.similarityRate);
	}
}
