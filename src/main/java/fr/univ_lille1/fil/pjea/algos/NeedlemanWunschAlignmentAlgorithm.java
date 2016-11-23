package fr.univ_lille1.fil.pjea.algos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.ToIntBiFunction;

/**
 * Classe qui calcule le score d'alignement entre 2 chaines d'élément fourni.
 *
 *  @see <a href="https://fr.wikipedia.org/wiki/Algorithme_de_Needleman-Wunsch">https://fr.wikipedia.org/wiki/Algorithme_de_Needleman-Wunsch</a>
 */
public class NeedlemanWunschAlignmentAlgorithm {
	
	/**
	 * Calcule le score d'alignement utilisant l'algorithme de
	 * Needleman-Wunsch.
	 *
	 * @see <a href="https://fr.wikipedia.org/wiki/Algorithme_de_Needleman-Wunsch">https://fr.wikipedia.org/wiki/Algorithme_de_Needleman-Wunsch</a>
	 *
	 * @param <T> le type des éléments des chaines o1 et o2
	 * @param o1 chaine 1
	 * @param o2 chaine 2
	 * @param d pénalité de trou
	 * @param similarityFunction fonction qui calcule la similarité entre deux éléments des chaines o1 et o2.
	 * Plus la valeur retournée par <code>similarityFunction</code> est grande, plus les éléments sont similaires.
	 * @return le score d'alignement obtenu
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
								tab[1][j - 1] + d));
				
			}
		}
		
		return tab[1][o2.size()];
	}


	/**
	 * Calcule le score d'alignement utilisant l'algorithme de
	 * Needleman-Wunsch.
	 *
	 * @see <a href="https://fr.wikipedia.org/wiki/Algorithme_de_Needleman-Wunsch">https://fr.wikipedia.org/wiki/Algorithme_de_Needleman-Wunsch</a>
	 *
	 * @param <T> le type des éléments des chaines o1 et o2
	 * @param o1 chaine 1
	 * @param o2 chaine 2
	 * @param d pénalité de trou
	 * @param similarityFunction fonction qui calcule la similarité entre deux éléments des chaines o1 et o2.
	 * Plus la valeur retournée par <code>similarityFunction</code> est grande, plus les éléments sont similaires.
	 * @return le score d'alignement obtenu
	 */
	public static <T> int compute(T[] o1, T[] o2, int d, ToIntBiFunction<T, T> similarityFunction) {
		return compute(Arrays.asList(o1), Arrays.asList(o2), d, similarityFunction);
	}



	/**
	 * Calcule le score d'alignement utilisant l'algorithme de
	 * Needleman-Wunsch.
	 *
	 * @see <a href="https://fr.wikipedia.org/wiki/Algorithme_de_Needleman-Wunsch">https://fr.wikipedia.org/wiki/Algorithme_de_Needleman-Wunsch</a>
	 *
	 * @param o1 chaine 1
	 * @param o2 chaine 2
	 * @param d pénalité de trou
	 * @param similarityFunction fonction qui calcule la similarité entre deux éléments des chaines o1 et o2.
	 * Plus la valeur retournée par <code>similarityFunction</code> est grande, plus les éléments sont similaires.
	 * @return le score d'alignement obtenu
	 */
	public static int compute(char[] o1, char[] o2, int d, ToIntBiFunction<Character, Character> similarityFunction) {
		// il est impossible de faire Arrays.asList() sur o1 et o2 car elle ne supporte pas les listes de char
		List<Character> o1List = new ArrayList<>(o1.length);
		for (char c : o1)
			o1List.add(c);
		List<Character> o2List = new ArrayList<>(o2.length);
		for (char c : o1)
			o1List.add(c);
		return compute(o1List, o2List, d, similarityFunction);
	}


	/**
	 * Calcule le score d'alignement utilisant l'algorithme de
	 * Needleman-Wunsch.
	 *
	 * @see <a href="https://fr.wikipedia.org/wiki/Algorithme_de_Needleman-Wunsch">https://fr.wikipedia.org/wiki/Algorithme_de_Needleman-Wunsch</a>
	 *
	 * @param o1 chaine 1
	 * @param o2 chaine 2
	 * @param d pénalité de trou
	 * @param similarityFunction fonction qui calcule la similarité entre deux éléments des chaines o1 et o2.
	 * Plus la valeur retournée par <code>similarityFunction</code> est grande, plus les éléments sont similaires.
	 * @return le score d'alignement obtenu
	 */
	public static int compute(String o1, String o2, int d, ToIntBiFunction<Character, Character> similarityFunction) {
		return compute(o1.toCharArray(), o2.toCharArray(), d, similarityFunction);
	}
	
}
