package command.normal;

import command.CommandInterpreter;

public class Run extends Step {

	public Run() {
		super();
	}
	/**
	 * Ejecuta el comando run, que se encarga de ejecutar todas las instrucciones del usuario.
	 */
	public boolean executeCommand() {
		boolean ejecutado = true;
		
		while (ejecutado && !CommandInterpreter.cpu.getHALT())
			ejecutado = CommandInterpreter.cpu.step();
		
		return ejecutado;
	}

	public CommandInterpreter crear() {
		return new Run();
	}
	
	public String toString() {
		return "RUN";
	}
}
