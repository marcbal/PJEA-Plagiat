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
	
	
	private Java8File(File file, List<Token> tokens, List<String> fileLines, Vocabulary vocabulary) {
		this.file = file;
		this.tokens = Collections.unmodifiableList(tokens);
		this.fileLines = Collections.unmodifiableList(fileLines);
		this.vocabulary = vocabulary;
	}
	
	
	public Footprint getFootprint(int q, int t) {
		return new WinnowingFootprintBuilder(tokens, q, t).build();
	}
	
	public Footprint getFootprint(int q) {
		return new WinnowingFootprintBuilder(this, q).build();
	}
	
	protected Java8File filterPackage() {
		if (tokens.get(0).getText().equals("package")) return this;

		/* For the token */
		List<Token> tks;
		int endPackageDclTok = 0;
		while (!tokens.get(endPackageDclTok).getText().equals(";")) endPackageDclTok++;
		tks = tokens.subList(endPackageDclTok + 1, tokens.size());
		
		/* For the fileLines */
		boolean end = false;
		int nLines = fileLines.size();
		int iLine = 0, iChar = 0;
		for (iLine = 0; iLine < nLines && !end; ++iLine) {
			
			int lenLine = fileLines.get(iLine).length();
			for (iChar = 0; iChar < lenLine && !end; iChar++) {
				if (fileLines.get(iLine).charAt(iChar) == ';') end = true;
			}
		}

		List<String> fLs = fileLines.subList(iLine, nLines);
		fileLines.set(0, fileLines.get(0).substring(iChar + 1));
		
		return new Java8File(file, tks, fLs, vocabulary);
	}
	
	protected Java8File filterImport() {
		if (tokens.get(0).getText().equals("import")) return this;
		
		/* For the token */
		List<Token> tks;
		int endImportDclTok = 0;
		
		while (!tokens.get(endImportDclTok).getText().equals("import")) {
			
			while (!tokens.get(endImportDclTok).getText().equals(";")) endImportDclTok++;
			endImportDclTok++;
		}

		tks = tokens.subList(endImportDclTok, tokens.size());
		
		/* For the fileLines */
		boolean endLine = false;
		int nLines = fileLines.size();
		int iLine = 0, iChar = 0;
		
		for (iLine = 0; iLine < nLines; ++iLine) {
			if (!fileLines.get(iLine).startsWith("import")) {
				iChar = -1; // Pour le substring plus loin utilisant l'index
				break;
			}

			int lenLine = fileLines.get(iLine).length();
			for (iChar = 0; iChar < lenLine && !endLine; iChar++) {
				if (fileLines.get(iLine).charAt(iChar) == ';') endLine = true;
			}
		}

		List<String> fLs = fileLines.subList(iLine, nLines);
		fileLines.set(0, fileLines.get(0).substring(iChar + 1));
		
		return new Java8File(file, tks, fLs, vocabulary);
	}
	
	public Java8File filterPackageAndImport() {
		return filterPackage().filterImport();
	}
	
	
	public static Lexer getJava8Lexer(File f) throws IOException {
		ANTLRInputStream in = new ANTLRFileStream(f.getPath());
		return new Java8Lexer(in);
	}
	
	
	
}
