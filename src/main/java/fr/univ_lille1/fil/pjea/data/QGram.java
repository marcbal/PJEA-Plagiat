package fr.univ_lille1.fil.pjea.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.antlr.v4.runtime.Token;

import fr.univ_lille1.fil.pjea.TokenUtils;
import fr.univ_lille1.fil.pjea.algos.LevenshteinDistanceAlgorithm;
import fr.univ_lille1.fil.pjea.algos.NeedlemanWunschAlignmentAlgorithm;

public class QGram extends ArrayList<Token> {
	private static final long serialVersionUID = 1L;


	private final int hashCode;
	private final int qGramPosition;

	QGram(List<? extends Token> toks, int pos, int computedHashCode) {
		super(toks);
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
	public void add(int index, Token element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean add(Token e) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends Token> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends Token> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Token remove(int index) {
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
	public Token set(int index, Token element) {
		throw new UnsupportedOperationException();
	}
	// ---------------------------------





	public int needlemanWunschAlignment(QGram q, int d) {
		return NeedlemanWunschAlignmentAlgorithm.compute(this, q, d, TokenUtils::tokenSimilarity);
	}

	public int levenshteinDistance(QGram qGramCompared) {
		return LevenshteinDistanceAlgorithm.compute(this, qGramCompared, TokenUtils::equalsTokens);
	}





	@Override
	public String toString() {
		return "[" + Integer.toHexString(hashCode()) + ":"
				+ String.join(",", this.stream().map(Token::getText).toArray(s -> new String[s])) + "]";
	}





	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof QGram))
			return false;
		if (hashCode() != obj.hashCode())
			return false;
		QGram other = (QGram) obj;
		if (size() != other.size())
			return false;
		for (int i = 0; i < size(); i++) {
			if (!TokenUtils.equalsTokens(get(i), other.get(i)))
				return false;
		}
		return true;
	}


}
