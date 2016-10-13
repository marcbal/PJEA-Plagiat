package fr.univ_lille1.fil.pjea.qgrams;

import static org.junit.Assert.*;

import java.util.List;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.Token;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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

	
	@Test
	public void testAlignmentNeedlemanWunschEquals() {
		assertTrue(qGram.equals(qGramEqual));
		assertEquals(qGram.size()*2, qGram.alignmentNeedlemanWunsch(qGram));
	}
	
	@Test
	public void testAlignmentNeedlemanWunschOther() {
		assertEquals(qGram.size()*2-1, (qGramOther.alignmentNeedlemanWunsch(qGram)));
	}

}
