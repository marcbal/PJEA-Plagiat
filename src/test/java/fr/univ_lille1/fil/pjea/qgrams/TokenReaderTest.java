package fr.univ_lille1.fil.pjea.qgrams;

import static org.junit.Assert.*;

import java.io.IOException;
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
	
	
	@Test
	public void testTokenReaderListOfQextendsTokenIntInt() throws IOException {
		Java8File f = new Java8File("src/test/resources/fileNbT8.java");
		TokenReader tokenReader = new TokenReader(f.tokens, 1, 1); // même paramètre que le constructeur appelé dans le setUp();
		assertEquals(fileNbT8TokenReader.getAllQGrams(), tokenReader.getAllQGrams());
		TokenReader tokenReader2 = new TokenReader(f.tokens, 2, 2);
		assertEquals(4, tokenReader2.getAllQGrams().size());
		assertEquals(2, tokenReader2.getAllQGrams().get(0).size());
		assertEquals(0, tokenReader2.getAllQGrams().get(0).getQGramPosition());
		assertEquals(2, tokenReader2.getAllQGrams().get(1).getQGramPosition());
		assertEquals(4, tokenReader2.getAllQGrams().get(2).getQGramPosition());
	}

	@SuppressWarnings("static-method")
	@Test
	public void testTokenReaderJava8FileInt() throws IOException {
		TokenReader tokenReader = new TokenReader(new Java8File("src/test/resources/fileNbT8.java"), 2);
		assertEquals(4, tokenReader.getAllQGrams().size());
		assertEquals(2, tokenReader.getAllQGrams().get(0).size());
		assertEquals(0, tokenReader.getAllQGrams().get(0).getQGramPosition());
		assertEquals(2, tokenReader.getAllQGrams().get(1).getQGramPosition());
		assertEquals(4, tokenReader.getAllQGrams().get(2).getQGramPosition());
	}

	@Test
	public void testTokenReaderJava8File() throws IOException {
		TokenReader tokenReader = new TokenReader(new Java8File("src/test/resources/fileNbT8.java"));
		assertEquals(fileNbT8TokenReader.getAllQGrams(), tokenReader.getAllQGrams());
	}
	
	

}

