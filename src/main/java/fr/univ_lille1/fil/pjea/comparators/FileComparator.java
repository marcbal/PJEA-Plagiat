package fr.univ_lille1.fil.pjea.comparators;

import fr.univ_lille1.fil.pjea.data.ComparisonResult;
import fr.univ_lille1.fil.pjea.data.Java8File;



/**
 * Super-classe de tous les comparateur de fichiers. Elle fourni
 * un moyen de représenter un comparateur de fichier :
 * <ul>
 * 	<li>Constructeur qui prend en paramètre les deux fichiers à comparer</li>
 * 	<li>Méthode exécutant l'algorithme de comparaison et retournant un résultat de comparaison</li>
 * </ul>
 */
public abstract class FileComparator {
	
	protected final Java8File file1, file2;
	
	/**
	 * @param f1 le premier fichier comparé
	 * @param f2 le deuxième fichier qui sera comparé au premier
	 */
	
	public FileComparator(Java8File f1, Java8File f2) {
		file1 = f1;
		file2 = f2;
	}
	
	
	/**
	 * @return Un résultat de comparaison {@link ComparisonResult}.
	 * @throws Exception si, pour une raison quelconque, une exception est levée pendant
	 * l'exécution de cette méthode.
	 */
	public abstract ComparisonResult computeDifference() throws Exception;
	
	
	
}
