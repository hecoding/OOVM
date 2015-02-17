package mv.ins.bool;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.ins.Instruction;
import mv.mvSystem.in.InStream;
import mv.mvSystem.out.OutStream;

public class Not extends Bool {
	
	public Not() {
		super();
	}

	public void execute (Memory<Integer> mem, OperandStack<Integer> pila, ExecutionManager gestor, InStream in, OutStream out) {
		int cima = pila.pop();
			
		if (cima == 0) pila.push(1);
		else pila.push(0);
	}
	
	protected Instruction crear() {
		return new Not();
	}
	
	public String toString() {
		return "NOT";
	}
}