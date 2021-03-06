package fr.univ_lille1.fil.pjea.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.antlr.v4.runtime.Token;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univ_lille1.fil.pjea.PlagiatVEMPTest;

public class QGramTokenContainerTest {
	
	QGramTokenContainer fileNbT8QGramContainer;
	
	@Before
	public void setUp() throws Exception {
		fileNbT8QGramContainer = new QGramTokenContainer(new Java8File(PlagiatVEMPTest.TEST_FILE_NB_TOKEN_8), 1, 1);
	}
	
	@After
	public void tearDown() throws Exception {
		fileNbT8QGramContainer = null;
	}
	
	@Test
	public void testIterator() {
		Iterator<QGram<Token>> it = fileNbT8QGramContainer.iterator();
		assertNotNull(it);
		assertTrue(it.hasNext());
		QGramToken qg1 = (QGramToken) it.next();
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
	
	@Test(expected = NoSuchElementException.class)
	public void testIteratorNoSuchElementException() {
		Iterator<QGram<Token>> it = fileNbT8QGramContainer.iterator();
		it.next();
		it.next();
		it.next();
		it.next();
		it.next();
		it.next();
		it.next();
		it.next();
		assertFalse(it.hasNext());
		it.next(); // must throw NoSuchElementException
	}
	
	
	@Test
	public void testTokenReaderListOfTokenIntInt() throws IOException {
		Java8File f = new Java8File(PlagiatVEMPTest.TEST_FILE_NB_TOKEN_8);
		QGramTokenContainer qGramContainer = new QGramTokenContainer(f.tokens, 1, 1); // même paramètre que le constructeur appelé dans le setUp();
		assertEquals(fileNbT8QGramContainer, qGramContainer);
		QGramTokenContainer qGramContainer2 = new QGramTokenContainer(f.tokens, 2, 2);
		assertEquals(4, qGramContainer2.size());
		assertEquals(2, qGramContainer2.get(0).size());
		assertEquals(0, qGramContainer2.get(0).getQGramPosition());
		assertEquals(2, qGramContainer2.get(1).getQGramPosition());
		assertEquals(4, qGramContainer2.get(2).getQGramPosition());
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void testTokenReaderJava8FileInt() throws IOException {
		QGramTokenContainer qGramContainer = new QGramTokenContainer(new Java8File(PlagiatVEMPTest.TEST_FILE_NB_TOKEN_8), 2);
		assertEquals(4, qGramContainer.size());
		assertEquals(2, qGramContainer.get(0).size());
		assertEquals(0, qGramContainer.get(0).getQGramPosition());
		assertEquals(2, qGramContainer.get(1).getQGramPosition());
		assertEquals(4, qGramContainer.get(2).getQGramPosition());
	}
	
	@Test
	public void testTokenReaderJava8File() throws IOException {
		QGramTokenContainer qGramContainer = new QGramTokenContainer(new Java8File(PlagiatVEMPTest.TEST_FILE_NB_TOKEN_8));
		assertEquals(fileNbT8QGramContainer, qGramContainer);
	}
	
	@Test
	public void testTokenReaderListOfTokenInt() throws IOException {
		Java8File f = new Java8File(PlagiatVEMPTest.TEST_FILE_NB_TOKEN_8);
		QGramTokenContainer qGramContainer = new QGramTokenContainer(f, 1);
		assertEquals(fileNbT8QGramContainer, qGramContainer);
	}
	
	@Test
	public void testTokenReaderListOfToken() throws IOException {
		Java8File f = new Java8File(PlagiatVEMPTest.TEST_FILE_NB_TOKEN_8);
		QGramTokenContainer qGramContainer = new QGramTokenContainer(f);
		assertEquals(fileNbT8QGramContainer, qGramContainer);
	}
	
	
	
	
	
	@Test(expected = UnsupportedOperationException.class)
	public void testAddIntQGram() {
		fileNbT8QGramContainer.add(0, null);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testAddQGram() {
		fileNbT8QGramContainer.add(null);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testAddAllCollectionOfQGram() {
		fileNbT8QGramContainer.addAll(null);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testAddAllIntCollectionOfQGram() {
		fileNbT8QGramContainer.addAll(0, null);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testClear() {
		fileNbT8QGramContainer.clear();
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testRemoveInt() {
		fileNbT8QGramContainer.remove(0);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testRemoveObject() {
		fileNbT8QGramContainer.remove(null);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testRemoveAll() {
		fileNbT8QGramContainer.removeAll(null);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testRetainAll() {
		fileNbT8QGramContainer.retainAll(null);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testSet() {
		fileNbT8QGramContainer.set(0, null);
	}
	
	
	
	
	
}

