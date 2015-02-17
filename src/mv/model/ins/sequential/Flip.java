package mv.model.ins.sequential;

import mv.model.cpu.ExecutionManager;
import mv.model.cpu.Memory;
import mv.model.cpu.OperandStack;
import mv.model.ins.Instruction;
import mv.model.mvsystem.in.InStream;
import mv.model.mvsystem.out.OutStream;

public class Flip extends Sequential {

	public Flip() {
		super();
	}

	public void execute (Memory<Integer> mem, OperandStack<Integer> pila, ExecutionManager gestor, InStream in, OutStream out) {
		int temp1 = pila.pop();
		int temp2 = pila.pop();
		pila.push(temp1);
		pila.push(temp2);
	}
	
	protected Instruction crear() {
		
		return new Flip();
	}

	protected Instruction crear(int n) {
		
		return null;
	}

	public String toString() {
		return "FLIP";
	}
}
