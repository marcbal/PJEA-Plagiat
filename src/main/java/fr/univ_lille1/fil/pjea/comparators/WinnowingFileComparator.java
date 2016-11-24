package fr.univ_lille1.fil.pjea.comparators;

import fr.univ_lille1.fil.pjea.data.Footprint;
import fr.univ_lille1.fil.pjea.data.Java8File;

public class WinnowingFileComparator extends FileComparator {
	
	private final int heuristicQ; // = 15;
	private final double heuristicRatioDiffThreshold; // = 0.3;
	
	private final double scoreFilterWinnowing;
	
	
	public WinnowingFileComparator(Java8File f1, Java8File f2, int q, double ratioDiffThreshold) {
		super(f1, f2);
		heuristicQ = q;
		heuristicRatioDiffThreshold = ratioDiffThreshold;
		
		scoreFilterWinnowing = initScoreFilterWinnowing();
	}
	
	/**
	 * Pour {@link WinnowingFileComparator}, cette méthode retoune seulement 0
	 * ou 1. 0 pour dire que les deux fichiers sont assez différents pour ne pas
	 * considérer qu'il y a plagiat, 1 si une analyse plus profonde est
	 * nécessaire.
	 */
	
	protected double initScoreFilterWinnowing() {
		
		Footprint footprint1 = file1.getFootprint(heuristicQ);
		Footprint footprint2 = file2.getFootprint(heuristicQ);
		
		int cptId = 0;
		int nMin = Math.min(footprint1.size(), footprint2.size());
		int nMax = Math.max(footprint1.size(), footprint2.size());
		for (int i = 0; i < nMin; ++i) {
			if (footprint1.get(i).equals(footprint2.get(i))) {
				cptId++;
			}
		}
		
		return cptId / (double) nMax;
	}
	
	
	@Override
	public double computeDifference() throws Exception {
		
		// TODO tester cette méthode plus convenablement et définir des heuristique
		// convenable
		return scoreFilterWinnowing;
	}
	
	public boolean isDifferent() {
		return  scoreFilterWinnowing < heuristicRatioDiffThreshold;
	}
	
	
}
