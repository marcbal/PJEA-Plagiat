package fr.univ_lille1.fil.pjea.algos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

/**
 * Classe qui calcule la distance de Levenshtein.
 *
 * @see <a href="https://fr.wikipedia.org/wiki/Distance_de_Levenshtein">https://fr.wikipedia.org/wiki/Distance_de_Levenshtein</a>
 */
public class LevenshteinDistanceAlgorithm {
	/**
	 * Calcule la distance de Levenshtein.
	 *
	 * @see <a href="https://fr.wikipedia.org/wiki/Distance_de_Levenshtein">https://fr.wikipedia.org/wiki/Distance_de_Levenshtein</a>
	 *
	 * @param <T> le type des éléments des chaines o1 et o2
	 * @param o1 chaine 1
	 * @param o2 chaine 2
	 * @param equalsFunction une fonction qui détermine si un élément de o1 donné est égale à un élément de o2 fourni
	 * @return la distance de Levenshtein
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

	/**
	 * Calcule la distance de Levenshtein.
	 *
	 * @see <a href="https://fr.wikipedia.org/wiki/Distance_de_Levenshtein">https://fr.wikipedia.org/wiki/Distance_de_Levenshtein</a>
	 *
	 * @param <T> le type des éléments des chaines o1 et o2
	 * @param o1 chaine 1
	 * @param o2 chaine 2
	 * @param equalsFunction une fonction qui détermine si un élément de o1 donné est égale à un élément de o2 fourni
	 * @return la distance
	 */
	public static <T> int compute(T[] o1, T[] o2, BiFunction<T, T, Boolean> equalsFunction) {
		return compute(Arrays.asList(o1), Arrays.asList(o2), equalsFunction);
	}

	/**
	 * Calcule la distance de Levenshtein.
	 *
	 * @see <a href="https://fr.wikipedia.org/wiki/Distance_de_Levenshtein">https://fr.wikipedia.org/wiki/Distance_de_Levenshtein</a>
	 *
	 * @param o1 chaine 1
	 * @param o2 chaine 2
	 * @param equalsFunction une fonction qui détermine si un caractère de o1 donné est égale à un caractère de o2 fourni
	 * @return la distance de Levenshtein
	 */
	public static int compute(char[] o1, char[] o2, BiFunction<Character, Character, Boolean> equalsFunction) {
		// il est impossible de faire Arrays.asList() sur o1 et o2 car elle ne supporte pas les listes de char
		List<Character> o1List = new ArrayList<>(o1.length);
		for (char c : o1)
			o1List.add(c);
		List<Character> o2List = new ArrayList<>(o2.length);
		for (char c : o1)
			o1List.add(c);
		return compute(o1List, o2List, equalsFunction);
	}
	/**
	 * Calcule la distance de Levenshtein entre deux chaines de caractères
	 *
	 * @see <a href="https://fr.wikipedia.org/wiki/Distance_de_Levenshtein">https://fr.wikipedia.org/wiki/Distance_de_Levenshtein</a>
	 *
	 * @param o1 chaine 1
	 * @param o2 chaine 2
	 * @param equalsFunction une fonction qui détermine si un caractère de o1 donné est égale à un caractère de o2 donné
	 * @return la distance de Levenshtein
	 */
	public static int compute(String o1, String o2, BiFunction<Character, Character, Boolean> equalsFunction) {
		return compute(o1.toCharArray(), o2.toCharArray(), equalsFunction);
	}
	
	
	
	
	
}
