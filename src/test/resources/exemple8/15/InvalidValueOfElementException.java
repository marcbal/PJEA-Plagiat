package ppc.util;

// compilation depuis le répertoier src : javac -d ../classes ./ppc/util/InvalidValueOfElementException.java

public class InvalidValueOfElementException extends RuntimeException {

	public InvalidValueOfElementException() {
	}
	
	public InvalidValueOfElementException(String msg) {
		super(msg);
	}
}
