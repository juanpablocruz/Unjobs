package engine;

public class Position {
	public int x;
	public int y;
	
	public Position(int x,int y){
		this.x = x;
		this.y = y;
	}
	public void move(int x,int y){
		this.x += x;
		this.y += y;
	}
}
