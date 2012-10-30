package gui;

import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;
import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import characters.Hero;

import com.sun.opengl.util.FPSAnimator;
import com.sun.opengl.util.texture.Texture;
import drivers.Keyboard;
import drivers.Mouse;
import engine.Image;

/**
 * @author Juan Pablo Cruz
 * @category GUI OpenGL
 */
public class GUI extends GLCanvas implements GLEventListener{	
	
	private static final long serialVersionUID = 1L;
	private Texture heroTexture;
	public FPSAnimator animator;
	public int Squarex = 0,Squarey=0;
	private GLU glu;
	private GL gl;
	public BufferedImage bufferedImage = null;
	private GLAutoDrawable gLDrawable;
	
	public GUI(int width, int height, GLCapabilities capabilities) {
		 /**
		 * <p>Main GUI Class. This is the main window.
		 * It carries the info and then creates the GUIPanel
		 * where magic happens.</p>
		 */
		super(capabilities);
		  // Get the default graphic device and try full screen mode		
        setSize(width, height);
        addGLEventListener(this);
	}
	public void drawSquare(GLAutoDrawable gLDrawable,float x1, float y1, float lado,GL gl)
	{
		gl.glColor3f(0.9f, 0.5f, 0.2f);
		gl.glBegin(GL.GL_QUADS);
		gl.glVertex3f(x1,y1,0);
		gl.glVertex3f(x1,y1+lado,0);
		gl.glVertex3f(x1+lado,y1+lado,0);
		gl.glVertex3f(x1+lado,y1,0);
		gl.glEnd();
	}
	private void setCamera(GL gl, GLU glu, float distance) {
		// Change to projection matrix.
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();

        // Perspective.
        float widthHeightRatio = (float) getWidth() / (float) getHeight();
        glu.gluPerspective(45, widthHeightRatio, 1, 1000);
        glu.gluLookAt(0, 0, distance, 0, 0, 0, 0, 1, 0);

        // Change back to model view matrix.
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
	
	private static int ceilingPow2(int n) {
		int pow2 = 1;
		while (n > pow2) {
		pow2 = pow2<<1;
		}
		return pow2;
	}
	
	public void display(GLAutoDrawable gLDrawable)
	{		
		if (!animator.isAnimating()) {
            return;
        }

		gl = gLDrawable.getGL();
		
        // Clear screen.
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        // Set camera.
        setCamera(gl, glu, 30);

        // Prepare light parameters.
        float SHINE_ALL_DIRECTIONS = 1;
        float[] lightPos = {-30, 0, 0, SHINE_ALL_DIRECTIONS};
        float[] lightColorAmbient = {0.2f, 0.2f, 0.2f, 1f};
        float[] lightColorSpecular = {0.8f, 0.8f, 0.8f, 1f};

        // Set light parameters.
        gl.glLightfv(GL.GL_LIGHT1, GL.GL_POSITION, lightPos, 0);
        gl.glLightfv(GL.GL_LIGHT1, GL.GL_AMBIENT, lightColorAmbient, 0);
        gl.glLightfv(GL.GL_LIGHT1, GL.GL_SPECULAR, lightColorSpecular, 0);

        // Enable lighting in GL.
        gl.glEnable(GL.GL_LIGHT1);
        gl.glEnable(GL.GL_LIGHTING);

        // Set material properties.
        float[] rgba = {0.5f, 1f, 1f};
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, rgba, 0);
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, rgba, 0);
        gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, 0.5f);
		
        // Apply texture.
        //heroTexture.enable();
        //heroTexture.bind();
        
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        setCamera(gl, glu, 100);
        
        //Draw square.
        this.drawSquare(gLDrawable, Squarex, Squarey, 10,gl);
        
        this.drawSquare(gLDrawable, 20, 20, 10, this.gl); 
        
        
	}
	
	public void displayChanged(GLAutoDrawable gLDrawable, boolean modeChanged, boolean deviceChanged)
	{
		throw new UnsupportedOperationException("Changing display is not supported.");	
	}
	
	public void init(GLAutoDrawable gLDrawable)
	{
		this.gLDrawable = gLDrawable;
		final GL gl = gLDrawable.getGL();	
		gLDrawable.setGL(new DebugGL(gl));
		gLDrawable.addKeyListener(new Keyboard(this));
		gLDrawable.addMouseListener(new Mouse(this));
		
		// Enable z- (depth) buffer for hidden surface removal. 
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glDepthFunc(GL.GL_LEQUAL);

        // Enable smooth shading.
        gl.glShadeModel(GL.GL_SMOOTH);

        // Define "clear" color.
        gl.glClearColor(0f, 0f, 0f, 0f);

        // We want a nice perspective.
        gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);

        // Create GLU.
        glu = new GLU();
        
        // Start animator (which should be a field).
        animator = new FPSAnimator(this, 75);
        animator.start();	      
     
	}
	public void drawHero(Hero heroe){
		loadTextures(heroe.Chartile.imagen.imageName);
		this.drawSquare(gLDrawable, heroe.Chartile.posicion.x, heroe.Chartile.posicion.y, 10, this.gl); 
	}
	
	public void loadTextures(String image){
		
		int w = 0;
		int h = 0;
		Image imagen = new Image(image, 10, 10);
		w = ceilingPow2(imagen.getWidth());
	    h = ceilingPow2(imagen.getHeight());
	    
		WritableRaster raster = Raster.createInterleavedRaster (DataBuffer.TYPE_BYTE,w,h,4,null);
		ComponentColorModel colorModel=
			new ComponentColorModel (ColorSpace.getInstance(ColorSpace.CS_sRGB),
					new int[] {8,8,8,8},true,false,ComponentColorModel.TRANSLUCENT,
					DataBuffer.TYPE_BYTE);
		BufferedImage dukeImg = new BufferedImage (colorModel,raster,false,null);
 
		Graphics2D g = dukeImg.createGraphics();
		imagen.paintComponent(g);
		DataBufferByte dukeBuf = (DataBufferByte)raster.getDataBuffer();
		byte[] dukeRGBA = dukeBuf.getData();
		ByteBuffer bb = ByteBuffer.wrap(dukeRGBA);
		bb.position(0);
		bb.mark();
				gl.glBindTexture(GL.GL_TEXTURE_2D, 13);
				gl.glPixelStorei(GL.GL_UNPACK_ALIGNMENT, 1);
				gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_CLAMP);
				gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_CLAMP);
				gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
				gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
				gl.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_REPLACE);
				gl.glTexImage2D (GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, w, h, 0, GL.GL_RGBA, 
						GL.GL_UNSIGNED_BYTE, bb);
	 
				int left = 100;
				int top = 100;
				gl.glEnable(GL.GL_TEXTURE_2D);
				gl.glBindTexture (GL.GL_TEXTURE_2D, 13);
				gl.glBegin (GL.GL_POLYGON);
				gl.glTexCoord2d (0, 0);
				gl.glVertex2d (left,top);
				gl.glTexCoord2d(1,0);
				gl.glVertex2d (left + w, top);
				gl.glTexCoord2d(1,1);
				gl.glVertex2d (left + w, top + h);
				gl.glTexCoord2d(0,1);
				gl.glVertex2d (left, top + h);
				gl.glEnd ();	
				gl.glFlush();
	}
	
	public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width, int height)
	{
		this.gl = gLDrawable.getGL();
        gl.glViewport(0, 0, width, height);
	}
	public void reshape(GLAutoDrawable gLDrawable,int width, int height)
	{
	}		
	
	public static GLCapabilities createGLCapabilities() {
	        GLCapabilities capabilities = new GLCapabilities();
	        capabilities.setRedBits(8);
	        capabilities.setBlueBits(8);
	        capabilities.setGreenBits(8);
	        capabilities.setAlphaBits(8);
	        return capabilities;
	    }
}
