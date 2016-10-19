package fr.univ_lille1.fil.pjea;

import java.io.File;
import java.util.Arrays;

import fr.univ_lille1.fil.pjea.comparators.WinnowingAndAlignmentFileComparator;

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
					
					double result = new WinnowingAndAlignmentFileComparator(files[i], files[j]).computeDifference();
					
					int printedResult = (int)(Math.round(result*100));
					if (printedResult > 100) {
						System.err.println("Le résutat retourné est "+result+" mais "
								+ "c'est un résultat invalide. Une valeur entre 0 et "
								+ "1 est demandée. La valeur est tronquée à 100% (1.0) "
								+ "pour respecter le format de sortie.");
						printedResult = 100;
					}
					else if (printedResult < 0) {
						System.err.println("Le résutat retourné est "+result+" mais "
								+ "c'est un résultat invalide. Une valeur entre 0 et "
								+ "1 est demandée. La valeur est tronquée à 0% (0.0) "
								+ "pour respecter le format de sortie.");
						printedResult = 0;
					}
					System.out.println(files[i]+" and "+files[j]+": "+printedResult+"%");
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
}
