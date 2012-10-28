package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;

import characters.Hero;
import engine.Functions.List;

public class GUIPanel extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;
	private static double WIDTH, HEIGHT;
	private final int fraps = 20;
	Thread mainThread;
	public volatile boolean condicion;
	public Graphics g;
	public Graphics2D g2;
	private List[] layers;
	private Hero heroe;
	private int capa = 0;
	
	public GUIPanel(){
		
		this.setBounds(100, 100, 500, 500);
		this.setVisible(true);
        this.setIgnoreRepaint(true);
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.condicion = true;
        this.setDoubleBuffered(true);
        this.layers = new List[3];
      
        mainThread = new Thread(this);
        mainThread.start();
	}
	public void drawLayer(List layer){
		//Draws the given layer list.
		this.layers[capa] = layer;
		capa++;
	}
	public void drawNPC(List layer){
		//Draws the given npclayer
		this.layers[capa] = layer;
		capa++;
	}
	
	public void drawHero(Hero heroe){
		this.heroe = heroe;
	}
	
    private void render(){

        RenderingHints rh =
                new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                                   RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

         g2.setRenderingHints(rh);

         Dimension size = getSize();
         WIDTH = size.getWidth();
         HEIGHT = size.getHeight();
         heroe.Chartile.Draw(g2);
         /*for(int i=0;i<this.layers.length ;i++){
        	 for(int j=0;j<this.layers[i].len() ;j++){
        	 ((Image) this.layers[i].lista[j]).paintComponent(g2);
        	 }
        	// drawNPC(g2);
         }*/
         
         Toolkit.getDefaultToolkit().sync();
         g.dispose();
    }
    public void addNotify() {
        super.addNotify();
        mainThread = new Thread(this);
        mainThread.start();
    }
    
    public void run() {
    	long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();
        while(condicion){
            render();
            super.update(g2);
            
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = fraps - timeDiff;
            if (sleep < 0)
                sleep = 2;
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            beforeTime = System.currentTimeMillis();
        }
        System.exit(0);
    }
}
