package fr.univ_lille1.fil.pjea.comparators;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import fr.univ_lille1.fil.pjea.data.Java8File;
import fr.univ_lille1.fil.pjea.data.QGramContainer;

public class LineEndsFileComparator extends FileComparator {
	
	
	public static final int QGRAM_LENGTH = 3;
	
	
	public LineEndsFileComparator(Java8File f1, Java8File f2) {
		super(f1, f2);
	}
	
	@Override
	public double computeDifference() throws Exception {
		List<String> spacesF1 = extractEndLines(this.file1);
		List<String> spacesF2 = extractEndLines(this.file2);
		int cpt = 0;
		int nF1 = spacesF1.size(), nF2 = spacesF2.size();
		spacesF1.removeIf(s -> s.isEmpty());
		spacesF2.removeIf(s -> s.isEmpty());
		
		if (spacesF1.size() < QGRAM_LENGTH || spacesF2.size() < QGRAM_LENGTH)
			return 0;
		/* Si on a le même nombre de lignes avec espaces invisible dans les deux fichiers,
		 * On testera sur les deux listes sur leur taille respective et non sur le nombre de lignes
		 * non vides des fichiers.
		 * Règle le bug où deux fichiers identiques n'auront jamais le ratio 1
		 */
		if (spacesF1.size() == spacesF2.size() && nF1 == nF2)
			nF1 = nF2 = spacesF1.size();
		
		for (Iterator<String> it = spacesF1.iterator(); it.hasNext() || it.hasNext();)
			if (spacesF2.contains(it.next()))
				cpt++;
		
		for (Iterator<String> it = spacesF2.iterator(); it.hasNext();)
			if (spacesF1.contains(it.next()))
				cpt++;
		
		return (cpt / 2.0) / (((double) nF1 + (double) nF2)/2);
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
	
	public static void main(String[] args) {
		List<String> pd1 = Arrays.asList(
				" ",
				"  ",
				"   ");
		List<String> pd2 = Arrays.asList(
				" ",
				"  ",
				"    ",
				"   ");
		
		QGramContainer<String> qgc1 = new QGramContainer<>(pd1, 1, QGRAM_LENGTH);
		QGramContainer<String> qgc2 = new QGramContainer<>(pd2, 1, QGRAM_LENGTH);
		
		int scoreMax = QGRAM_LENGTH;
		int score = 0;
		
		for (int i1 = 0; i1 < qgc1.size(); i1++) {
			for (int i2 = 0; i2 < qgc2.size(); i2++) {
				int currentScore = qgc1.get(i1).needlemanWunschAlignment(qgc2.get(i2), -1);
				if (currentScore > score)
					score = currentScore;
			}
		}
		
		// c'est une suggestion :
		System.out.println(score);
		System.out.println(scoreMax);
		System.out.println(score / (double) scoreMax);
	}
	
	
}
