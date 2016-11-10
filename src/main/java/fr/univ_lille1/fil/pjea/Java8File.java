package fr.univ_lille1.fil.pjea;

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

public class Java8File {
	
	public final File file;
	public final List<Token> tokens;
	public final List<String> fileLines;
	public final Vocabulary vocabulary; 
	
	
	public Java8File(String fileName) throws IOException {
		file = new File(fileName);
		
		Lexer lexer = getJava8Lexer(file);
		
		tokens = Collections.unmodifiableList(lexer.getAllTokens());
		
		try (BufferedReader r = new BufferedReader(new FileReader(file))) {
			fileLines = Collections.unmodifiableList(r.lines().collect(Collectors.toList()));
		}
		
		vocabulary = lexer.getVocabulary();
	}
	

	public static Lexer getJava8Lexer(File f) throws IOException {
        ANTLRInputStream in = new ANTLRFileStream(f.getPath());
        return new Java8Lexer(in);
	}

}
