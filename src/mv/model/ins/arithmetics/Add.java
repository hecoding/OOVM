package mv.model.ins.arithmetics;

import mv.model.cpu.ExecutionManager;
import mv.model.cpu.Memory;
import mv.model.cpu.OperandStack;
import mv.model.ins.Instruction;
import mv.model.mvsystem.in.InStream;
import mv.model.mvsystem.out.OutStream;

public class Add extends Arithmetics {
	
	public Add() {
		super();
	}

	public void execute (Memory<Integer> mem, OperandStack<Integer> pila, ExecutionManager gestor, InStream in, OutStream out) {
		int cima = pila.pop();
		int subcima = pila.pop();

		pila.push(subcima + cima);
	}
	
	protected Instruction crear() {
		return new Add();
	}
	
	public String toString() {
		return "ADD";
	}
}
