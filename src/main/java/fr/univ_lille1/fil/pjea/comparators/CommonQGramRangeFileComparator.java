package fr.univ_lille1.fil.pjea.comparators;

import fr.univ_lille1.fil.pjea.data.Java8File;
/**
 *
 * Classe qui compare deux fichiers par l'utlisation de l'algortihme de comparaison des QGrams
 *
 */

public class CommonQGramRangeFileComparator extends FileComparator {
	
	private int qMin;
	private int qMax;
	
	/**
	 * 
	 * @param f1 fichier
	 * @param f2 fichier
	 * @param qMin taille minimum de QGrams
	 * @param qMax taille maximal de QGrams
	 */
	
	public CommonQGramRangeFileComparator(Java8File f1, Java8File f2, int qMin, int qMax) {
		super(f1, f2);
		this.qMin = qMin;
		this.qMax = qMax;
	}
	
	/**
	 * Compare deux fichiers par l'algorithme de comparaison de Qgram
	 * @return le taux de Qgram identique par rapport aux deux fichiers
	 * @throws Exception
	 */
	@Override
	public double computeDifference() throws Exception {
		
		double totalResult = 0;
		int divizor = 0;
		for (int q = qMin; q <= qMax; q++) {
			double subResult = new CommonQGramFileComparator(file1, file2, q).computeDifference();
			int qSquare = (q - qMin) * (q - qMin) + 1;
			totalResult += subResult * qSquare;
			divizor += qSquare;
		}
		
		return totalResult / divizor;
	}
	
}
