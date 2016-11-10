package fr.univ_lille1.fil.pjea.comparators;

import fr.univ_lille1.fil.pjea.Java8File;

public class CommonQGramRangeFileComparator extends FileComparator {
	
	private int qMin;
	private int qMax;

	public CommonQGramRangeFileComparator(Java8File f1, Java8File f2, int qMin, int qMax) {
		super(f1, f2);
		this.qMin = qMin;
		this.qMax = qMax;
	}

	@Override
	public double computeDifference() throws Exception {
		
		double totalResult = 0;
		int divizor = 0;
		for (int q=qMin; q <= qMax; q++) {
			double subResult = new CommonQGramFileComparator(file1, file2, q).computeDifference();
			int qSquare = (q-qMin)*(q-qMin)+1;
			totalResult += subResult * qSquare;
			divizor += qSquare;
		}
		
		return totalResult / divizor;
	}

}
