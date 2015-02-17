package mv.model.ins.sequential;

import mv.model.cpu.ExecutionManager;
import mv.model.cpu.Memory;
import mv.model.cpu.OperandStack;
import mv.model.ins.Instruction;
import mv.model.mvsystem.in.InStream;
import mv.model.mvsystem.out.OutStream;

public class Store extends Sequential {
	private int argumento;
	
	public Store() { // tiene que estar por la lista de InstructionParser
		super();
	}
	
	public Store(int n) {
		super();
	
		this.argumento = n;
	}

	public void execute (Memory<Integer> mem, OperandStack<Integer> pila, ExecutionManager gestor, InStream in, OutStream out) {
		if (this.argumento < 0) throw new IllegalArgumentException("La posición donde guardar debe ser positiva");
		
		mem.guardar(this.argumento, pila.pop());
	}

	protected Instruction crear() {
		
		return null;
	}

	protected Instruction crear(int n) {
		return new Store(n);
	}

	public String toString() {
		return "STORE " + this.argumento;
	}
}
