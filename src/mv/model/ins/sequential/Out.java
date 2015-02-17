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

public class Out extends Sequential {
	
	public Out() {
		super();
	}
	
	public void execute (CPU cpu) {
		try {
			cpu.getOutStream().writeChar(cpu.getOperandStack().pop());
			
		} catch (IOException e) {
			throw new MVError (this, e.getMessage());
		}

	}

	public void execute (Memory<Integer> mem, OperandStack<Integer> pila, ExecutionManager gestor, InStream in, OutStream out) {
		try {
			out.writeChar (pila.pop());
			
		} catch (IOException e) {
			throw new MVError (this, e.getMessage());
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
