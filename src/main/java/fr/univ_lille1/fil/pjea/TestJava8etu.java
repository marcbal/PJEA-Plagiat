package fr.univ_lille1.fil.pjea;
import fr.univ_lille1.fil.pjea.analyzer.java8.*;
import org.antlr.v4.runtime.*;




public class TestJava8etu {

    public static void main(String[] args) throws Exception {
    	if (args.length == 0)
    		throw new IllegalArgumentException("Au moins un paramètre est requis");
        ANTLRInputStream in = new ANTLRFileStream(args[0]);
        Java8Lexer lexer = new Java8Lexer(in);
        // Vocabulary voc = lexer.getVocabulary();
        for (Token tok : lexer.getAllTokens()) {
           System.out.println(tok.getText()+"	"+lexer.getVocabulary().getSymbolicName(tok.getType())); 
        }        
        
        
    }
}