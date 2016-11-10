package fr.univ_lille1.fil.pjea.testfilegenerator;

import java.io.File;
import java.util.Iterator;

import org.antlr.v4.runtime.Token;

import fr.univ_lille1.fil.pjea.Java8File;

public class FileGenerator {

	
	public static void main(String[] args) {
		
	}

	
	public static File BuildFile(Java8File f1, Java8File f2) throws Exception{
		
		File newFile = new File("concact"+f1.file.getName()+"-"+f2.file.getName());
		
        Iterator<? extends Token> listToken1 = f1.tokens.iterator();
        Iterator<? extends Token> listToken2 = f1.tokens.iterator();
        
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
