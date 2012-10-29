package engine;

import mapa.*;
import gui.*;
import drivers.Mouse;
import engine.Functions.*;
import characters.*;

import java.awt.BorderLayout;
import java.util.Scanner;

import javax.media.opengl.GLCapabilities;
import javax.swing.JFrame;


/**
 * @author Juan Pablo Cruz
 * @category Engine
 * <p>Main Engine class. Here everything gets initialized</p>
 */

public class Engine {
	
	public static List mapWorld,olayer,npclayer;
	public static Mouse mouse;
	public static Hero Heroe;
	public static GUI interfaz;
	public static String World;
	public static String HeroImage,MapWorld,OLayer,NPCLayer;
	public static JFrame gui;
	
	public static <T> void print(T text){
		/**
		 * @author Juan Pablo Cruz
		 * @category Engine
		 * @description Prints any text
		 * <p>Takes an object of any type and prints it</p>
		 */
		System.out.println(text);
	}
	
	public static int startUp(){
		// Loading designed World
		Resources recursos = new Resources();
		recursos.Read(World);
		print("Starting engine..");
		/*
		 * Load maps
		 */
		print("Loading maps..");
		//Layer mapa = new Layer(MapWorld);
		//mapWorld = mapa.Read();
		mapWorld = new List(1);
		mapWorld.lista[0] = 1;
		
		if(mapWorld==null){
			print("Error loading maps");
			return 0;
		}
		else{
			//Creates the window.
			GLCapabilities capabilities = GUI.createGLCapabilities();
	        interfaz = new GUI(500, 800, capabilities);
	        gui = new JFrame("Engine");
	        gui.getContentPane().add(interfaz, BorderLayout.CENTER);
	        gui.setSize(800, 500);
	        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        print("Done");
		}
		/*
		 * Load Objects layer
		 */
		print("Loading Object layer");
		//Layer objectlayer = new Layer(OLayer);
		//olayer = objectlayer.Read();
		olayer = new List(1);
		olayer.lista[0] = 1;
		if(olayer==null){
			print("Error loading Object layer");
			return 0;
		}
		else{
			print("Done");
		}
		/*
		 * Load NPC
		 */
		print("Populating world");
		//Layer npcmap = new Layer(NPCLayer);
		//npclayer = npcmap.Read();
		npclayer = new List(1);
		npclayer.lista[0]=1;
		if(npclayer==null){
			print("Error loading Object layer");
			return 0;
		}
		else{
			print("Done");
		}
		/*
		 * Load Hero
		 */
		print("Welcoming a new hero");
		Tile heroTile = new Tile(13, 0, 0, HeroImage, 13, true);
		Heroe = new Hero("Unjobs", heroTile);
		//interfaz.drawHero(Heroe);
		return 1;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int bye = 1;
		while(bye==1){
			print(" ---------------------- ");
			print("|        ENGINE        |");
			print(" ---------------------- ");
			print("Menu: ");
			print("1-Start engine");
			print("0-Exit engine");
			int i = input.nextInt();
			switch(i){
			case 0:
				bye=0;
				print("Bye");
				break;
			case 1:
				World = "C:\\Users\\Juan Pablo\\workspace\\Engine\\resources.txt";
				int res = startUp();
				if(res==0){
					print("Exiting");
					gui.dispose();
				}
				else{
					print("World created");
			        gui.setVisible(true);
			        interfaz.requestFocus();
				}
				bye=0;
				break;
			}
		}
		input.close();
	}
}
