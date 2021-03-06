package fr.univ_lille1.fil.pjea.comparators;

import java.util.List;

import org.antlr.v4.runtime.Token;

import fr.univ_lille1.fil.pjea.data.ComparisonResult;
import fr.univ_lille1.fil.pjea.data.Java8File;
import fr.univ_lille1.fil.pjea.data.QGramToken;
import fr.univ_lille1.fil.pjea.data.QGramTokenContainer;

/**
 * Classe de comparaison de deux fichiers qui utilise l'algorithme de Needleman-Wunsh
 *
 */
public class AlignmentFileComparator extends FileComparator {
	
	private final double heuristicPlagiatThreshold;
	
	/**
	 * @param f1 fichier
	 * @param f2 fichier
	 * @param hPlagiatThreshold le taux de similarité pour lequel
	 * si cette valeur est dépassée, on considère qu'il y a plagiat.
	 */
	public AlignmentFileComparator(Java8File f1, Java8File f2, double hPlagiatThreshold) {
		super(f1, f2);
		heuristicPlagiatThreshold = hPlagiatThreshold;
	}
	
	/**
	 * Compare deux fichiers par l'algorithme de Needleman-Wunsh
	 * @return le taux d'alignement du nombre de tokens identiques
	 */
	@Override
	public ComparisonResult computeDifference() throws Exception {
		
		List<Token> tokens1 = file1.getTokens();
		List<Token> tokens2 = file2.getTokens();
		
		
		if (tokens1.size() == 0 || tokens2.size() == 0)
			return new ComparisonResult(null, 0);
		
		QGramToken qGram1 = (QGramToken) new QGramTokenContainer(tokens1, tokens1.size()).get(0);
		QGramToken qGram2 = (QGramToken) new QGramTokenContainer(tokens2, tokens2.size()).get(0);
		
		// peut être négatif si les deux fichiers ont un taux d'alignement des tokens supérieur au nombre total de token.
		double val = qGram1.needlemanWunschAlignment(qGram2, -1) / (double) Math.max(qGram1.size(), qGram2.size());
		
		return new ComparisonResult((val > heuristicPlagiatThreshold) ? (Boolean)true : val <= 0 ? false : null, val < 0 ? 0 : val);
	}
}
