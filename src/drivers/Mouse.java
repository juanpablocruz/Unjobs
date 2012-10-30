package drivers;
import engine.Engine;
import gui.GUI;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener{
	private GUI interfaz;
	public Mouse(GUI interfaz){
		this.interfaz = interfaz;
	}
	public void mouseClicked(MouseEvent e) {
		  if ((e.getModifiers() & e.BUTTON3_MASK) != 0) {
			  this.interfaz.Squarex +=10;
		  }
		  if ((e.getModifiers() & e.BUTTON1_MASK) != 0) {
			  this.interfaz.Squarex -=10;
		  }		  
	}	
	public void mousePressed(MouseEvent e) {
		
	}

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}
