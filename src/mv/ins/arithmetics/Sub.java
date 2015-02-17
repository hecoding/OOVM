package mv.ins.arithmetics;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.exceptions.insException.NotEnoughArgsException;
import mv.ins.Instruction;

public class Sub extends Arithmetics {
	
	public Sub() {
		super();
	}

	public void execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		if (pila.getSize() < 2) throw new NotEnoughArgsException();
		else {
			int cima = pila.desapilar();
			int subcima = pila.desapilar();
			
			pila.apilar(subcima - cima);
		}
	}

	protected Instruction crear() {
		return new Sub();
	}
	
	public String toString() {
		return "SUB";
	}
}