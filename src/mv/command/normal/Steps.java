package mv.command.normal;

import mv.command.CommandInterpreter;
import mv.exceptions.cpuExceptions.NoInstructionsException;

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
	 * Ejecuta x steps seguidos, en funci�n del argumento introducido.
	 */
	public void executeCommand() throws NoInstructionsException {
		int i = 0;
		
		while (i < this.argumento && !CommandInterpreter.cpu.getHALT()) {
			
			if (mustShowState)
				System.out.println ("Comienza la ejecuci�n de " + cpu.getCurrentInstr());
			
			CommandInterpreter.cpu.step();
			
			if (mustShowState)
				System.out.println ("El estado de la m�quina tras ejecutar la instrucci�n es:" + System.lineSeparator() + cpu);
			
			i++;
		}
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
