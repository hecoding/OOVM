package mv.ins.branch;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.ins.Instruction;
import mv.mvSystem.in.InStream;
import mv.mvSystem.out.OutStream;

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
