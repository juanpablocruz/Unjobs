package engine.Functions;

public class List{
	public Object[] lista;
	
	public List(int len){
		this.lista = new Object[len];
	}
	public void append(Object element){
		int len = lista.length;
		this.lista = new Object[len+1];
		this.lista[len] = element;
	}
	public int len(){
		return this.lista.length;
	}
}
