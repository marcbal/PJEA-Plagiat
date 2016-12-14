package fr.univ_lille1.fil.pjea;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class PlagiatVEMPTest {
	
	public static final String TEST_FILE_DIR = "src/test/resources/";
	
	public static final String TEST_FILE_NB_TOKEN_16 = TEST_FILE_DIR + "fileNbT16.java";
	public static final String TEST_FILE_NB_TOKEN_12 = TEST_FILE_DIR + "fileNbT12.java";
	public static final String TEST_FILE_NB_TOKEN_8 = TEST_FILE_DIR + "fileNbT8.java";
	public static final String TEST_FILE_WINNOWING = TEST_FILE_DIR + "filewinnowing.java";
	
	public static final String[] TEST_PACK_1 = new String[] {
			TEST_FILE_DIR + "test_pack_1/file1.java",
			TEST_FILE_DIR + "test_pack_1/file1copy.java",
			TEST_FILE_DIR + "test_pack_1/file1modif.java",
			TEST_FILE_DIR + "test_pack_1/file1rearanged.java"
	};
	
	public static final String[] TEST_PACK_2 = new String[] {
			TEST_FILE_DIR + "test_pack_2/BigFile1.java",
			TEST_FILE_DIR + "test_pack_2/BigFile2.java"
	};
	
	public static final String[] TEST_MEGA_PACK = new String[] {
			TEST_FILE_DIR + "mega_test/BC-lastbuild-A-E.java",
			TEST_FILE_DIR + "mega_test/BC-2yearsbefore-A-E.java"
	};
	
	public static final String[] TEST_PACK_SPACES = new String[] {
			TEST_FILE_DIR + "test_pack_spaces/file1.java",
			TEST_FILE_DIR + "test_pack_spaces/file1copy.java",
			TEST_FILE_DIR + "test_pack_spaces/filespaces1.java",
			TEST_FILE_DIR + "test_pack_spaces/filespaces2.java",
			TEST_FILE_DIR + "test_pack_spaces/fileRandomSpace1.java",
			TEST_FILE_DIR + "test_pack_spaces/fileRandomSpace2.java"
	};
	
	public static final String[] TEST_EXEMPLE_8 = listFilesRecursive(new File(TEST_FILE_DIR + "exemple8"));
	
	
	// TODO ajouter d'autres jeu de test (et créer une méthode de test associé, ci-dessous)
	
	
	
	
	
	private static String[] listFilesRecursive(File d) {
		List<String> ret = new ArrayList<>();
		
		if (!d.isDirectory())
			return new String[0];
		for (File f : d.listFiles()) {
			if (f.isDirectory()) {
				for (String s : listFilesRecursive(f)) {
					ret.add(s);
				}
			}
			else if (f.getName().toLowerCase().endsWith(".java")) {
				ret.add(f.getPath());
			}
			
		}
		
		return ret.toArray(new String[ret.size()]);
	}
	
	
	
	
	
	
	
	
	// DONT TOUCH
	private static final PrintStream STDOUT = System.out;
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.setOut(STDOUT); // restaure stdout in System.out;
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
	 * Les méthodes de tests pour main().
	 * Chacun exécute une seule fois la fonction main.
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
		PlagiatVEMP.main(new String[] { TEST_PACK_1[0] });
		assertEquals("", outputContent.toString());
	}
	
	@Test
	public void testMainPack1() {
		PlagiatVEMP.main(TEST_PACK_1);
		String output = outputContent.toString();
		STDOUT.println(output);
		assertEquals(6, output.split(System.lineSeparator()).length);
		
	}
	
	@Test
	public void testMainExemple8() {
		PlagiatVEMP.main(TEST_EXEMPLE_8);
		String output = outputContent.toString();
		STDOUT.println(output);
		assertEquals(TEST_EXEMPLE_8.length * (TEST_EXEMPLE_8.length - 1) / 2, output.split(System.lineSeparator()).length);
		
	}
	
	@Test
	public void testMainPack2() {
		PlagiatVEMP.main(TEST_PACK_2);
		String output = outputContent.toString();
		STDOUT.println(output);
		assertEquals(1, output.split(System.lineSeparator()).length);
	}
	
	@Test
	public void testPackSpaces() {
		PlagiatVEMP.main(TEST_PACK_SPACES);
		String output = outputContent.toString();
		STDOUT.println(output);
		assertEquals(15, output.split(System.lineSeparator()).length);
	}
	
	@Test
	public void testMainMega1() {
		PlagiatVEMP.main(TEST_MEGA_PACK);
		String output = outputContent.toString();
		STDOUT.println(output);
		assertEquals(1, output.split(System.lineSeparator()).length);
	}
	@SuppressWarnings("static-method")
	@Test(expected = UncheckedIOException.class)
	public void testMainFileNotExist() {
		PlagiatVEMP.main(new String[] { "target/.unexistant_file" });
	}
	
	
}
