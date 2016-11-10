package fr.univ_lille1.fil.pjea.testfilegenerator;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.antlr.v4.runtime.Token;

import fr.univ_lille1.fil.pjea.Java8File;

public class FileGenerator {

	private static final String[] EOL_TOKENS = {";", "{", "}"};
	
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
		
		buildFile(files[0], files[1]);
	}

	
	public static File buildFile(Java8File f1, Java8File f2) throws Exception {
		
		File newFile = new File("concact" + f1.file.getName() + "-" + f2.file.getName());
		
        List<Token> listToken1 = f1.tokens;
        List<Token> listToken2 = f2.tokens;
        
        List<Token> listTokenR = new ArrayList<>(listToken1);
        listTokenR.addAll(listToken2);
        
        
        List<Token> imports = new ArrayList<>();
        List<Token> allDecl = new ArrayList<>();
       
        boolean nextClassDecl = false;
        
        Iterator<Token> iter = listTokenR.iterator();
        for (Token token; iter.hasNext();) {
        	token = iter.next();
        	
            /* Remove declaration package line */
            if (token.getText() == "package") {
    	        do {
    	        	token = iter.next();
    	        } while (!token.getText().equals(";")); 
    	        	
            } else if (token.getText() == "import") {
        		do {
        			imports.add(token);
        			token = iter.next();
        		} while (!token.getText().equals(";"));
        			
        	} else { /* other decl */  
        		
        	}
        	
        	
        	//System.out.println(token.getText() + "	" + f1.vocabulary.getSymbolicName(token.getType()));
        }
        
        /* Printing */ 
        StringBuilder importsSb = new StringBuilder();
        StringBuilder otherDeclSb = new StringBuilder();
        //for ...
        //if (Arrays.stream(EOL_TOKENS).anyMatch(e -> e.equals(token.getText()))) {
    		importsSb.append('\n');
    	//}
        
        
        
        
		return newFile;
	}

	
	
}
