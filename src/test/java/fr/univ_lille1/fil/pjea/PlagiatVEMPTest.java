package fr.univ_lille1.fil.pjea;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class PlagiatVEMPTest {
	
	private static final String TEST_FILE_DIR = "src/test/resources/";
	
	private static final String[] TEST_PACK_1 = new String[] {
			TEST_FILE_DIR + "file1.java",
			TEST_FILE_DIR + "file1copy.java",
			TEST_FILE_DIR + "file1modif.java",
			TEST_FILE_DIR + "file1rearanged.java"
	};
	
	// TODO ajouter d'autres jeu de test (et créer une méthode de test associé, ci-dessous)
	
	// IMPORTANT : ne pas mettre de jeu de test trop lourd. On réserve ça pour des
	// tests manuels
	
	
	
	
	
	// DONT TOUCH
	private static final PrintStream stdout = System.out;

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.setOut(stdout); // restaure stdout in System.out;
	}
	// ----------
	
	
	ByteArrayOutputStream outputContent;

	@Before
	public void setUp() throws Exception {
		outputContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputContent, true));
	}

	@After
	public void tearDown() throws Exception {
		outputContent = null;
	}
	

	
	
	
	/*
	 * 
	 * Les classes de tests.
	 * Chacun exécute une fois la fonction main
	 * et le contenu de la sortie standard est récupérable
	 * via outputContent.toString();
	 */
	
	@Test
	public void testMainNoFile() {
		PlagiatVEMP.main(new String[0]);
		assertEquals("", outputContent.toString());
	}

	@Test
	public void testMainOneFile() {
		PlagiatVEMP.main(new String[] {TEST_PACK_1[0]});
		assertEquals("", outputContent.toString());
	}

	@Test
	public void testMainPack1() {
		PlagiatVEMP.main(TEST_PACK_1);
		String output = outputContent.toString();
		assertNotSame("", output);
	}

}
