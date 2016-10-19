package fr.univ_lille1.fil.pjea.comparators;

import java.io.File;
import java.util.List;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;

import fr.univ_lille1.fil.pjea.builder.WinnowingFootprintBuilder;
import fr.univ_lille1.fil.pjea.qgrams.QGram;
import fr.univ_lille1.fil.pjea.qgrams.TokenReader;

public class WinnowingAndAlignmentFileComparator extends FileComparator {
	
	
	
	public WinnowingAndAlignmentFileComparator(File f1, File f2) {
		super(f1, f2);
	}

	@Override
	public double computeDifference() throws Exception {
		Lexer lexer1 = getJava8Lexer(file1);
		Lexer lexer2 = getJava8Lexer(file2);
		
		/*
		 * TODO ICI :
		 * calcul en se basant sur le winnowing. Si ce calcul indique
		 * que la comparaison des gGrams n'est pas nécessaire (car
		 * les deux fichiers sont trop différents), on fait :
		 * 		return 0;
		 */

		List<? extends Token> tokens1 = lexer1.getAllTokens();
		List<? extends Token> tokens2 = lexer2.getAllTokens();
		
		QGram qGram1 = new TokenReader(tokens1, tokens1.size(), tokens1.size()).getAllQGrams().get(0);
		QGram qGram2 = new TokenReader(tokens2, tokens2.size(), tokens2.size()).getAllQGrams().get(0);
		
		// peut être négatif si les deux fichiers ont un taux d'alignement des tokens supérieur au nombre total de token.
		double val = qGram1.alignmentNeedlemanWunsch(qGram2, -1) / (double)Math.max(qGram1.size(), qGram2.size());
		
		return val < 0 ? 0 : val;
	}
	
	protected static boolean checkFootPrint(Lexer lex1, Lexer lex2) {
		// TODO 
		//lex1.
		
		WinnowingFootprintBuilder wfpBuilder1 = new WinnowingFootprintBuilder(lex1, 0, 0); // Init false 
		WinnowingFootprintBuilder wfpBuilder2 = new WinnowingFootprintBuilder(lex2, 0, 0); // Init false
		
		
		
		return false;
	}
	

}
