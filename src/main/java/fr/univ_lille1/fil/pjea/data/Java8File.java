package fr.univ_lille1.fil.pjea.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.Vocabulary;

import fr.univ_lille1.fil.pjea.antlr_lexers.java8.Java8Lexer;
import fr.univ_lille1.fil.pjea.data.builder.WinnowingFootprintBuilder;

public class Java8File {
	
	public final File file;
	public final Vocabulary vocabulary;
	protected List<Token> tokens;
	protected List<String> fileLines;
	
	protected Java8File cleanedFile;
	
	
	@SuppressWarnings("unchecked")
	public Java8File(String fileName) throws IOException {
		file = new File(fileName);
		
		Lexer lexer = getJava8Lexer(file);
		
		tokens = (List<Token>) lexer.getAllTokens();
		
		try (BufferedReader r = new BufferedReader(new FileReader(file))) {
			fileLines = r.lines().collect(Collectors.toList());
		}
		
		vocabulary = lexer.getVocabulary();
		
		cleanedFile = new CleanedJava8File(this);
	}
	
	/**
	 * Utilisé uniquement par la sous-classe {@link CleanedJava8File}
	 * pour définir les valeurs des propriétés final
	 * @param file
	 * @param vocabulary
	 */
	protected Java8File(File file, Vocabulary vocabulary) {
		this.file = file;
		this.vocabulary = vocabulary;
	}
	
	
	public Footprint getFootprint(int q, int t) {
		return new WinnowingFootprintBuilder(tokens, q, t).build();
	}
	
	public Footprint getFootprint(int q) {
		return new WinnowingFootprintBuilder(this, q).build();
	}
	
	
	public static Lexer getJava8Lexer(File f) throws IOException {
		ANTLRInputStream in = new ANTLRFileStream(f.getPath());
		return new Java8Lexer(in);
	}
	
	
	
	
	public Java8File getCleanedFile() {
		return cleanedFile;
	}
	
	public List<Token> getTokens() {
		return Collections.unmodifiableList(tokens);
	}
	
	public List<String> getLines() {
		return Collections.unmodifiableList(fileLines);
	}
	
	
	
}
