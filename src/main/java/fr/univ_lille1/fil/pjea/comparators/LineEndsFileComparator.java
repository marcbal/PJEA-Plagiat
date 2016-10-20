package fr.univ_lille1.fil.pjea.comparators;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import fr.univ_lille1.fil.pjea.Java8File;

public class LineEndsFileComparator extends FileComparator {
	
	public LineEndsFileComparator(Java8File f1, Java8File f2) {
		super(f1, f2);
	}

	@Override
	public double computeDifference() throws Exception {
		List<String> spacesF1 = extractEndLines(this.file1);
		List<String> spacesF2 = extractEndLines(this.file2);
		
		// TODO comparer les deux listes de fin de lignes
		
		return 0;
	}
	
	private static List<String> extractEndLines(Java8File f) {
		List<String> linesEnd = f.fileLines.stream().map(line -> {
				return line.substring(getLastIndex(line), line.length());
			} ).collect(Collectors.toList());
	
		return linesEnd;
	}
	
	private static int getLastIndex(String s){
		for(int i = s.length()-1; i>=0;i--)
			if(!Character.isWhitespace(s.charAt(i)))
				return i+1;
		return 0;
	}
	
	public static void main(String[] args) {
		Java8File[] files = Arrays.stream(args)
				.map(arg -> {
					try {
						return new Java8File(arg);
					}catch (IOException e1) {
						throw new UncheckedIOException(e1);
					}
				})
				.toArray((length) -> new Java8File[length]);
		LineEndsFileComparator.extractEndLines(files[0]);
		
	}

}
