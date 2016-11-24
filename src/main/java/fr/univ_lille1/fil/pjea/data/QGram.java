package fr.univ_lille1.fil.pjea.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class QGram<T> extends ArrayList<T> {
	private static final long serialVersionUID = 1L;
	
	
	private final int qGramPosition;
	
	QGram(List<T> toks, int pos) {
		super(toks);
		qGramPosition = pos;
	}
	
	
	public int getQGramPosition() {
		return qGramPosition;
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
	
	
	
	
	
	
}
