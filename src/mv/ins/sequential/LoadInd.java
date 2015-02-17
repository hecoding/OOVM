package mv.ins.sequential;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.ins.Instruction;
import mv.mvSystem.in.InStream;
import mv.mvSystem.out.OutStream;

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
