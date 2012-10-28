package gui;

import java.awt.Graphics2D;

import drivers.*;
import javax.swing.JFrame;


public class GUI extends JFrame {

    /*
	 * Main GUI Class. This is the main window, it carries the info and then creates the GUIPanel
	 * where magic happens. 
	 */
	private static final long serialVersionUID = 1L;
	public GUIPanel canvas;

	public GUI() {
		this.setSize(200, 200);
		this.canvas = new GUIPanel();
		this.canvas.g = this.getGraphics();
        this.canvas.g2 = (Graphics2D) this.canvas.g;
        add(canvas);
		this.setTitle("Engine");
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addWindowListener(new Window(this));
        this.addKeyListener(new Keyboard(this));
        this.addMouseListener(new Mouse(this));
        
        setVisible(true);
    }
}

