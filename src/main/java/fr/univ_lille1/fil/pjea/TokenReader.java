package fr.univ_lille1.fil.pjea;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;



/**
 * Classe {@link Iterable} qui permet de parcourir les léxèmes du {@link Lexer}
 * passé en paramètre.<br/>
 * Cette classe a été créé pour répondre aux questions 10 et 11 du TP <i>Lexémisation</i>.
 */
public class TokenReader implements Iterable<TokenReader.QGram> {
	
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

	public TokenReader(Lexer lexer, int step, int qGramSize) {
		this(lexer.getAllTokens(), step, qGramSize);
	}

	/**
	 * Équivaut à
	 * <pre>new TokenReader(lexer, qGramSize, qGramSize)</pre>
	 * @param lexer
	 * @param qGramSize
	 */
	public TokenReader(Lexer lexer, int qGramSize) {
		this(lexer, qGramSize, qGramSize);
	}
	
	/**
	 * Équivaut à
	 * <pre>new TokenReader(lexer, 1, 1)</pre>
	 * @param lexer
	 */
	public TokenReader(Lexer lexer) {
		this(lexer, 1, 1);
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
							.mapToInt(TokenReader::hashCodeToken).toArray());
				}
				else {
					hashCodeBuilder.putHashCode(returnedTokens.stream()
							.skip(Math.max(qGramSize - step, 0)) // retain only last Token that are not present in the previous QGram
							.mapToInt(TokenReader::hashCodeToken).toArray());
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
	
	
	
	
	public class QGram implements List<Token> {
		private final int hashCode;
		private List<Token> qGramTokens;
		private final int qGramPosition;
		
		@SuppressWarnings("unchecked")
		private QGram(List<? extends Token> toks, int pos, int computedHashCode) {
			qGramTokens = (List<Token>) toks;
			qGramPosition = pos;
			hashCode = computedHashCode;
		}
		

		public int getQGramPosition() {
			return qGramPosition;
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
			QGram other = (QGram) obj;
			if (size() != other.size()) return false;
			for (int i = 0; i < size() && i < other.size(); i++) {
				if (!equalsTokens(get(i), other.get(i)))
					return false;
			}
			return true;
		}
		
		
		
		@Override
		public String toString() {
			return "["+Integer.toHexString(hashCode())+":" + String.join(",", this.stream().map(Token::getText).toArray(s -> new String[s])) + "]";
		}
		
		
	}
	

	
	
	private static boolean equalsTokens(Token t1, Token t2) {
		if (t1 == null && t2 == null) return true;
		if (t1 == null || t2 == null) return false;
		return t1.getText().equals(t2.getText()) && t1.getType() == t2.getType();
	}
	
	public static int hashCodeToken(Token t) {
		return t.getType() << 4 + t.getText().hashCode();
	}
	
}
