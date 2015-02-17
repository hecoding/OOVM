package mv.command;

import mv.command.debugger.PopC;
import mv.command.debugger.PushC;
import mv.command.debugger.Write;
import mv.command.normal.Quit;
import mv.command.normal.Run;
import mv.command.normal.Step;
import mv.command.normal.Steps;
import mv.exceptions.CPUParserException;

/**
 * Clase encargada de parsear los comandos
 * @author Samuel Lapuente y Héctor Laria
 *
 */
public class CommandParser {
	private static CommandInterpreter[] commands = {
		new Run(),
		new Quit(),
		new Step(),
		new Steps(),
		new PushC(),
		new PopC(),
		new Write()
	};
	
	/**
	 * Interpreta los comandos introducidos y de ser correctos, los crea.
	 * LLama a las otras funciones para hacer esto.
	 * @param s String a parsear.
	 * @return Un comando si se puede parsear la entrada, null en el caso contrario.
	 * @throws CPUParserException CPUParserException Si el string no contiene una instrucción parseable
	 */
	public static CommandInterpreter parse (String s) throws CPUParserException{
		try {
			for (CommandInterpreter comm : commands) {
				CommandInterpreter command = comm.parseCommand(s);
				
				if (command != null)
					return command;
			}
		} catch (NumberFormatException e) {
			throw new CPUParserException ("los argumentos deben ser números");
		}
		
		throw new CPUParserException();
	}
}
