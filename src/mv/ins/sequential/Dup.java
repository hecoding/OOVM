package mv.ins.sequential;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.exceptions.insException.NoArgsException;
import mv.ins.Instruction;

public class Dup extends Sequential {

	public Dup() {
		super();
	}

	public void execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		if (pila.esVacia()) throw new NoArgsException();
		
		int cima = pila.desapilar();
		pila.apilar(cima);
		pila.apilar(cima);
	}
	
	protected Instruction crear() {
		
		return new Dup();
	}

	protected Instruction crear(int n) {
		
		return null;
	}

	public String toString() {
		return "DUP";
	}
}
