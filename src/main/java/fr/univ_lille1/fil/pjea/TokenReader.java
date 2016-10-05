package fr.univ_lille1.fil.pjea;

import java.util.Iterator;
import java.util.List;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;



/**
 * Classe {@link Iterable} qui permet de parcourir les léxèmes du {@link Lexer}
 * passé en paramètre.<br/>
 * Cette classe a été créé pour répondre aux questions 10 et 11 du TP <i>Lexémisation</i>.
 */
public class TokenReader implements Iterable<List<? extends Token>> {
	
	private final int step;
	private final int bufferSize;
	private final List<? extends Token> tokens;
	
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
	public TokenReader(Lexer lexer, int stp, int buffSize) {
		step = stp;
		bufferSize = buffSize;
		tokens = lexer.getAllTokens();
	}

	/**
	 * Équivaut à
	 * <pre>new TokenReader(lexer, buffSize, buffSize)</pre>
	 * @param lexer
	 * @param buffSize
	 */
	public TokenReader(Lexer lexer, int buffSize) {
		this(lexer, buffSize, buffSize);
	}
	
	/**
	 * Équivaut à
	 * <pre>new TokenReader(lexer, 1, 1)</pre>
	 * @param lexer
	 */
	public TokenReader(Lexer lexer) {
		this(lexer, 1, 1);
	}
	
	
	
	
	
	@Override
	public Iterator<List<? extends Token>> iterator() {
		return new Iterator<List<? extends Token>>() {
			
			int currentPos = 0;
			
			
			/**
			 * Returns the next element in the iteration.<br/>
			 * The last iteration's element may contains less elements than expected,
			 * but at least one.
			 */
			@Override
			public List<? extends Token> next() {
				List<? extends Token> returnedTokens = tokens.subList(currentPos, Math.min(tokens.size(), currentPos+bufferSize));
				currentPos += step;
				return returnedTokens;
			}
			
			@Override
			public boolean hasNext() {
				return (currentPos < tokens.size());
			}
		};
	}
	
	
}
