package command;

import command.debugger.Pop;
import command.debugger.Push;
import command.debugger.Write;
import command.normal.Quit;
import command.normal.Run;
import command.normal.Step;
import command.normal.Steps;

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
		new Push(),
		new Pop(),
		new Write()
	};
	
	/**
	 * Interpreta los comandos introducidos y de ser correctos, los crea.
	 * LLama a las otras funciones para hacer esto.
	 */
	public static CommandInterpreter parse (String s){
		for (CommandInterpreter comm : commands) {
			CommandInterpreter command = comm.parseCommand(s);
			
			if (command != null)
				return command;
		}
		
		return null;
	}
}
