package mv.model.ins.bool;

import mv.model.cpu.ExecutionManager;
import mv.model.cpu.Memory;
import mv.model.cpu.OperandStack;
import mv.model.ins.Instruction;
import mv.model.mvsystem.in.InStream;
import mv.model.mvsystem.out.OutStream;

public class And extends Bool{

	public And() {
		super();
	}
	
	public void execute (Memory<Integer> mem, OperandStack<Integer> pila, ExecutionManager gestor, InStream in, OutStream out) {
		int cima = pila.pop();
		int subcima = pila.pop();

		if(subcima == cima) pila.push(1);
		else pila.push(0);
	}

	protected Instruction crear() {
		return new And();
	}
	
	public String toString() {
		return "AND";
	}

}
