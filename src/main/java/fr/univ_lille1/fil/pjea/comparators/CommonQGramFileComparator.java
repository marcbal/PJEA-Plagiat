package fr.univ_lille1.fil.pjea.comparators;

import java.util.HashSet;
import java.util.Set;

import fr.univ_lille1.fil.pjea.data.Java8File;
import fr.univ_lille1.fil.pjea.data.QGram;
import fr.univ_lille1.fil.pjea.data.QGramContainer;

public class CommonQGramFileComparator extends FileComparator {

	private int q;

	public CommonQGramFileComparator(Java8File f1, Java8File f2, int q) {
		super(f1, f2);
		this.q = q;
	}

	@Override
	public double computeDifference() throws Exception {

		Set<QGram> qGrams1 = new HashSet<>(new QGramContainer(file1, 1, q));
		Set<QGram> qGrams2 = new HashSet<>(new QGramContainer(file2, 1, q));
		int nbQGramTotal = Math.min(qGrams1.size(), qGrams2.size());

		Set<QGram> commonQGram = new HashSet<>(qGrams1);
		commonQGram.retainAll(qGrams2);

		return commonQGram.size() / (double) nbQGramTotal;
	}

}
