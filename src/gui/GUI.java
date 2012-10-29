package gui;

import java.io.IOException;
import java.io.InputStream;

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
import com.sun.opengl.util.texture.TextureData;
import com.sun.opengl.util.texture.TextureIO;

/**
 * @author Juan Pablo Cruz
 * @category GUI OpenGL
 */
public class GUI extends GLCanvas implements GLEventListener{	
	
	private static final long serialVersionUID = 1L;
	private Texture heroTexture;
	public FPSAnimator animator;
	private GLU glu;
	private GL gl;
	private GLAutoDrawable gLDrawable;
	
	public GUI(int width, int height, GLCapabilities capabilities) {
		 /**
		 * <p>Main GUI Class. This is the main window.
		 * It carries the info and then creates the GUIPanel
		 * where magic happens.</p>
		 */
		super(capabilities);
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
        this.drawSquare(gLDrawable, 0, 0, 10,gl);
        
        
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
		loadTextures(heroe.Chartile.imagen.imageName, heroTexture);
		this.drawSquare(gLDrawable, heroe.Chartile.posicion.x, heroe.Chartile.posicion.y, 10, this.gl);    
	}
	
	private void loadTextures(String image, Texture textura){
		try {
            InputStream stream = getClass().getResourceAsStream(image);
            TextureData data = TextureIO.newTextureData(stream, false, "png");
            textura = TextureIO.newTexture(data);
        }
        catch (IOException exc) {
            exc.printStackTrace();
            System.exit(1);
        }
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
