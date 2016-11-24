package fr.univ_lille1.fil.pjea.comparators;

import fr.univ_lille1.fil.pjea.data.Java8File;

public class MasterFileComparator extends FileComparator {
	
	public MasterFileComparator(Java8File file1, Java8File file2) {
		super(file1, file2);
	}
	
	@Override
	public double computeDifference() throws Exception {
		
		
		
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
		boolean winnowingTest = new WinnowingFileComparator(file1, file2, 15, 0.3).computeDifference() < 0.1;
		//if (winnowingTest)
		//	return 0;
		
		
		
		
		/*
		 * Calcul du score d'alignement
		 */
		double alignmentScore = new AlignmentFileComparator(file1, file2).computeDifference();
		
		
		
		
		/*
		 * Calcul du score basé sur les caractères invisibles en fin de ligne
		 * 
		 * ---------- ATTENTION ! ----------
		 * TODO affiner les tests pour LineEndsFileComparator pour vérifier la
		 * cohérence de la sortie du comparateur (cas particulier de fichier d'entrée)
		 */
		//
		double lineEndsScore = 0;//new LineEndsFileComparator(file1, file2).computeDifference();
		
		
		
		/*
		 * Score d'alignement : poids de 100 % du score total
		 * Score fin de ligne : poids de  50 % du score total
		 * RÉSULTAT FINAL : résultat entre 0% et 150%, tronqué à 100% avant retour
		 */
		double finalResult = alignmentScore + lineEndsScore/2d;
		return truncateToRange(finalResult, 0, 1);
	}
	
}
