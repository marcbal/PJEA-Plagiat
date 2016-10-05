package fr.univ_lille1.fil.pjea;

import java.io.File;
import java.util.Arrays;

public class PlagiatVEMP {

	
	
	public static void main(String[] args) {
		
		File[] files = Arrays.stream(args)
				.map(arg -> new File(arg))
				.toArray((length) -> new File[length]);
		
		for (int i=0; i<files.length; i++) {
			for (int j=i+1; j<files.length; j++) {
				try {
				double result = new FileComparator(files[i], files[j]).computeDifference();
				System.out.println(files[i]+" and "+files[j]+": "+(Math.round(result*100))+"%");
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
