package ins.branch;

import cpu.ExecutionManager;
import cpu.Memory;
import cpu.OperandStack;
import ins.Instruction;

public class BT extends Branch {

	public BT() {
		super();
	}
	
	public BT (int n) {
		super();
		this.argumento = n;
	}
	
	public boolean execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		if (pila.esVacia()) return false;
		else {
			if (pila.desapilar() != 0)
				gestor.setNextPC(this.argumento);
			
			return true;
		}
	}

	protected Instruction crear(int n) {
		
		return new BT (n);
	}

	public String toString() {
		return "BT " + this.argumento;
	}
}
