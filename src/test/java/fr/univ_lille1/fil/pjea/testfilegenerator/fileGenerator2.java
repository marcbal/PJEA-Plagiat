package fr.univ_lille1.fil.pjea.testfilegenerator;
import java.io.*;

class fileGenerator2{

	static String  line = null;
        public static void main(String[] args) {

 	try{

		BufferedReader file = new BufferedReader(new FileReader("progCreationFichier.java"));
		//BufferedReader file = new BufferedReader(new FileReader("f"));

		FileOutputStream newFile = new FileOutputStream("newfichier.java");
		BufferedWriter bwFile  = new BufferedWriter(new OutputStreamWriter(newFile));

	   while((line=file.readLine())!= null){
		if(line.contains("package"))
		   continue;
	       if(line.contains("public")){
		 line = line.replace("public","");
	       }

		for(int i=0 ; i<line.length();i++){
			bwFile.write(line.charAt(i));	
		}
		bwFile.newLine();

		
	  }

	  file.close();
	  bwFile.close();

	}catch(Exception e){


	}
      
  }


	
}
