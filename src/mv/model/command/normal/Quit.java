package mv.model.command.normal;

import mv.model.command.CommandInterpreter;
import mv.model.exceptions.cpuExceptions.NoInstructionsException;

public class Quit extends Normal {

	public Quit() {
		super();
	}
	
	/**
	 * Ejecuta el comando quit, para cerrar la aplicación.
	 */
	public void executeCommand() throws NoInstructionsException {
		CommandInterpreter.quit = true;
	}

	public CommandInterpreter crear() {

		return new Quit();
	}
	
	public CommandInterpreter crear(int n) {
		
		return null;
	}
	
	public String toString() {
		return "QUIT";
	}

}
