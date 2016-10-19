package fr.univ_lille1.fil.pjea;

import org.antlr.v4.runtime.Token;

public class TokenUtils {
	
	
	public static boolean equalsTokens(Token t1, Token t2) {
		if (t1 == null && t2 == null) return true;
		if (t1 == null || t2 == null) return false;
		return t1.getText().equals(t2.getText()) && t1.getType() == t2.getType();
	}
	
	public static int hashCodeToken(Token t) {
		return t.getType() << 4 + t.getText().hashCode();
	}
	
	
	public static int tokenSimilarity(Token t1, Token t2) {
		return (equalsTokens(t1, t2)) ? 1 : 0;
		/* TODO exemple d'amélioration : dans le cas où t1 et t2 sont des
		 * identifieurs Java (nom de variable, de classe, ...), on peut dire que
		 * la similarité est != de 0 même si le .getText() du token est différent.
		 * Ceci de permet de plus ou moins gérer les changements de noms de variables.
		 */
	}
	
	
}
