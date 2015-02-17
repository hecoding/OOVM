package mv.ins.sequential;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.ins.Instruction;

public class Halt extends Sequential {

	public Halt() {
		super();
	}

	public void execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		gestor.setHalt(true);
		
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
