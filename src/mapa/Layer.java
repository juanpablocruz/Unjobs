package mapa;

import java.io.*;
import java.util.Scanner;

import engine.Engine;
import engine.Functions.*;

/**
 * @author Juan Pablo Cruz
 * @category Layer
 * @description Layer reader
 */

public class Layer {
	private String file;
	
	public Layer(String archivo){
		this.file = archivo;
	}
	
	public List Read() {
		try{
			
			List mapa = new List(0);
			Scanner filein = new Scanner(new File(this.file));
			  while (filein.hasNextLine()){
				  Scanner colReader = new Scanner(filein.nextLine());
				   List col = new List(0);
				   while(colReader.hasNextInt())
				    {
				        col.append(colReader.nextInt());
				    }
				    mapa.append(col);
				    colReader.close();
			  }
			  filein.close();
			  return mapa;
			    }catch (Exception e){//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
			  }
		return null;
	}

}
