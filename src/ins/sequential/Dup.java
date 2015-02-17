package ins.sequential;

import cpu.ExecutionManager;
import cpu.Memory;
import cpu.OperandStack;
import ins.Instruction;

public class Dup extends Sequential {

	public Dup() {
		super();
	}

	public boolean execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		if (pila.esVacia()) return false;
		else {
			int cima = pila.desapilar();
			pila.apilar(cima);
			pila.apilar(cima);

			return true;
		}
	}
	
	protected Instruction crear() {
		
		return new Dup();
	}

	protected Instruction crear(int n) {
		
		return null;
	}

	public String toString() {
		return "DUP";
	}
}
