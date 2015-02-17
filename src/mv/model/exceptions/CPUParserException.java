package mv.model.exceptions;

/**
 * Indica las condiciones que son deseables de coger debido al parseo de una expresi�n que reconoce la CPU de la m�quina virtual.
 * Esta clase hereda de Exception, por lo que es necesario declarar en la firma que se lanza este tipo de Excepci�n.
 * @author Samuel Lapuente y H�ctor Laria
 *
 */
@SuppressWarnings("serial")
public class CPUParserException extends Exception {
	// ParseException de Java no se adec�a a nuestras necesidades porque no sabemos lo que es un errorOffset - H�ctor
	
	public CPUParserException() {
		super("la entrada no contiene una expresi�n parseable");
	}

	public CPUParserException(String arg0) {
		super(arg0);
	}

	public CPUParserException(Throwable arg0) {
		super(arg0);
	}

	public CPUParserException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public CPUParserException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
