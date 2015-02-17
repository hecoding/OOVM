package ins.bool;

import cpu.ExecutionManager;
import cpu.Memory;
import cpu.OperandStack;
import ins.Instruction;

public class Not extends Bool {
	
	public Not() {
		super();
	}

	public boolean execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		if (pila.esVacia()) return false;
		else {
			int cima = pila.desapilar();
			
			if (cima == 0) pila.apilar(1);
			else pila.apilar(0);
			
			return true;
		}
	}
	
	protected Instruction crear() {
		return new Not();
	}
	
	public String toString() {
		return "NOT";
	}
}