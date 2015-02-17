package mv.ins.comparation;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.exceptions.insException.NotEnoughArgsException;
import mv.ins.Instruction;

public class LT extends Comparation {
	
	public LT() {
		super();
	}

	public void execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		if (pila.getSize() < 2) throw new NotEnoughArgsException();

		int cima = pila.desapilar();
		int subcima = pila.desapilar();

		if (subcima < cima) pila.apilar(1);
		else pila.apilar(0);
	}
	
	protected Instruction crear() {
		return new LT();
	}
	
	public String toString() {
		return "LT";
	}
}