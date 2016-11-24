package fr.univ_lille1.fil.pjea.comparators;

import java.util.HashSet;
import java.util.Set;

import fr.univ_lille1.fil.pjea.data.Java8File;
import fr.univ_lille1.fil.pjea.data.QGramContainer;
import fr.univ_lille1.fil.pjea.data.QGramToken;

/**
 *
 * Classe de comparaison de deux fichiers qui utilise l'algorithme de comparaison de Qgram suivant taille donnée
 *
 */
public class CommonQGramFileComparator extends FileComparator {
	
	private int q;
	/**
	 * 
	 * @param f1 fichier
	 * @param f2 fichier
	 * @param q taille des QGrams à comparer
	 */
	public CommonQGramFileComparator(Java8File f1, Java8File f2, int q) {
		super(f1, f2);
		this.q = q;
	}
	
	/**
	 * Compare deux fichiers par l'algorithme de comparaison de Qgram
	 * @return le taux de Qgram identique par rapport aux deux fichiers
	 * @throws Exception
	 */
	@Override
	public double computeDifference() throws Exception {
		
		Set<QGramToken> qGrams1 = new HashSet<>(new QGramContainer(file1, 1, q));
		Set<QGramToken> qGrams2 = new HashSet<>(new QGramContainer(file2, 1, q));
		int nbQGramTotal = Math.min(qGrams1.size(), qGrams2.size());
		
		Set<QGramToken> commonQGram = new HashSet<>(qGrams1);
		commonQGram.retainAll(qGrams2);
		
		return commonQGram.size() / (double) nbQGramTotal;
	}
	
}
