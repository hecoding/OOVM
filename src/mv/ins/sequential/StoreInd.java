package mv.ins.sequential;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.ins.Instruction;
import mv.mvSystem.in.InStream;
import mv.mvSystem.out.OutStream;

public class StoreInd extends Sequential {
	
	public StoreInd() {
		super();
	}

	public void execute (Memory<Integer> mem, OperandStack<Integer> pila, ExecutionManager gestor, InStream in, OutStream out) {
		int dato = pila.pop();
		int puntero = pila.pop();
		if (puntero < 0) throw new IllegalArgumentException("La posición donde guardar debe ser positiva");
		
		mem.guardar(puntero, dato);
	}

	protected Instruction crear() {
		
		return new StoreInd();
	}

	protected Instruction crear(int n) {
		return null;
	}

	public String toString() {
		return "STOREIND";
	}

}
