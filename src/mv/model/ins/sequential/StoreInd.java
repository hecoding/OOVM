package mv.model.ins.sequential;

import mv.model.cpu.ExecutionManager;
import mv.model.cpu.Memory;
import mv.model.cpu.OperandStack;
import mv.model.ins.Instruction;
import mv.model.mvsystem.in.InStream;
import mv.model.mvsystem.out.OutStream;

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
