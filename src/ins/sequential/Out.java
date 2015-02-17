package ins.sequential;

import cpu.ExecutionManager;
import cpu.Memory;
import cpu.OperandStack;
import ins.Instruction;

public class Out extends Sequential {
	
	public Out() {
		super();
	}

	public boolean execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		if (pila.esVacia()) return false;
		else {
			System.out.println((char)pila.desapilar());
		
			return true;
		}
	}

	protected Instruction crear() {
		
		return new Out();
	}

	protected Instruction crear(int n) {
		
		return null;
	}

	public String toString() {
		return "OUT";
	}

}
