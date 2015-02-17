package mv.command.normal;

import mv.command.CommandInterpreter;
import mv.exceptions.cpuExceptions.NoInstructionsException;

public class Step extends Normal {

	public Step() {
		super();
	}
	
	/**
	 * Ejecuta el comando step, lo cual ejecuta una instrucción.
	 */
	public void executeCommand() throws NoInstructionsException {
		
		if (mustShowState)
			System.out.println ("Comienza la ejecución de " + cpu.getCurrentInstr());
		
		CommandInterpreter.cpu.step();
		
		if (mustShowState)
			System.out.println ("El estado de la máquina tras ejecutar la instrucción es:" + System.lineSeparator() + cpu);
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