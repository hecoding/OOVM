package mv.mvSystem.out;

import java.io.IOException;

/**
 * Interfaz que se encarga de la salida de la m�quina virtual.
 * @author Samuel Lapuente y H�ctor Laria
 *
 */
public interface OutStrategy {
	/**
	 * Abre el stream de salida.
	 * @throws IOException si ha habido alg�n problema al abrir.
	 */
	public void open() throws IOException;
	
	/**
	 * Escribe el byte en el stream. (entero convertido a car�cter)
	 * @param x La representaci�n en entero del car�cter a escribir.
	 * @throws IOException si no se ha podido escribir.
	 */
	public void write(int x) throws IOException;
	
	/**
	 * Cierra el stream de salida.
	 * @throws IOException si ha habido alg�n problema al cerrar.
	 */
	public void close() throws IOException;
}
