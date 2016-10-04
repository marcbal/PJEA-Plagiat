package fr.univ_lille1.fil.pjea;
import fr.univ_lille1.fil.pjea.analyzer.java8.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;




public class TestJava8etu {

    public static void main(String[] args) throws Exception {
    	if (args.length == 0)
    		throw new IllegalArgumentException("Au moins un paramètre est requis");
        ANTLRInputStream in = new ANTLRFileStream(args[0]);
        Java8Lexer lexer = new Java8Lexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // create a parser that feeds off the tokens buffer
        Java8Parser parser = new Java8Parser(tokens);
        ParseTree tree = parser.compilationUnit(); // begin parsing at init rule
        System.out.println(tree.toStringTree(parser).replaceAll("\\)", ")\n")); // print LISP-style tree      
        
        
    }
}