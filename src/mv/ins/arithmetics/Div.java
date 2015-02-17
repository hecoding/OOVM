package mv.ins.arithmetics;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.ins.Instruction;
import mv.mvSystem.in.InStream;
import mv.mvSystem.out.OutStream;

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

