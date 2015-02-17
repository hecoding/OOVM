package mv.mvSystem.out;

import java.io.IOException;

/**
 * Interfaz que se encarga de la salida de la máquina virtual.
 * @author Samuel Lapuente y Héctor Laria
 *
 */
public interface OutStrategy {
	/**
	 * Abre el stream de salida.
	 * @throws IOException si ha habido algún problema al abrir.
	 */
	public void open() throws IOException;
	
	/**
	 * Escribe el byte en el stream. (entero convertido a carácter)
	 * @param x La representación en entero del carácter a escribir.
	 * @throws IOException si no se ha podido escribir.
	 */
	public void write(int x) throws IOException;
	
	/**
	 * Cierra el stream de salida.
	 * @throws IOException si ha habido algún problema al cerrar.
	 */
	public void close() throws IOException;
}
