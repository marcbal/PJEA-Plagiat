package fr.univ_lille1.fil.pjea.data.builder;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.Token;
import org.javatuples.Pair;

import fr.univ_lille1.fil.pjea.data.Footprint;
import fr.univ_lille1.fil.pjea.data.Java8File;
import fr.univ_lille1.fil.pjea.data.QGram;
import fr.univ_lille1.fil.pjea.data.QGramTokenContainer;

/**
 * Représente un builder permettant de générer une empreinte
 * grâce à l'algorithme du Winnowing.
 * <p>
 * Le principe de cet algorithme est de générer une empreinte du fichier.
 * Pour ce faire elle hash les QGrams via le formalisme de Rabin-Karp.
 * Une fois effectué elle regroupe ses valeurs dans des fenetres glissantes de taille q.
 * L'empreinte se contruit en parcourant ses objets et en selectionnant les valeurs suivant des contraintes (minimum, unicité),
 * elle ajoute donc la position et la valeur des elements choisis.
 * Une fois effectuée, elle les trie par position.
 * </p>
 *
 * @see <a href="http://www.fil.univ-lille1.fr/~varre/portail/pje/docs/fingerprint.pdf">http://www.fil.univ-lille1.fr/~varre/portail/pje/docs/fingerprint.pdf</a>
 * @see RabinHashCodeBuilder
 * @see Footprint
 *
 */
public class WinnowingFootprintBuilder {
	/**
	 * Indique la taille de l'objet sur laquelle le Winnowing travail.
	 * Cet attribut à comme pré-conditions le fait qu'il doit être strictement superieur à zéro.
	 */
	private int t;
	
	/**
	 * Indique la taille des fênetre glissantes.
	 * Cet attribut à comme pré-conditions le fait qu'il doit être strictement superieur à zéro.
	 */
	private int q;
	
	/**
	 * C'est une liste des objets hachés sur lequel le Winnowing travail.
	 * Elle symbolise l'entrée général du problème.
	 */
	private List<Integer> hashQgrams;
	
	/**
	 * Cela permet de créer une instance vide, afin de pouvoir l'initialiser avec la méthode {@link #init(List, int, int)}.
	 */
	WinnowingFootprintBuilder() {}
	
	
	/**
	 * Construit une instance du probléme à partir d'une réprésentation
	 * @param file le fichier sur lequel le {@link Footprint} sera construit
	 * @param q taille des QGrams
	 */
	public WinnowingFootprintBuilder(Java8File file, int q) {
		init(new QGramTokenContainer(file, 1, q)
				.stream().map(QGram::hashCode)
				.collect(Collectors.toList()),
				q,
				file.getTokens().size());
		System.out.println("WinnowingFootprintBuilder, file.getTokens() : " + file.getTokens().size());
		System.out.println("WinnowingFootprintBuilder, hashQgrams : " + hashQgrams.size());
	}
	
	public WinnowingFootprintBuilder(List<Token> tokens, int q, int t) {
		init(new QGramTokenContainer(tokens, 1, q)
				.stream().map(QGram::hashCode)
				.collect(Collectors.toList()),
				q,
				t);
	}
	
	/**
	 *
	 * @param hashQgrams
	 * @param q
	 * @param t
	 * @return
	 */
	final WinnowingFootprintBuilder init(List<Integer> hashQgrams, int q, int t) {
		this.hashQgrams = Collections.unmodifiableList(hashQgrams);
		this.q = q;
		this.t = t;
		return this;
	}
	
	public Footprint build() {
		
		int w = t - q + 1;
		
		Pair<Integer, Integer> curMin;
		
		Map<Integer, Integer> listMin = new HashMap<>();
		
		
		for (int i = 0; i < w; i++) {
			curMin = minFrame(i, q, hashQgrams, listMin);
			if (curMin != null) {
				listMin.put(curMin.getValue0(), curMin.getValue1());
			}
		}
		
		return new Footprint(listMin.entrySet().stream()
				.map(e -> new Pair<>(e.getKey(), e.getValue()))
				.sorted((p1, p2) -> Integer.compare(p1.getValue0(), p2.getValue0()))
				.collect(Collectors.toList()));
	}
	
	/**
	 *
	 * @param i
	 * @param nFrame
	 * @param list
	 * @return Retourne une paire composé d'une position et de la valeur du minimum
	 */
	private static Pair<Integer, Integer> minFrame(int i, int nFrame, List<Integer> list,
			Map<Integer, Integer> listMin) {
		int len = list.size();
		if (i + nFrame > len) { // TODO i >= len, à verifier
			return null;
		}
		
		
		int elm, min = list.get(i), pos = i;
		
		for (int j = i + 1; j < i + nFrame; j++) {
			elm = list.get(j);
			
			if (min > elm) {
				min = elm;
				pos = j;
			}
			else if (min == elm && !listMin.containsKey(j)) {
				pos = j;
			}
		}
		
		return !listMin.containsKey(pos) ? new Pair<>(pos, min) : null;
	}
	
}
