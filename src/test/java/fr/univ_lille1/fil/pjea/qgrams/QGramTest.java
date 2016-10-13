package fr.univ_lille1.fil.pjea.qgrams;

import static org.junit.Assert.*;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.Lexer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univ_lille1.fil.pjea.antlr_lexers.java8.Java8Lexer;

public class QGramTest {

	QGram qGram;
	QGram qGramEqual;
	
	@Before
	public void setUp() throws Exception {
		ANTLRInputStream in = new ANTLRFileStream("src/test/resources/file1.java");
        Lexer l = new Java8Lexer(in);
		qGram = new QGram(l.getAllTokens().subList(15, 25), 15, 134454645);
		qGramEqual = new QGram(l.getAllTokens().subList(15, 25), 15, 134454645);
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
	public void testAlignmentNeedlemanWunsch() {
		assertEquals(qGram.size()*2, (qGramEqual.alignmentNeedlemanWunsch(qGram)));
	}

}
