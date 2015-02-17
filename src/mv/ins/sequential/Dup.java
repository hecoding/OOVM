package mv.ins.sequential;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.ins.Instruction;
import mv.mvSystem.in.InStream;
import mv.mvSystem.out.OutStream;

public class Dup extends Sequential {

	public Dup() {
		super();
	}

	public void execute (Memory<Integer> mem, OperandStack<Integer> pila, ExecutionManager gestor, InStream in, OutStream out) {
		int cima = pila.pop();
		pila.push(cima);
		pila.push(cima);
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
