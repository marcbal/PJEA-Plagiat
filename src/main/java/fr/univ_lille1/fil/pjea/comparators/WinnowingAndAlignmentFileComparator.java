package fr.univ_lille1.fil.pjea.comparators;

import fr.univ_lille1.fil.pjea.data.Footprint;
import fr.univ_lille1.fil.pjea.data.Java8File;

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

		Footprint footprint1 = file1.getFootprint(HEURISTIC_Q);
		Footprint footprint2 = file2.getFootprint(HEURISTIC_Q);

		int cptId = 0;
		int nMin = Math.min(footprint1.size(), footprint2.size());
		int nMax = Math.max(footprint1.size(), footprint2.size());
		for (int i = 0; i < nMin; ++i) {
			if (footprint1.get(i).equals(footprint2.get(i))) {
				cptId++;
			}
		}

		return (cptId / (double) nMax) <= HEURISTIC_RATIO_DIFF;
	}


}
