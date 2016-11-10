package fr.univ_lille1.fil.pjea.testfilegenerator;

import java.io.IOException;
import java.io.PrintStream;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.antlr.v4.runtime.Token;

import fr.univ_lille1.fil.pjea.data.Java8File;

public class FileGenerator {

	private static final String[] EOL_TOKENS = {";", "{", "}"};
	private static final String[] MODIFIER_TOKENS = {"public", "protected", "private"};
	
	private List<Token> imports = new ArrayList<>();
	private List<Token> otherDecls = new ArrayList<>();
	private boolean firstPackage = true;
	
	
	public FileGenerator(Java8File... files) {
		for (Java8File java8File : files) {
			separate(java8File);
		}
	}
	
	public void separate(Java8File f) {
		
        List<Token> tokens = f.tokens;
         
        int nextClassDecl = 0;
        
        Iterator<Token> iter = tokens.iterator();
        for (Token token; iter.hasNext();) {
        	token = iter.next();
        	
            /* Remove declaration package line */
            if (token.getText().equals("package")) {
            	do {
            		if (firstPackage ) imports.add(token);
    	        	token = iter.next();
    	        } while (!token.getText().equals(";")); 
            	
            	if (firstPackage) imports.add(token);
            	firstPackage = false;
    	        	
            } else if (token.getText().equals("import")) {
            	do {
        			imports.add(token);
        			token = iter.next();
        		} while (!token.getText().equals(";"));
            	
            	imports.add(token);
            	
        	} else { /* other decl */  
        		final String tokenText = token.getText(); /* For the lambda */ 
        		if (nextClassDecl == 0 && 
        				Arrays.stream(MODIFIER_TOKENS).anyMatch(e -> e.equals(tokenText))) {
        			token = iter.next();
        		}
        		String tt = token.getText();
        		if (tt.equals("{")) {
        			nextClassDecl++;
        		} else if (tt.equals("}")) {
        			nextClassDecl--;
        		}
        		otherDecls.add(token);
        	}
     	
        }
        
       return;
	}
	
	
	
	void print(PrintStream stream) {
    	
		for (Token imp : imports) {
			final String tt = imp.getText();
			stream.print(tt + " ");
			if (Arrays.stream(EOL_TOKENS).anyMatch(e -> e.equals(tt))) stream.println();
	
		}
		
		for (Token decl : otherDecls) {
			final String tt = decl.getText();
			stream.print(tt + " ");
			if (Arrays.stream(EOL_TOKENS).anyMatch(e -> e.equals(tt))) stream.println();
		}
	}
	
	
	
	public static void main(String[] args) throws Exception {
		Java8File[] files = Arrays.stream(args)
				.map(arg -> {
					try  {
						return new Java8File(arg);
					} catch (IOException e1) {
						throw new UncheckedIOException(e1);
					}
				})
				.toArray((length) -> new Java8File[length]);
        
		new FileGenerator(files).print(System.out);
	}
	
}
