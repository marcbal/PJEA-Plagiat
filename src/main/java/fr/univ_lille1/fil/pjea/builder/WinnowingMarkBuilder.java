package fr.univ_lille1.fil.pjea.analyzer.java8.algo;

import java.io.File;
import java.util.Iterator;

import org.antlr.v4.runtime.Lexer;

import fr.univ_lille1.fil.pjea.TokenReader;
import fr.univ_lille1.fil.pjea.TokenReader.QGram;
import fr.univ_lille1.fil.pjea.comparators.FileComparator;

public class WinnowingFileComparator extends FileComparator {

	/**
	 * 
	 */
	private int t;
	private int k; 
	private int q;
	
	public WinnowingFileComparator(File f1, File f2, int q, int t, int k) {
		super(f1, f2);
		this.q = q;
		this.t = t;
		this.k = k;
	}

	
	@Override
	public double computeDifference() throws Exception {
			
		Lexer lexer1 = getJava8Lexer(file1);
		Lexer lexer2 = getJava8Lexer(file2);
		
	
	   // Iterator<QGram> it1 = new TokenReader(lexer1).iterator();
	    //Iterator<QGram> it2 = new TokenReader(lexer2).iterator();
	    
		
	    //for ()
	    
	    
		return 0;
	}
	
}
