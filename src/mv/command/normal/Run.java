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
				System.out.println ("Comienza la ejecución de " + cpu.getCurrentInstr());
			
			CommandInterpreter.cpu.step();
			
			if (mustShowState)
				System.out.println ("El estado de la máquina tras ejecutar la instrucción es:" + System.lineSeparator() + cpu);	
		}
	}

	public CommandInterpreter crear() {
		return new Run();
	}
	
	public String toString() {
		return "RUN";
	}
}
