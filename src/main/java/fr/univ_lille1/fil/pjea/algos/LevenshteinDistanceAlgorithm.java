package fr.univ_lille1.fil.pjea.algos;

import java.util.List;
import java.util.function.BiFunction;

public class LevenshteinDistanceAlgorithm {
	/**
	 * Calcule la distance de Levenshtein.
	 * 
	 * @see https://fr.wikipedia.org/wiki/Distance_de_Levenshtein
	 * @param <T> le type des éléments des chaines o1 et o2
	 * @param o1 chaine 1
	 * @param o2 chaine 2
	 * @param equalsFunction
	 * @return la distance
	 */
	public static <T> int compute(List<T> o1, List<T> o2, BiFunction<T, T, Boolean> equalsFunction) {
		// Last version was O(n*m), this one only memorizes previous line of distance matrix
		// which makes this algorithm O(n)
		int[] prev = new int[o2.size() + 1];

		for (int j = 0; j < o2.size() + 1; j++) {
			prev[j] = j;
		}

		for (int i = 1; i < o1.size() + 1; i++) {

			// calculate current line of distance matrix
			int[] curr = new int[o2.size() + 1];
			curr[0] = i;

			for (int j = 1; j < o2.size() + 1; j++) {
				int d1 = prev[j] + 1;
				int d2 = curr[j - 1] + 1;
				int d3 = prev[j - 1] + (equalsFunction.apply(o1.get(i - 1), o2.get(j - 1)) ? 0 : 1);
				curr[j] = Math.min(Math.min(d1, d2), d3);
			}

			// define current line of distance matrix as previous  
			prev = curr;
		}
		return prev[o2.size()];
	}
}
