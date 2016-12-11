package fr.univ_lille1.fil.pjea.comparators;

import java.util.Set;

import fr.univ_lille1.fil.pjea.data.ComparisonResult;
import fr.univ_lille1.fil.pjea.data.Java8File;
import fr.univ_lille1.fil.pjea.data.builder.WinnowingFootprintBuilder;

/**
 * Calcul le taux de ressemblance entre deuc fichiers en calculant
 * un taux de ressemblance de leurs empreintes.
 * Les empreintes sont calculés via la méthode du Winnowing
 * @see WinnowingFootprintBuilder
 */
public class WinnowingFileComparator extends FileComparator {
	
	private final int heuristicQ; // = 15;
	private final double heuristicRatioDiffThreshold; // = 0.3;
	
	public WinnowingFileComparator(Java8File f1, Java8File f2, int q, double ratioDiffThreshold) {
		super(f1, f2);
		heuristicQ = q;
		heuristicRatioDiffThreshold = ratioDiffThreshold;
		
	}
	
	
	
	@Override
	public ComparisonResult computeDifference() throws Exception {
		
		// TODO tester cette méthode plus convenablement et définir des heuristique
		// convenable
		
		Set<Integer> footprint1 = file1.getFootprint(heuristicQ).getAsSetOfValues();
		Set<Integer> footprint2 = file2.getFootprint(heuristicQ).getAsSetOfValues();
		int nFp1 = footprint1.size();
		int nFp2 = footprint2.size();
		/*
		if (Math.min(nFp1, nFp2) == 0) {
			return new ComparisonResult(false, 0);
		}*/
		
		footprint1.retainAll(footprint2);
		
		
		
		double score = footprint1.size() / (double) Math.min(nFp1, nFp2);
		System.out.println(score);
		
		return new ComparisonResult(score < heuristicRatioDiffThreshold ? false : null, score);
	}
	
	
}
