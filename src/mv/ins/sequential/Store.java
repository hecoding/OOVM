package mv.ins.sequential;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.ins.Instruction;
import mv.mvSystem.in.InStream;
import mv.mvSystem.out.OutStream;

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
