package fr.univ_lille1.fil.pjea.data;

import java.util.ArrayList;

/**
 * Java8Files, mais sans les déclarations de package et d'import.
 */
public class CleanedJava8File extends Java8File {
	
	public final Java8File originalFile;
	
	/**
	 * Créer une version de Java8File passé en paramètre, qui ne contient ni
	 * la déclaration de package, ni les déclarations d'import.
	 * @param le fichier de base
	 */
	CleanedJava8File(Java8File original) {
		super(original.file, original.vocabulary);
		
		originalFile = original;
		
		cleanedFile = this;
		
		/* Filtrage des tokens */
		int iToken = 0;
		while (originalFile.tokens.get(iToken).getText().equals("import") || originalFile.tokens.get(iToken).getText().equals("package")) {
			while (!originalFile.tokens.get(++iToken).getText().equals(";"));
			iToken++;
		}
		
		tokens = originalFile.tokens.subList(iToken, originalFile.tokens.size());
		
		
		/* Filtrage des lignes */
		fileLines = new ArrayList<>(originalFile.fileLines.subList(
				tokens.get(0).getLine() - 1,originalFile.fileLines.size()));
		fileLines.set(0, fileLines.get(0).substring(tokens.get(0).getCharPositionInLine()));
	}
	
	
	
	
	
}
