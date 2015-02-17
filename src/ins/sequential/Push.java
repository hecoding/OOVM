package ins.sequential;

import cpu.ExecutionManager;
import cpu.Memory;
import cpu.OperandStack;
import ins.Instruction;

public class Push extends Sequential {
	private int argumento;
	
	public Push() { // tiene que estar por la lista de InstructionParser
		super();
	}
	
	public Push (int n) {
		super();
		
		this.argumento = n;
	}

	public boolean execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		pila.apilar(this.argumento);
		
		return true;
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
