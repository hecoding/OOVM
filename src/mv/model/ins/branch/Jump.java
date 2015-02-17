package mv.model.ins.branch;

import mv.model.cpu.ExecutionManager;
import mv.model.cpu.Memory;
import mv.model.cpu.OperandStack;
import mv.model.ins.Instruction;
import mv.model.mvsystem.in.InStream;
import mv.model.mvsystem.out.OutStream;

public class Jump extends Branch {

	public Jump() {
		super();
	}
	
	public Jump (int n) {
		super();
		this.argumento = n;
	}
	
	public void execute (Memory<Integer> mem, OperandStack<Integer> pila, ExecutionManager gestor, InStream in, OutStream out) {
		if (this.argumento < 0) throw new IllegalArgumentException("La posición a saltar debe ser positiva");
		
		gestor.setNextPC(this.argumento);
	}

	protected Instruction crear(int n) {
		
		return new Jump (n);
	}
	
	public String toString() {
		return "JUMP " + this.argumento;
	}
}
