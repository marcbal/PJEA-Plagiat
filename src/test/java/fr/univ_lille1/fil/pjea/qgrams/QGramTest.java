package fr.univ_lille1.fil.pjea.qgrams;

import static org.junit.Assert.*;

import java.io.IOException;

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
	public void testIdenticalQGramDistanceIsZero(){
		assertEquals(0, qGram.distance(qGram));
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void testIdenticalQGramDistance() {
		ANTLRInputStream in1 = null;
		try {
			in1 = new ANTLRFileStream("src/test/resources/fileNbT8.java");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ANTLRInputStream in2 = null;
		try {
			in2 = new ANTLRFileStream("src/test/resources/fileNbT12.java");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Lexer l1 = new Java8Lexer(in1);
        Lexer l2 = new Java8Lexer(in2);
        QGram qGram1 = new TokenReader(l1, 8).iterator().next();
		QGram qGram2 = new TokenReader(l2, 11).iterator().next();
		/**
		 * Info : Pourquoi 4 ? On compte le nombre de tokens manuellement dans les fichiers de comparaison
		 * Et on ajoute 1 pour car le nom de la classe est changée dans le second.
		 */
		assertEquals(4, qGram1.distance(qGram2));
	
	}
	
	@Test
	public void testAlignmentNeedlemanWunsch() {
		assertEquals(qGram.size()*2, (qGramEqual.alignmentNeedlemanWunsch(qGram)));
	}

}
