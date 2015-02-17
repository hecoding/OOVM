package mv.command.normal;

import mv.command.CommandInterpreter;
import mv.exceptions.cpuExceptions.NoInstructionsException;

public class Step extends Normal {

	public Step() {
		super();
	}
	
	/**
	 * Ejecuta el comando step, lo cual ejecuta una instrucción.
	 */
	public void executeCommand() throws NoInstructionsException {
		CommandInterpreter.cpu.step();
		
	}

	public CommandInterpreter crear() {
		return new Step();
	}
	
	public CommandInterpreter crear(int n) {
		
		return null;
	}
	
	public String toString() {
		return "STEP";
	}

}