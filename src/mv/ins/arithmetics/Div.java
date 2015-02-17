package mv.ins.arithmetics;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.exceptions.insException.NotEnoughArgsException;
import mv.ins.Instruction;

public class Div extends Arithmetics {

	public Div() {
		super();
	}
	
	public void execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		if (pila.getSize() < 2) throw new NotEnoughArgsException();
		
		int cima = pila.desapilar();

		if (cima == 0) {
			pila.apilar(cima); // De no hacer esto, nos quedamos sin cima y
									// la división tampoco se hace.

			throw new IllegalArgumentException("División por cero");
			// http://stackoverflow.com/questions/1657887/how-should-i-throw-a-divide-by-zero-exception-in-java-without-actually-dividing
		} else {
			int subcima = pila.desapilar();

			pila.apilar(subcima / cima);
		}
	}
	
	protected Instruction crear() {
		return new Div();
	}
	
	public String toString() {
		return "DIV";
	}
}

