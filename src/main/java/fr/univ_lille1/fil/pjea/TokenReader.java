package fr.univ_lille1.fil.pjea;

import java.util.Iterator;
import java.util.List;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;

public class TokenReader implements Iterable<List<? extends Token>> {
	
	private final int step;
	private final int bufferSize;
	private final List<? extends Token> tokens;
	
	public TokenReader(Lexer lexer, int stp, int buffSize) {
		step = stp;
		bufferSize = buffSize;
		tokens = lexer.getAllTokens();
	}
	
	public TokenReader(Lexer lexer, int buffSize) {
		this(lexer, buffSize, buffSize);
	}
	
	public TokenReader(Lexer lexer) {
		this(lexer, 1, 1);
	}
	
	
	
	
	
	@Override
	public Iterator<List<? extends Token>> iterator() {
		// TODO Auto-generated method stub
		return new Iterator<List<? extends Token>>() {
			
			int currentPos = 0;
			
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
