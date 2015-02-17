package ins.sequential;

import cpu.ExecutionManager;
import cpu.Memory;
import cpu.OperandStack;
import ins.Instruction;

public class Flip extends Sequential {

	public Flip() {
		super();
	}

	public boolean execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		if (pila.getSize() < 2) return false;
		else {
			int temp1 = pila.desapilar();
			int temp2 = pila.desapilar();
			pila.apilar(temp1);
			pila.apilar(temp2);

			return true;
		}
	}
	
	protected Instruction crear() {
		
		return new Flip();
	}

	protected Instruction crear(int n) {
		
		return null;
	}

	public String toString() {
		return "FLIP";
	}
}
