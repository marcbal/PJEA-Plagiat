package shifumi.util;

import java.io.IOException;
import java.util.Scanner;

/**
 * A tool class, to read String and int from standard input
 *
  */
public class Input {

	private static Scanner scanner = new Scanner(System.in);

	/**
	 * read a String from standard input
	 * 
	 * @return the read string
	 */
	public static String readString() {
			return Input.scanner.next();
	}

	/**
     * read an int from standard input
     * 
	 * @return the read int
	 * @exception java.io.IOException if input does not correspond to an int
	 */
	public static int readInt() throws java.io.IOException {
		try {
			return Input.scanner.nextInt();
		} catch (Exception e) {
			Input.scanner.skip(".*");
			throw new java.io.IOException();
		}
	}

}// Input
