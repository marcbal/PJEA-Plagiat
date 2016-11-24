package fr.univ_lille1.fil.pjea.testfilegenerator;

import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;

import fr.univ_lille1.fil.pjea.PlagiatVEMPTest;

public class FileGeneratorTest {
	
	static final String DIR = PlagiatVEMPTest.TEST_FILE_DIR + "mega_test/";
	
	PrintStream sysout;
	
	@SuppressWarnings("resource")
	@Before
	public void setUp() throws Exception {
		sysout = System.out;
		System.setOut(new PrintStream(DIR + "vide.java"));
	}
	
	@After
	public void tearDown() throws Exception {
		System.out.close();
		System.setOut(sysout);
	}
	
	@SuppressWarnings("static-method")
	//@Test
	public void testMain() {
		File inputDir = new File(DIR + "in");
		inputDir.mkdirs(); // si nécessaire
		String[] listFiles = inputDir.list((f,n) -> n.endsWith(".java"));
		listFiles = Arrays.stream(listFiles)
				.map((f) -> DIR + "in/" + f)
				.collect(Collectors.toList())
				.toArray(new String[listFiles.length]);
		FileGenerator.main(listFiles);
	}
	
}
