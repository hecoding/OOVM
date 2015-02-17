package mv.command;

import mv.cpu.CPU;
import mv.exceptions.cpuExceptions.NoInstructionsException;

public abstract class CommandInterpreter {
	protected static CPU cpu;
	protected static boolean quit;
	protected static boolean mustShowState;
	
	public abstract void executeCommand() throws NoInstructionsException;
	public abstract CommandInterpreter crear();
	public abstract CommandInterpreter crear (int n);
	public abstract CommandInterpreter parseCommand (String s);
	
	/**
	 * Linkea una instancia de cpu con un atributo.
	 * @param c parámetro privado de cpu.
	 */
	public static void configureCommandInterpreter (CPU c, boolean interactiveMode) { // cpu entera, para poder acceder a step
		cpu = c;
		mustShowState = interactiveMode;
	}
	
	public static boolean isQuit() {
		return quit;
	}
	
}
