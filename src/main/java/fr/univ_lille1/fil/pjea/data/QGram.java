package fr.univ_lille1.fil.pjea.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.ToIntBiFunction;

import fr.univ_lille1.fil.pjea.algos.LevenshteinDistanceAlgorithm;
import fr.univ_lille1.fil.pjea.algos.NeedlemanWunschAlignmentAlgorithm;

public class QGram<T> extends ArrayList<T> {
	private static final long serialVersionUID = 1L;
	
	
	private final int qGramPosition;
	
	private final int hashCode;
	
	
	QGram(List<T> toks, int pos, int computedHashcode) {
		super(toks);
		qGramPosition = pos;
		hashCode = computedHashcode;
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
	public void add(int index, T element) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean add(T e) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean addAll(Collection<? extends T> c) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public T remove(int index) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public T set(int index, T element) {
		throw new UnsupportedOperationException();
	}
	// ---------------------------------
	
	
	
	
	public int levenshteinDistance(QGram<T> qGramCompared, BiFunction<T, T, Boolean> equalsFunction) {
		return LevenshteinDistanceAlgorithm.compute(this, qGramCompared, equalsFunction);
	}
	
	public int levenshteinDistance(QGram<T> qGramCompared) {
		return levenshteinDistance(qGramCompared, Objects::equals);
	}
	
	
	
	public int needlemanWunschAlignment(QGram<T> q, int d, ToIntBiFunction<T, T> S) {
		return NeedlemanWunschAlignmentAlgorithm.compute(this, q, d, S);
	}
	
	public int needlemanWunschAlignment(QGram<T> q, int d, BiFunction<T, T, Boolean> Sbool) {
		return needlemanWunschAlignment(q, d, (ToIntBiFunction<T, T>) ((o1, o2) -> Sbool.apply(o1, o2) ? 1 : 0));
	}
	
	public int needlemanWunschAlignment(QGram<T> q, int d) {
		return needlemanWunschAlignment(q, d, (BiFunction<T, T, Boolean>) Objects::equals);
	}
	
	
	
}
