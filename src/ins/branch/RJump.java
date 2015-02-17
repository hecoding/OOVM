package ins.branch;

import cpu.ExecutionManager;
import cpu.Memory;
import cpu.OperandStack;
import ins.Instruction;

public class RJump extends Branch {

	public RJump() {
		super();
	}
	
	public RJump (int n) {
		super();
		this.argumento = n;
	}
	
	public boolean execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		gestor.setNextPC(gestor.getPC() + this.argumento);
			
		return true;
	}

	protected Instruction crear(int n) {
		
		return new RJump (n);
	}
	
	public String toString() {
		return "RJUMP " + this.argumento;
	}
}
