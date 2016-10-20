package fr.univ_lille1.fil.pjea;

import java.io.IOException;
import java.util.Arrays;

import fr.univ_lille1.fil.pjea.comparators.WinnowingAndAlignmentFileComparator;

public class PlagiatVEMP {
	
	
	public static void main(String[] args) {
		
		/*
		 * Convert an array of string that contains file path
		 * to an array of File instance that represents the sames file path.
		 */
		Java8File[] files = Arrays.stream(args)
				.map(arg -> {
					try {
						return new Java8File(arg);
					}catch (IOException e1) {
						throw new RuntimeException(e1);
					}
				})
				.toArray((length) -> new Java8File[length]);
		
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
					System.out.println(files[i].file+" and "+files[j].file+": "+printedResult+"%");
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
}
