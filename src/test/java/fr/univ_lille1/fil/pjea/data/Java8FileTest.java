package fr.univ_lille1.fil.pjea.data;

import org.antlr.v4.runtime.Token;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
	
	@Test
	public void testFilterImport() {
		
		// Vocabulary voc = lexer.getVocabulary();
		
		for (Token tok : file1.fileWithoutPkgImp.tokens) {
			System.out.println(tok.getText()+"	"+ file1.fileWithoutPkgImp.vocabulary.getSymbolicName(tok.getType()));
		}
		
		System.out.println("\n-------------------\n");
		for (String line : file1.fileWithoutPkgImp.fileLines) {
			System.out.println(line);
		}
	}
	
	@Test
	public void testFilterPackage() {
		//fail("Pas encore implémenté");
	}
	
	
}
