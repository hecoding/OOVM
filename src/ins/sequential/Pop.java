package ins.sequential;

import cpu.ExecutionManager;
import cpu.Memory;
import cpu.OperandStack;
import ins.Instruction;

public class Pop extends Sequential {
	
	public Pop() {
		super();
	}

	public boolean execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		if (pila.esVacia()) return false;
		else {
			pila.desapilar();
			
			return true;
		}
	}

	protected Instruction crear() {
		
		return new Pop();
	}
	
	protected Instruction crear(int n) {
		
		return null;
	}

	public String toString() {
		return "POP";
	}
}
