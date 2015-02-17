package command;

import cpu.CPU;

public abstract class CommandInterpreter {
	protected static CPU cpu;
	protected static boolean quit;
	
	public abstract boolean executeCommand();
	public abstract CommandInterpreter crear();
	public abstract CommandInterpreter crear (int n);
	public abstract CommandInterpreter parseCommand (String s);
	
	/**
	 * Linkea una instancia de cpu con un atributo.
	 * @param c parámetro privado de cpu.
	 */
	public static void configureCommandInterpreter (CPU c) { // cpu entera, para poder acceder a step
		cpu = c;
	}
	
	public static boolean isQuit() {
		return quit;
	}
	
}
