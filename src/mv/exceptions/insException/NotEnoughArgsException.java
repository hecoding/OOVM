package mv.exceptions.insException;

/**
 * Indica que no hay suficientes argumentos en la pila de operandos para ejecutar una instrucci�n concreta.
 * Hereda de NoArgsException.
 * @author Samuel Lapuente y H�ctor Laria
 *
 */
public class NotEnoughArgsException extends NoArgsException {

	public NotEnoughArgsException() {
		super ("faltan operandos en la pila (hay 1)");
	}
	
	public NotEnoughArgsException(String s) {
		super(s);
	}

}
