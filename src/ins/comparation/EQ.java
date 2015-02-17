package ins.comparation;

import cpu.ExecutionManager;
import cpu.Memory;
import cpu.OperandStack;
import ins.Instruction;

public class EQ extends Comparation {
	
	public EQ() {
		super();
	}
	
	public boolean execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		if (pila.getSize() < 2) return false;
		else {
			int cima = pila.desapilar();
			int subcima = pila.desapilar();

			if (subcima == cima) pila.apilar(1);
			else pila.apilar(0);

			return true;
		}
	}

	protected Instruction crear() {
		return new EQ();
	}
	
	public String toString() {
		return "EQ";
	}
}
