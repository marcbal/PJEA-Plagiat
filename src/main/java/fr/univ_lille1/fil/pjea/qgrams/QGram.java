package fr.univ_lille1.fil.pjea.qgrams;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.antlr.v4.runtime.Token;

public class QGram implements List<Token> {
	private final int hashCode;
	private List<Token> qGramTokens;
	private final int qGramPosition;
	
	@SuppressWarnings("unchecked") QGram(List<? extends Token> toks, int pos, int computedHashCode) {
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
			if (!TokenReader.equalsTokens(get(i), other.get(i)))
				return false;
		}
		return true;
	}
	
	
	
	@Override
	public String toString() {
		return "["+Integer.toHexString(hashCode())+":" + String.join(",", this.stream().map(Token::getText).toArray(s -> new String[s])) + "]";
	}
	
	
}