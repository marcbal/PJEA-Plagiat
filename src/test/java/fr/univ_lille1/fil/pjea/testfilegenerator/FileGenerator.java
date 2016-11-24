package fr.univ_lille1.fil.pjea.testfilegenerator;

import java.io.IOException;
import java.io.PrintStream;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.Vocabulary;

import fr.univ_lille1.fil.pjea.data.Java8File;

/*
 * TODO prendre en charge le cas où plusieurs classes fournis ont le même noms (dans des packages différents)
 */
public class FileGenerator {
	
	private static final String INDENTATION = "    ";
	private static final String[] END_OF_LINE_TOKENS = { ";", "{", "}" };
	private static final String[] MODIFIER_TOKENS = { "public", "protected", "private" };
	
	private List<Token> packageDeclarator = new ArrayList<>();
	private List<Token> imports = new ArrayList<>();
	private List<Token> classes = new ArrayList<>();
	private boolean packageAlreadyDeclared = false;
	private Vocabulary vocabulary;
	
	
	public FileGenerator(Java8File... files) {
		if (files == null || files.length == 0)
			return;
		vocabulary = files[0].vocabulary;
		for (Java8File java8File : files) {
			System.err.println(java8File.file);
			separate(java8File);
		}
	}
	
	public void separate(Java8File f) {
		
		List<Token> tokens = f.getTokens();
		
		int nextClassDecl = 0;
		
		Iterator<Token> iter = tokens.iterator();
		for (Token token; iter.hasNext();) {
			token = iter.next();
			
			/* Remove declaration package line */
			if (token.getText().equals("package")) {
				do {
					if (!packageAlreadyDeclared)
						packageDeclarator.add(token);
					token = iter.next();
				} while (!token.getText().equals(";"));
				
				if (!packageAlreadyDeclared)
					packageDeclarator.add(token);
				packageAlreadyDeclared = true;
				
			}
			else if (token.getText().equals("import")) {
				do {
					imports.add(token);
					token = iter.next();
				} while (!token.getText().equals(";"));
				
				imports.add(token);
				
			}
			else { /* other decl */
				if (nextClassDecl == 0 &&
						isTokenJavaModifier(token)) {
					token = iter.next();
				}
				String tt = token.getText();
				if (tt.equals("{")) {
					nextClassDecl++;
				}
				else if (tt.equals("}")) {
					nextClassDecl--;
				}
				classes.add(token);
			}
			
		}
		
		return;
	}
	
	private static boolean isTokenJavaModifier(Token t) {
		return Arrays.stream(MODIFIER_TOKENS).anyMatch(e -> e.equals(t.getText()));
	}
	
	
	
	
	
	void print(PrintStream stream) {
		System.err.println("Printing ...");
		printTokenList(stream, packageDeclarator);
		stream.println();
		printTokenList(stream, imports);
		stream.println();
		printTokenList(stream, classes);
	}
	
	
	
	private void printTokenList(PrintStream stream, List<Token> tokens) {
		Token prevT = null;
		int currentIndent = 0;
		
		for (Token t : tokens) {
			if (addSpaceBetween(prevT, t))
				stream.print(' ');
			stream.print(t.getText());
			if (endOfLineAfterToken(t)) {
				stream.println();
				if (t.getText().equals("{"))
					currentIndent++;
				else if (t.getText().equals("}"))
					currentIndent--;
				stream.print(indent(currentIndent));
			}
			prevT = t;
		}
	}
	
	private boolean addSpaceBetween(Token t1, Token t2) {
		if (t1 == null
				|| endOfLineAfterToken(t1)
				|| t1.getText().equals("(")
				|| t1.getText().equals("[")
				|| t1.getText().equals("@"))
			return false;
		if (t2.getText().equals(";")
				|| t2.getText().equals(",")
				|| t2.getText().equals(")")
				|| t2.getText().equals("]"))
			return false;
		if (t1.getText().equals(".") || t2.getText().equals("."))
			return false;
		if (vocabulary.getSymbolicName(t1.getType()).equals("Identifier")
				&& (t2.getText().equals("(")
						|| t2.getText().equals("[")))
			return false;
		
		return true;
	}
	
	
	
	private static boolean endOfLineAfterToken(Token t) {
		return Arrays.stream(END_OF_LINE_TOKENS).anyMatch(e -> e.equals(t.getText()));
	}
	
	private static String indent(int count) {
		String str = "";
		for (int i = 0; i < count; i++) {
			str += INDENTATION;
		}
		return str;
	}
	
	
	
	public static void main(String[] args) {
		
		/*
		 * Convert an array of string that contains file path
		 * to an array of Java8File instance that represents a file
		 */
		Java8File[] files = Arrays.stream(args)
				.map(arg -> {
					try {
						return new Java8File(arg);
					} catch (IOException e1) {
						throw new UncheckedIOException(e1);
					}
				})
				.toArray((length) -> new Java8File[length]);
		
		new FileGenerator(files).print(System.out);
	}
	
	
}
