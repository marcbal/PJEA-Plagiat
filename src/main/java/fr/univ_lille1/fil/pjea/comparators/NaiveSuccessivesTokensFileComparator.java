package fr.univ_lille1.fil.pjea.comparators;

import java.util.Iterator;

import org.antlr.v4.runtime.Token;

import fr.univ_lille1.fil.pjea.Java8File;
import fr.univ_lille1.fil.pjea.TokenUtils;
import fr.univ_lille1.fil.pjea.qgrams.QGram;
import fr.univ_lille1.fil.pjea.qgrams.TokenReader;

public class NaiveSuccessivesTokensFileComparator extends FileComparator {
	 
	
	public NaiveSuccessivesTokensFileComparator(Java8File f1, Java8File f2) {
		super(f1, f2);
	}
	
	
	@Override
	public double computeDifference() throws Exception {
		
	
	    Iterator<QGram> it1 = new TokenReader(file1).iterator();
	    Iterator<QGram> it2 = new TokenReader(file2).iterator();
	    
	    int total = 0, nbEquals = 0;
	    
	    while (it1.hasNext() && it2.hasNext()) {
	    	total++;
	    	Token t1 = it1.next().get(0);
	    	Token t2 = it2.next().get(0);
	    	if (TokenUtils.equalsTokens(t1, t2)) {
	    		nbEquals++;
	    	}
	    }
	    while (it1.hasNext() || it2.hasNext()) {
	    	if (it1.hasNext()) it1.next();
	    	if (it2.hasNext()) it2.next();
	    	total++;
	    }
		
		return nbEquals/(double)total;
	}

}
