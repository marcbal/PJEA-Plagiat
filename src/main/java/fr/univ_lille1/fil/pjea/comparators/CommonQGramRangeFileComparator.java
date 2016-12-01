package fr.univ_lille1.fil.pjea.comparators;

import fr.univ_lille1.fil.pjea.data.ComparisonResult;
import fr.univ_lille1.fil.pjea.data.Java8File;
/**
 * Compare deux fichiers en appelant successivement le comparateur
 * {@link CommonQGramFileComparator} avec une taille de qGram allant
 * de qMin à qMax (paramètres de {@link #CommonQGramRangeFileComparator(Java8File, Java8File, int, int)}.
 * les scores obtenu avec des grands qGram ont un poids plus fort que les scores obtenus avec des petit
 * qgrams.
 */

public class CommonQGramRangeFileComparator extends FileComparator {
	
	private int qMin;
	private int qMax;
	
	/**
	 * 
	 * @param f1 fichier
	 * @param f2 fichier
	 * @param qMin taille minimum de QGrams
	 * @param qMax taille maximum de QGrams
	 */
	
	public CommonQGramRangeFileComparator(Java8File f1, Java8File f2, int qMin, int qMax) {
		super(f1, f2);
		this.qMin = qMin;
		this.qMax = qMax;
	}
	
	@Override
	public ComparisonResult computeDifference() throws Exception {
		
		double totalResult = 0;
		int divizor = 0;
		for (int q = qMin; q <= qMax; q++) {
			ComparisonResult subComparison = new CommonQGramFileComparator(file1, file2, q).computeDifference();
			int qSquare = (q - qMin) * (q - qMin) + 1;
			totalResult += subComparison.similarityRate * qSquare;
			divizor += qSquare;
		}
		
		return new ComparisonResult(null, totalResult / divizor);
	}
	
}
