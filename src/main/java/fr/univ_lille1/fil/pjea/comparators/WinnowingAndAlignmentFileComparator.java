package fr.univ_lille1.fil.pjea.comparators;

import java.util.List;

import org.javatuples.Pair;

import fr.univ_lille1.fil.pjea.Java8File;
import fr.univ_lille1.fil.pjea.builder.WinnowingFootprintBuilder;

public class WinnowingAndAlignmentFileComparator extends FileComparator {
	
	public static final int HEURISTIC_Q = 15;
	public static final double HEURISTIC_RATIO_DIFF = 0.3; /* 30% */
	
	public WinnowingAndAlignmentFileComparator(Java8File f1, Java8File f2) {
		super(f1, f2);
	}


	@Override
	public double computeDifference() throws Exception {
		
		if (isDifferentFootprint())
			return 0;

		return new AlignmentFileComparator(file1, file2).computeDifference();
	}
	
	protected boolean isDifferentFootprint() {
		
		// TODO Revoir le throw
		
		WinnowingFootprintBuilder wfpBuilder1 = new WinnowingFootprintBuilder(file1, HEURISTIC_Q); 
		WinnowingFootprintBuilder wfpBuilder2 = new WinnowingFootprintBuilder(file2, HEURISTIC_Q); 
		
		List<Pair<Integer, Integer>> footprint1 = wfpBuilder1.build();
		List<Pair<Integer, Integer>> footprint2 = wfpBuilder2.build();
		
		int cptId = 0;
		int fp1Size = footprint1.size();
		int fp2Size = footprint2.size();
		int nMin = Math.min(fp1Size, fp2Size);
		int nMax = Math.max(fp1Size, fp2Size);
		for (int i = 0; i < nMin; ++i) {
			if (footprint1.get(i).equals(footprint2.get(i))) {
				cptId ++;
			}
		}
		
		double ratio = cptId / nMax;
		
		return ratio <= HEURISTIC_RATIO_DIFF;
	}
	

}
