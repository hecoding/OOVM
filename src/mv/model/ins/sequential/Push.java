package mv.model.ins.sequential;

import mv.model.cpu.ExecutionManager;
import mv.model.cpu.Memory;
import mv.model.cpu.OperandStack;
import mv.model.ins.Instruction;
import mv.model.mvsystem.in.InStream;
import mv.model.mvsystem.out.OutStream;

public class Push extends Sequential {
	private int argumento;
	
	public Push() { // tiene que estar por la lista de InstructionParser
		super();
	}
	
	public Push (int n) {
		super();
		
		this.argumento = n;
	}

	public void execute (Memory<Integer> mem, OperandStack<Integer> pila, ExecutionManager gestor, InStream in, OutStream out) {
		pila.push(this.argumento);
		
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
