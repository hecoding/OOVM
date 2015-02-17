package mv.ins.bool;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.exceptions.insException.NoArgsException;
import mv.ins.Instruction;

public class Not extends Bool {
	
	public Not() {
		super();
	}

	public void execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		if (pila.esVacia()) throw new NoArgsException();

		int cima = pila.desapilar();
			
		if (cima == 0) pila.apilar(1);
		else pila.apilar(0);
	}
	
	protected Instruction crear() {
		return new Not();
	}
	
	public String toString() {
		return "NOT";
	}
}