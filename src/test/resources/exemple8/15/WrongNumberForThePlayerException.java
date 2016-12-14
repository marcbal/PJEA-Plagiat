package ppc.util;

// compilation depuis le répertoier src : javac -d ../classes ./ppc/util/WrongNumberForThePlayerException.java

public class WrongNumberForThePlayerException extends RuntimeException {

	public WrongNumberForThePlayerException() {
	}
	
	public WrongNumberForThePlayerException(String msg) {
		super(msg);
	}
}
