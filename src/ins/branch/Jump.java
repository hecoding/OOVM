package ins.branch;

import cpu.ExecutionManager;
import cpu.Memory;
import cpu.OperandStack;
import ins.Instruction;

public class Jump extends Branch {

	public Jump() {
		super();
	}
	
	public Jump (int n) {
		super();
		this.argumento = n;
	}
	
	public boolean execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		gestor.setNextPC(this.argumento);
			
		return true;
	}

	protected Instruction crear(int n) {
		
		return new Jump (n);
	}
	
	public String toString() {
		return "JUMP " + this.argumento;
	}
}
