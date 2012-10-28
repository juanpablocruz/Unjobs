package characters;

import engine.Tile;

public class Hero extends Character{

	public Hero(String name, Tile tile) {
		super(name, tile);
	}
	@Override
	public void movement(int x, int y){
		this.Chartile.posicion.move(x, y);
		changeTile();
	}
}
