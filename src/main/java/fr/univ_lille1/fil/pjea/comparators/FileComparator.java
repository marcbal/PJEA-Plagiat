package fr.univ_lille1.fil.pjea.comparators;

import fr.univ_lille1.fil.pjea.Java8File;



/**
 * Take two source files and compute similarity of them.
 */
public abstract class FileComparator {
	
	protected final Java8File file1, file2;
	
	public FileComparator(Java8File f1, Java8File f2) {
		file1 = f1;
		file2 = f2;
	}
	
	
	/**
	 * @return a floating point number between 0 and 1 which represent the equality of the two files.
	 * @throws Exception
	 */
	public abstract double computeDifference() throws Exception;
	
	
	
	
	
	

	
}
