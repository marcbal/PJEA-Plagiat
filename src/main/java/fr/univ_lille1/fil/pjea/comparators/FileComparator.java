package fr.univ_lille1.fil.pjea.comparators;

import java.io.File;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.Lexer;

import fr.univ_lille1.fil.pjea.antlr_lexers.java8.Java8Lexer;



/**
 * Take two source files and compute similarity of them.
 */
public abstract class FileComparator {
	
	protected final File file1, file2;
	
	public FileComparator(File f1, File f2) {
		file1 = f1;
		file2 = f2;
	}
	
	
	/**
	 * @return a floating point number between 0 and 1 which represent the equality of the two files.
	 * @throws Exception
	 */
	public abstract double computeDifference() throws Exception;
	
	
	
	
	
	
	
	
	
	protected static Lexer getJava8Lexer(File f) throws IOException {
        ANTLRInputStream in = new ANTLRFileStream(f.getPath());
        return new Java8Lexer(in);
	}

	
}
