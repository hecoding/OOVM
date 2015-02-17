package command.normal;

import command.CommandInterpreter;

public class Quit extends Normal {

	public Quit() {
		super();
	}
	
	/**
	 * Ejecuta el comando quit, para cerrar la aplicaci�n.
	 */
	public boolean executeCommand() {
		CommandInterpreter.quit = true;
		
		return true;
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
