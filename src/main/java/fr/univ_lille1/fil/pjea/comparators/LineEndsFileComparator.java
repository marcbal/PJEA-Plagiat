package fr.univ_lille1.fil.pjea.comparators;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import fr.univ_lille1.fil.pjea.data.Java8File;

public class LineEndsFileComparator extends FileComparator {

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
		
		if (spacesF1.isEmpty() || spacesF2.isEmpty())
			return 0;
		/* Si on a le même nombre de lignes avec espaces invisible des dans les deux fichiers,
		 * On testera sur les deux listes sur leur taille respective et non sur le nombre de lignes
		 * non vides des fichiers.
		 * Règle le bug où deux fichiers identiques n'auront jamais le ratio 1
		 */
		if (spacesF1.size() == spacesF2.size())
			nF1 = nF2 = spacesF1.size();

		for (Iterator<String> it = spacesF1.iterator(); it.hasNext() || it.hasNext();)
			if (spacesF2.contains(it.next()))
				cpt++;

		for (Iterator<String> it = spacesF2.iterator(); it.hasNext();)
			if (spacesF1.contains(it.next()))
				cpt++;

		return (cpt / 2.0) / Math.min((double) nF1, (double) nF2);
	}

	private static List<String> extractEndLines(Java8File f) {
		return f.fileLines.stream()
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
