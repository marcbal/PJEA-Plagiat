package fr.univ_lille1.fil.pjea.comparators;

import fr.univ_lille1.fil.pjea.data.Java8File;



/**
 * FileComporator est une classe qui prend Deux fichiers et compare leurs differences.
 */
public abstract class FileComparator {
	
	protected final Java8File file1, file2;
	
	public FileComparator(Java8File f1, Java8File f2) {
		file1 = f1;
		file2 = f2;
	}
	
	
	/**
	 * @return Un nombre compris entre 0 et 1 qui représente l'égalité des deux fichiers.
	 * @throws Exception
	 */
	public abstract double computeDifference() throws Exception;
	
	
	
	
	
}
