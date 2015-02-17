package mv.exceptions.cpuExceptions;

/**
 * Indica que no se ha metido ningún programa, por lo que la CPU no puede ejecutar nada.
 * Esta clase hereda de Exception, por lo que es necesario declarar en la firma que se lanza este tipo de Excepción.
 * @author Samuel Lapuente y Héctor Laria
 *
 */
public class NoInstructionsException extends Exception {
	// Dado que no hay instrucciones en la máquina es un error irrecuperable, prefiero lanzar una checked Exception - Héctor
	// http://docs.oracle.com/javase/tutorial/essential/exceptions/runtime.html
	public NoInstructionsException() {
		super("No hay instrucciones.");
	}

	public NoInstructionsException(String message) {
		super(message);
	}

	public NoInstructionsException(Throwable cause) {
		super(cause);
	}

	public NoInstructionsException(String message, Throwable cause) {
		super(message, cause);
	}

}
