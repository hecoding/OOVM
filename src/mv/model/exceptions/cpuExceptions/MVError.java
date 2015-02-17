package mv.model.exceptions.cpuExceptions;

import mv.model.ins.Instruction;

/**
 * Indica que ha habido un error al intentar ejecutar el programa en la CPU.
 * Esta clase hereda de RuntimeException, por lo que no hace falta declarar que se lanza esta excepci�n.
 * @author Samuel Lapuente y H�ctor Laria
 *
 */
@SuppressWarnings("serial")
public class MVError extends RuntimeException {
	// Dado que la excepci�n es un error recuperable, prefiero lanzar una unchecked Exception - H�ctor
	// http://docs.oracle.com/javase/tutorial/essential/exceptions/runtime.html
	public MVError (String s) {
		super(s);
	}
	
	public MVError (Instruction inst, String s) {
		super("Error ejecutando la instrucci�n " + inst + ": " + s);
	}

}
