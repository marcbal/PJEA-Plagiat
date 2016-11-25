package fr.univ_lille1.fil.pjea.data;

import java.util.List;

import org.antlr.v4.runtime.Token;

import fr.univ_lille1.fil.pjea.TokenUtils;

/**
 * Représente une liste de {@link QGramToken}. Bien que toutes les
 * méthodes utilise la classe {@link QGram}<code>&lt;Token&gt;</code>, les
 * valeurs de retour peuvent être casté en {@link QGramToken}.
 */
public class QGramTokenContainer extends QGramContainer<Token> {
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 
	 * @param tokens les tokens à partir duquel générer les qgrams de token
	 * 
	 * @see QGramContainer#QGramContainer(List, int, int, java.util.function.ToIntFunction)
	 */
	public QGramTokenContainer(List<Token> tokens, int step, int qGramSize) {
		super(tokens, step, qGramSize, TokenUtils::hashCodeToken);
	}
	
	
	
	
	/**
	 * Équivaut à
	 * <pre>new QGramTokenContainer(file, qGramSize, qGramSize)</pre>
	 */
	public QGramTokenContainer(List<Token> tokens, int qGramSize) {
		super(tokens, qGramSize, qGramSize, TokenUtils::hashCodeToken);
	}
	
	/**
	 * Équivaut à
	 * <pre>new QGramTokenContainer(file, 1, 1)</pre>
	 */
	public QGramTokenContainer(List<Token> tokens) {
		super(tokens, 1, 1, TokenUtils::hashCodeToken);
	}
	
	
	
	
	public QGramTokenContainer(Java8File file, int step, int qGramSize) {
		this(file.tokens, step, qGramSize);
	}
	
	public QGramTokenContainer(Java8File file, int qGramSize) {
		this(file.tokens, qGramSize);
	}
	
	public QGramTokenContainer(Java8File file) {
		this(file.tokens);
	}
	
	
	
	
	
	
	
	@Override
	protected QGram<Token> newQGram(List<Token> toks, int pos, int computedHashcode) {
		return new QGramToken(toks, pos, computedHashcode);
	}
	
}
