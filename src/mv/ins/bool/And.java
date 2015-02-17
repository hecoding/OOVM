package mv.ins.bool;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.exceptions.insException.NotEnoughArgsException;
import mv.ins.Instruction;

public class And extends Bool{

	public And() {
		super();
	}
	
	public void execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		if (pila.getSize() < 2) throw new NotEnoughArgsException();
	
		int cima = pila.desapilar();
		int subcima = pila.desapilar();

		if(subcima == cima) pila.apilar(1);
		else pila.apilar(0);
	}

	protected Instruction crear() {
		return new And();
	}
	
	public String toString() {
		return "AND";
	}

}
