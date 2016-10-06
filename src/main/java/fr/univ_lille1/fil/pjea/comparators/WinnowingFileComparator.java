package fr.univ_lille1.fil.pjea.comparators;

import java.io.File;

public class WinnowingFileComparator extends FileComparator {

	
	private int t;
	
	public WinnowingFileComparator(File f1, File f2, int t) {
		super(f1, f2);
		this.t = t;
	}

	@Override
	public double computeDifference() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	
	
}
