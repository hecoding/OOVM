package mv.mvSystem.in;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Interfaz que se encarga de la entrada de la máquina virtual.
 * @author Samuel Lapuente y Héctor Laria
 *
 */
public interface InStrategy {
	/**
	 * Abre el stream de entrada.
	 * @throws FileNotFoundException
	 * @throws IOException si ha habido algún problema al abrir.
	 */
	public void open() throws FileNotFoundException, IOException;
	
	/**
	 * Lee un byte del stream de entrada.
	 * @return el byte leído.
	 * @throws IOException si no se ha podido escribir.
	 */
	public int read() throws IOException;
	
	/**
	 * Cierra el stream de entrada.
	 * @throws IOException si ha habido algún problema al cerrar.
	 */
	public void close() throws IOException;
}
