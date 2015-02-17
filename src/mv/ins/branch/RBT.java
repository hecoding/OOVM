package mv.ins.branch;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.ins.Instruction;
import mv.mvSystem.in.InStream;
import mv.mvSystem.out.OutStream;

public class RBT extends Branch {

	public RBT() {
		super();
	}
	
	public RBT (int n) {
		super();
		this.argumento = n;
	}
	
	public void execute (Memory<Integer> mem, OperandStack<Integer> pila, ExecutionManager gestor, InStream in, OutStream out) {
		int posicion = gestor.getPC() + this.argumento;
		if (posicion < 0) throw new IllegalArgumentException("La posición a saltar debe ser positiva");
		
		if (pila.pop() != 0)
			gestor.setNextPC (posicion);
	}

	protected Instruction crear(int n) {
		
		return new RBT (n);
	}

	public String toString() {
		return "RBT " + this.argumento;
	}
}
