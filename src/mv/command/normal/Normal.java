package mv.command.normal;

import mv.command.CommandInterpreter;

/**
 * Comandos de propósito general durante la construcción del programa.
 * @author Samuel Lapuente y Héctor Laria
 *
 */
public abstract class Normal extends CommandInterpreter {
	
	/**
	 * Interpreta los comandos introducidos y de ser correctos, los crea.
	 */
	public CommandInterpreter parseCommand (String s) {
		String[] cadena = s.split(" ");
		
		if (cadena.length == 2) {
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
