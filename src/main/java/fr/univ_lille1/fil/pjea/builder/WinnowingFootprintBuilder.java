package fr.univ_lille1.fil.pjea.builder;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.Token;
import org.javatuples.Pair;

import fr.univ_lille1.fil.pjea.Java8File;
import fr.univ_lille1.fil.pjea.qgrams.QGram;
import fr.univ_lille1.fil.pjea.qgrams.TokenReader;


public class WinnowingFootprintBuilder {

	/**
	 * 
	 */
	private int t; 
	private int q;
	private List<Integer> hashQgrams;
	
	
	public WinnowingFootprintBuilder() {

	}
	
	public WinnowingFootprintBuilder(Java8File file, int q) {
		init(new TokenReader(file, 1, q).getAllQGrams()
		          .stream().map(QGram::hashCode)
		          .collect(Collectors.toList()), 
			q,
			file.tokens.size()
		);
	}
	
	public WinnowingFootprintBuilder(List<? extends Token> tokens, int q, int t) {
		init(new TokenReader(tokens, 1, q).getAllQGrams()
								           .stream().map(QGram::hashCode)
								           .collect(Collectors.toList()), 
			 q,
			 t
		);
	}
	
	/**
	 * 
	 * @param hashQgrams
	 * @param q
	 * @param t
	 */
	WinnowingFootprintBuilder init(List<Integer> hashQgrams, int q, int t) {
		this.hashQgrams = Collections.unmodifiableList(hashQgrams);
		this.q = q;
		this.t = t;	
		return this;
	}
	
	public List<Pair<Integer, Integer>> build() {
		
		List<Pair<Integer, Integer>> footprint; 
		
		
		int w = t - q + 1;
		
		Pair<Integer, Integer> curMin;
		
		Map<Integer, Integer> listMin = new HashMap<>();
		
	
		for (int i = 0; i < w; i++) {
			curMin = minFrame(i, q, hashQgrams, listMin);
			if (curMin != null) {
				listMin.put(curMin.getValue0(), curMin.getValue1());
			}
		}
		
		footprint = listMin.entrySet().stream().map((e) -> new Pair<>(e.getKey(), e.getValue())).collect(Collectors.toList());
		footprint.sort((p1, p2) -> Integer.compare(p1.getValue0(), p2.getValue0()));
		return footprint;
	}
	
	/**
	 * 	
	 * @param i
	 * @param n
	 * @param list
	 * @return Retourne une paire composé d'une position et de la valeur du minimum
	 */
	private static Pair<Integer, Integer> minFrame(int i, int nFrame, List<Integer> list, Map<Integer, Integer> listMin) {
		int len = list.size();
		if (i >= len || i + nFrame > len) return null;

		
		int elm, min = list.get(i), pos = i;
		
		for (int j = i + 1; j < i + nFrame; j++) {
			elm = list.get(j);
			
				if (min > elm) {
					min = elm; 
					pos = j; 
				} else if (min == elm && !listMin.containsKey(j)) {
					pos = j;
				}	
		}
		
		return !listMin.containsKey(pos) ? new Pair<>(pos, min) : null;
	}
	
}
