package fr.univ_lille1.fil.pjea.comparators;

import java.util.HashSet;
import java.util.Set;

import org.antlr.v4.runtime.Token;

import fr.univ_lille1.fil.pjea.data.ComparisonResult;
import fr.univ_lille1.fil.pjea.data.Java8File;
import fr.univ_lille1.fil.pjea.data.QGram;
import fr.univ_lille1.fil.pjea.data.QGramTokenContainer;

/**
 * Compare deux fichiers en comptant le nombre de qGram de taille q
 * (paramètre du contructeur) qu'il y a en commun entre les fichiers.
 * Ce nombre est ensuite divisé par le nombre de qGram qu'il y
 * a dans le plus petit fichier.
 */
public class CommonQGramFileComparator extends FileComparator {
	
	private int q;
	/**
	 * @param f1 fichier
	 * @param f2 fichier
	 * @param q taille des QGrams à comparer
	 */
	public CommonQGramFileComparator(Java8File f1, Java8File f2, int q) {
		super(f1, f2);
		this.q = q;
	}
	
	@Override
	public ComparisonResult computeDifference() throws Exception {
		
		Set<QGram<Token>> qGrams1 = new HashSet<>(new QGramTokenContainer(file1, 1, q));
		Set<QGram<Token>> qGrams2 = new HashSet<>(new QGramTokenContainer(file2, 1, q));
		int nbQGramTotal = Math.min(qGrams1.size(), qGrams2.size());
		
		Set<QGram<Token>> commonQGram = new HashSet<>(qGrams1);
		commonQGram.retainAll(qGrams2);
		
		return new ComparisonResult(null, commonQGram.size() / (double) nbQGramTotal);
	}
	
}
