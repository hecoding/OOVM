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
