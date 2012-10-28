package characters;
import engine.Tile;

public abstract class Character {
	public Tile Chartile;
	String name;
	
	public Character(String name,Tile tile){
		this.Chartile = tile;
		this.name = name;
	}
	
	protected void changeTile(){
		
	}
	
	public void movement(int x, int y){
		
	}
	public void attack(){
		
	}
	public void die(){
		
	}
}
