package command.normal;

import command.CommandInterpreter;

public class Step extends Normal {

	public Step() {
		super();
	}
	
	/**
	 * Ejecuta el comando step, lo cual ejecuta una instrucción.
	 */
	public boolean executeCommand() {
		
		return CommandInterpreter.cpu.step();
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