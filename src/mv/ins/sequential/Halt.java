package mv.ins.sequential;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.ins.Instruction;
import mv.mvSystem.in.InStream;
import mv.mvSystem.out.OutStream;

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
