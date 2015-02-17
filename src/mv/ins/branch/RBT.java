package mv.ins.branch;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.exceptions.insException.NoArgsException;
import mv.ins.Instruction;

public class RBT extends Branch {

	public RBT() {
		super();
	}
	
	public RBT (int n) {
		super();
		this.argumento = n;
	}
	
	public void execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		if (pila.esVacia()) throw new NoArgsException();
		int posicion = gestor.getPC() + this.argumento;
		if (posicion < 0) throw new IllegalArgumentException("La posición a saltar debe ser positiva");
		
		if (pila.desapilar() != 0)
			gestor.setNextPC (posicion);
	}

	protected Instruction crear(int n) {
		
		return new RBT (n);
	}

	public String toString() {
		return "RBT " + this.argumento;
	}
}
