package mv;
import java.util.Scanner;
/**
 * @author Samuel Lapuente y H�ctor Laria. 2� A
 */

public class Main {

	/**
	 * Se encarga de la ejecuci�n y la salida por consola.
	 * Se encarga tambi�n de interpretar los errores del usuario.
	 * @param args Argumentos de entrada.
	 */
	public static void main(String[] args) {
		CPU cpu = new CPU();
		Scanner sc = new Scanner(System.in);
		Instruction instruccion;
		String cadena;
		
		do {
			System.out.println("Introduzca una instrucci�n: ");
			cadena = sc.nextLine();
			
			instruccion = InstructionParser.parse(cadena);
			
			if (instruccion == null)
				System.out.println("Error: Instrucci�n incorrecta");
			else {
				System.out.println("Comienza la ejecuci�n de " + instruccion);
				
				if (!cpu.execute(instruccion))
					System.out.println("Error en la ejecuci�n de la instrucci�n");
				else
					System.out.println("El estado de la m�quina tras ejecutar la instrucci�n es:" + System.lineSeparator() + cpu);
			}
		} while (!cpu.getHALT());

		sc.close();
	}
}