package mv.ins.sequential;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.exceptions.insException.NoArgsException;
import mv.ins.Instruction;

public class Store extends Sequential {
	private int argumento;
	
	public Store() { // tiene que estar por la lista de InstructionParser
		super();
	}
	
	public Store(int n) {
		super();
	
		this.argumento = n;
	}

	public void execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		if (pila.esVacia()) throw new NoArgsException();
		if (this.argumento < 0) throw new IllegalArgumentException("La posición donde guardar debe ser positiva");
		
		mem.guardar(this.argumento, pila.desapilar());
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
