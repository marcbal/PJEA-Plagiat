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
		Java8File file1 = this.file1.filterPackageAndImport();
		Java8File file2 = this.file2.filterPackageAndImport();
		
		/*
		 * Utilisation du winnowing pour vérifier si ça vaut vraiment
		 * la peine de comparer en profondeur les deux fichiers.
		 * 
		 * TODO calibrer les deux heuristiques passés en paramètre
		 * TODO vérifier l'implémentation de WinnowingFileComparator
		 */
		//if (new WinnowingFileComparator(file1, file2, 15, 0.3).computeDifference() < 0.1)
		//	return 0;
		
		double alignmentScore = new AlignmentFileComparator(file1, file2).computeDifference();
		
		// TODO affiner les tests pour LineEndsFileComparator
		double lineEndsScore = 0;//new LineEndsFileComparator(file1, file2).computeDifference();
		
		
		
		
		double finalResult = alignmentScore + lineEndsScore/2d;
		
		return truncateToRange(finalResult, 0, 1);
	}
	
}
