package mv.ins.sequential;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.ins.Instruction;

public class Load extends Sequential {
	private int argumento;
	
	public Load() { // tiene que estar por la lista de InstructionParser
		super();
	}
	
	public Load(int n) {
		super();
		
		this.argumento = n;
	}

	public void execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		if (this.argumento < 0) throw new IllegalArgumentException("La posición a cargar debe ser positiva");
		
		pila.apilar(mem.cargar(this.argumento));
	}

	protected Instruction crear() {
		
		return null;
	}

	protected Instruction crear(int n) {
		
		return new Load(n);
	}

	public String toString() {
		return "LOAD " + this.argumento;
	}
}
