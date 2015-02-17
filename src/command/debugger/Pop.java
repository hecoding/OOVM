package command.debugger;

import command.CommandInterpreter;

public class Pop extends Debugger {
	
	public Pop() {
		super();
	}
	/**
	 * Ejecuta un pop para debuggear durante la ejecución.
	 */
	public boolean executeCommand() {
		
		if (super.stack.esVacia()) return false;
		else {
			super.stack.desapilar();
			
			return true;
		}
	}

	public CommandInterpreter crear() {
		
		return new Pop();
	}

	public CommandInterpreter crear(int n) {
		
		return null;
	}
	
	public CommandInterpreter crear(int n, int m) {
		
		return null;
	}

	public String toString() {
		return "POP";
	}
}
