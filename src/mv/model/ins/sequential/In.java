package mv.model.ins.sequential;

import java.io.IOException;

import mv.model.cpu.CPU;
import mv.model.cpu.ExecutionManager;
import mv.model.cpu.Memory;
import mv.model.cpu.OperandStack;
import mv.model.exceptions.cpuExceptions.MVError;
import mv.model.ins.Instruction;
import mv.model.mvsystem.in.InStream;
import mv.model.mvsystem.out.OutStream;

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
