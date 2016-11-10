package fr.univ_lille1.fil.pjea.algos;

import java.util.List;
import java.util.function.ToIntBiFunction;

public class AlignmentNeedlemanWunschAlgorithm {
	
	/**
	 * Calcule le score d'alignement utilisant l'algorithme de
	 * Needleman-Wunsch.
	 * @see https://fr.wikipedia.org/wiki/Algorithme_de_Needleman-Wunsch
	 * @param <T> le type des éléments des chaines o1 et o2
	 * @param o1 chaine 1
	 * @param o2 chaine 2
	 * @param d pénalité de trou
	 * @param similarityFunction fonction de similarité S
	 * @return le score obtenu
	 */
	public static <T> int compute(List<T> o1, List<T> o2, int d, ToIntBiFunction<T, T> similarityFunction) {
		int[][] tab = new int[2][o2.size() + 1];

        for (int i = 0; i <= o2.size(); i++)
            tab[1][i] = i * d;

        for (int i = 1; i <= o1.size(); i++) {
        	tab[0] = tab[1];
        	tab[1] = new int[o2.size() + 1];
        	tab[1][0] = i * d;
            for (int j = 1; j <= o2.size(); j++) {
            	int S = similarityFunction.applyAsInt(o1.get(i - 1), o2.get(j - 1));
                tab[1][j] = Math.max(tab[0][j - 1] + S,
                					Math.max(tab[0][j] + d,
                							tab[1][j - 1] + d
                							)
                					);
                
            }
        }
        
        return tab[1][o2.size()];
	}

}
