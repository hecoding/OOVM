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
		while (!CommandInterpreter.cpu.getHALT()) {
			
			if (mustShowState)
				System.out.println ("Comienza la ejecuci�n de " + cpu.getCurrentInstr());
			
			CommandInterpreter.cpu.step();
			
			if (mustShowState)
				System.out.println ("El estado de la m�quina tras ejecutar la instrucci�n es:" + System.lineSeparator() + cpu);	
		}
	}

	public CommandInterpreter crear() {
		return new Run();
	}
	
	public String toString() {
		return "RUN";
	}
}
