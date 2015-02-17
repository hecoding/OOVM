package mv.ins.branch;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.ins.Instruction;

public class Jump extends Branch {

	public Jump() {
		super();
	}
	
	public Jump (int n) {
		super();
		this.argumento = n;
	}
	
	public void execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
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
