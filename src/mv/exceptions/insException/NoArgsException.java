package mv.exceptions.insException;

import java.util.EmptyStackException;

/**
 * Indica que no hay operandos en la pila.
 * @author Samuel Lapuente y Héctor Laria
 *
 */
public class NoArgsException extends EmptyStackException {
	private String message;
	
	public NoArgsException() {
		super();
		this.message = "Faltan operandos en la pila (hay 0)";
	}
	
	public NoArgsException (String s) {
		super();
		this.message = s;
	}
	
	public String getMessage() {
		return this.message;
	}

}
