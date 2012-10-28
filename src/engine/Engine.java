package engine;

import mapa.*;
import gui.*;
import drivers.Mouse;
import engine.Functions.*;
import characters.*;

import java.util.Scanner;

public class Engine {
	public static List mapWorld,olayer,npclayer;
	public static Mouse mouse;
	public static Hero Heroe;
	public static GUI interfaz;
	public static String World;
	public static String HeroImage,MapWorld,OLayer,NPCLayer;
	
	public static <T> void print(T text){
		System.out.println(text);
	}
	
	public static int startUp(){
		// Loading designed World
		Resources recursos = new Resources();
		recursos.Read(World);
		print("Starting engine..");
		/*
		 * Load Hero
		 */
		print("Welcoming a new hero");
		Tile heroTile = new Tile(13, 0, 0, HeroImage, 13, true);
		Heroe = new Hero("Unjobs", heroTile);
		/*
		 * Load maps
		 */
		print("Loading maps..");
		Layer mapa = new Layer(MapWorld);
		mapWorld = mapa.Read();
		if(mapWorld==null){
			print("Error loading maps");
			return 0;
		}
		else{
			interfaz = new GUI();
			interfaz.canvas.drawHero(Heroe);
		}
		/*
		 * Load Objects layer
		 */
		print("Loading Object layer");
		Layer objectlayer = new Layer(OLayer);
		olayer = objectlayer.Read();
		if(olayer==null){
			print("Error loading Object layer");
			return 0;
		}		
		/*
		 * Load Physics
		 */
		print("Creating physics");
		/*
		 * Load Drivers
		 */
		print("Loading Drivers");
		//mouse = new Mouse();
		/*
		 * Load NPC
		 */
		print("Populating world");
		Layer npcmap = new Layer(NPCLayer);
		npclayer = npcmap.Read();
		if(npclayer==null){
			print("Error loading Object layer");
			return 0;
		}
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
				interfaz.canvas.condicion=false;
				//interfaz.dispose();
				break;
			case 1:
				World = "C:\\Users\\Juan Pablo\\workspace\\Engine\\resources.txt";
				int res = startUp();
				if(res==0)print("Exiting");
				else{
					print("World created");
					//drawWorld();
					//mainLoop();
				}
				bye=0;
				break;
			}
		}
		input.close();
	}
}
