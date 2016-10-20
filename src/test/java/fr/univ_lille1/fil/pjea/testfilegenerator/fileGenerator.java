package fr.univ_lille1.fil.pjea.testfilegenerator;

import java.io.File;
import java.util.Iterator;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.Lexer;

import fr.univ_lille1.fil.pjea.antlr_lexers.java8.Java8Lexer;
import fr.univ_lille1.fil.pjea.qgrams.QGram;
import fr.univ_lille1.fil.pjea.qgrams.TokenReader;

public class fileGenerator {

	
	public static void main(String[] args) {
		
	}

	
	public static File BuildFile(File f1, File f2) throws Exception{
		
		File newFile = new File("concact"+f1.getName()+"-"+f2.getName());
		
		ANTLRInputStream file1 = new ANTLRFileStream(f1.getPath());
        Lexer lexer1 = new Java8Lexer(file1);
        
        ANTLRInputStream file2 = new ANTLRFileStream(f2.getPath());
        Lexer lexer2 = new Java8Lexer(file2);
        
        Iterator<QGram> listToken1 = new TokenReader(lexer1).iterator();
        Iterator<QGram> listToken2 = new TokenReader(lexer2).iterator();
        
        //listToken1.remove();
        //listToken2.remove();
        
        while(listToken1.hasNext()){
  		  if(listToken1.next().equals("import")){
  			  while(!listToken1.next().equals(";")){
  				  
  			  }
  		  }
  	  	}
        
        
        while(listToken2.hasNext()){
  		  if(listToken2.next().equals("import")){
  			  
  		  }
  	  	}
        
        


		 
		
		 
	
		 return newFile;
	}
}
