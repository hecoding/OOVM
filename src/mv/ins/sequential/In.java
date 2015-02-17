package mv.ins.sequential;

import java.io.IOException;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.exceptions.cpuExceptions.HardwareException;
import mv.ins.Instruction;
import mv.mvSystem.MVSystem;

public class In extends Sequential {
	
	public In() {
		super();
	}

	public void execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		int entrada;
		try {
			entrada = MVSystem.in.read();
		} catch (IOException e) {
			throw new HardwareException (this, e.getMessage());
		}
		if (entrada == '\n') pila.apilar(-1); // carácter de final de línea
		
		pila.apilar((int) entrada);
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
