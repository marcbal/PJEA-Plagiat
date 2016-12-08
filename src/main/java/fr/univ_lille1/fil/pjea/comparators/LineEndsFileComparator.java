package fr.univ_lille1.fil.pjea.comparators;

import java.util.List;
import java.util.stream.Collectors;

import fr.univ_lille1.fil.pjea.data.ComparisonResult;
import fr.univ_lille1.fil.pjea.data.Java8File;
import fr.univ_lille1.fil.pjea.data.QGram;
import fr.univ_lille1.fil.pjea.data.QGramContainer;

public class LineEndsFileComparator extends FileComparator {
	
	
	public static final int QGRAM_LENGTH = 5;
	
	
	public LineEndsFileComparator(Java8File f1, Java8File f2) {
		super(f1, f2);
	}
	
	@Override
	public ComparisonResult computeDifference() throws Exception {
		List<String> spacesF1 = extractEndLines(this.file1);
		List<String> spacesF2 = extractEndLines(this.file2);
		spacesF1.removeIf(s -> s.isEmpty());
		spacesF2.removeIf(s -> s.isEmpty());
		
		if (spacesF1.size() < QGRAM_LENGTH || spacesF2.size() < QGRAM_LENGTH)
			return new ComparisonResult(null, 0);
		
		int scoreMax = QGRAM_LENGTH;
		int score = 0;
		
		for (QGram<String> qg1 : new QGramContainer<>(spacesF1, 1, QGRAM_LENGTH, 1021)) {
			for (QGram<String> qg2 : new QGramContainer<>(spacesF2, 1, QGRAM_LENGTH, 1021)) {
				int currentScore = qg1.needlemanWunschAlignment(qg2, -1);
				if (currentScore > score)
					score = currentScore;
			}
		}
		
		return new ComparisonResult((score == scoreMax) ? true : null, score / (double) scoreMax);
	}
	
	private static List<String> extractEndLines(Java8File f) {
		return f.getLines().stream()
				.filter(line -> !line.trim().isEmpty()) // on supprime les lignes sans contenu visible
				.map(line -> line.substring(getLastIndex(line), line.length())) // on garde que les espaces après le dernier caractère visible
				.collect(Collectors.toList());
	}
	
	static int getLastIndex(String s) {
		for (int i = s.length() - 1; i >= 0; i--)
			if (!Character.isWhitespace(s.charAt(i)))
				return i + 1;
		return 0;
	}
	
}
