package mv.command.debugger;

import mv.command.CommandInterpreter;
import mv.exceptions.cpuExceptions.MVError;
import mv.exceptions.cpuExceptions.NoInstructionsException;
import mv.ins.sequential.Push;
import mv.ins.sequential.Store;

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
	 * @throws NoInstructionsException 
	 */
	public void executeCommand() throws NoInstructionsException {
		cpu.step(new Push (this.valor));
		cpu.step(new Store (this.posicion));
	}

	public CommandInterpreter crear() {
		
		return null;
	}

	public CommandInterpreter crear(int n) {
		
		return null;
	}
	
	public CommandInterpreter crear (int posicion, int valor) {
		if (posicion < 0) throw new MVError ("Error: La posición debe ser igual a cero o positiva");
		
		return new Write(posicion, valor);
	}
	
	public String toString() {
		return "WRITE " + this.posicion + " " + this.valor;
	}
}
