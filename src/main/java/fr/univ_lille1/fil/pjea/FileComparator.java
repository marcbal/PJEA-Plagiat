package fr.univ_lille1.fil.pjea;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;

import fr.univ_lille1.fil.pjea.analyzer.java8.Java8Lexer;



/**
 * Compare deux fichiers sources et indique le taux de similitude.
 */
public class FileComparator {
	
	private File file1, file2;
	
	public FileComparator(File f1, File f2) {
		file1 = f1;
		file2 = f2;
	}
	
	
	/**
	 * @return a number between 0 and 1.
	 * 0 is full different
	 * and 1 is totally equals.
	 * @throws Exception 
	 */
	public double computeDifference() throws Exception {
		Lexer lexer1 = getLexer(file1);
		Lexer lexer2 = getLexer(file2);
		

        Iterator<List<? extends Token>> it1 = new TokenReader(lexer1).iterator();
        Iterator<List<? extends Token>> it2 = new TokenReader(lexer2).iterator();
        
        while (it1.hasNext() && it2.hasNext()) {
        	Token t1 = it1.next().get(0);
        	Token t2 = it2.next().get(0);
        	if (!t1.getText().equals(t2.getText()) || !(t1.getType() == t2.getType()))
        		return 0;
        	
        }
		
		return (it1.hasNext() || it2.hasNext()) ? 0 : 1;
	}
	
	
	
	
	
	
	private Lexer getLexer(File f) throws IOException {
        ANTLRInputStream in = new ANTLRFileStream(f.getPath());
        return new Java8Lexer(in);
	}

	
	
	
	
	

    public static void oldMain(String[] args) throws Exception {
    	if (args.length == 0)
    		throw new IllegalArgumentException("Au moins un paramètre est requis");
        ANTLRInputStream in = new ANTLRFileStream(args[0]);
        Java8Lexer lexer = new Java8Lexer(in);
        
        TokenReader tokenReader = new TokenReader(lexer, 1, 7);
        int i=0;
        for (List<? extends Token> tokens : tokenReader) {
        	for (Token t : tokens) {
        		System.out.println("It="+i+" Token="+t.getText());
        	}
        	i++;
        }
        
    }

	
}
