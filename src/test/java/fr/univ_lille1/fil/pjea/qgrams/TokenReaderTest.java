package fr.univ_lille1.fil.pjea.qgrams;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univ_lille1.fil.pjea.Java8File;

public class TokenReaderTest {

	TokenReader fileNbT8TokenReader;
	
	@Before
	public void setUp() throws Exception {
		fileNbT8TokenReader = new TokenReader(new Java8File("src/test/resources/fileNbT8.java"), 1, 1);
	}

	@After
	public void tearDown() throws Exception {
		fileNbT8TokenReader = null;
	}

	@Test
	public void testIterator() {
		Iterator<QGram> it = fileNbT8TokenReader.iterator();
		assertNotNull(it);
		assertTrue(it.hasNext());
		QGram qg1 = it.next();
		assertEquals(1, qg1.size());
		assertEquals("public", qg1.get(0).getText());
		assertEquals("class", it.next().get(0).getText());
		assertEquals("fileDist1", it.next().get(0).getText());
		assertEquals("{", it.next().get(0).getText());
		assertEquals("int", it.next().get(0).getText());
		assertEquals("i", it.next().get(0).getText());
		assertEquals(";", it.next().get(0).getText());
		assertEquals("}", it.next().get(0).getText());
		assertFalse(it.hasNext());
	}

	@Test(expected=NoSuchElementException.class)
	public void testIteratorNoSuchElementException() {
		Iterator<QGram> it = fileNbT8TokenReader.iterator();
		it.next(); it.next();
		it.next(); it.next();
		it.next(); it.next();
		it.next(); it.next();
		assertFalse(it.hasNext());
		it.next(); // must throw NoSuchElementException
	}
	
	@Test
	public void testEqualsTokens() {
	}

	@Test
	public void testHashCodeToken() {
	}
	
	

}

