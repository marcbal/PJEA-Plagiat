package fr.univ_lille1.fil.pjea.comparators;

import java.util.List;

import org.antlr.v4.runtime.Token;

import fr.univ_lille1.fil.pjea.Java8File;
import fr.univ_lille1.fil.pjea.qgrams.QGram;
import fr.univ_lille1.fil.pjea.qgrams.TokenReader;

public class AlignmentFileComparator extends FileComparator {

	public AlignmentFileComparator(Java8File f1, Java8File f2) {
		super(f1, f2);
	}


	@Override
	public double computeDifference() throws Exception {
		
		List<? extends Token> tokens1 = file1.tokens;
		List<? extends Token> tokens2 = file2.tokens;
		
		QGram qGram1 = new TokenReader(tokens1, tokens1.size(), tokens1.size()).getAllQGrams().get(0);
		QGram qGram2 = new TokenReader(tokens2, tokens2.size(), tokens2.size()).getAllQGrams().get(0);
		
		// peut être négatif si les deux fichiers ont un taux d'alignement des tokens supérieur au nombre total de token.
		double val = qGram1.alignmentNeedlemanWunsch(qGram2, -1) / (double)Math.max(qGram1.size(), qGram2.size());
		
		return val < 0 ? 0 : val;
	}
}
