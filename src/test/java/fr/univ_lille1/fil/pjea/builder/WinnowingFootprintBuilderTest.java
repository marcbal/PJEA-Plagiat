package fr.univ_lille1.fil.pjea.builder;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.javatuples.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class WinnowingFootprintBuilderTest {

	List<Integer> rabinHashCodeExemple;
	List<Pair<Integer, Integer>> expectedResult;
	
	@Before
	public void setUp() throws Exception {
		rabinHashCodeExemple = new ArrayList<>(Arrays.asList(77, 72, 42, 17, 98, 50, 17, 98, 8, 88, 67, 39, 77, 72, 42, 17, 98));
		expectedResult = new ArrayList<>(Arrays.asList(new Pair<>(3, 17), 
													   new Pair<>(6, 17),
													   new Pair<>(8, 8),
													   new Pair<>(11, 39),
													   new Pair<>(15, 17)
				                                       ));
	}
	
	@After
	public void tearDown() throws Exception {
		rabinHashCodeExemple = null;
	}
	
	@Test
	public void buildTest() {
		WinnowingFootprintBuilder footprintBuilder = new WinnowingFootprintBuilder(rabinHashCodeExemple, 4, rabinHashCodeExemple.size());
		try {
			List<Pair<Integer, Integer>> footPrintBuild = footprintBuilder.build();
			assertTrue("Test du build : " + footPrintBuild + " " + expectedResult, footPrintBuild.equals(expectedResult));
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void buildWithTokenReaderTest() {
		// TODO continuer le test
		//fail();

	}
	
}
