package fr.univ_lille1.fil.pjea.builder;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.javatuples.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univ_lille1.fil.pjea.Java8File;
import fr.univ_lille1.fil.pjea.PlagiatVEMPTest;
import fr.univ_lille1.fil.pjea.data.Footprint;


public class WinnowingFootprintBuilderTest {

	List<Integer> rabinHashCodeExample = Arrays.asList(77, 72, 42, 17, 98, 50, 17, 98, 8, 88, 67, 39, 77, 72, 42, 17, 98);
	Footprint expectedResult = new Footprint(Arrays.asList(new Pair<>(3, 17), 
													   new Pair<>(6, 17),
													   new Pair<>(8, 8),
													   new Pair<>(11, 39),
													   new Pair<>(15, 17)
				                                       ));
	
	@Before
	public void setUp() throws Exception {
		
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testBuildFromExample() throws Exception {
		WinnowingFootprintBuilder footprintBuilder = new WinnowingFootprintBuilder().init(rabinHashCodeExample, 
				4, rabinHashCodeExample.size());
		
		Footprint footPrintBuild = footprintBuilder.build();
		
		assertEquals(expectedResult, footPrintBuild);
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void testBuildFromFile() throws Exception {
		Java8File f = new Java8File(PlagiatVEMPTest.TEST_PACK_1[0]);
		WinnowingFootprintBuilder b1 = new WinnowingFootprintBuilder(f, 2);
		WinnowingFootprintBuilder b2 = new WinnowingFootprintBuilder(f.tokens, 2, f.tokens.size());
		assertEquals(b1.build(), b2.build());
	}
	
}
