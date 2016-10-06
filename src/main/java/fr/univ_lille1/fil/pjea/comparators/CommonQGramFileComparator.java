package fr.univ_lille1.fil.pjea.comparators;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.antlr.v4.runtime.Lexer;

import fr.univ_lille1.fil.pjea.TokenReader;
import fr.univ_lille1.fil.pjea.TokenReader.QGram;

public class CommonQGramFileComparator extends FileComparator {
	
	private int q;
	
	public CommonQGramFileComparator(File f1, File f2, int q) {
		super(f1, f2);
		this.q = q;
	}

	@Override
	public double computeDifference() throws Exception {
		Lexer lexer1 = getJava8Lexer(file1);
		Lexer lexer2 = getJava8Lexer(file2);
		
	    Set<QGram> qGrams1 = new HashSet<>(new TokenReader(lexer1, 1, q).getAllQGrams());
	    Set<QGram> qGrams2 = new HashSet<>(new TokenReader(lexer2, 1, q).getAllQGrams());
	    int nbQGramTotal = Math.min(qGrams1.size(), qGrams2.size());
	    
	    Set<QGram> commonQGram = new HashSet<>(qGrams1);
	    commonQGram.retainAll(qGrams2);
	    System.out.println(commonQGram.size());
	    System.out.println(nbQGramTotal);
	    
	    return commonQGram.size() / (double) nbQGramTotal;
	}

}
