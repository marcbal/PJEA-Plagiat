package fr.univ_lille1.fil.pjea.data;

import java.util.List;

import org.antlr.v4.runtime.Token;

import fr.univ_lille1.fil.pjea.TokenUtils;

/**
 * Représente une liste de {@link QGramToken}. Bien que toutes les
 * méthodes utilise la classe {@link QGram}<code>&lt;Token></code>, les
 * valeurs de retour peuvent être casté en {@link QGramToken}.
 */
public class QGramTokenContainer extends QGramContainer<Token> {
	private static final long serialVersionUID = 1L;
	
	
	
	
	/**
	 * Contruit un {@link Iterable} qui parcours la liste des léxèmes du {@link QGram}
	 * passé en paramètre de stp à stp éléments. Chaque itération retourne un QGram de
	 * buffSize {@link Token}.<br/>
	 * Par exemple :
	 * <pre>new TokenReader(lexer, 1, 1).iterator()</pre> retournera à chaque
	 * iteration les éléments 0, 1, 2, ...<br/>
	 * <pre>new TokenReader(lexer, 1, 5).iterator()</pre> retournera à chaque
	 * iteration les éléments {0, ..., 4}, {1, ..., 5}, {2, ..., 6}, ...<br/>
	 * <pre>new TokenReader(lexer, 5, 5).iterator()</pre> retournera à chaque
	 * iteration les éléments {0, ..., 4}, {5, ..., 9}, {10, ..., 14}, ...<br/>
	 *
	 * @param tokens le {@link QGram} depuis lequel on récupère tous les léxèmes
	 * @param stp le pas de déplacement du buffer dans la liste des léxèmes, après chaque itération
	 * @param buffSize la taille du buffer, c'est à dire le nombre d'élément retourné à
	 * chaque itération.
	 */
	public QGramTokenContainer(List<Token> tokens, int step, int qGramSize) {
		super(tokens, step, qGramSize, TokenUtils::hashCodeToken);
	}
	
	
	
	
	/**
	 * Équivaut à
	 * <pre>new QGramTokenContainer(file, qGramSize, qGramSize)</pre>
	 * @param file
	 * @param qGramSize
	 */
	public QGramTokenContainer(List<Token> tokens, int qGramSize) {
		super(tokens, qGramSize, qGramSize, TokenUtils::hashCodeToken);
	}
	
	/**
	 * Équivaut à
	 * <pre>new QGramTokenContainer(file, 1, 1)</pre>
	 * @param file
	 */
	public QGramTokenContainer(List<Token> tokens) {
		super(tokens, 1, 1, TokenUtils::hashCodeToken);
	}
	
	
	
	
	public QGramTokenContainer(Java8File file, int step, int qGramSize) {
		this(file.tokens, step, qGramSize);
	}
	
	/**
	 * Équivaut à
	 * <pre>new TokenReader(file, qGramSize, qGramSize)</pre>
	 * @param file
	 * @param qGramSize
	 */
	public QGramTokenContainer(Java8File file, int qGramSize) {
		this(file.tokens, qGramSize);
	}
	
	/**
	 * Équivaut à
	 * <pre>new TokenReader(file, 1, 1)</pre>
	 * @param file
	 */
	public QGramTokenContainer(Java8File file) {
		this(file.tokens);
	}
	
	
	
	
	
	
	
	@Override
	protected QGram<Token> newQGram(List<Token> toks, int pos, int computedHashcode) {
		return new QGramToken(toks, pos, computedHashcode);
	}
	
}
