package fr.univ_lille1.fil.pjea.comparators;

import fr.univ_lille1.fil.pjea.data.ComparisonResult;
import fr.univ_lille1.fil.pjea.data.Footprint;
import fr.univ_lille1.fil.pjea.data.Java8File;
import fr.univ_lille1.fil.pjea.data.QGramToken;

public class MasterFileComparator extends FileComparator {
	
	/**
	 * Heuristique correspondant à la taille d'un {@link QGramToken} pour
	 * la comparaison des {@link Footprint} entre les deux fichiers
	 */
	public static final int HEURISTIC_Q = 5;
	/**
	 * Heuristique correspondant au taux de similarité entre les
	 * {@link Footprint} (entre 0 et 1) pour lequel si la valeur calculée
	 * est inférieure à cette constante, alors les deux fichiers sont
	 * considérés assez différents pour conclure qu'il n'y a pas de plagiat.
	 */
	public static final double HEURISTIC_RATIO_DIFF_THRESHOLD = 0.3;
	/**
	 * Heuristique correspondant au taux de similarité entre les
	 * deux fichiers (via l'algorithme d'alignement) (entre 0 et 1)
	 * pour lequel si la valeur calculée est supérieure à cette constante,
	 * alors les deux fichiers sont considérés assez égaux pour conclure
	 * qu'il y a du plagiat.
	 */
	public static final double HEURISTIC_ALIGNMENT_PLAGIAT_THRESHOLD = 0.9;
	
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
		
		if (Boolean.FALSE.equals(winnowingComparison.isPlagiarism))
			return winnowingComparison;
		
		
		
		
		/*
		 * Calcul du score d'alignement
		 */
		ComparisonResult alignmentComparison = new AlignmentFileComparator(file1, file2, HEURISTIC_ALIGNMENT_PLAGIAT_THRESHOLD).computeDifference();
		
		
		/*
		 * Calcul du score basé sur les caractères invisibles en fin de ligne
		 */
		ComparisonResult lineEndsComparison = new LineEndsFileComparator(file1, file2).computeDifference();
		
		
		
		/*
		 * Score d'alignement : poids de 100 % du score total
		 * Score fin de ligne : poids de  50 % du score total
		 * RÉSULTAT FINAL : résultat entre 0% et 150%
		 */
		double finalResult = alignmentComparison.similarityRate + lineEndsComparison.similarityRate/2d;
		return new ComparisonResult(finalResult, alignmentComparison, lineEndsComparison);
	}
	
}
