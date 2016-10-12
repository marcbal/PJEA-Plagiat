package fr.univ_lille1.fil.pjea.comparators;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.Lexer;

import fr.univ_lille1.fil.pjea.qgrams.QGram;
import fr.univ_lille1.fil.pjea.qgrams.TokenReader;

public class WinnowingFileComparator extends FileComparator {

	
	private int t;
	private int k;
	
	public WinnowingFileComparator(File f1, File f2, int t, int k) {
		super(f1, f2);
		this.t = t;
		this.k = k;
	}

	@Override
	public double computeDifference() throws Exception {
		Lexer lexer1 = getJava8Lexer(this.file1);
		Lexer lexer2 = getJava8Lexer(this.file2);
		
		/* Construction des listes de tokens hashé */ 
		
		List<Integer> lq1 = new TokenReader(lexer1, 1, this.k).getAllQGrams().stream().map(QGram::hashCode).collect(Collectors.toList());
		List<Integer> lq2 = new TokenReader(lexer2, 1, this.k).getAllQGrams().stream().map(QGram::hashCode).collect(Collectors.toList());
		
		List<List<Integer>> listH1  = new ArrayList<>();
		List<List<Integer>> listH2 = new ArrayList<>();
		
		for(int i=0; i< this.k-this.t+1;i++){
			listH1.add(lq1.subList(i, i+this.k-1));
			listH2.add(lq2.subList(i, i+this.k-1));
		}
		
		
		
		
		
		
		
		return 0;
	}
	

	
	
	
	
}
