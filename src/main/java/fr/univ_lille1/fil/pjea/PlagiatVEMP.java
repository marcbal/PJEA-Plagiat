package fr.univ_lille1.fil.pjea;

import java.util.List;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.Token;

import fr.univ_lille1.fil.pjea.analyzer.java8.Java8Lexer;

public class PlagiatVEMP {


    public static void main(String[] args) throws Exception {
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
