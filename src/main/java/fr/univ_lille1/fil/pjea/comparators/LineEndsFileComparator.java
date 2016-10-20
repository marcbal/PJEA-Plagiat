package fr.univ_lille1.fil.pjea.comparators;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LineEndsFileComparator extends FileComparator {
	
	public LineEndsFileComparator(File f1, File f2) {
		super(f1, f2);
	}

	@Override
	public double computeDifference() throws Exception {
		List<String> spacesF1 = extractEndLines(this.file1);
		List<String> spacesF2 = extractEndLines(this.file2);
		
		
		return 0;
	}
	
	@SuppressWarnings("resource")
	private static List<String> extractEndLines(File f) throws IOException{
		BufferedReader buff = new BufferedReader(( new FileReader(f)));
		List<String> lines = buff.lines().map(line -> {
				return line.substring(getLastIndex(line), line.length());
			} ).collect(Collectors.toList());
		buff.close();
	
		return lines;
	}
	
	private static int getLastIndex(String s){
		for(int i = s.length()-1; i>=0;i--)
			if(!Character.isWhitespace(s.charAt(i)))
				return i+1;
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		File[] files = Arrays.stream(args)
				.map(arg -> new File(arg))
				.toArray((length) -> new File[length]);
		LineEndsFileComparator.extractEndLines(files[0]);
		
	}

}
