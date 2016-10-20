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

import fr.univ_lille1.fil.pjea.antlr_lexers.java8.Java8Lexer;

public class Java8File {
	
	public final File file;
	public final List<? extends Token> tokens;
	public final List<String> fileLines;
	
	
	public Java8File(String fileName) throws IOException {
		file = new File(fileName);
		tokens = Collections.unmodifiableList(getJava8Lexer(file).getAllTokens());
		try (BufferedReader r = new BufferedReader(new FileReader(file))) {
			fileLines = Collections.unmodifiableList(r.lines().collect(Collectors.toList()));
		}
	}
	
	
	
	
	
	
	
	public static Lexer getJava8Lexer(File f) throws IOException {
        ANTLRInputStream in = new ANTLRFileStream(f.getPath());
        return new Java8Lexer(in);
	}

}
