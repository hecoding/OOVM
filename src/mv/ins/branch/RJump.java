package mv.ins.branch;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.ins.Instruction;

public class RJump extends Branch {

	public RJump() {
		super();
	}
	
	public RJump (int n) {
		super();
		this.argumento = n;
	}
	
	public void execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
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
