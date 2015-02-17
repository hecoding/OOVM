package command.debugger;

import command.CommandInterpreter;

public class Push extends Debugger {
	private int argumento;
	
	public Push() {
		super();
	}
	
	public Push (int n) {
		super();
		this.argumento = n;
	}
	
	/**
	 * Ejecuta el comando push para debuggear en ejecución.
	 */
	public boolean executeCommand() {
		super.stack.apilar(this.argumento);
		
		return true;
	}

	public CommandInterpreter crear() {
		
		return null;
	}

	public CommandInterpreter crear(int n) {
		return new Push(n);
	}
	
	public CommandInterpreter crear(int n, int m) {
		
		return null;
	}
	
	public String toString() {
		return "PUSH " + this.argumento;
	}
}
