package fr.univ_lille1.fil.pjea.comparators;

import fr.univ_lille1.fil.pjea.data.ComparisonResult;
import fr.univ_lille1.fil.pjea.data.Java8File;

public class MasterFileComparator extends FileComparator {
	
	public static final int HEURISTIC_Q = 15;
	public static final double HEURISTIC_RATIO_DIFF_THRESHOLD = 0.3;
	
	public MasterFileComparator(Java8File file1, Java8File file2) {
		super(file1, file2);
	}
	
	@Override
	public ComparisonResult computeDifference() throws Exception {
		
		
		
		/*
		 * Filtrage des lignes 'package' et 'import'
		 * qui n'ont pas besoin d'être comparés
		 */
		Java8File file1 = this.file1.getCleanedFile();
		Java8File file2 = this.file2.getCleanedFile();
		
		
		
		/*
		 * Utilisation du winnowing pour vérifier si ça vaut vraiment
		 * la peine de comparer en profondeur les deux fichiers.
		 * 
		 * ---------- ATTENTION ! ----------
		 * TODO calibrer les deux heuristiques passés en paramètre
		 * TODO vérifier l'implémentation de WinnowingFileComparator
		 */
		@SuppressWarnings("unused")
		
		ComparisonResult winnowingComparison = new WinnowingFileComparator(file1, file2, HEURISTIC_Q,
				HEURISTIC_RATIO_DIFF_THRESHOLD).computeDifference();
		
		//if (Boolean.FALSE.equals(winnowingComparison.isPlagiarism))
		//	return winnowingComparison;
		
		
		
		
		/*
		 * Calcul du score d'alignement
		 */
		ComparisonResult alignmentComparison = new AlignmentFileComparator(file1, file2).computeDifference();
		
		
		/*
		 * Calcul du score basé sur les caractères invisibles en fin de ligne
		 * 
		 * ---------- ATTENTION ! ----------
		 * TODO affiner les tests pour LineEndsFileComparator pour vérifier la
		 * cohérence de la sortie du comparateur (cas particulier de fichier d'entrée)
		 */
		//
		ComparisonResult lineEndsComparison = /* TEMP */new ComparisonResult(null, 0);//new LineEndsFileComparator(file1, file2).computeDifference();
		
		
		
		/*
		 * Score d'alignement : poids de 100 % du score total
		 * Score fin de ligne : poids de  50 % du score total
		 * RÉSULTAT FINAL : résultat entre 0% et 150%
		 */
		double finalResult = alignmentComparison.similarityRate + lineEndsComparison.similarityRate/2d;
		return new ComparisonResult(finalResult, alignmentComparison, lineEndsComparison);
	}
	
}
