package fr.univ_lille1.fil.pjea.comparators;

import java.util.HashSet;
import java.util.Set;

import fr.univ_lille1.fil.pjea.Java8File;
import fr.univ_lille1.fil.pjea.qgrams.QGram;
import fr.univ_lille1.fil.pjea.qgrams.TokenReader;

public class CommonQGramFileComparator extends FileComparator {
	
	private int q;
	
	public CommonQGramFileComparator(Java8File f1, Java8File f2, int q) {
		super(f1, f2);
		this.q = q;
	}

	@Override
	public double computeDifference() throws Exception {
		
	    Set<QGram> qGrams1 = new HashSet<>(new TokenReader(file1, 1, q).getAllQGrams());
	    Set<QGram> qGrams2 = new HashSet<>(new TokenReader(file2, 1, q).getAllQGrams());
	    int nbQGramTotal = Math.min(qGrams1.size(), qGrams2.size());
	    
	    Set<QGram> commonQGram = new HashSet<>(qGrams1);
	    commonQGram.retainAll(qGrams2);
	    System.out.println(commonQGram.size());
	    System.out.println(nbQGramTotal);
	    
	    return commonQGram.size() / (double) nbQGramTotal;
	}

}
