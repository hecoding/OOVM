package mv.model.ins.branch;

import mv.model.cpu.ExecutionManager;
import mv.model.cpu.Memory;
import mv.model.cpu.OperandStack;
import mv.model.ins.Instruction;
import mv.model.mvsystem.in.InStream;
import mv.model.mvsystem.out.OutStream;

public class JumpInd extends Branch {

	public JumpInd() {
		super();
	}
	
	public void execute (Memory<Integer> mem, OperandStack<Integer> pila, ExecutionManager gestor, InStream in, OutStream out) {
		int indice = pila.pop();
		if (indice < 0) throw new IllegalArgumentException("La posición a saltar debe ser positiva");
		
		gestor.setNextPC (indice);	
	}

	protected Instruction crear() {
		
		return new JumpInd();
	}
	
	protected Instruction crear(int n) {
		
		return null;
	}
	
	public String toString() {
		return "JUMPIND";
	}
}
