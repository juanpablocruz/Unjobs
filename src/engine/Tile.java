package engine;
import java.awt.Graphics2D;

import engine.Image;

public class Tile {
	public Image imagen;
	public Position posicion;
	private int tiletype;
	private boolean collide;
	public int id;
	
	public Tile(int identificador,int x, int y, String imagename, int tiletype, boolean collide){
		this.posicion = new Position(x,y);
		this.imagen = new Image(imagename,x,y);
		this.tiletype = tiletype;
		this.collide = collide;
		this.id = identificador;
	}
	public void Draw(Graphics2D g){
		this.imagen.paintComponent(g);
	}
}