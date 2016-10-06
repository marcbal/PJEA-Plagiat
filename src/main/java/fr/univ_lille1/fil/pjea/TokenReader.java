package fr.univ_lille1.fil.pjea;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;



/**
 * Classe {@link Iterable} qui permet de parcourir les léxèmes du {@link Lexer}
 * passé en paramètre.<br/>
 * Cette classe a été créé pour répondre aux questions 10 et 11 du TP <i>Lexémisation</i>.
 */
public class TokenReader implements Iterable<TokenReader.QGram> {
	
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
	
	
	
	
	public List<QGram> getAllQGram() {
		List<QGram> qGrams = new ArrayList<>();
		for (QGram qGram : this) {
			qGrams.add(qGram);
		}
		return qGrams;
	}
	
	
	
	
	@Override
	public Iterator<QGram> iterator() {
		return new Iterator<QGram>() {
			
			int currentPos = 0;
			
			
			/**
			 * Returns the next element in the iteration.<br/>
			 * The last iteration's element may contains less elements than expected,
			 * but at least one.
			 */
			@Override
			public QGram next() {
				List<? extends Token> returnedTokens = tokens.subList(currentPos, Math.min(tokens.size(), currentPos+bufferSize));
				int qGramPos = currentPos;
				currentPos += step;
				return new QGram(returnedTokens, qGramPos);
			}
			
			@Override
			public boolean hasNext() {
				return (currentPos < tokens.size());
			}
		};
	}
	
	
	
	
	public class QGram implements List<Token> {
		private final int hashCode;
		private List<Token> qGramTokens;
		private final int qGramPosition;
		
		@SuppressWarnings("unchecked")
		private QGram(List<? extends Token> toks, int pos) {
			qGramTokens = (List<Token>) toks;
			hashCode = qGramTokens.hashCode();
			qGramPosition = pos;
		}
		
		
		
		@Override
		public int hashCode() {
			return hashCode;
		}
		
		
		
		
		/*
		 * All methods that throws UnsupportedOperationException
		 */
		@Override
		public void add(int index, Token element) { throw new UnsupportedOperationException(); }
		@Override
		public boolean add(Token e) { throw new UnsupportedOperationException(); }
		@Override
		public boolean addAll(Collection<? extends Token> c) { throw new UnsupportedOperationException(); }
		@Override
		public boolean addAll(int index, Collection<? extends Token> c) { throw new UnsupportedOperationException(); }
		@Override
		public void clear() { throw new UnsupportedOperationException(); }
		@Override
		public Token remove(int index) { throw new UnsupportedOperationException(); }
		@Override
		public boolean remove(Object o) { throw new UnsupportedOperationException(); }
		@Override
		public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); }
		@Override
		public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }
		@Override
		public Token set(int index, Token element) { throw new UnsupportedOperationException(); }
		// ---------------------------------
		
		
		
		@Override
		public boolean contains(Object o) {
			return qGramTokens.contains(o);
		}
		@Override
		public boolean containsAll(Collection<?> c) {
			return qGramTokens.containsAll(c);
		}
		@Override
		public Token get(int index) {
			return qGramTokens.get(index);
		}
		@Override
		public int indexOf(Object o) {
			return qGramTokens.indexOf(o);
		}
		@Override
		public boolean isEmpty() {
			return qGramTokens.isEmpty();
		}
		@Override
		public Iterator<Token> iterator() {
			return qGramTokens.iterator();
		}
		@Override
		public int lastIndexOf(Object o) {
			return qGramTokens.lastIndexOf(o);
		}
		@Override
		public ListIterator<Token> listIterator() {
			return qGramTokens.listIterator();
		}
		@Override
		public ListIterator<Token> listIterator(int index) {
			return qGramTokens.listIterator(index);
		}
		@Override
		public int size() {
			return qGramTokens.size();
		}
		@Override
		public List<Token> subList(int fromIndex, int toIndex) {
			return qGramTokens.subList(fromIndex, toIndex);
		}
		@Override
		public Object[] toArray() {
			return qGramTokens.toArray();
		}
		@Override
		public <T> T[] toArray(T[] a) {
			return qGramTokens.toArray(a);
		}
		
		
		
		
		
		
		
		

		@Override
		public boolean equals(Object obj) {
			if (obj == null) return false;
			if (!(obj instanceof QGram)) return false;
			if (hashCode() != obj.hashCode()) return false;
			return qGramTokens.equals(((QGram)obj).qGramTokens);
		}
		
		
		
		
	}
	
	
	
	
}
