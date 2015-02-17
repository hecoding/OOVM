package mv.ins.sequential;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.ins.Instruction;
import mv.mvSystem.in.InStream;
import mv.mvSystem.out.OutStream;

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
