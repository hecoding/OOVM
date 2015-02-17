package mv.ins.sequential;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.exceptions.insException.NoArgsException;
import mv.ins.Instruction;

public class LoadInd extends Sequential {
	
	public LoadInd() {
		super();
	}

	public void execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		if (pila.esVacia()) throw new NoArgsException();
		int aux = pila.desapilar();
		if (aux < 0) throw new IllegalArgumentException("La posición a cargar debe ser positiva");
		
		pila.apilar(mem.cargar(aux));
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
