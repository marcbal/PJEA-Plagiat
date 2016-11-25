package fr.univ_lille1.fil.pjea.data;

import org.antlr.v4.runtime.Token;
import org.junit.After;
import org.junit.Before;

import fr.univ_lille1.fil.pjea.PlagiatVEMPTest;

public class Java8FileTest {
	
	
	Java8File file1;
	
	@Before
	public void setUp() throws Exception {
		file1 = new Java8File(PlagiatVEMPTest.TEST_PACK_1[0]);
	}
	
	@After
	public void tearDown() throws Exception {
		file1 = null;
	}
	
	// @Test // ce test ne fait que des print
	public void testFilterImport() {
		
		for (Token tok : file1.cleanedFile.tokens) {
			System.out.println(tok.getText()+"	"+ file1.cleanedFile.vocabulary.getSymbolicName(tok.getType()));
		}
		
		System.out.println("\n-------------------\n");
		for (String line : file1.cleanedFile.fileLines) {
			System.out.println(line);
		}
	}
	
	
}
