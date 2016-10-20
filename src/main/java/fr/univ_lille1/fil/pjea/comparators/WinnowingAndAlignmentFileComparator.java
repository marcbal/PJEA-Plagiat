package fr.univ_lille1.fil.pjea.comparators;

import java.util.List;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;
import org.javatuples.Pair;

import fr.univ_lille1.fil.pjea.Java8File;
import fr.univ_lille1.fil.pjea.builder.WinnowingFootprintBuilder;
import fr.univ_lille1.fil.pjea.qgrams.QGram;
import fr.univ_lille1.fil.pjea.qgrams.TokenReader;

public class WinnowingAndAlignmentFileComparator extends FileComparator {
	
	public static final int HEURISTIC_Q = 15;
	public static final double HEURISTIC_RATIO_DIFF = 0.3; /* 30% */
	
	public WinnowingAndAlignmentFileComparator(Java8File f1, Java8File f2) {
		super(f1, f2);
	}


	@Override
	public double computeDifference() throws Exception {
		
		/*
		 * TODO ICI :
		 * calcul en se basant sur le winnowing. Si ce calcul indique
		 * que la comparaison des gGrams n'est pas nécessaire (car
		 * les deux fichiers sont trop différents), on fait :
		 * 		return 0;
		 */

		List<? extends Token> tokens1 = file1.tokens;
		List<? extends Token> tokens2 = file2.tokens;
		
		QGram qGram1 = new TokenReader(tokens1, tokens1.size(), tokens1.size()).getAllQGrams().get(0);
		QGram qGram2 = new TokenReader(tokens2, tokens2.size(), tokens2.size()).getAllQGrams().get(0);
		
		// peut être négatif si les deux fichiers ont un taux d'alignement des tokens supérieur au nombre total de token.
		double val = qGram1.alignmentNeedlemanWunsch(qGram2, -1) / (double)Math.max(qGram1.size(), qGram2.size());
		
		return val < 0 ? 0 : val;
	}
	
	protected boolean isDifferentFootprint() {
		
		// TODO Revoir le throw
		
		WinnowingFootprintBuilder wfpBuilder1 = new WinnowingFootprintBuilder(file1, HEURISTIC_Q); 
		WinnowingFootprintBuilder wfpBuilder2 = new WinnowingFootprintBuilder(file2, HEURISTIC_Q); 
		
		List<Pair<Integer, Integer>> footprint1 = wfpBuilder1.build();
		List<Pair<Integer, Integer>> footprint2 = wfpBuilder2.build();
		
		int cptId = 0;
		int fp1Size = footprint1.size();
		int fp2Size = footprint2.size();
		int nMin = Math.min(fp1Size, fp2Size);
		int nMax = Math.max(fp1Size, fp2Size);
		for (int i = 0; i < nMin; ++i) {
			if (footprint1.get(i).equals(footprint2.get(i))) {
				cptId ++;
			}
		}
		
		double ratio = cptId / nMax;
		
		return ratio <= HEURISTIC_RATIO_DIFF;
	}
	

}
