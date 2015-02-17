package mv.model.ins.arithmetics;

import mv.model.cpu.ExecutionManager;
import mv.model.cpu.Memory;
import mv.model.cpu.OperandStack;
import mv.model.ins.Instruction;
import mv.model.mvsystem.in.InStream;
import mv.model.mvsystem.out.OutStream;

public class Div extends Arithmetics {

	public Div() {
		super();
	}
	
	public void execute (Memory<Integer> mem, OperandStack<Integer> pila, ExecutionManager gestor, InStream in, OutStream out) {
		int cima = pila.pop();

		if (cima == 0) {
			pila.push(cima); // De no hacer esto, nos quedamos sin cima y
									// la división tampoco se hace.

			throw new IllegalArgumentException("División por cero");
			// http://stackoverflow.com/questions/1657887/how-should-i-throw-a-divide-by-zero-exception-in-java-without-actually-dividing
		} else {
			int subcima = pila.pop();

			pila.push(subcima / cima);
		}
	}
	
	protected Instruction crear() {
		return new Div();
	}
	
	public String toString() {
		return "DIV";
	}
}

