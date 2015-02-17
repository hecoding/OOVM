package mv.mvSystem.in;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Interfaz que se encarga de la entrada de la m�quina virtual.
 * @author Samuel Lapuente y H�ctor Laria
 *
 */
public interface InStrategy {
	/**
	 * Abre el stream de entrada.
	 * @throws FileNotFoundException
	 * @throws IOException si ha habido alg�n problema al abrir.
	 */
	public void open() throws FileNotFoundException, IOException;
	
	/**
	 * Lee un byte del stream de entrada.
	 * @return el byte le�do.
	 * @throws IOException si no se ha podido escribir.
	 */
	public int read() throws IOException;
	
	/**
	 * Cierra el stream de entrada.
	 * @throws IOException si ha habido alg�n problema al cerrar.
	 */
	public void close() throws IOException;
}
