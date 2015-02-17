package ins.sequential;

import cpu.ExecutionManager;
import cpu.Memory;
import cpu.OperandStack;
import ins.Instruction;

public class Halt extends Sequential {

	public Halt() {
		super();
	}

	public boolean execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		gestor.setHalt(true);
		
		return true;
	}
	
	protected Instruction crear() {
		
		return new Halt();
	}

	protected Instruction crear(int n) {
		
		return null;
	}

	public String toString() {
		return "HALT";
	}
}
