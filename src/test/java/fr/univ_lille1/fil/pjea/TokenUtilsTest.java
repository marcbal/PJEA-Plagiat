package fr.univ_lille1.fil.pjea;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import fr.univ_lille1.fil.pjea.data.Java8File;

public class TokenUtilsTest {

	@SuppressWarnings("static-method")
	@Test
	public void testEqualsTokens() throws IOException {
		Java8File f = new Java8File(PlagiatVEMPTest.TEST_FILE_NB_TOKEN_16);
		assertTrue(TokenUtils.equalsTokens(f.tokens.get(0), f.tokens.get(0)));
		assertTrue(TokenUtils.equalsTokens(f.tokens.get(4), f.tokens.get(7)));
		assertFalse(TokenUtils.equalsTokens(f.tokens.get(4), f.tokens.get(5)));
		assertFalse(TokenUtils.equalsTokens(f.tokens.get(5), f.tokens.get(8)));
		assertFalse(TokenUtils.equalsTokens(null, f.tokens.get(8)));
		assertFalse(TokenUtils.equalsTokens(f.tokens.get(0), null));
		assertTrue(TokenUtils.equalsTokens(null, null));
		assertTrue(TokenUtils.equalsTokens(f.tokens.get(2), f.tokens.get(11)));
	}

}
