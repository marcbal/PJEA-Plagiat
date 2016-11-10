package fr.univ_lille1.fil.pjea;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Arrays;

import fr.univ_lille1.fil.pjea.comparators.AlignmentFileComparator;
import fr.univ_lille1.fil.pjea.data.Java8File;

public class PlagiatVEMP {
	
	
	public static void main(String[] args) {
		
		/*
		 * Convert an array of string that contains file path
		 * to an array of Java8File instance that represents a file
		 */
		Java8File[] files = Arrays.stream(args)
				.map(arg -> {
					try {
						return new Java8File(arg);
					}catch (IOException e1) {
						throw new UncheckedIOException(e1);
					}
				})
				.toArray((length) -> new Java8File[length]);
		/*
		 * Compare all the files 2 by 2
		 */
		for (int i=0; i<files.length; i++) {
			for (int j=i+1; j<files.length; j++) {
				try {
					
					double result = new AlignmentFileComparator(files[i], files[j]).computeDifference();
					
					outputResult(files[i].file.toString(), files[j].file.toString(), result);
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
	
	
	
	
	public static void outputResult(String f1, String f2, double result) {

		int printedResult = (int)(Math.round(result*100));
		if (printedResult > 100) {
			printedResult = 100;
		}
		else if (printedResult < 0) {
			printedResult = 0;
		}
		System.out.println(f1+" and "+f2+": "+printedResult+"%");
		
	}
	
	
}
