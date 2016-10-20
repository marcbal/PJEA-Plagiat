package fr.univ_lille1.fil.pjea.qgrams;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;

import fr.univ_lille1.fil.pjea.Java8File;
import fr.univ_lille1.fil.pjea.TokenUtils;



/**
 * Classe {@link Iterable} qui permet de parcourir les léxèmes du {@link Lexer}
 * passé en paramètre.<br/>
 * Cette classe a été créé pour répondre aux questions 10 et 11 du TP <i>Lexémisation</i>.
 */
public class TokenReader implements Iterable<QGram> {
	
	private final int step;
	private final int qGramSize;
	private final List<? extends Token> tokens;
	
	
	public TokenReader(List<? extends Token> tokens, int step, int qGramSize) {
		this.step = step;
		this.qGramSize = qGramSize;
		this.tokens = tokens;
	}
	
	
	/**
	 * Contruit un {@link Iterable} qui parcours la liste des léxèmes du {@link Lexer}
	 * passé en paramètre de stp à stp éléments. Chaque itération retourne buffSize
	 * {@link Token}.<br/>
	 * Par exemple :
	 * <pre>new TokenReader(lexer, 1, 1).iterator()</pre> retournera à chaque
	 * iteration les éléments 0, 1, 2, ...<br/>
	 * <pre>new TokenReader(lexer, 1, 5).iterator()</pre> retournera à chaque
	 * iteration les éléments {0, ..., 4}, {1, ..., 5}, {2, ..., 6}, ...<br/>
	 * <pre>new TokenReader(lexer, 5, 5).iterator()</pre> retournera à chaque
	 * iteration les éléments {0, ..., 4}, {5, ..., 9}, {10, ..., 14}, ...<br/>
	 * 
	 * @param lexer le {@link Lexer} depuis lequel on récupère tous les léxèmes
	 * @param stp le pas de déplacement du buffer dans la liste des léxèmes, après chaque itération
	 * @param buffSize la taille du buffer, c'est à dire le nombre d'élément retourné à
	 * chaque itération.
	 */

	public TokenReader(Java8File file, int step, int qGramSize) {
		this(file.tokens, step, qGramSize);
	}

	/**
	 * Équivaut à
	 * <pre>new TokenReader(lexer, qGramSize, qGramSize)</pre>
	 * @param lexer
	 * @param qGramSize
	 */
	public TokenReader(Java8File file, int qGramSize) {
		this(file, qGramSize, qGramSize);
	}
	
	/**
	 * Équivaut à
	 * <pre>new TokenReader(lexer, 1, 1)</pre>
	 * @param lexer
	 */
	public TokenReader(Java8File file) {
		this(file, 1, 1);
	}
	
	

	
	
	public List<QGram> getAllQGrams() {

		List<QGram> qGrams = new ArrayList<>();
		for (QGram qGram : this) {
			qGrams.add(qGram);
		}
		return qGrams;
	}
	
	
	
	
	@Override
	public Iterator<QGram> iterator() {
		return new Iterator<QGram>() {
			
			private int currentPos = 0;
			
			private RabinHashCodeBuilder hashCodeBuilder = null;
			
			@Override
			public QGram next() {
				if (!hasNext())
					throw new NoSuchElementException();
				List<? extends Token> returnedTokens = tokens.subList(currentPos, Math.min(tokens.size(), currentPos+qGramSize));
				
				
				if (hashCodeBuilder == null) {
					hashCodeBuilder = new RabinHashCodeBuilder(256, qGramSize,
							returnedTokens.stream()
							.mapToInt(TokenUtils::hashCodeToken).toArray());
				}
				else {
					hashCodeBuilder.putHashCode(returnedTokens.stream()
							.skip(Math.max(qGramSize - step, 0)) // retain only last Token that are not present in the previous QGram
							.mapToInt(TokenUtils::hashCodeToken).toArray());
				}
				
				int qGramPos = currentPos;
				QGram next = new QGram(returnedTokens, qGramPos, hashCodeBuilder.getCurrentHashCode());
				
				currentPos += step;
				
				return next;
			}
			
			@Override
			public boolean hasNext() {
				return (currentPos <= tokens.size() - qGramSize);
			}
		};
	}
	
	
	
}
