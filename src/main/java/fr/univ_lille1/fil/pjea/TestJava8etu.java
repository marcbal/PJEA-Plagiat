package fr.univ_lille1.fil.pjea;
import fr.univ_lille1.fil.pjea.analyzer.java8.*;
import org.antlr.v4.runtime.*;

public class TestJava8etu {

    public static void main(String[] args) throws Exception {
        ANTLRInputStream in = new ANTLRFileStream("src/main/java/fr/univ_lille1/fil/pjea/TestJava8etu.java");
        Java8Lexer lexer = new Java8Lexer(in);
        Vocabulary voc = lexer.getVocabulary();
        for (Token tok : lexer.getAllTokens()) {
           System.out.println(tok.getText()); 
        }        
    }
}