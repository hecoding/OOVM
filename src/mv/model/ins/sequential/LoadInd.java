package mv.model.ins.sequential;

import mv.model.cpu.ExecutionManager;
import mv.model.cpu.Memory;
import mv.model.cpu.OperandStack;
import mv.model.ins.Instruction;
import mv.model.mvsystem.in.InStream;
import mv.model.mvsystem.out.OutStream;

public class LoadInd extends Sequential {
	
	public LoadInd() {
		super();
	}

	public void execute (Memory<Integer> mem, OperandStack<Integer> pila, ExecutionManager gestor, InStream in, OutStream out) {
		int aux = pila.pop();
		if (aux < 0) throw new IllegalArgumentException("La posición a cargar debe ser positiva");
		
		pila.push(mem.cargar(aux));
	}

	protected Instruction crear() {
		
		return new LoadInd();
	}

	protected Instruction crear(int n) {
		
		return null;
	}

	public String toString() {
		return "LOADIND";
	}
}
