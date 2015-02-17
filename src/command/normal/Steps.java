package command.normal;

import command.CommandInterpreter;

public class Steps extends Step {
	private int argumento;
	
	public Steps() {
		super();
	}
	
	public Steps (int n) {
		super();
		this.argumento = n;
	}
	
	/**
	 * Ejecuta x steps seguidos, en función del argumento introducido.
	 */
	public boolean executeCommand() {
		int i = 0;
		boolean ejecutado = true;
		
		while (i < this.argumento && ejecutado && !CommandInterpreter.cpu.getHALT()) {
			ejecutado = CommandInterpreter.cpu.step();
			i++;
		}
		
		return ejecutado;
	}
	
	public CommandInterpreter crear() {
		return null;
	}

	public CommandInterpreter crear (int n) {
		if (n <= 0)
			return null;
		else
			return new Steps(n);
	}
	
	public String toString() {
		return "STEP " + this.argumento;
	}
	
}
