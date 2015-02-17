package mv.ins.branch;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.ins.Instruction;
import mv.exceptions.insException.NoArgsException;

public class JumpInd extends Branch {

	public JumpInd() {
		super();
	}
	
	public void execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		if(pila.esVacia()) throw new NoArgsException();
		int indice = pila.desapilar();
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
