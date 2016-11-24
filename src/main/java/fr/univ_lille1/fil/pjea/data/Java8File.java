package fr.univ_lille1.fil.pjea.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
	public final List<Token> tokens;
	public final List<String> fileLines;
	public final Vocabulary vocabulary;
	
	public final Java8File fileWithoutPkgImp;
	
	
	public Java8File(String fileName) throws IOException {
		file = new File(fileName);
		
		Lexer lexer = getJava8Lexer(file);
		
		tokens = Collections.unmodifiableList(lexer.getAllTokens());
		
		try (BufferedReader r = new BufferedReader(new FileReader(file))) {
			fileLines = Collections.unmodifiableList(r.lines().collect(Collectors.toList()));
		}
		
		vocabulary = lexer.getVocabulary();
		
		fileWithoutPkgImp = filterPackageAndImport();
	}
	
	
	private Java8File(File file, List<Token> tokens, List<String> fileLines, Vocabulary vocabulary,
			Java8File fileWithoutPkgImp) {
		this.file = file;
		this.tokens = Collections.unmodifiableList(tokens);
		this.fileLines = Collections.unmodifiableList(fileLines);
		this.vocabulary = vocabulary;
		this.fileWithoutPkgImp = fileWithoutPkgImp;
	}
	
	private Java8File(File file, List<Token> tokens, List<String> fileLines, Vocabulary vocabulary) {
		this(file, tokens, fileLines, vocabulary, null);
	}
	
	
	public Footprint getFootprint(int q, int t) {
		return new WinnowingFootprintBuilder(tokens, q, t).build();
	}
	
	public Footprint getFootprint(int q) {
		return new WinnowingFootprintBuilder(this, q).build();
	}
	
	protected Java8File filterPackageAndImport() {
		
		/* Filtrage via les tokens */
		int iToken = 0;
		while (tokens.get(iToken).getText().equals("import") || tokens.get(iToken).getText().equals("package")) {
			while (!tokens.get(++iToken).getText().equals(";"));
			iToken++;
		}
		
		List<Token> retainedTokens = tokens.subList(iToken, tokens.size());
		
		
		/* filtrage des lignes */
		int iLine = retainedTokens.get(0).getLine() - 1;
		int iChar = retainedTokens.get(0).getCharPositionInLine();
		
		List<String> retainedLines = new ArrayList<>(fileLines.subList(iLine, fileLines.size()));
		
		retainedLines.set(0, retainedLines.get(0).substring(iChar));
		
		return new Java8File(file, retainedTokens, retainedLines, vocabulary);
	}
	
	
	public static Lexer getJava8Lexer(File f) throws IOException {
		ANTLRInputStream in = new ANTLRFileStream(f.getPath());
		return new Java8Lexer(in);
	}
	
	
	
}
