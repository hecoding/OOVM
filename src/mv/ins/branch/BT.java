package mv.ins.branch;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.exceptions.insException.NoArgsException;
import mv.ins.Instruction;

public class BT extends Branch {

	public BT() {
		super();
	}
	
	public BT (int n) {
		super();
		this.argumento = n;
	}
	
	public void execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		if (pila.esVacia()) throw new NoArgsException();
		if (this.argumento < 0) throw new IllegalArgumentException("La posición a saltar debe ser positiva");
	
		if (pila.desapilar() != 0)
			gestor.setNextPC(this.argumento);

	}

	protected Instruction crear(int n) {
		
		return new BT (n);
	}

	public String toString() {
		return "BT " + this.argumento;
	}
}
