package mv.model.command.debugger;

import mv.model.command.CommandInterpreter;
/**
 * Clase que hereda de commands y se usa para los comandos de debug
 * @author Samuel Lapuente y Héctor Laria
 *
 */
public abstract class Debugger extends CommandInterpreter {
	public abstract CommandInterpreter crear();
	public abstract CommandInterpreter crear(int n);
	public abstract CommandInterpreter crear (int n, int m); // también se podría hacer un crear (Integer[] args)
	
	/**
	 * Interpreta los comandos introducidos y de ser correctos, los crea.
	 */
	public CommandInterpreter parseCommand (String s) {
		String[] cadena = s.split(" ");
		
		if (cadena.length == 3) {
			String thisCadena[] = this.toString().split(" ");
			if (cadena[0].equalsIgnoreCase(thisCadena[0]))
				return crear (Integer.parseInt(cadena[1]), Integer.parseInt(cadena[2]));
		}
		else if (cadena.length == 2) {
			String thisCadena[] = this.toString().split(" ");
			if (cadena[0].equalsIgnoreCase(thisCadena[0]))
				return crear (Integer.parseInt(cadena[1]));
		}
		else if (cadena.length == 1) {
			if (cadena[0].equalsIgnoreCase(this.toString()))
				return crear();
		}
		
		return null;
	}
}
