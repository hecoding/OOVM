package command.debugger;

import command.CommandInterpreter;

public class Write extends Debugger {
	private int posicion;
	private int valor;

	public Write() {
		super();
	}
	
	public Write (int posicion, int valor) {
		super();
		this.posicion = posicion;
		this.valor = valor;
	}
	
	/**
	 * Ejecuta el comando write para modificar la memoria en ejecución.
	 */
	public boolean executeCommand() {
		if (this.posicion < 0) return false;
		else {
			super.memory.guardar(posicion, valor);
			return true;
		}
	}

	public CommandInterpreter crear() {
		
		return null;
	}

	public CommandInterpreter crear(int n) {
		
		return null;
	}
	
	public CommandInterpreter crear (int posicion, int valor) {
		if (posicion < 0) return null;
		else {
			return new Write(posicion, valor);
		}
	}
	
	public String toString() {
		return "WRITE " + this.posicion + " " + this.valor;
	}
}
