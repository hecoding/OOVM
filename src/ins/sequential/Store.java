package ins.sequential;

import cpu.ExecutionManager;
import cpu.Memory;
import cpu.OperandStack;
import ins.Instruction;

public class Store extends Sequential {
	private int argumento;
	
	public Store() { // tiene que estar por la lista de InstructionParser
		super();
	}
	
	public Store(int n) {
		super();
	
		this.argumento = n;
	}

	public boolean execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		if (pila.esVacia() || this.argumento < 0) return false;
		else {
			mem.guardar(this.argumento, pila.desapilar());
			
			return true;
		}
	}

	protected Instruction crear() {
		
		return null;
	}

	protected Instruction crear(int n) {
		return new Store(n);
	}

	public String toString() {
		return "STORE " + this.argumento;
	}
}
