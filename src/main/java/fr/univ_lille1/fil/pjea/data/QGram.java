package fr.univ_lille1.fil.pjea.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.antlr.v4.runtime.Token;

import fr.univ_lille1.fil.pjea.TokenUtils;

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
	
	public int distance(QGram qGramCompared){
		// Last version was O(n*m), this one only memorizes previous line of distance matrix
		// which makes this algorithm O(n)
	    int[] prev = new int[ qGramCompared.size()+1];

	    for( int j = 0; j < qGramCompared.size() + 1; j++ ) {
	        prev[ j ] = j;
	    }

	    for( int i = 1; i < this.size() + 1; i++ ) {

	        // calculate current line of distance matrix     
	        int[] curr = new int[ qGramCompared.size() + 1 ];
	        curr[0] = i;

	        for( int j = 1; j < qGramCompared.size() + 1; j++ ) {
	            int d1 = prev[ j ] + 1;
	            int d2 = curr[ j - 1 ] + 1;
	            int d3 = prev[ j - 1 ] + (TokenUtils.equalsTokens(this.get(i-1), qGramCompared.get(j-1))?0:1) ;
	            curr[ j ] = Math.min( Math.min( d1, d2 ), d3 );
	        }

	        // define current line of distance matrix as previous     
	        prev = curr;
	    }
	    return prev[ qGramCompared.size() ];
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
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof QGram)) return false;
		if (hashCode() != obj.hashCode()) return false;
		QGram other = (QGram) obj;
		if (size() != other.size()) return false;
		for (int i = 0; i < size(); i++) {
			if (!TokenUtils.equalsTokens(get(i), other.get(i)))
				return false;
		}
		return true;
	}
	
	
	
	
	
	
	public int alignmentNeedlemanWunsch(QGram q, int d) {
		int[][] tab = new int[2][q.size() + 1];

        for (int i = 0; i <= q.size(); i++)
            tab[1][i] = i * d;

        for (int i = 1; i <= size(); i++) {
        	tab[0] = tab[1];
        	tab[1] = new int[q.size() + 1];
        	tab[1][0] = i * d;
            for (int j = 1; j <= q.size(); j++) {
            	int S = TokenUtils.tokenSimilarity(get(i - 1), q.get(j - 1));
                tab[1][j] = Math.max(tab[0][j - 1] + S,
                					Math.max(tab[0][j] + d,
                							tab[1][j - 1] + d
                							)
                					);
                
            }
        }
        
        return tab[1][q.size()];
	}
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return "["+Integer.toHexString(hashCode())+":" + String.join(",", this.stream().map(Token::getText).toArray(s -> new String[s])) + "]";
	}
	
	
}