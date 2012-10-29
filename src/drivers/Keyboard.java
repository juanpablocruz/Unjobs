package drivers;

import gui.*;
import characters.Hero;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
    GUI interfaz;
    Hero heroe;
    
    public Keyboard(GUI objeto){
        interfaz = objeto;
        this.heroe = engine.Engine.Heroe;
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
        case KeyEvent.VK_KP_RIGHT:
        	this.heroe.movement(10, 0);
        	break;
        case KeyEvent.VK_KP_LEFT:
        	this.heroe.movement(-10,0);
        	break;
        case KeyEvent.VK_SPACE:
        	//interfaz.canvas.condicion = false;
        	break;
        }
        
    }

    public void keyReleased(KeyEvent e) {

    }
}
