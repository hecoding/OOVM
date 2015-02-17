package mv.ins.sequential;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.exceptions.insException.NotEnoughArgsException;
import mv.ins.Instruction;

public class StoreInd extends Sequential {
	
	public StoreInd() {
		super();
	}

	public void execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		if (pila.getSize() < 2) throw new NotEnoughArgsException();
		int dato = pila.desapilar();
		int puntero = pila.desapilar();
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
