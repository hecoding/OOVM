package mv.model.ins.sequential;

import mv.model.cpu.ExecutionManager;
import mv.model.cpu.Memory;
import mv.model.cpu.OperandStack;
import mv.model.ins.Instruction;
import mv.model.mvsystem.in.InStream;
import mv.model.mvsystem.out.OutStream;

public class Load extends Sequential {
	private int argumento;
	
	public Load() { // tiene que estar por la lista de InstructionParser
		super();
	}
	
	public Load(int n) {
		super();
		
		this.argumento = n;
	}

	public void execute (Memory<Integer> mem, OperandStack<Integer> pila, ExecutionManager gestor, InStream in, OutStream out) {
		if (this.argumento < 0) throw new IllegalArgumentException("La posición a cargar debe ser positiva");
		
		pila.push(mem.cargar(this.argumento));
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
