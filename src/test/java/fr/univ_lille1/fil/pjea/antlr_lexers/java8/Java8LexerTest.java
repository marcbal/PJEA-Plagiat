package fr.univ_lille1.fil.pjea.antlr_lexers.java8;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.Token;
import org.junit.Test;

import fr.univ_lille1.fil.pjea.PlagiatVEMPTest;

public class Java8LexerTest {

	@SuppressWarnings("static-method")
	@Test
	public void testGetAllToken() throws IOException {
		ANTLRInputStream in = new ANTLRFileStream(PlagiatVEMPTest.TEST_PACK_1[0]);
		Java8Lexer lexer = new Java8Lexer(in);
		// Vocabulary voc = lexer.getVocabulary();
		for (Token tok : lexer.getAllTokens()) {
			System.out.println(tok.getText()+"	"+lexer.getVocabulary().getSymbolicName(tok.getType()));
		}
	}

}
