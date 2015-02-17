package ins.branch;

import cpu.ExecutionManager;
import cpu.Memory;
import cpu.OperandStack;
import ins.Instruction;

public class RBF extends Branch {

	public RBF() {
		super();
	}
	public RBF (int n) {
		super();
		this.argumento = n;
	}
	
	public boolean execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		if (pila.esVacia()) return false;
		else {
			if (pila.desapilar() == 0)
				gestor.setNextPC(gestor.getPC() + this.argumento);
			
			return true;
		}
	}

	protected Instruction crear(int n) {
		
		return new RBF (n);
	}

	public String toString() {
		return "RBF " + this.argumento;
	}
}
