package mv.model.ins.branch;

import mv.model.cpu.ExecutionManager;
import mv.model.cpu.Memory;
import mv.model.cpu.OperandStack;
import mv.model.ins.Instruction;
import mv.model.mvsystem.in.InStream;
import mv.model.mvsystem.out.OutStream;

public class RJump extends Branch {

	public RJump() {
		super();
	}
	
	public RJump (int n) {
		super();
		this.argumento = n;
	}
	
	public void execute (Memory<Integer> mem, OperandStack<Integer> pila, ExecutionManager gestor, InStream in, OutStream out) {
		int posicion = gestor.getPC() + this.argumento;
		if (posicion < 0) throw new IllegalArgumentException("La posición a saltar debe ser positiva");
		
		gestor.setNextPC (posicion);
	}

	protected Instruction crear(int n) {
		
		return new RJump (n);
	}
	
	public String toString() {
		return "RJUMP " + this.argumento;
	}
}
