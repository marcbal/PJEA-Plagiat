package fr.univ_lille1.fil.pjea.qgrams;

import static org.junit.Assert.*;


import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.Lexer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univ_lille1.fil.pjea.antlr_lexers.java8.Java8Lexer;

public class TokenReaderTest {

		TokenReader toks;
	@Before
	public void setUp() throws Exception {
		
		ANTLRInputStream in = new ANTLRFileStream("src/test/resources/file1.java");
        Lexer l = new Java8Lexer(in);
		toks = new TokenReader(l.getAllTokens().subList(15, 25), 1, 1);
	}

	@After
	public void tearDown() throws Exception {
		toks = null;
	}

	@Test
	public void testIterator() {
	}
	
	@Test
	public void testEqualsTokens() {
	}

	@Test
	public void testHashCodeToken() {
	}
	
	

}

