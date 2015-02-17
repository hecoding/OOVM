package mv.ins.comparation;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.ins.Instruction;
import mv.mvSystem.in.InStream;
import mv.mvSystem.out.OutStream;

public class EQ extends Comparation {
	
	public EQ() {
		super();
	}
	
	public void execute (Memory<Integer> mem, OperandStack<Integer> pila, ExecutionManager gestor, InStream in, OutStream out) {
		int cima = pila.pop();
		int subcima = pila.pop();

		if (subcima == cima) pila.push(1);
		else pila.push(0);
	}

	protected Instruction crear() {
		return new EQ();
	}
	
	public String toString() {
		return "EQ";
	}
}
