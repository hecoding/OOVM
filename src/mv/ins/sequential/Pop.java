package mv.ins.sequential;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.exceptions.insException.NoArgsException;
import mv.ins.Instruction;

public class Pop extends Sequential {
	
	public Pop() {
		super();
	}

	public void execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		if (pila.esVacia()) throw new NoArgsException();
		
		pila.desapilar();
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
