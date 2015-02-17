package mv.exceptions.cpuExceptions;

import mv.ins.Instruction;

/**
 * Indica que ha habido un error al intentar ejecutar el programa en la CPU.
 * Esta clase hereda de RuntimeException, por lo que no hace falta declarar que se lanza esta excepci�n.
 * @author Samuel Lapuente y H�ctor Laria
 *
 */
public class HardwareException extends RuntimeException {
	// Dado que la excepci�n es un error recuperable, prefiero lanzar una unchecked Exception - H�ctor
	// http://docs.oracle.com/javase/tutorial/essential/exceptions/runtime.html
	public HardwareException(String arg0) {
		super(arg0);
	}
	
	public HardwareException (Instruction inst, String s) {
		super("Error ejecutando la instrucci�n " + inst + ": " + s);
	}

	public HardwareException(Throwable arg0) {
		super(arg0);
	}

	public HardwareException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
