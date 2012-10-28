package characters;

import org.python.core.PyInstance;

import engine.Tile;
import engine.Functions.*;


public class NPC extends Character{
	List functionList;
	
	public NPC(String name, Tile tile) {
		super(name, tile);
	}
	
	public void addFunctions(String function){
		this.functionList.append(function);
	}
	
	public void callFunction(int funcion){
		Python ie = new Python();
		ie.execfile(this.functionList.lista[funcion].toString());
		PyInstance hello = ie.createClass("Script", "None");
		hello.invoke("run");	
	}
}
