package fr.univ_lille1.fil.pjea.qgrams;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univ_lille1.fil.pjea.qgrams.RabinHashCodeBuilder;

public class RabinHashCodeBuilderTest {
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void test1() {
		int base = 1138763548;
		RabinHashCodeBuilder builder = new RabinHashCodeBuilder(base, 3);
		
		assertEquals(0, builder.getCurrentHashCode());
		
		builder.putHashCode(1138763568);
		builder.putHashCode(7673569);
		builder.putHashCode(-465197);
		
		// here sould be the hashcode of 54, 79 and 97.
		int hash1 = builder.getCurrentHashCode();
		
		builder.putHashCode(118);
		assertNotSame(hash1, builder.getCurrentHashCode());
		
		builder.putHashCode(1138763568);
		assertNotSame(hash1, builder.getCurrentHashCode());
		
		builder.putHashCode(7673569);
		assertNotSame(hash1, builder.getCurrentHashCode());
		
		builder.putHashCode(-465197);
		assertEquals(hash1, builder.getCurrentHashCode());
	}

}
