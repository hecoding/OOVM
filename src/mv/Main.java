package mv;
import java.util.Scanner;
/**
 * @author Samuel Lapuente y Héctor Laria. 2º A
 */

public class Main {

	/**
	 * Se encarga de la ejecución y la salida por consola.
	 * Se encarga también de interpretar los errores del usuario.
	 * @param args Argumentos de entrada.
	 */
	public static void main(String[] args) {
		CPU cpu = new CPU();
		Scanner sc = new Scanner(System.in);
		Instruction instruccion;
		String cadena;
		
		do {
			System.out.println("Introduzca una instrucción: ");
			cadena = sc.nextLine();
			
			instruccion = InstructionParser.parse(cadena);
			
			if (instruccion == null)
				System.out.println("Error: Instrucción incorrecta");
			else {
				System.out.println("Comienza la ejecución de " + instruccion);
				
				if (!cpu.execute(instruccion))
					System.out.println("Error en la ejecución de la instrucción");
				else
					System.out.println("El estado de la máquina tras ejecutar la instrucción es:" + System.lineSeparator() + cpu);
			}
		} while (!cpu.getHALT());

		sc.close();
	}
}