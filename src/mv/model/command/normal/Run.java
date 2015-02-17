package mv.model.command.normal;

import mv.model.command.CommandInterpreter;
import mv.model.exceptions.cpuExceptions.NoInstructionsException;

public class Run extends Step {

	public Run() {
		super();
	}
	
	/**
	 * Ejecuta el comando run, que se encarga de ejecutar todas las instrucciones del usuario.
	 */
	public void executeCommand() throws NoInstructionsException {
		cpu.run();
	}

	public CommandInterpreter crear() {
		return new Run();
	}
	
	public String toString() {
		return "RUN";
	}
}
