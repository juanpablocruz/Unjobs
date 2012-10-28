package mapa;

import engine.Tile;

public class Levelchange {
	private Layer mapa;
	private Tile casilla;
	public Levelchange(int x, int y, String mapaStr, String urlimage){
		this.mapa = new Layer(mapaStr);
		this.casilla = new Tile(y, y, y, urlimage, -1, false);
	}
}
