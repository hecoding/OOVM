package command.normal;

import command.CommandInterpreter;

/**
 * Comandos de propósito general durante la consstrucción del programa.
 * @author Samuel Lapuente y Héctor Laria
 *
 */
public abstract class Normal extends CommandInterpreter {

	public abstract boolean executeCommand();
	public abstract CommandInterpreter crear();
	public abstract CommandInterpreter crear (int n);
	
	/**
	 * Interpreta los comandos introducidos y de ser correctos, los crea.
	 */
	public CommandInterpreter parseCommand (String s) {
		String[] cadena = s.split(" ");
		
		if (cadena.length == 2) {
			String thisCadena[] = this.toString().split(" ");
			if (cadena[0].equalsIgnoreCase(thisCadena[0]) && esNum(cadena[1]))
				return crear (Integer.parseInt(cadena[1]));
		}
		else if (cadena.length == 1) {
			if (cadena[0].equalsIgnoreCase(this.toString()))
				return crear();
		}
		
		return null;
	}
	
	private static boolean esNum(String s) {
		char[] numeroChar = new char[s.length()];
		boolean esNumero = true;
		int i = 0;
		
		numeroChar = s.toCharArray();	
		if (numeroChar[0] == '-' || numeroChar[0] == '+') i++;
		
		while (i < numeroChar.length && esNumero) {
			if (numeroChar[i] < '0' || numeroChar[i] > '9')
				esNumero = false;
			i++;
		}
		
		return esNumero;
	}
}
