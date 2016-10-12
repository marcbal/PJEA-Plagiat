package fr.univ_lille1.fil.pjea.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.Lexer;
import org.javatuples.Pair;

import fr.univ_lille1.fil.pjea.qgrams.QGram;
import fr.univ_lille1.fil.pjea.qgrams.TokenReader;


public class WinnowingMarkBuilder {

	/**
	 * 
	 */
	private int t; 
	private int q;
	
	private Lexer lexer;
	
	public WinnowingMarkBuilder(Lexer lexer, int q, int t) {
		this.lexer = lexer;
		this.q = q;
		this.t = t;
	}

	
	public List<Pair<Integer, Integer>> build() throws Exception {
		
		List<Pair<Integer, Integer>> mark = new ArrayList<>();
		
		/* Construction des listes de tokens hashé */ 
		
		List<Integer> hashQgrams = new TokenReader(lexer, 0, t).getAllQGrams().stream().map(QGram::hashCode).collect(Collectors.toList());
		int w = t - q + 1;
		
		Pair<Integer, Boolean> curMin;
		
		/* 90 17 17 (98) / 17 17 98 25 {25 est pris, ou 17 est pris, ou aucun de cette frame ?} */
		/* Boucle principale de l'algorithme */ 
		for (int i = 0; i < w; i++) {
			curMin = min(i, q, hashQgrams);
			if (curMin.getValue1()) {
				
			}
		}
		
	    
		return mark;
	}
	
	private static Pair<Integer, Boolean> min(int i, int n, List<Integer> list) {
		int len = list.size();
		if (i >= len || i + n > len) return null;
		
		int min = list.get(i);
		int pos = i;
		int elm;
		boolean alone = true;
		for (int j = i + 1; j < i + n; j++) {
			elm = list.get(j);
			if (min > elm) {
				min = elm;
				pos = j;
				alone = true;
			} else if (min == elm) {
				alone = false;
				pos = j;
			}
		}
		return new Pair<>(pos, alone);
	}
	
}
