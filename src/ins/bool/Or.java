package ins.bool;

import cpu.ExecutionManager;
import cpu.Memory;
import cpu.OperandStack;
import ins.Instruction;

public class Or extends Bool {
	
	public Or() {
		super();
	}

	public boolean execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		if (pila.getSize() < 2) return false;
		else {
			int cima = pila.desapilar();
			int subcima = pila.desapilar();

			if ((subcima + cima) != 0) pila.apilar(1);
			else pila.apilar(0);
			
			return true;
		}
	}

	protected Instruction crear() {
		return new Or();
	}
	
	public String toString() {
		return "OR";
	}
}