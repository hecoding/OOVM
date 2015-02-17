package mv.model.ins.arithmetics;

import mv.model.cpu.ExecutionManager;
import mv.model.cpu.Memory;
import mv.model.cpu.OperandStack;
import mv.model.ins.Instruction;
import mv.model.mvsystem.in.InStream;
import mv.model.mvsystem.out.OutStream;

public class Sub extends Arithmetics {
	
	public Sub() {
		super();
	}

	public void execute (Memory<Integer> mem, OperandStack<Integer> pila, ExecutionManager gestor, InStream in, OutStream out) {
		int cima = pila.pop();
		int subcima = pila.pop();

		pila.push(subcima - cima);
	}

	protected Instruction crear() {
		return new Sub();
	}
	
	public String toString() {
		return "SUB";
	}
}