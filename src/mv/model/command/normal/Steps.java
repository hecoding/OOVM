package mv.model.command.normal;

import mv.model.command.CommandInterpreter;
import mv.model.exceptions.cpuExceptions.NoInstructionsException;

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
	public void executeCommand() throws NoInstructionsException {		
		CommandInterpreter.cpu.stepn(this.argumento);	
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
