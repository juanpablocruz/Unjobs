package engine.Functions;

import java.io.File;
import java.util.Scanner;

import engine.Engine;

public class Resources {
	private String file;

	public void Read(String file){
		this.file = file;
		try{
			Scanner filein = new Scanner(new File(this.file));
			  while (filein.hasNextLine()){
				  Scanner colReader = new Scanner(filein.nextLine());
				   while(colReader.hasNext())
				    {
					   String fila="";
					   switch(colReader.next()){
					   case "HeroImage":
						   fila = colReader.next();
						   fila += " ";
						   fila += colReader.next();
						   Engine.HeroImage = fila;
						   break;
					   case "MapWorld":
						   fila = colReader.next();
						   fila += " ";
						   fila += colReader.next()+'"';
						   Engine.MapWorld = fila;
						   break;
					   case "OLayer":
						   fila = colReader.next();
						   fila += " ";
						   fila += colReader.next();
						   Engine.OLayer = fila;
						   break;
					   case "NPCLayer":
						   fila = colReader.next();
						   fila += " ";
						   fila += colReader.next();
						   Engine.NPCLayer = fila;
						   break;
					   }
				    }
				    colReader.close();
			  }
			  filein.close();
			    }catch (Exception e){//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
			  }
	}
}
