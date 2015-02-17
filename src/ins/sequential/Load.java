package ins.sequential;

import cpu.ExecutionManager;
import cpu.Memory;
import cpu.OperandStack;
import ins.Instruction;

public class Load extends Sequential {
	private int argumento;
	
	public Load() { // tiene que estar por la lista de InstructionParser
		super();
	}
	
	public Load(int n) {
		super();
		
		this.argumento = n;
	}

	public boolean execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		if (this.argumento < 0) return false;
		else {
			pila.apilar(mem.cargar(this.argumento));
			return true;
		}
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
