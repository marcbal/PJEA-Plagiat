package fr.univ_lille1.fil.pjea.comparators;

import fr.univ_lille1.fil.pjea.data.Java8File;



/**
 * Classe qui prend deux fichiers et calcule leurs differences.
 */
public abstract class FileComparator {
	
	protected final Java8File file1, file2;
	
	/**
	 * 
	 * @param f1 fichier
	 * @param f2 fichier
	 */
	
	public FileComparator(Java8File f1, Java8File f2) {
		file1 = f1;
		file2 = f2;
	}
	
	
	/**
	 * @return Un nombre compris entre 0 et 1 qui représente l'égalité des deux fichiers.
	 * @throws Exception
	 */
	public abstract double computeDifference() throws Exception;
	
	
	
	/**
	 * 
	 * @param v
	 * @param min
	 * @param max
	 * @return max si v>max, min si v<min, v sinon.
	 */
	
	public static double truncateToRange(double v, double min, double max) {
		return Math.max(min, Math.min(max, v));
	}
	
	
	
}
