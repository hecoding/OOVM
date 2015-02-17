package mv.ins.sequential;

import java.io.IOException;

import mv.cpu.CPU;
import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.exceptions.cpuExceptions.MVError;
import mv.ins.Instruction;
import mv.mvSystem.in.InStream;
import mv.mvSystem.out.OutStream;

public class In extends Sequential {
	
	public In() {
		super();
	}
	
	public void execute (CPU cpu) {
		try {
			cpu.getOperandStack().push(cpu.getInStream().read());
		} catch (IOException e) {
			throw new MVError (this, e.getMessage());
		}

	}

	public void execute (Memory<Integer> mem, OperandStack<Integer> pila, ExecutionManager gestor, InStream in, OutStream out) {
		int entrada;
		try {
			entrada = in.read();
		} catch (IOException e) {
			throw new MVError (this, e.getMessage());
		}
		if (entrada == '\n')
			pila.push(-1); // carácter de final de línea
		
		pila.push((int) entrada);
	}

	protected Instruction crear() {
		
		return new In();
	}

	protected Instruction crear(int n) {
		
		return null;
	}

	public String toString() {
		return "IN";
	}
	
}
