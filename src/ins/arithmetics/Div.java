package ins.arithmetics;

import cpu.ExecutionManager;
import cpu.Memory;
import cpu.OperandStack;
import ins.Instruction;

public class Div extends Arithmetics {

	public Div() {
		super();
	}
	
	public boolean execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		if (pila.getSize() < 2) return false;
		else {
			int cima = pila.desapilar();

			if (cima == 0) {
				pila.apilar(cima); // De no hacer esto, nos quedamos sin cima y
									// la división tampoco se hace.

				return false;
			} else {
				int subcima = pila.desapilar();

				pila.apilar(subcima / cima);
				return true;
			}
		}
	}
	
	protected Instruction crear() {
		return new Div();
	}
	
	public String toString() {
		return "DIV";
	}
}

