package mv.ins.sequential;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.exceptions.insException.NotEnoughArgsException;
import mv.ins.Instruction;

public class Flip extends Sequential {

	public Flip() {
		super();
	}

	public void execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		if (pila.getSize() < 2) throw new NotEnoughArgsException();
		
		int temp1 = pila.desapilar();
		int temp2 = pila.desapilar();
		pila.apilar(temp1);
		pila.apilar(temp2);
	}
	
	protected Instruction crear() {
		
		return new Flip();
	}

	protected Instruction crear(int n) {
		
		return null;
	}

	public String toString() {
		return "FLIP";
	}
}
