package mv.command.normal;

import mv.command.CommandInterpreter;
import mv.exceptions.cpuExceptions.NoInstructionsException;

public class Run extends Step {

	public Run() {
		super();
	}
	
	/**
	 * Ejecuta el comando run, que se encarga de ejecutar todas las instrucciones del usuario.
	 */
	public void executeCommand() throws NoInstructionsException {
		if (mustShowState)
			cpu.verboseRun();
		else
			cpu.run();
	}

	public CommandInterpreter crear() {
		return new Run();
	}
	
	public String toString() {
		return "RUN";
	}
}
