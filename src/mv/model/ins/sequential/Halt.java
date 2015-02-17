package mv.model.ins.sequential;

import mv.model.cpu.ExecutionManager;
import mv.model.cpu.Memory;
import mv.model.cpu.OperandStack;
import mv.model.ins.Instruction;
import mv.model.mvsystem.in.InStream;
import mv.model.mvsystem.out.OutStream;

public class Halt extends Sequential {

	public Halt() {
		super();
	}

	public void execute (Memory<Integer> mem, OperandStack<Integer> pila, ExecutionManager gestor, InStream in, OutStream out) {
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
