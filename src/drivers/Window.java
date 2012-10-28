package drivers;

import gui.GUI;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Window implements WindowListener{
    GUI interfaz;

    public Window(GUI objeto){
    	interfaz = objeto;
    }
	
	public void windowActivated(WindowEvent e) {
	}

	public void windowClosed(WindowEvent e) {
        interfaz.canvas.condicion = false;
        System.exit(0);
	}

	public void windowClosing(WindowEvent e) {
	}

	public void windowDeactivated(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowIconified(WindowEvent e) {
	}

	public void windowOpened(WindowEvent e) {
	}

}
