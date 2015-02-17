package mv.command.debugger;

import mv.command.CommandInterpreter;
import mv.exceptions.cpuExceptions.NoInstructionsException;
import mv.ins.sequential.Pop;

public class PopC extends Debugger {
	
	public PopC() {
		super();
	}
	/**
	 * Ejecuta un pop para debuggear durante la ejecución.
	 * @throws NoInstructionsException 
	 */
	public void executeCommand() throws NoInstructionsException {
		cpu.step(new Pop());
	}

	public CommandInterpreter crear() {
		
		return new PopC();
	}

	public CommandInterpreter crear(int n) {
		
		return null;
	}
	
	public CommandInterpreter crear(int n, int m) {
		
		return null;
	}

	public String toString() {
		return "POP";
	}
}
