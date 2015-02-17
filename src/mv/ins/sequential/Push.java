package mv.ins.sequential;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.ins.Instruction;

public class Push extends Sequential {
	private int argumento;
	
	public Push() { // tiene que estar por la lista de InstructionParser
		super();
	}
	
	public Push (int n) {
		super();
		
		this.argumento = n;
	}

	public void execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		pila.apilar(this.argumento);
		
	}
	
	protected Instruction crear() {
		
		return null;
	}

	protected Instruction crear(int n) {
		return new Push(n);
	}

	public String toString() {
		return "PUSH " + this.argumento;
	}
}
