package fr.univ_lille1.fil.pjea.data;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FootprintTest {
	
	Footprint fp;
	
	@Before
	public void setUp() throws Exception {
		fp = new Footprint(new ArrayList<>());
	}
	
	@After
	public void tearDown() throws Exception {}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testClear() {
		fp.clear();
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testAddIntPairOfIntegerInteger() {
		fp.add(0, null);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testAddPairOfIntegerInteger() {
		fp.add(null);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testAddAllCollectionOfQextendsPairOfIntegerInteger() {
		fp.addAll(null);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testAddAllIntCollectionOfQextendsPairOfIntegerInteger() {
		fp.addAll(0, null);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testRemoveInt() {
		fp.remove(0);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testRemoveObject() {
		fp.remove(null);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testRemoveAllCollectionOfQ() {
		fp.removeAll(null);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testRetainAllCollectionOfQ() {
		fp.retainAll(null);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testSetIntPairOfIntegerInteger() {
		fp.set(0, null);
	}
	
}
