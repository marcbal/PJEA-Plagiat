package fr.univ_lille1.fil.pjea.qgrams;

import static org.junit.Assert.*;

import java.util.List;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.Token;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univ_lille1.fil.pjea.Java8File;
import fr.univ_lille1.fil.pjea.antlr_lexers.java8.Java8Lexer;

public class QGramTest {

	QGram qGram;
	QGram qGramEqual;
	QGram qGramOther;
	
	@Before
	public void setUp() throws Exception {
		ANTLRInputStream in = new ANTLRFileStream("src/test/resources/file1.java");
        List<? extends Token> toks = new Java8Lexer(in).getAllTokens();
		qGram      = new QGram(toks.subList(15, 25), 15, 134454645);
		qGramEqual = new QGram(toks.subList(15, 25), 15, 134454645);
		qGramOther = new QGram(toks.subList(15, 26), 15, 344546457);
	}

	@After
	public void tearDown() throws Exception {
		qGram = null;
		qGramEqual = null;
		qGramOther = null;
	}

	@Test
	public void testHashCode() {
		assertEquals(134454645,qGram.hashCode());
		
	}

	@Test
	public void testGetQGramPosition() {
		assertEquals(15,qGram.getQGramPosition());
	}

	

	@Test
	public void testSize() {
		assertEquals(10,qGram.size());
		
	}

	
	
	public void testIdenticalQGramDistanceIsZero(){
		assertEquals(0, qGram.distance(qGram));
	}
	
	
	
	
	@SuppressWarnings("static-method")
	@Test
	public void testIdenticalQGramDistance() throws IOException {
		Java8File f1 = new Java8File("src/test/resources/fileNbT8.java");
		Java8File f2 = new Java8File("src/test/resources/fileNbT12.java");
        QGram qGram1 = new TokenReader(f1, 8).iterator().next();
		QGram qGram2 = new TokenReader(f2, 11).iterator().next();
		/*
		 * Info : Pourquoi 4 ? On compte le nombre de tokens manuellement dans les fichiers de comparaison
		 * Et on ajoute 1 pour car le nom de la classe est changée dans le second.
		 */
		assertEquals(4, qGram1.distance(qGram2));
		assertTrue(qGram1.distance(qGram2) == qGram2.distance(qGram1));

	}
	

	
	@Test
	public void testAlignmentNeedlemanWunschEquals() {
		assertTrue(qGram.equals(qGramEqual));
		assertEquals(qGram.size(), qGram.alignmentNeedlemanWunsch(qGram, -1));
	}
	
	
	
	@Test
	public void testAlignmentNeedlemanWunschOther() {
		assertEquals(qGram.size()-1, (qGramOther.alignmentNeedlemanWunsch(qGram, -1)));
	}
	
	
	

	@Test(expected=UnsupportedOperationException.class)
	public void testAddIntToken() {
		qGram.add(0, null);
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testAddToken() {
		qGram.add(null);
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testAddAllCollectionOfQextendsToken() {
		qGram.addAll(null);
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testAddAllIntCollectionOfQextendsToken() {
		qGram.addAll(0, null);
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testClear() {
		qGram.clear();
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testRemoveInt() {
		qGram.remove(0);
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testRemoveObject() {
		qGram.remove(null);
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testRemoveAll() {
		qGram.removeAll(null);
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testRetainAll() {
		qGram.retainAll(null);
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testSet() {
		qGram.set(0, null);
	}

	
	
	
	
	
	
	
	
	
	@Test
	public void testEqualsObject() {
		assertFalse(qGram.equals(null));
		assertFalse(qGram.equals(new Object()));
		assertFalse(qGram.equals(this));
		assertTrue(qGram.equals(qGram));
		assertTrue(qGram.equals(qGramEqual));
		assertFalse(qGram.equals(qGramOther));
	}

	@Test
	public void testToString() {
		assertEquals("[8039d75:.,IOException,;,import,java,.,nio,.,file,.]", qGram.toString());
	}
	
	
	

}
