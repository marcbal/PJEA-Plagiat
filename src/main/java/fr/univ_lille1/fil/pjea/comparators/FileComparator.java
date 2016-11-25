package fr.univ_lille1.fil.pjea.comparators;

import fr.univ_lille1.fil.pjea.data.Java8File;



/**
 * Classe qui prend deux fichiers et calcule leurs differences.
 */
public abstract class FileComparator {
	
	protected final Java8File file1, file2;
	
	/**
	 * @param f1 fichier
	 * @param f2 fichier
	 */
	
	public FileComparator(Java8File f1, Java8File f2) {
		file1 = f1;
		file2 = f2;
	}
	
	
	/**
	 * @return Un nombre compris entre 0 et 1 qui représente l'égalité des deux fichiers.
	 * @throws Exception si une des implémentations lance une exception
	 */
	public abstract double computeDifference() throws Exception;
	
	
	
	/**
	 * Tronque la valeur v entre min et max.
	 * @param v la valeur à tronquer
	 * @param min la valeur minimum à retourner
	 * @param max la valeur maximum à retourner
	 * @return max si v &gt; max, min si v &lt; min, v sinon.
	 * Si max &lt; min, alors min sera retournée.
	 */
	
	public static double truncateToRange(double v, double min, double max) {
		return Math.max(min, Math.min(max, v));
	}
	
	
	
}
