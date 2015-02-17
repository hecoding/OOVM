package ins.branch;

import cpu.ExecutionManager;
import cpu.Memory;
import cpu.OperandStack;
import ins.Instruction;

public class RBT extends Branch {

	public RBT() {
		super();
	}
	
	public RBT (int n) {
		super();
		this.argumento = n;
	}
	
	public boolean execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		if (pila.esVacia()) return false;
		else {
			if (pila.desapilar() != 0)
				gestor.setNextPC(gestor.getPC() + this.argumento);
			
			return true;
		}
	}

	protected Instruction crear(int n) {
		
		return new RBT (n);
	}

	public String toString() {
		return "RBT " + this.argumento;
	}
}
