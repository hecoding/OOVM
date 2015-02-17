package mv.ins.sequential;

import java.io.IOException;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.exceptions.cpuExceptions.HardwareException;
import mv.exceptions.insException.NoArgsException;
import mv.ins.Instruction;
import mv.mvSystem.MVSystem;

public class Out extends Sequential {
	
	public Out() {
		super();
	}

	public void execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		if (pila.esVacia()) throw new NoArgsException();
		
		try {
			MVSystem.out.write (pila.desapilar());
			
		} catch (IOException e) {
			throw new HardwareException (this, e.getMessage());
		}
	}

	protected Instruction crear() {
		
		return new Out();
	}

	protected Instruction crear(int n) {
		
		return null;
	}

	public String toString() {
		return "OUT";
	}

}
