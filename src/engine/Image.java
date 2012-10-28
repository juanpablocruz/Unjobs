package engine;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Image extends JPanel{

	private static final long serialVersionUID = 1L;
	private BufferedImage image;
    private int x,y;
    public Image(String imagename,int x, int y) {
    	this.x = x;
    	this.y = y;
       try {                
          image = ImageIO.read(new File(imagename));
       } catch (IOException ex) {
            // handle exception...
       }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, x, y, null);           
    }

}
