package mv.ins.branch;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.ins.Instruction;
import mv.mvSystem.in.InStream;
import mv.mvSystem.out.OutStream;

public class BT extends Branch {

	public BT() {
		super();
	}
	
	public BT (int n) {
		super();
		this.argumento = n;
	}
	
	public void execute (Memory<Integer> mem, OperandStack<Integer> pila, ExecutionManager gestor, InStream in, OutStream out) {
		if (this.argumento < 0) throw new IllegalArgumentException("La posición a saltar debe ser positiva");
	
		if (pila.pop() != 0)
			gestor.setNextPC(this.argumento);

	}

	protected Instruction crear(int n) {
		
		return new BT (n);
	}

	public String toString() {
		return "BT " + this.argumento;
	}
}
