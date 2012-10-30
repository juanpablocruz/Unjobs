package drivers;

import characters.Hero;
import gui.GUI;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import engine.Engine;

public class Keyboard implements KeyListener{
    /**
	 * 
	 */
	private GUI interfaz;
	private int windowWidth, windowHeight;
	public Keyboard(GUI interfaz) {
		this.interfaz = interfaz;
	}
	public void keyPressed(KeyEvent e)
    {
	switch(e.getKeyCode()){
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		case KeyEvent.VK_LEFT:
			this.interfaz.Squarex -=2;
			break;
		case KeyEvent.VK_RIGHT:
			this.interfaz.Squarex +=2;
			break;
		case KeyEvent.VK_UP:
			this.interfaz.Squarey += 2;
			break;
		case KeyEvent.VK_DOWN:
			this.interfaz.Squarey -= 2;
			break;
			// F1 to toggle between full-screen and windowed modes
        case KeyEvent.VK_F1: 
           if (!Engine.fullScreen) {  // Saved the current size for restoration
              Dimension screenSize = Engine.gui.getSize();
              windowWidth  = (int)screenSize.getWidth();
              windowHeight = (int)screenSize.getHeight();
           }
           Engine.fullScreen = !Engine.fullScreen;
           Engine.gui.setVisible(false); // Hide the display
           if (Engine.gui.isDisplayable())
              Engine.gui.dispose();      // For changing the decoration
           if (Engine.fullScreen) {
              if (Engine.device.isFullScreenSupported()) {
                 Engine.gui.setUndecorated(true);
                 Engine.gui.setResizable(false);
                 Engine.device.setFullScreenWindow(Engine.gui);
              }
           } else {
              Engine.gui.setUndecorated(false);  // Put the title and border back
              Engine.device.setFullScreenWindow(null); // Windowed mode
              Engine.gui.setSize(800, 500);
              Engine.gui.setResizable(true);
           }
           Engine.gui.setVisible(true);  // Show it
           interfaz.requestFocus();
           break;
		} 
    }
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
   
}
