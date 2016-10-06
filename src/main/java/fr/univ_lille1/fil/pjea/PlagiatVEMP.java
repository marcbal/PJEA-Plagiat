package fr.univ_lille1.fil.pjea;

import java.io.File;
import java.util.Arrays;

import fr.univ_lille1.fil.pjea.comparators.CommonQGramRangeFileComparator;

public class PlagiatVEMP {
	
	
	public static void main(String[] args) {
		
		/*
		 * Convert an array of string that contains file path
		 * to an array of File instance that represents the sames file path.
		 */
		File[] files = Arrays.stream(args)
				.map(arg -> new File(arg))
				.toArray((length) -> new File[length]);
		
		/*
		 * Compare all the files 2 by 2
		 */
		for (int i=0; i<files.length; i++) {
			for (int j=i+1; j<files.length; j++) {
				try {
					
					double result = new CommonQGramRangeFileComparator(files[i], files[j], 5, 15).computeDifference();
					
					System.out.println(files[i]+" and "+files[j]+": "+(Math.round(result*100))+"%");
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
}
