package mv.model.ins.sequential;

import mv.model.cpu.ExecutionManager;
import mv.model.cpu.Memory;
import mv.model.cpu.OperandStack;
import mv.model.ins.Instruction;
import mv.model.mvsystem.in.InStream;
import mv.model.mvsystem.out.OutStream;

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
