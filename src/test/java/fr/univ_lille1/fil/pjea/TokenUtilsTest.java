package fr.univ_lille1.fil.pjea;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import fr.univ_lille1.fil.pjea.data.Java8File;

public class TokenUtilsTest {
	
	@SuppressWarnings("static-method")
	@Test
	public void testEqualsTokens() throws IOException {
		Java8File f = new Java8File(PlagiatVEMPTest.TEST_FILE_NB_TOKEN_16);
		assertTrue(TokenUtils.equalsTokens(f.getTokens().get(0), f.getTokens().get(0)));
		assertTrue(TokenUtils.equalsTokens(f.getTokens().get(4), f.getTokens().get(7)));
		assertFalse(TokenUtils.equalsTokens(f.getTokens().get(4), f.getTokens().get(5)));
		assertFalse(TokenUtils.equalsTokens(f.getTokens().get(5), f.getTokens().get(8)));
		assertFalse(TokenUtils.equalsTokens(null, f.getTokens().get(8)));
		assertFalse(TokenUtils.equalsTokens(f.getTokens().get(0), null));
		assertTrue(TokenUtils.equalsTokens(null, null));
		assertTrue(TokenUtils.equalsTokens(f.getTokens().get(2), f.getTokens().get(11)));
	}
	
}
