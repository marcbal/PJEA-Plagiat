package fr.univ_lille1.fil.pjea.comparators;

import java.util.Iterator;

import org.antlr.v4.runtime.Token;

import fr.univ_lille1.fil.pjea.TokenUtils;
import fr.univ_lille1.fil.pjea.data.Java8File;
import fr.univ_lille1.fil.pjea.data.QGram;
import fr.univ_lille1.fil.pjea.data.QGramTokenContainer;

public class NaiveSuccessivesTokensFileComparator extends FileComparator {
	
	
	public NaiveSuccessivesTokensFileComparator(Java8File f1, Java8File f2) {
		super(f1, f2);
	}
	
	
	@Override
	public double computeDifference() throws Exception {
		QGramTokenContainer cont1 = new QGramTokenContainer(file1);
		QGramTokenContainer cont2 = new QGramTokenContainer(file2);
		
		int nbEquals = 0;
		for (Iterator<QGram<Token>> it1 = cont1.iterator(), it2 = cont2.iterator(); it1.hasNext() && it2.hasNext();) {
			if (TokenUtils.equalsTokens(it1.next().get(0), it2.next().get(0))) {
				nbEquals++;
			}
		}
		
		return nbEquals / (double) Math.max(cont1.size(), cont2.size());
	}
	
}
