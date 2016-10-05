package fr.univ_lille1.fil.pjea.comparators;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;

import fr.univ_lille1.fil.pjea.TokenReader;

public class StrictEqualsFilesComparator extends FileComparator {
	
	public StrictEqualsFilesComparator(File f1, File f2) {
		super(f1, f2);
	}
	
	
	@Override
	public double computeDifference() throws Exception {
		Lexer lexer1 = getJava8Lexer(file1);
		Lexer lexer2 = getJava8Lexer(file2);
		
	
	    Iterator<List<? extends Token>> it1 = new TokenReader(lexer1).iterator();
	    Iterator<List<? extends Token>> it2 = new TokenReader(lexer2).iterator();
	    
	    while (it1.hasNext() && it2.hasNext()) {
	    	Token t1 = it1.next().get(0);
	    	Token t2 = it2.next().get(0);
	    	if (!t1.getText().equals(t2.getText()) || !(t1.getType() == t2.getType()))
	    		return 0;
	    	
	    }
		
		return (it1.hasNext() || it2.hasNext()) ? 0 : 1;
	}

}
