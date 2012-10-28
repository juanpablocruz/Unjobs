package engine.Functions;

import org.python.core.PyInstance;
import org.python.util.PythonInterpreter;

public class Python {
	PythonInterpreter interpreter = null;
	
	public Python(){
		PythonInterpreter.initialize(System.getProperties(),
	            System.getProperties(), new String[0]);
	
		this.interpreter = new PythonInterpreter();
	}
	public void execfile( final String fileName )
	   {
	      this.interpreter.execfile(fileName);
	   }

	   public PyInstance createClass( final String className, final String opts )
	   {
	      return (PyInstance) this.interpreter.eval(className + "(" + opts + ")");
	   }

	 /*public static void main( String gargs[] )
	   {
	      InterpreterExample ie = new InterpreterExample();

	      ie.execfile("hello.py");

	      PyInstance hello = ie.createClass("Hello", "None");

	      hello.invoke("run");
	   }*/
}
