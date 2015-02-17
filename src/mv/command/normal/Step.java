package mv.command.normal;

import mv.command.CommandInterpreter;
import mv.exceptions.cpuExceptions.NoInstructionsException;

public class Step extends Normal {

	public Step() {
		super();
	}
	
	/**
	 * Ejecuta el comando step, lo cual ejecuta una instrucci�n.
	 */
	public void executeCommand() throws NoInstructionsException {
		
		if (mustShowState)
			System.out.println ("Comienza la ejecuci�n de " + cpu.getCurrentInstr());
		
		CommandInterpreter.cpu.step();
		
		if (mustShowState)
			System.out.println ("El estado de la m�quina tras ejecutar la instrucci�n es:" + System.lineSeparator() + cpu);
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