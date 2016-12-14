package rps.util;

public class InputMain {
	
	// to test Input
	public static void main(String[] args) {
		
		System.out.print(" chaine : ? ");
		String chaineLue = Input.readString();
		System.out.println("lue  => " + chaineLue);
		int intLu;
		try {
			System.out.print(" int : ? ");
			intLu = Input.readInt();
			System.out.println("lu  => " + intLu);
		} catch (java.io.IOException e) {
		    //"exception... : la saisie n'est pas un entier";
		    // on peut par exemple ici fournir une valeur par défaut : 
		    intLu = 0;
			System.out.println("corrigé  => " + intLu);
		}
    }
}
