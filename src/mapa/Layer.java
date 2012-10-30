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
	
	public int[][] Read() {
		try{
			
			int[][] mapa = new int[200][200];
			Scanner filein = new Scanner(new File(this.file));
			int i = 0;
			  while (filein.hasNextLine()){
				  Scanner colReader = new Scanner(filein.nextLine());
				   int[] col = new int[200];
				   int j = 0;
				   while(colReader.hasNextInt())
				    {
				        col[j] = colReader.nextInt();
				        j++;
				    }
				    mapa[i] = col;
				    i++;
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
